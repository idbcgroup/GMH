package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class ObuChild extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * this is the parent obu
	 */
	@ManyToOne
	@JoinColumn(name = "parentObuFk")
	private Obu parentObu;
	
	/**
	 * this is the obu associated with this child obu
	 */
	@OneToOne
	@JoinColumn(name = "obuFk")
	private Obu obu;
}
