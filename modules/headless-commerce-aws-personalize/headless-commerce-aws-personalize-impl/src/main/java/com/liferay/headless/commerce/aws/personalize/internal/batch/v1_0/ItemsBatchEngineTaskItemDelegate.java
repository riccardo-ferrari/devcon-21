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

import com.liferay.asset.kernel.model.AssetCategory;
import com.liferay.asset.kernel.service.AssetCategoryLocalService;
import com.liferay.batch.engine.BaseBatchEngineTaskItemDelegate;
import com.liferay.batch.engine.BatchEngineTaskItemDelegate;
import com.liferay.batch.engine.pagination.Page;
import com.liferay.batch.engine.pagination.Pagination;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.headless.commerce.aws.personalize.dto.v1_0.Items;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "batch.engine.task.item.delegate.name=items-aws-personalize",
	service = BatchEngineTaskItemDelegate.class
)
public class ItemsBatchEngineTaskItemDelegate
	extends BaseBatchEngineTaskItemDelegate<Items> {

	@Override
	public Class<Items> getItemClass() {
		return Items.class;
	}

	@Override
	public Page<Items> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		List<CPDefinition> cpDefinitions =
			_cpDefinitionLocalService.getCPDefinitions(
				pagination.getStartPosition(), pagination.getEndPosition());

		return Page.of(
			TransformUtil.transform(cpDefinitions, this::_toItems),
			Pagination.of(pagination.getPage(), pagination.getPageSize()),
			_cpDefinitionLocalService.getCPDefinitionsCount());
	}

	private Items _toItems(CPDefinition cpDefinition) {
		List<AssetCategory> categories =
			_assetCategoryLocalService.getCategories(
				cpDefinition.getModelClassName(),
				cpDefinition.getCPDefinitionId());

		Stream<AssetCategory> categoryStream = categories.stream();

		String categoriesString = categoryStream.map(
			AssetCategory::getCategoryId
		).map(
			String::valueOf
		).collect(
			Collectors.joining("|")
		);

		return new Items() {
			{
				CATEGORY = categoriesString;
				ITEM_ID = String.valueOf(cpDefinition.getCPDefinitionId());
			}
		};
	}

	@Reference
	private AssetCategoryLocalService _assetCategoryLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

}