/**
 * 
 */
package org.fourgeeks.gha.ejb.gmh;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gmh.EiaType;

/**
 * @author emiliot
 *
 */
public class EiaTypeComponent extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;
	
	/**Attributes*/
	private boolean componentRequired;
	/** Componente requerido u opcional */
	private boolean componentReplaceable;
	/** Componente se puede sustituir, 
	 * NO = se debe usar exactamente el componente indicado por el fabricante */
	
	/**
	 * 
	 */
	public EiaTypeComponent() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param componentRequired
	 * @param componentReplaceable
	 */
	public EiaTypeComponent(boolean componentRequired,
			boolean componentReplaceable) {
		super();
		this.componentRequired = componentRequired;
		this.componentReplaceable = componentReplaceable;
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public boolean isComponentRequired() {
		return componentRequired;
	}

	public boolean isComponentReplaceable() {
		return componentReplaceable;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setComponentRequired(boolean componentRequired) {
		this.componentRequired = componentRequired;
	}

	public void setComponentReplaceable(boolean componentReplaceable) {
		this.componentReplaceable = componentReplaceable;
	}
	
}
