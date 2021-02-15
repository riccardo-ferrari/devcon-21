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

package com.liferay.headless.commerce.aws.personalize.internal.batch.v1_0;

import com.liferay.batch.engine.BaseBatchEngineTaskItemDelegate;
import com.liferay.batch.engine.BatchEngineTaskItemDelegate;
import com.liferay.batch.engine.pagination.Page;
import com.liferay.batch.engine.pagination.Pagination;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.service.CommerceAddressService;
import com.liferay.headless.commerce.aws.personalize.dto.v1_0.Users;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "batch.engine.task.item.delegate.name=users-aws-personalize",
	service = BatchEngineTaskItemDelegate.class
)
public class UsersBatchEngineTaskItemDelegate
	extends BaseBatchEngineTaskItemDelegate<Users> {

	@Override
	public Class<Users> getItemClass() {
		return Users.class;
	}

	@Override
	public Page<Users> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		List<CommerceAccount> commerceAccounts =
			_commerceAccountLocalService.getCommerceAccounts(
				pagination.getStartPosition(), pagination.getEndPosition());

		return Page.of(
			TransformUtil.transform(commerceAccounts, this::_toUsers),
			Pagination.of(pagination.getPage(), pagination.getPageSize()),
			_commerceAccountLocalService.getCommerceAccountsCount());
	}

	private Users _toUsers(CommerceAccount commerceAccount)
		throws PortalException {

		CommerceAddress commerceAddress =
			_commerceAddressService.getCommerceAddress(
				commerceAccount.getDefaultBillingAddressId());

		CommerceCountry commerceCountry = commerceAddress.getCommerceCountry();

		return new Users() {
			{
				COUNTRY = commerceCountry.getTwoLettersISOCode();
				USER_ID = String.valueOf(
					commerceAccount.getCommerceAccountId());
			}
		};
	}

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private CommerceAddressService _commerceAddressService;

}