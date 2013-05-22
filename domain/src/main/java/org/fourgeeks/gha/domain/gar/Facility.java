package org.fourgeeks.gha.domain.gar;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gmh.Equipment;

@Entity
public class Facility extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "waioFk")
	private Waio waio;
	
	@OneToMany(mappedBy = "facility")
	private Collection <Equipment> equipments;
	
	@OneToOne
	@JoinColumn(name = "resourceServiceFk")
	private ResourceService resourceService;

}
