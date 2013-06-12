/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Waio;

/**
 * @author emiliot
 * Many To Many Eia-Waio
 *
 */
@Entity
@Table(name="eiawaio", uniqueConstraints=@UniqueConstraint(columnNames={"eiaFk", "waioFk"}))
public class EiaWaio extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "eiaFk")
	private Eia eia;
	
	@ManyToOne
	@JoinColumn(name = "waioFk")
	private Waio waio;
	
	public Eia getEia() {
		return eia;
	}
	public Waio getWaio() {
		return waio;
	}
	public void setEia(Eia eia) {
		this.eia = eia;
	}
	public void setWaio(Waio waio) {
		this.waio = waio;
	}

}
