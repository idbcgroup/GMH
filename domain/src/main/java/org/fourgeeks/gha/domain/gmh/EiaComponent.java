/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
public class EiaComponent extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "parentEiaFk", nullable=false)
	private Eia parentEia;
	
	@OneToOne
	@JoinColumn(name = "eiaFk", nullable=false)
	private Eia eia;
	
	/**Attributes*/
	private String componentObs;
	/** Observaciones sobre este componente en el equipo padre length =255 */

	/**
	 * 
	 */
	public EiaComponent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param parentEia
	 * @param eia
	 * @param componentObs
	 */
	public EiaComponent(Eia parentEia, Eia eia, String componentObs) {
		super();
		this.parentEia = parentEia;
		this.eia = eia;
		this.componentObs = componentObs;
	}

	public Eia getEia() {
		return eia;
	}

	public String getComponentObs() {
		return componentObs;
	}

	public void setEia(Eia eia) {
		this.eia = eia;
	}

	public void setComponentObs(String componentObs) {
		this.componentObs = componentObs;
	}

	public Eia getParentEia() {
		return parentEia;
	}

	public void setParentEia(Eia parentEia) {
		this.parentEia = parentEia;
	}
	
	
}
