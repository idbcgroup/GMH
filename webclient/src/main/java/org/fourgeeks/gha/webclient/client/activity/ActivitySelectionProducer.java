package org.fourgeeks.gha.webclient.client.activity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;

/**
 * 
 * @author caparicio
 * 
 */
public interface ActivitySelectionProducer {

	public void addActivitySelectionListener(
			ActivitySelectionListener activitySelectionListener);

	public void removeActivitySelectionListener(
			ActivitySelectionListener activitySelectionListener);

	public void notifyActivity(MaintenanceActivity activity);
}