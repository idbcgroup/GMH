/**
 * 
 */
package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.mix.Institution;

/**
 * @author alacret when the BPU is working inside the BPI but is from another
 *         Institution
 * 
 */

@Entity
@Table(name = "BpuInstitution")
public class BpuInstitution extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;

	@OneToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;
}
