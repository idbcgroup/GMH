package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class JobPositionChild extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This is the parent job Position
	 */
	@ManyToOne
	@JoinColumn(name = "parentJobPositionFk")
	private JobPosition parentJobPosition;
	
	/**
	 * This is the JobPosition associated with this child JobPosition
	 */
	@OneToOne
	@JoinColumn(name = "jobPositionFk")
	private JobPosition jobPosition;
	
}
