/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.sql.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaPictureStateEnum;

/**
 * @author emiliot
 *
 */

@Entity
public class EiaTypePicture extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "eiaTypeFk", nullable=false)
	private EiaType eiaType;
	
	/**Attributes*/
	private int number; /** Número de Foto tomada length =4 */
	
	@Basic(fetch=FetchType.LAZY)
	@Lob @Column(nullable = false)
	private byte[] picture; /** Foto del Equipo o Instalación length = */
	
	private String description; /** Descripción del Equipo o Instalación length =255 */
	
	@Column(nullable=false)
	private Date date; /** Fecha en que se tomo la foto length =22 */
	
	@Column(nullable=false)
	private EiaPictureStateEnum pictureState; /** Estado de la foto length =60 */

	/**
	 * 
	 */
	public EiaTypePicture() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param eiaType
	 * @param number
	 * @param picture
	 * @param description
	 * @param date
	 * @param pictureState
	 */
	public EiaTypePicture(EiaType eiaType, int number, byte[] picture,
			String description, Date date, EiaPictureStateEnum pictureState) {
		this.eiaType = eiaType;
		this.number = number;
		this.picture = picture;
		this.description = description;
		this.date = date;
		this.pictureState = pictureState;
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public int getNumber() {
		return number;
	}

	public byte[] getPicture() {
		return picture;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}

	public EiaPictureStateEnum getPictureState() {
		return pictureState;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPictureState(EiaPictureStateEnum pictureState) {
		this.pictureState = pictureState;
	}
	
	

}
