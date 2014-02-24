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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "viewFk",
		"permissionFk" }))
@NamedQueries(value = { @NamedQuery(name = "ViewPermission.getAll", query = "SELECT e from ViewPermission e order by e.view") })
public class ViewPermission extends AbstractCodeEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "view-not-null")
	@ManyToOne
	@JoinColumn(name = "viewFk", nullable = false)
	private View view;
	@NotNull(message = "permission-not-null")
	@ManyToOne
	@JoinColumn(name = "permissionFk", nullable = false)
	private Permission permission;

	/**
	 * 
	 */
	public ViewPermission() {
	}

	/**
	 * @param appForm
	 * @param view
	 * @param permission
	 */
	public ViewPermission(View view, Permission permission) {
		setCode(view.getCode() + permission.getCode());
		this.view = view;
		this.permission = permission;
	}

	/**
	 * @return the function
	 */
	public Permission getPermission() {
		return permission;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}
}