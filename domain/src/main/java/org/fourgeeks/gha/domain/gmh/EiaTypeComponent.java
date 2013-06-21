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
public class EiaTypeComponent extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "parentEiaTypeFk", nullable = false)
	private EiaType parentEiaType;
	
	@OneToOne
	@JoinColumn(name = "eiaTypeFk", nullable = false)
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
	 * @param parentEiaType
	 * @param eiaType
	 * @param componentRequired
	 * @param componentReplaceable
	 */
	public EiaTypeComponent(EiaType parentEiaType, EiaType eiaType,
			boolean componentRequired, boolean componentReplaceable) {
		super();
		this.parentEiaType = parentEiaType;
		this.eiaType = eiaType;
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

	public EiaType getParentEiaType() {
		return parentEiaType;
	}

	public void setParentEiaType(EiaType parentEiaType) {
		this.parentEiaType = parentEiaType;
	}
	
}
