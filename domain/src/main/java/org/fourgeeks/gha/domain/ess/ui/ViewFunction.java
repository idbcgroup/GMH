package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.ess.auth.Function;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "ui", uniqueConstraints = @UniqueConstraint(columnNames = {
		"viewFk", "functionFk" }))
@NamedQueries(value = { @NamedQuery(name = "ViewFunction.getAll", query = "SELECT e from ViewFunction e order by e.view") })
public class ViewFunction extends AbstractCodeEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "view-not-null")
	@ManyToOne
	@JoinColumn(name = "viewFk", nullable = false)
	private View view;
	@NotNull(message = "function-not-null")
	@ManyToOne
	@JoinColumn(name = "functionFk", nullable = false)
	private Function function;

	/**
	 * 
	 */
	public ViewFunction() {
	}

	/**
	 * @param appForm
	 * @param view
	 * @param function
	 */
	public ViewFunction(View view, Function function) {
		setCode(view.getCode() + function.getCode());
		this.view = view;
		this.function = function;
	}

	/**
	 * @return the function
	 */
	public Function getFunction() {
		return function;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}
}