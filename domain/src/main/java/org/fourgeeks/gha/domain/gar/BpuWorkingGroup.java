/**
 * 
 */
package org.fourgeeks.gha.domain.gar;

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
public class BpuWorkingGroup extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This BPU represents the group
	 */
	@OneToOne
	@JoinColumn(name = "bpuGroupFk")
	private Bpu bpuGroup;

	/**
	 * The Bpu
	 */
	@ManyToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;

}
