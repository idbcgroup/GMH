package org.fourgeeks.gha.domain.ess.ui;

import java.io.Serializable;

/**
 * @author alacret
 * 
 */
public class AppFormViewFunctionId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppForm appForm;
	private View view;
	private Function function;

	/**
	 * 
	 */
	public AppFormViewFunctionId() {
	}

	/**
	 * @param appForm
	 * @param view
	 * @param function
	 */
	public AppFormViewFunctionId(AppForm appForm, View view, Function function) {
		this.appForm = appForm;
		this.view = view;
		this.function = function;
	}

	/**
	 * @return the appForm
	 */
	public AppForm getAppForm() {
		return appForm;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;

		if (!(o instanceof AppFormViewFunctionId))
			return false;

		AppFormViewFunctionId newO = (AppFormViewFunctionId) o;

		return newO.appForm.getCode().equals(appForm.getCode())
				&& newO.view.getCode().equals(view.getCode())
				&& newO.function.getCode().equals(function.getCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return appForm.hashCode() + view.hashCode() + function.hashCode();
	}

	/**
	 * @return the function
	 */
	public Function getFunction() {
		return function;
	}

}