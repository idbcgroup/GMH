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

/**
 * @author alacret
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "appFormFk",
		"viewFk", "functionFk" }))
@NamedQueries(value = { @NamedQuery(name = "AppFormViewFunction.getAll", query = "SELECT e from AppFormViewFunction e order by e.appForm") })
public class AppFormViewFunction extends AbstractCodeEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "app-not-null")
	@ManyToOne
	@JoinColumn(name = "appFormFk", nullable = false)
	private AppForm appForm;
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
	public AppFormViewFunction() {
	}

	/**
	 * @param appForm
	 * @param view
	 * @param function
	 */
	public AppFormViewFunction(AppForm appForm, View view, Function function) {
		setCode(appForm.getCode() + view.getCode() + function.getCode());
		setAppForm(appForm);
		setView(view);
		setFunction(function);
	}

	/**
	 * @return the appForm
	 */
	public AppForm getAppForm() {
		return appForm;
	}

	/**
	 * @param appForm
	 *            the appForm to set
	 */
	public void setAppForm(AppForm appForm) {
		this.appForm = appForm;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(View view) {
		this.view = view;
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