package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Entity
@Table(name = "BpuFunction", uniqueConstraints = @UniqueConstraint(columnNames = {
		"bpuFk", "functionFk" }))
@NamedQueries(value = { @NamedQuery(name = "BpuFunction.findByBpu", query = "SELECT e from BpuFunction e WHERE e.bpu = :bpu order by e.id") })
public class BpuFunction extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(nullable = false, name = "bpuFk")
	private Bpu bpu;

	@ManyToOne
	@JoinColumn(nullable = false, name = "functionFk")
	private Function function;

	/**
	 * 
	 */
	public BpuFunction() {
	}

	/**
	 * @param bpu
	 * @param function
	 */
	public BpuFunction(Bpu bpu, Function function) {
		this.bpu = bpu;
		this.function = function;
	}

	/**
	 * @return the bpu
	 */
	public Bpu getBpu() {
		return bpu;
	}

	/**
	 * @param bpu
	 *            the bpu to set
	 */
	public void setBpu(Bpu bpu) {
		this.bpu = bpu;
	}

	/**
	 * @return the function
	 */
	public Function getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(Function function) {
		this.function = function;
	}
}
