/**
 * 
 */
package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;

/**
 * @author emiliot
 * 
 */

@Entity
public class JobPosition extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "workingAreaFk")
	private WorkingArea workingArea;

	/**
	 * Boss of this job position
	 */
	@ManyToOne
	@JoinColumn(name = "parentJobPositionFk")
	private JobPosition parentJobPosition;

	@ManyToOne
	@JoinColumn(name = "jobFk")
	private Job job;

	/**
	 * 
	 */
	public JobPosition() {
		// TODO Auto-generated constructor stub
	}

	public Job getJob() {
		return job;
	}

	public JobPosition getParentJobPosition() {
		return parentJobPosition;
	}

	public WorkingArea getWorkingArea() {
		return workingArea;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void setParentJobPosition(JobPosition parentJobPosition) {
		this.parentJobPosition = parentJobPosition;
	}

	public void setWorkingArea(WorkingArea workingArea) {
		this.workingArea = workingArea;
	}
}
