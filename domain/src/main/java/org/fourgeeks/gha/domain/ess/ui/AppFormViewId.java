package org.fourgeeks.gha.domain.ess.ui;

import java.io.Serializable;

/**
 * @author alacret
 * 
 */
public class AppFormViewId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AppForm appForm;
	private View view;

	/**
	 * 
	 */
	public AppFormViewId() {
	}

	/**
	 * @param appForm
	 * @param view
	 */
	public AppFormViewId(AppForm appForm, View view) {
		this.appForm = appForm;
		this.view = view;
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

		if (!(o instanceof AppFormViewId))
			return false;

		AppFormViewId newO = (AppFormViewId) o;

		return newO.appForm.getCode().equals(appForm.getCode())
				&& newO.view.getCode().equals(view.getCode());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return appForm.hashCode() + view.hashCode();
	}

}