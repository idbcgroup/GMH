package org.fourgeeks.gha.domain.ess.ui;

import java.io.Serializable;

import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
public class AppFormViewFunctionBpuId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppForm appForm;
	private View view;
	private Function function;
	private Bpu bpu;

	/**
	 * 
	 */
	public AppFormViewFunctionBpuId() {
	}

	/**
	 * @param appForm
	 * @param view
	 * @param function
	 * @param bpu
	 */
	public AppFormViewFunctionBpuId(AppForm appForm, View view,
			Function function, Bpu bpu) {
		this.appForm = appForm;
		this.view = view;
		this.function = function;
		this.bpu = bpu;
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

		if (!(o instanceof AppFormViewFunctionBpuId))
			return false;

		AppFormViewFunctionBpuId newO = (AppFormViewFunctionBpuId) o;

		return newO.appForm.getCode().equals(appForm.getCode())
				&& newO.view.getCode().equals(view.getCode())
				&& newO.function.getCode().equals(function.getCode())
				&& newO.bpu.getId() == bpu.getId();
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

	/**
	 * @return the bpu
	 */
	public Bpu getBpu() {
		return bpu;
	}
}