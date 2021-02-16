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

import com.liferay.batch.engine.BatchEngineExportTaskExecutor;
import com.liferay.batch.engine.BatchEngineImportTaskExecutor;
import com.liferay.batch.engine.BatchEngineTaskContentType;
import com.liferay.batch.engine.BatchEngineTaskExecuteStatus;
import com.liferay.batch.engine.model.BatchEngineExportTask;
import com.liferay.batch.engine.service.BatchEngineExportTaskLocalService;
import com.liferay.batch.engine.service.BatchEngineImportTaskLocalService;
import com.liferay.commerce.aws.personalize.internal.helper.CommerceAmazonPersonalizeHelper;
import com.liferay.dispatch.executor.ScheduledTaskExecutor;
import com.liferay.dispatch.model.DispatchLog;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.dispatch.service.DispatchLogLocalService;
import com.liferay.dispatch.service.DispatchTriggerLocalService;
import com.liferay.portal.kernel.backgroundtask.constants.BackgroundTaskConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Ferrari
 */
public abstract class BaseExportScheduledTaskExecutor
	implements ScheduledTaskExecutor {

	@Override
	public void execute(long dispatchTriggerId)
		throws IOException, PortalException {

		DispatchTrigger dispatchTrigger =
			dispatchTriggerLocalService.getDispatchTrigger(dispatchTriggerId);

		DispatchLog dispatchLog = dispatchLogLocalService.addDispatchLog(
			dispatchTrigger.getUserId(), dispatchTrigger.getDispatchTriggerId(),
			null, null, null, new Date(),
			BackgroundTaskConstants.STATUS_IN_PROGRESS);

		BatchEngineExportTask batchEngineExportTask =
			batchEngineExportTaskLocalService.addBatchEngineExportTask(
				dispatchTrigger.getCompanyId(), dispatchTrigger.getUserId(),
				null, getExportClassName(),
				BatchEngineTaskContentType.CSV.name(),
				BatchEngineTaskExecuteStatus.INITIAL.name(),
				getExportFiledList(), new HashMap<>(),
				getBatchEngineTaskItemDelegateName());

		try {
			batchEngineExportTaskExecutor.execute(batchEngineExportTask);

			BatchEngineTaskExecuteStatus batchEngineTaskExecuteStatus =
				BatchEngineTaskExecuteStatus.valueOf(
					batchEngineExportTask.getExecuteStatus());

			if (batchEngineTaskExecuteStatus.equals(
					BatchEngineTaskExecuteStatus.COMPLETED)) {

				if (_log.isDebugEnabled()) {
					_log.debug(
						"Completed batch engine export task for " +
							batchEngineExportTask.getClassName());
				}

				InputStream inputStream =
					batchEngineExportTaskLocalService.openContentInputStream(
						batchEngineExportTask.getBatchEngineExportTaskId());

				ZipInputStream zipInputStream = new ZipInputStream(inputStream);

				zipInputStream.getNextEntry();

				File tempFile = FileUtil.createTempFile(zipInputStream);

				commerceAmazonPersonalizeHelper.upload(
					getExportClassName(), tempFile);

				tempFile.delete();

				dispatchLogLocalService.updateDispatchLog(
					dispatchLog.getDispatchLogId(), new Date(), null,
					"Upload complete",
					BackgroundTaskConstants.STATUS_SUCCESSFUL);
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			dispatchLogLocalService.updateDispatchLog(
				dispatchLog.getDispatchLogId(), new Date(),
				exception.getMessage(), null,
				BackgroundTaskConstants.STATUS_FAILED);
		}
	}

	protected abstract String getBatchEngineTaskItemDelegateName();

	protected abstract String getExportClassName();

	protected abstract List<String> getExportFiledList();

	@Reference
	protected BatchEngineExportTaskExecutor batchEngineExportTaskExecutor;

	@Reference
	protected BatchEngineExportTaskLocalService
		batchEngineExportTaskLocalService;

	@Reference
	protected BatchEngineImportTaskExecutor batchEngineImportTaskExecutor;

	@Reference
	protected BatchEngineImportTaskLocalService
		batchEngineImportTaskLocalService;

	@Reference
	protected CommerceAmazonPersonalizeHelper commerceAmazonPersonalizeHelper;

	@Reference
	protected DispatchLogLocalService dispatchLogLocalService;

	@Reference
	protected DispatchTriggerLocalService dispatchTriggerLocalService;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseExportScheduledTaskExecutor.class);

}