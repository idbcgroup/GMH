package org.fourgeeks.gha.domain.oru;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

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

}
