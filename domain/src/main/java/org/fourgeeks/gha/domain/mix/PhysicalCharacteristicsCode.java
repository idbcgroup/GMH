package org.fourgeeks.gha.domain.mix;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.PccStatusEnum;

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
	
	/**Attributes*/
	private Date dateCreated; /** Fecha de Creación de las Señas o Características Fïsicas asociadas a un CIUDADANO length =12 */
	
	@Enumerated(EnumType.STRING)
	private PccStatusEnum pccStatus; /** Validez de las Señas o Características Fïsicas asociadas a un CIUDADANO length =60 */



}
