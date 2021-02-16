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

package com.liferay.commerce.aws.personalize.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Riccardo Ferrari
 */
@ExtendedObjectClassDefinition(
	category = "commerce", scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
	id = "com.liferay.commerce.aws.personalize.configuration.AmazonPersonalizeConfiguration",
	localization = "content/Language",
	name = "amazon-personalize-configuration-name"
)
public interface AmazonPersonalizeConfiguration {

	@Meta.AD(name = "campaign-arn", required = false)
	public String campaignARN();

	@Meta.AD(deflt = "us-east-1", name = "region", required = false)
	public String region();

	@Meta.AD(
		deflt = "devcon21-commerce-aws-personalize",
		name = "amazon-personalize-bucket", required = false
	)
	public String bucket();

}