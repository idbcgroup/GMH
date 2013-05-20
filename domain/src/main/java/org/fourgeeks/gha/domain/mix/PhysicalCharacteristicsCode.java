package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class PhysicalCharacteristicsCode extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "physicalCharacteristicsFk")
	private PhysicalCharacteristics physicalCharacteristics;
	
	@ManyToOne
	@JoinColumn(name = "citizenFk")
	private Citizen citizen;

}
