package org.fourgeeks.gha.domain.ess.auth;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "auth", uniqueConstraints = @UniqueConstraint(columnNames = {
		"functionFk", "bpuFk" }))
@NamedQueries(value = { @NamedQuery(name = "FunctionBpu.findByBpu", query = "SELECT e from FunctionBpu e WHERE e.bpu = :bpu order by e.function") })
public class FunctionBpu extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "bpu-not-null")
	@ManyToOne
	@JoinColumn(name = "bpuFk", nullable = false)
	private Bpu bpu;
	@NotNull(message = "function-not-null")
	@ManyToOne
	@JoinColumn(name = "functionFk", nullable = false, columnDefinition = "varchar(255) REFERENCES auth.function(code) ON UPDATE CASCADE ON DELETE CASCADE")
	private Function function;

	/**
	 * 
	 */
	public FunctionBpu() {
	}

	/**
	 * @param bpu
	 * @param appForm
	 * @param view
	 * @param function
	 */
	public FunctionBpu(Bpu bpu, Function function) {
		super();
		setCode(function.getCode() + bpu.getId());
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
	 * @return the function
	 */
	public Function getFunction() {
		return function;
	}
}