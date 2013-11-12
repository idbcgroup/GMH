/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.EiaPictureStateEnum;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = { @NamedQuery(name = "EiaPicture.findByEia", query = "SELECT e from EiaPicture e WHERE e.eia = :eia order by e.id") })
public class EiaPicture extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaFk", nullable = false)
	private Eia eia;

	/** Attributes */
	private int number;
	/** Número de Foto tomada length =4 */

	@Column(nullable = false)
	private String picture;
	/** URL de la Foto del Equipo o Instalación length = */

	private String description;
	/** Descripción del Equipo o Instalación length =255 */

	@Column(nullable = false)
	private Date date;
	/** Fecha en que se tomo la foto length =22 */

	@Column(nullable = false)
	private EiaPictureStateEnum pictureState;

	/** Estado de la foto length =60 */

	/**
	 * 
	 */
	public EiaPicture() {
	}

	/**
	 * @param eia
	 * @param number
	 * @param picture
	 * @param description
	 * @param date
	 * @param pictureState
	 */
	public EiaPicture(Eia eia, int number, String picture, String description,
			Date date, EiaPictureStateEnum pictureState) {
		this.eia = eia;
		this.number = number;
		this.picture = picture;
		this.description = description;
		this.date = date;
		this.pictureState = pictureState;
	}

	/**
	 * @return the eia
	 */
	public Eia getEia() {
		return eia;
	}

	public int getNumber() {
		return number;
	}

	public String getPicture() {
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

	public void setEia(Eia eia) {
		this.eia = eia;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setPicture(String picture) {
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
