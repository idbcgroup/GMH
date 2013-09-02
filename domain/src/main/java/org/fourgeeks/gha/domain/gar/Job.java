package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;

@Entity
public class Job extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "workingAreaFk")
	private WorkingArea workingArea;
	
	@ManyToOne
	@JoinColumn(name = "jobCategoryFk")
	private JobCategory jobCategory;

	/**
	 * 
	 */
	public Job() {
		// TODO Auto-generated constructor stub
	}

	public WorkingArea getWorkingArea() {
		return workingArea;
	}

	public void setWorkingArea(WorkingArea workingArea) {
		this.workingArea = workingArea;
	}

	public JobCategory getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}

}
