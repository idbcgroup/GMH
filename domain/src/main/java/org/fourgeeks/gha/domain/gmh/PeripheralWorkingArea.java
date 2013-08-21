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
public class PeripheralWorkingArea extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "peripheralFk")
	private Peripheral peripheral;
	
	@ManyToOne
	@JoinColumn(name = "terminalFk")
	private Terminal terminal;

	/**
	 * 
	 */
	public PeripheralWorkingArea() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param peripheral
	 * @param terminal
	 */
	public PeripheralWorkingArea(Peripheral peripheral, Terminal terminal) {
		this.peripheral = peripheral;
		this.terminal = terminal;
	}

	public Peripheral getPeripheral() {
		return peripheral;
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setPeripheral(Peripheral peripheral) {
		this.peripheral = peripheral;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}
	
	
}
