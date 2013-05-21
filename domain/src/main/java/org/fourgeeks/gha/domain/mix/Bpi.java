package org.fourgeeks.gha.domain.mix;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.oru.Bpu;
import org.fourgeeks.gha.domain.oru.BuildingLocations;
import org.fourgeeks.gha.domain.oru.Obu;
import org.fourgeeks.gha.domain.oru.Waio;
import org.fourgeeks.gha.domain.srp.ServiceProvider;

@Entity
public class Bpi extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;

	@ManyToMany(mappedBy = "bpis")
	private Collection<Bpu> bpus;
	
	@OneToMany(mappedBy = "bpi")
	private Collection<BuildingLocations> buildingLocations;
	
	@OneToMany(mappedBy = "bpi")
	private Collection<Waio> waios;
	
	@OneToMany(mappedBy = "bpi")
	private Collection<Obu> obus;
	
	@OneToMany(mappedBy = "bpi")
	private Collection<ServiceProvider> serviceProviders;
}
