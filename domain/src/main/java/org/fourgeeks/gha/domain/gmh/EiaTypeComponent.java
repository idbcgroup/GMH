/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;


/**
 * @author emiliot
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "eiaTypeFk",
		"parentEiaTypeFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "EiaTypeComponent.getAll", query = "SELECT e from EiaTypeComponent e group by e.parentEiaType order by e.id"),
		@NamedQuery(name = "EiaTypeComponent.findByParentEiaType", query = "select etc from EiaTypeComponent etc where etc.parentEiaType = :eiaType order by etc.id") })
public class EiaTypeComponent extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "eiaTypeFk", nullable = false)
	private EiaType eiaType;

	@ManyToOne
	@JoinColumn(name = "parentEiaTypeFk", nullable = false)
	private EiaType parentEiaType;

	/** Attributes */
	private boolean componentRequired;
	/** Componente requerido u opcional */
	private boolean componentReplaceable;

	/**
	 * Componente se puede sustituir, NO = se debe usar exactamente el
	 * componente indicado por el fabricante
	 */

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
