package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class BpiChild extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * This represents the parent bpi
	 */
	@ManyToOne
	@JoinColumn(name = "parentBpiFk")
	private Bpi parentBpi;
	
	/**
	 * This respresents the bpi associated with this child bpi
	 */
	@OneToOne
	@JoinColumn(name = "bpiFk")
	private Bpi bpi;

}
