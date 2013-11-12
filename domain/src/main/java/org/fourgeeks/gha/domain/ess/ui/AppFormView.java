package org.fourgeeks.gha.domain.ess.ui;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

/**
 * @author alacret
 * 
 */
@Entity
@IdClass(AppFormViewId.class)
public class AppFormView implements Serializable {
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

	/**
	 * 
	 */
	public AppFormView() {
	}

	/**
	 * @param appForm
	 * @param view
	 */
	public AppFormView(AppForm appForm, View view) {
		setAppForm(appForm);
		setView(view);
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

}