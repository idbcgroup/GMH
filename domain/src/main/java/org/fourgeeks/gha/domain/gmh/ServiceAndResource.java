package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * Entity to represent resources and services
 * 
 * @author emiliot, naramirez
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ServiceAndResource extends AbstractCodeEntity {
	/** */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaFk", nullable = false)
	/** El equipo que se va a reportar como dañado o con falla */
	private Eia eia;

	/**
	 * @return the eia
	 */
	public Eia getEia() {
		return eia;
	}

	/**
	 * @param eia
	 *            the eia to set
	 */
	public void setEia(Eia eia) {
		this.eia = eia;
	}

}
