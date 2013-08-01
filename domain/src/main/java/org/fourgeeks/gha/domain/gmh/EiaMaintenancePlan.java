/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
public class EiaMaintenancePlan extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "eMaintenancePlanFk")
	private EiaTypeMaintenancePlan eMaintenancePlan;
	
	@ManyToOne
	@JoinColumn(name = "eiaFk")
	private Eia eia;
}
