/**
 * 
 */
package org.fourgeeks.gha.domain.logs;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.gar.Bpu;


/**
 * @author emiliot
 *
 */

@Entity
public class UILog extends GHALog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;
	
	/**
	 * 
	 */
	public UILog() {
		// TODO Auto-generated constructor stub
	}

	public Bpu getBpu() {
		return bpu;
	}

	public void setBpu(Bpu bpu) {
		this.bpu = bpu;
	}
}
