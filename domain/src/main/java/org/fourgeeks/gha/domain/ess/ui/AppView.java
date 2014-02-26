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
@Table(schema = "auth", uniqueConstraints = @UniqueConstraint(columnNames = {
		"appFk", "viewFk" }))
public class AppView extends AbstractCodeEntity {

	private static final long serialVersionUID = 1L;
	@NotNull(message = "app-not-null")
	@ManyToOne
	@JoinColumn(name = "appFk", nullable = false)
	private App app;

	@NotNull(message = "view-not-null")
	@ManyToOne
	@JoinColumn(name = "viewFk", nullable = false)
	private View view;

	/**
	 * 
	 */
	public AppView() {
	}

	/**
	 * @param app
	 * @param view
	 */
	public AppView(App app, View view) {
		setCode(app.getCode() + view.getCode());
		this.app = app;
		this.view = view;
	}

	/**
	 * @return the appForm
	 */
	public App getForm() {
		return app;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}
}