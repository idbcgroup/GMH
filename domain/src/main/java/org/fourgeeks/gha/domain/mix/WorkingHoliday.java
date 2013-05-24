package org.fourgeeks.gha.domain.mix;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class WorkingHoliday extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;
	
	/**Attributes*/
	
	private String description; /** Descripción - Nomrbre de la FESTIVIDAD length =255 */
	private Date holidayDate; /** Fecha - Colocar cuando es una fecha exacta length =12 */
//	private String workingHolidaysBpoNumberFk; /** BPO Número length =255 */
//	private String workingHolidaysBpoTypeFk; /** Tipo de BPO length =255 */
//	private Long bpiFk; /** <FKEY> ID de la institución length =19 */
//	private String bpiCode; /** Código de la institución length =20 */
	
	private String holidayMonth; /** Mes de la festividad length =20 */
	private Short holidayDay; /** Día de la festividad length =2 */
	
	@Enumerated(EnumType.STRING)
	private String holidayType; /** Tipo de Festividad length =60 */


}
