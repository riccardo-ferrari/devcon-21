/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.aws.personalize.internal.data.source;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.util.CommerceAccountHelper;
import com.liferay.commerce.aws.personalize.internal.helper.CommerceAmazonPersonalizeHelper;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.constants.CPWebKeys;
import com.liferay.commerce.product.data.source.CPDataSource;
import com.liferay.commerce.product.data.source.CPDataSourceResult;
import com.liferay.commerce.product.util.CPDefinitionHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "commerce.product.data.source.name=" + ItemSimilarityCPDataSourceImpl.NAME,
	service = CPDataSource.class
)
public class ItemSimilarityCPDataSourceImpl implements CPDataSource {

	public static final String NAME = "itemSimilarityDataSource";

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(getResourceBundle(locale), "item-similarity");
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public CPDataSourceResult getResult(
			HttpServletRequest httpServletRequest, int start, int end)
		throws Exception {

		CommerceAccount commerceAccount =
			commerceAccountHelper.getCurrentCommerceAccount(httpServletRequest);

		if (commerceAccount == null) {
			return new CPDataSourceResult(Collections.emptyList(), 0);
		}

		CPCatalogEntry cpCatalogEntry =
			(CPCatalogEntry)httpServletRequest.getAttribute(
				CPWebKeys.CP_CATALOG_ENTRY);

		if (cpCatalogEntry == null) {
			return new CPDataSourceResult(Collections.emptyList(), 0);
		}

		List<Long> recommendations =
			commerceAmazonPersonalizeHelper.getRecommendations(
				cpCatalogEntry.getCPDefinitionId());

		List<CPCatalogEntry> cpCatalogEntries = new ArrayList<>();

		long groupId = portal.getScopeGroupId(httpServletRequest);

		Locale portalLocale = portal.getLocale(httpServletRequest);

		List<Long> recommendationResults = ListUtil.subList(
			recommendations, start, end);

		for (Long recommendationResult : recommendationResults) {
			try {
				CPCatalogEntry recommendedCPCatalogEntry =
					cpDefinitionHelper.getCPCatalogEntry(
						commerceAccount.getCommerceAccountId(), groupId,
						recommendationResult, portalLocale);

				cpCatalogEntries.add(recommendedCPCatalogEntry);
			}
			catch (PortalException portalException) {
				if (_log.isDebugEnabled()) {
					_log.debug(portalException, portalException);
				}
			}
		}

		return new CPDataSourceResult(cpCatalogEntries, recommendations.size());
	}

	protected ResourceBundle getResourceBundle(Locale locale) {
		return ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());
	}

	@Reference(unbind = "-")
	protected CommerceAccountHelper commerceAccountHelper;

	@Reference(unbind = "-")
	protected CommerceAmazonPersonalizeHelper commerceAmazonPersonalizeHelper;

	@Reference(unbind = "-")
	protected CPDefinitionHelper cpDefinitionHelper;

	@Reference(unbind = "-")
	protected Portal portal;

	private static final Log _log = LogFactoryUtil.getLog(
		ItemSimilarityCPDataSourceImpl.class);

}