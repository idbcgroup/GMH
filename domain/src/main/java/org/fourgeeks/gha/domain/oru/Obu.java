package org.fourgeeks.gha.domain.oru;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Bpi;

@Entity
public class Obu extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "bpiFk")
	private Bpi bpi;
	
	@OneToMany(mappedBy = "obu")
	private Collection <ExternalObu> externalObus;
	
	@OneToMany(mappedBy = "obu")
	private Collection <CobeAccount> cobeAccounts;
	
	@OneToMany(mappedBy = "obu")
	private Collection <JobPosition> jobPositions;
	
	@OneToMany(mappedBy = "obu")
	private Collection <Waio> waios;
	
}
