package org.fourgeeks.gha.domain.gar;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.RoleIt;

@Entity
public class JobPosition extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;
	
	@ManyToOne
	@JoinColumn(name = "obuFk")
	private Obu obu;
	
	@ManyToOne
	@JoinColumn(name = "jobPositionDefinitionFk")
	private JobPositionDefinition jobPositionDef;
	
	@ManyToMany(mappedBy = "jobPositions")
	private Collection <Waio> waios;
	
	@ManyToOne
	@JoinColumn(name = "roleItFk")
	private RoleIt roleIt;
	
	/**
	 * This represents the children collection of this jobPosition
	 */
	@OneToMany(mappedBy = "parentJobPosition")
	private Collection<JobPositionChild> jobPositionChildren;
	
	/**
	 * This represents the link relation to my parent (if any),
	 * semantically it says who is my jobPositionChild to refer to my jobPosition parent
	 */
	@OneToOne(mappedBy = "jobPosition")
	private JobPositionChild jobPositionChild;

}
