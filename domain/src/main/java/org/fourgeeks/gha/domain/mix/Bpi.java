package org.fourgeeks.gha.domain.mix;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gar.ServiceProvider;
import org.fourgeeks.gha.domain.gar.Waio;

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
	private Collection<BuildingLocation> buildingLocations;
	
	@OneToMany(mappedBy = "bpi")
	private Collection<Waio> waios;
	
	@OneToMany(mappedBy = "bpi")
	private Collection<Obu> obus;
	
	@OneToMany(mappedBy = "bpi")
	private Collection<ServiceProvider> serviceProviders;
	
	/**
	 * This represents the children collection of this bpi
	 */
	@OneToMany(mappedBy = "parentBpi")
	private Collection<BpiChild> bpiChildren;
	
	/**
	 * This represents the link relation to my parent (if any),
	 * semantically it says who is my bpiChild to refer to my bpi parent
	 */
	@OneToOne(mappedBy = "bpi")
	private BpiChild bpiChild;
}
