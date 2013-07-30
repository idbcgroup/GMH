package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.DuplicatedCitizenStatusEnum;

@Entity
public class DuplicatedCitizenId extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "citizenToFk")
	private Citizen citizenTo;

	@ManyToOne
	@JoinColumn(name = "citizenFromFk")
	private Citizen citizenFrom;

	/** Attributes */
	private Boolean isPatient;
	/** Es paciente length =1 */
	private String hmeOk;
	/** No HME del Paciente Ok, solo si isPatient = TRUE length =255 */
	private Long citizenDuplicatedId;
	/** Identificación (citizenId) duplicada length =19 */
	private String citizenDuplicatedUeid;
	/** Legal Entity del Citizen Duplicado length =16 */
	private String duplicatedCitizenReason;
	/** Motivo Duplicación length =255 */
	private String hmeDuplicated;
	/** No de HME duplicada length =255 */

	private DuplicatedCitizenStatusEnum duplicatedCitizenStatus;
	/** Estado de la duplicación length =60 */

	private int Duplications;
	/** Cantidad de duplicaciones para este ciudadano length =2 */

}
