/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.Activity;

/**
 * @author emiliot ManyToMany between Resources and ProtocolActivities
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "activityFk",
		"resourceFk" }))
public class RequiredResources extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "activityFk", nullable = false)
	private Activity activity;

	@ManyToOne
	@JoinColumn(name = "resourceFk", nullable = false)
	private ServiceAndResource resource;

	/**
	 * 
	 */
	public RequiredResources() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param activity
	 * @param resource
	 */
	public RequiredResources(Activity activity, ServiceAndResource resource) {
		this.activity = activity;
		this.resource = resource;
	}

	/**
	 * 
	 * @return the activity that require the resource
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @return the resource required for the activity
	 */
	public ServiceAndResource getResource() {
		return resource;
	}

	/**
	 * 
	 * @param activity
	 *            the activity that require the resource
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * 
	 * @param resource
	 *            the resource required for the activity
	 */
	public void setResource(ServiceAndResource resource) {
		this.resource = resource;
	}

}
