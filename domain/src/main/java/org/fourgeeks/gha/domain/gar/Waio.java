package org.fourgeeks.gha.domain.gar;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gmh.Equipment;
import org.fourgeeks.gha.domain.gmh.SystemPeripheral;
import org.fourgeeks.gha.domain.gmh.Terminal;
import org.fourgeeks.gha.domain.mix.Bpi;

@Entity
public class Waio extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "bpiFk")
	private Bpi bpi;
	
	@ManyToOne
	@JoinColumn(name = "obuFk")
	private Obu obu;
	
	@OneToMany(mappedBy = "waio")
	private Collection <TransferRule> transferRules;
	
	@ManyToOne
	@JoinColumn(name = "buildingLocationFk")
	private BuildingLocation buildingLocation;
	
	@ManyToMany
	private Collection <JobPosition> jobPositions;
	
	@ManyToMany(mappedBy = "waios")
	private Collection <Bpu> bpus;
	
	@OneToMany(mappedBy = "waio")
	private Collection <Facility> facilities;
	
	@OneToMany(mappedBy = "waio")
	private Collection <Terminal> terminals;
	
	@ManyToMany(mappedBy = "waios")
	private Collection <Equipment> equipments;
	
	@ManyToMany(mappedBy = "waios")
	private Collection <SystemPeripheral> systemPeriferals;
	
	@ManyToOne
	@JoinColumn(name = "workingAreaFk")
	private WorkingArea workingArea;
}
