package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
		"viewFk" }))
public class AppFormView extends AbstractCodeEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "app-not-null")
	@ManyToOne
	@JoinColumn(name = "appFormFk", nullable = false)
	private AppForm appForm = null;

	@NotNull(message = "view-not-null")
	@ManyToOne
	@JoinColumn(name = "viewFk", nullable = false)
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
		setCode(appForm.getCode() + view.getCode());
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