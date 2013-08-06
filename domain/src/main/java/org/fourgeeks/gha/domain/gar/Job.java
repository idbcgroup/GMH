package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;

@Entity
public class Job extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "jobBaseFk")
	private JobBase jobBase;
	
	@ManyToOne
	@JoinColumn(name = "workingAreaFk")
	private WorkingArea workingArea;

	/**
	 * 
	 */
	public Job() {
		// TODO Auto-generated constructor stub
	}

	public JobBase getJobBase() {
		return jobBase;
	}

	public WorkingArea getWorkingArea() {
		return workingArea;
	}

	public void setJobBase(JobBase jobBase) {
		this.jobBase = jobBase;
	}

	public void setWorkingArea(WorkingArea workingArea) {
		this.workingArea = workingArea;
	}
	
	

}
