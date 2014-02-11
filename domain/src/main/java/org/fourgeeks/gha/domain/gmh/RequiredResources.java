/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.Activity;

/**
 * ManyToMany between Resources and ProtocolActivities
 * 
 * @author emiliot, caparicio
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "activityFk",
		"resourceFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "RequiredResources.findByServiceAndResource", query = "SELECT rr FROM RequiredResources rr WHERE rr.resource = :resource ORDER BY rr.id"),
		@NamedQuery(name = "RequiredResources.findByActivity", query = "SELECT rr FROM RequiredResources rr WHERE rr.activity = :activity ORDER BY rr.id") })
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

	private int quantity;

	/**
	 * 
	 */
	public RequiredResources() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param activity
	 * @param resource
	 * @param quantity
	 */
	public RequiredResources(Activity activity, ServiceAndResource resource,
			int quantity) {
		this.activity = activity;
		this.resource = resource;
		this.setQuantity(quantity);
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
	 * @return the quantity of resources used in the activity
	 */
	public int getQuantity() {
		return quantity;
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

	/**
	 * 
	 * @param quantity
	 *            the quantity of resources used in the activity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
