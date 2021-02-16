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

package com.liferay.commerce.aws.personalize.internal.helper;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.personalizeruntime.AmazonPersonalizeRuntime;
import com.amazonaws.services.personalizeruntime.AmazonPersonalizeRuntimeClientBuilder;
import com.amazonaws.services.personalizeruntime.model.GetRecommendationsRequest;
import com.amazonaws.services.personalizeruntime.model.GetRecommendationsResult;
import com.amazonaws.services.personalizeruntime.model.PredictedItem;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;

import com.liferay.commerce.aws.personalize.configuration.AmazonPersonalizeConfiguration;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;

/**
 * @author Riccardo Ferrari
 */
@Component(
	configurationPid = "com.liferay.commerce.aws.personalize.configuration.AmazonPersonalizeConfiguration",
	immediate = true, service = CommerceAmazonPersonalizeHelper.class
)
public class CommerceAmazonPersonalizeHelper {

	public File download(String bucketPath) throws Exception {
		S3Object s3Object = _amazonS3.getObject(
			_amazonPersonalizeConfiguration.bucket(), bucketPath);

		return FileUtil.createTempFile(s3Object.getObjectContent());
	}

	public List<Long> getRecommendations(long cpDefinitionId) {
		GetRecommendationsRequest getRecommendationsRequest =
			new GetRecommendationsRequest();

		getRecommendationsRequest = getRecommendationsRequest.withCampaignArn(
			_amazonPersonalizeConfiguration.campaignARN());
		getRecommendationsRequest = getRecommendationsRequest.withItemId(
			String.valueOf(cpDefinitionId));

		GetRecommendationsResult recommendations =
			_amazonPersonalizeRuntime.getRecommendations(
				getRecommendationsRequest);

		List<PredictedItem> itemList = recommendations.getItemList();

		Stream<PredictedItem> itemStream = itemList.stream();

		return itemStream.map(
			PredictedItem::getItemId
		).map(
			Long::valueOf
		).collect(
			Collectors.toList()
		);
	}

	public void upload(String bucketPath, File file) throws Exception {
		if (_log.isDebugEnabled()) {
			_log.debug(
				String.format(
					"Uploading %s to %s", file.getName(), bucketPath));
		}

		_amazonS3.putObject(
			_amazonPersonalizeConfiguration.bucket(), bucketPath, file);
	}

	@Activate
	@Modified
	protected void activate(
		BundleContext bundleContext, Map<String, Object> properties) {

		_bundleContext = bundleContext;

		_amazonPersonalizeConfiguration = ConfigurableUtil.createConfigurable(
			AmazonPersonalizeConfiguration.class, properties);

		EnvironmentVariableCredentialsProvider credentialsProvider =
			new EnvironmentVariableCredentialsProvider();

		_amazonPersonalizeRuntime =
			AmazonPersonalizeRuntimeClientBuilder.standard(
			).withCredentials(
				credentialsProvider
			).withRegion(
				_amazonPersonalizeConfiguration.region()
			).build();

		_amazonS3 = AmazonS3ClientBuilder.standard(
		).withCredentials(
			credentialsProvider
		).withRegion(
			_amazonPersonalizeConfiguration.region()
		).build();
	}

	@Deactivate
	protected void deactivate() {
		_bundleContext = null;
		_amazonPersonalizeConfiguration = null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAmazonPersonalizeHelper.class);

	private AmazonPersonalizeConfiguration _amazonPersonalizeConfiguration;
	private AmazonPersonalizeRuntime _amazonPersonalizeRuntime;
	private AmazonS3 _amazonS3;
	private BundleContext _bundleContext;

}