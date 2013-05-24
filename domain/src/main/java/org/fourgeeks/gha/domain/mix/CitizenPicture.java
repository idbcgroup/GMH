package org.fourgeeks.gha.domain.mix;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.PictureStateEnum;

@Entity
public class CitizenPicture extends AbstractEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "citizenFk")
	private Citizen citizen;
	
	/**Attributes*/
	private Long citizenIdFk; /** <FKEY> ID del Ciudadano con Foto length =19 */
	private int pictureNumber; /** Número de Foto tomada length =4 */
//	private int totalPictures; /** Total fotos para el Ciudadano length =4 */
	
	private Blob picture; /** Foto del Ciudadano length = */
	
	private String pictureDescription; /** Descripción de la FOTO del Ciudadano - OPCIONAL length =255 */
	private Date pictureDate; /** Fecha en que se tomo la foto length =22 */
	
	@Enumerated(EnumType.STRING)
	private PictureStateEnum pictureState; /** Estado de la foto length =60 */


	
	/**
	 * 
	 */
	public CitizenPicture() {
		super();
		// TODO Auto-generated constructor stub
	}
}
