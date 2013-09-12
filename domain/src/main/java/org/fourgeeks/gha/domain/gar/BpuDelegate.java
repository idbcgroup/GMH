/**
 * 
 */
package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
public class BpuDelegate extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The BPU that delegates its work into a deputeBpu
	 */
	@ManyToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;
	
	/**
	 * The BPU in which the work is delegated	
	 */
	@ManyToOne
	@JoinColumn(name = "bpuDeputeFk")
	private Bpu depute;

}
