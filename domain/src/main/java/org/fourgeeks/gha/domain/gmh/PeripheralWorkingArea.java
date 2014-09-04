/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.WorkingArea;

/**
 * @author emiliot
 * 
 */

@Entity
public class PeripheralWorkingArea extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "peripheralFk")
	private Peripheral peripheral;

	@ManyToOne
	@JoinColumn(name = "workingAreaFk")
	private WorkingArea workingArea;

	/**
	 * @return the workingArea
	 */
	public WorkingArea getWorkingArea() {
		return workingArea;
	}

	/**
	 * @param workingArea
	 *            the workingArea to set
	 */
	public void setWorkingArea(WorkingArea workingArea) {
		this.workingArea = workingArea;
	}

	/**
	 * 
	 */
	public PeripheralWorkingArea() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param peripheral
	 */
	public PeripheralWorkingArea(Peripheral peripheral) {
		this.peripheral = peripheral;
	}

	public Peripheral getPeripheral() {
		return peripheral;
	}

	public void setPeripheral(Peripheral peripheral) {
		this.peripheral = peripheral;
	}

}
