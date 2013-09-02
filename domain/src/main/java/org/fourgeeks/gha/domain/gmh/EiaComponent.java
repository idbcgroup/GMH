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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "eiaFk",
		"parentEiaFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "EiaComponent.getAll", query = "SELECT e from EiaComponent e group by e.parentEia order by e.id"),
		@NamedQuery(name = "EiaComponent.findByParentEia", query = "SELECT e from EiaComponent e WHERE e.parentEia = :parentEia order by e.id") })
public class EiaComponent extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "eiaFk", nullable = false)
	private Eia eia; // Yo mismo

	@ManyToOne
	@JoinColumn(name = "parentEiaFk", nullable = false)
	private Eia parentEia; // De quien soy componente

	/** Attributes */
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
