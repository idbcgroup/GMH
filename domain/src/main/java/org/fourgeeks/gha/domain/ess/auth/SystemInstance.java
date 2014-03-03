/**
 * 
 */
package org.fourgeeks.gha.domain.ess.auth;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "institutionFk",
		"itSystemFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "SystemInstance.getAll", query = "SELECT e from SystemInstance e order by e.id"),
		@NamedQuery(name = "SystemInstance.findBySystemInstance", query = "SELECT e from SystemInstance e where e like :systemInstance order by e.id") })
public class SystemInstance extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Institution associated with this system instance
	 */
	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;

	/**
	 * The itSystem associated with this instance
	 */
	@ManyToOne
	@JoinColumn(name = "itSystemFk")
	private ItSystem itSystem;

	/**
	 * 
	 */
	public SystemInstance() {
		// TODO Auto-generated constructor stub
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public ItSystem getItSystem() {
		return itSystem;
	}

	public void setItSystem(ItSystem itSystem) {
		this.itSystem = itSystem;
	}

}
