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

package com.liferay.commerce.aws.personalize.internal.dispatch.executor;

import com.liferay.dispatch.executor.ScheduledTaskExecutor;
import com.liferay.headless.commerce.aws.personalize.dto.v1_0.Users;

import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author riccardoferrari
 */
@Component(
	immediate = true,
	property = "scheduled.task.executor.type=" + UsersExportScheduledTaskExecutorImpl.KEY,
	service = ScheduledTaskExecutor.class
)
public class UsersExportScheduledTaskExecutorImpl
	extends BaseExportScheduledTaskExecutor {

	public static final String KEY = "aws-personalize-users-export";

	@Override
	public String getName() {
		return KEY;
	}

	@Override
	protected String getBatchEngineTaskItemDelegateName() {
		return "users-aws-personalize";
	}

	@Override
	protected String getExportClassName() {
		return Users.class.getName();
	}

	@Override
	protected List<String> getExportFiledList() {
		return Arrays.asList("USER_ID", "COUNTRY");
	}

}