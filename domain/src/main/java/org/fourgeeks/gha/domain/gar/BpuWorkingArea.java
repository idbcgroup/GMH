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
import org.fourgeeks.gha.domain.ess.WorkingArea;

/**
 * @author emiliot
 *
 */

@Entity
@Table(name = "BpuWorkingArea", uniqueConstraints = @UniqueConstraint(columnNames = { "bpuFk", "workingAreaFk" }))
public class BpuWorkingArea extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;
	
	@ManyToOne
	@JoinColumn(name = "workingAreaFk")
	private WorkingArea workingArea;

	/**
	 * 
	 */
	public BpuWorkingArea() {
		// TODO Auto-generated constructor stub
	}

	public Bpu getBpu() {
		return bpu;
	}

	public void setBpu(Bpu bpu) {
		this.bpu = bpu;
	}

	public WorkingArea getWorkingArea() {
		return workingArea;
	}

	public void setWorkingArea(WorkingArea workingArea) {
		this.workingArea = workingArea;
	}
}
