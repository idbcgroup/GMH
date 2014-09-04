package org.fourgeeks.gha.domain.gmh;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot, eduardoguerere
 */
@Entity
@DiscriminatorValue("preventive")
@NamedQueries(value = { @NamedQuery(name = "EiaPreventiveMaintenance.findByEiaType", query = "SELECT epm FROM EiaPreventiveMaintenance epm JOIN epm.planification planif WHERE planif.eia.eiaType = :eiaType ORDER BY epm.id") })
public class EiaPreventiveMaintenance extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	// TODO lista de consumibles y partes cambiadas al equipo

	/**
	 * 
	 */
	public EiaPreventiveMaintenance() {
		super();
	}

}
