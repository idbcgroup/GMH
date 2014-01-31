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
 * @author alacret
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "activityFk",
		"parentActivityFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "SubProtocolAndChecklist.getAll", query = "SELECT e from SubProtocolAndChecklist e order by e.id"),
		@NamedQuery(name = "SubProtocolAndChecklist.findByParentActivity", query = "SELECT e FROM  SubProtocolAndChecklist e WHERE e.parentActivity = :activity ORDER BY e.ordinal") })
public class SubProtocolAndChecklist extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "activityFk")
	private Activity activity;

	@ManyToOne
	@JoinColumn(name = "parentActivityFk")
	private Activity parentActivity;

	/**
	 * Number of order for activity
	 */
	private int ordinal;

	/**
	 * 
	 */
	public SubProtocolAndChecklist() {
	}

	/**
	 * @param activity
	 * @param parentActivity
	 * @param ordinal
	 */
	public SubProtocolAndChecklist(Activity parentActivity, Activity activity,
			int ordinal) {
		this.activity = activity;
		this.parentActivity = parentActivity;
		this.ordinal = ordinal;
	}

	/**
	 * @return the actvity
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @return the parent maintenance activity
	 */
	public Activity getParentActivity() {
		return parentActivity;
	}

	/**
	 * @return the ordinal
	 */
	public int getOrdinal() {
		return ordinal;
	}

	/**
	 * @param activity
	 *            the activity
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @param parentActivity
	 *            the parent protocol activity
	 */
	public void setParentActivity(Activity parentActivity) {
		this.parentActivity = parentActivity;
	}

	/**
	 * @param ordinal
	 *            the ordinal
	 */
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

}