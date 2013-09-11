/**
 * 
 */
package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.ItSystem;

/**
 * @author emiliot
 *
 */

@Entity
@Table(name = "SystemInstance", uniqueConstraints = @UniqueConstraint(columnNames = { "institutionFk", "itSystemFk" }))
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

}
