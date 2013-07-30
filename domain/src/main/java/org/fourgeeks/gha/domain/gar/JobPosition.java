package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.RoleIt;

@Entity
public class JobPosition extends AbstractEntity {

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

	@ManyToOne
	@JoinColumn(name = "roleItFk")
	private RoleIt roleIt;

	/**
	 * This represents the link relation to my parent (if any), semantically it
	 * says who is my jobPositionChild to refer to my jobPosition parent
	 */
	@OneToOne(mappedBy = "jobPosition")
	private JobPositionChild jobPositionChild;

}
