package org.fourgeeks.gha.domain.ess.ui;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 * @author alacret
 * 
 */
@Entity
@IdClass(AppFormViewFunctionId.class)
@NamedQueries(value = { @NamedQuery(name = "AppFormViewFunction.getAll", query = "SELECT e from AppFormViewFunction e order by e.appForm") })
public class AppFormViewFunction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull(message = "app-not-null")
	private AppForm appForm;
	@Id
	@NotNull(message = "view-not-null")
	private View view;
	@Id
	@NotNull(message = "function-not-null")
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