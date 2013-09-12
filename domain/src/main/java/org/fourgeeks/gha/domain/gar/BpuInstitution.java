/**
 * 
 */
package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author emiliot
 *
 */

@Entity
@Table(name = "BpuInstitution", uniqueConstraints = @UniqueConstraint(columnNames = { "institutionFk", "bpuFk" }))
public class BpuInstitution extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;
	
	@ManyToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;
}
