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
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.headless.commerce.aws.personalize.dto.v1_0.Interactions;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
@Component(
	immediate = true,
	property = "batch.engine.task.item.delegate.name=interactions-aws-personalize",
	service = BatchEngineTaskItemDelegate.class
)
public class InteractionsBatchEngineTaskItemDelegate
	extends BaseBatchEngineTaskItemDelegate<Interactions> {

	@Override
	public Class<Interactions> getItemClass() {
		return Interactions.class;
	}

	@Override
	public Page<Interactions> read(
			Filter filter, Pagination pagination, Sort[] sorts,
			Map<String, Serializable> parameters, String search)
		throws Exception {

		List<CommerceOrderItem> commerceOrderItems =
			_commerceOrderItemLocalService.getCommerceOrderItems(
				pagination.getStartPosition(), pagination.getEndPosition());

		return Page.of(
			TransformUtil.transform(commerceOrderItems, this::_toInteractions),
			Pagination.of(pagination.getPage(), pagination.getPageSize()),
			_commerceOrderItemLocalService.getCommerceOrderItemsCount());
	}

	private Interactions _toInteractions(CommerceOrderItem commerceOrderItem)
		throws Exception {

		CommerceOrder commerceOrder =
			_commerceOrderLocalService.getCommerceOrder(
				commerceOrderItem.getCommerceOrderId());

		final Date createDate = commerceOrderItem.getCreateDate();

		return new Interactions() {
			{
				ITEM_ID = String.valueOf(commerceOrderItem.getCPDefinitionId());
				TIMESTAMP = createDate.getTime();
				USER_ID = String.valueOf(commerceOrder.getCommerceAccountId());
			}
		};
	}

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

}