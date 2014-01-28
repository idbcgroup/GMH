package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author alacret
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaintenanceActivity.getAll", query = "SELECT e from MaintenanceActivity e order by e.id"),
		@NamedQuery(name = "MaintenanceActivity.findByMaintenancePlan", query = "SELECT act FROM MaintenanceProtocols mp JOIN mp.maintenanceActivity act WHERE mp.maintenancePlan = :plan order by act.id"),
		@NamedQuery(name = "MaintenanceActivity.findByServiceResource", query = "SELECT act FROM  MaintenanceActivityServiceResource e JOIN e.maintenanceActivity act WHERE e.serviceResource = :serviceResource") })
public class MaintenanceActivity extends AbstractEntity implements
		Comparable<MaintenanceActivity> {
	/** */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "activityFk", nullable = false)
	private Activity activity;

	/** */
	public MaintenanceActivity() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MaintenanceActivity activity) {
		if (activity.getId() > this.getId())
			return 1;
		if (activity.getId() < this.getId())
			return -1;
		return 0;
	}

	/**
	 * @return the activity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity
	 *            the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
