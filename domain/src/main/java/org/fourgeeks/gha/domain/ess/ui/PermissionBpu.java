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
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "auth", uniqueConstraints = @UniqueConstraint(columnNames = {
		"permissionFk", "bpuFk" }))
@NamedQueries(value = { @NamedQuery(name = "PermissionBpu.findByBpu", query = "SELECT e from PermissionBpu e WHERE e.bpu = :bpu order by e.permission") })
public class PermissionBpu extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "bpu-not-null")
	@ManyToOne
	@JoinColumn(name = "bpuFk", nullable = false)
	private Bpu bpu;
	@NotNull(message = "permission-not-null")
	@ManyToOne
	@JoinColumn(name = "permissionFk", nullable = false, columnDefinition = "varchar(255) REFERENCES auth.permission(code) ON UPDATE CASCADE ON DELETE CASCADE")
	private Permission permission;

	/**
	 * 
	 */
	public PermissionBpu() {
	}

	/**
	 * @param bpu
	 * @param appForm
	 * @param view
	 * @param permission
	 */
	public PermissionBpu(Bpu bpu, Permission permission) {
		super();
		setCode(permission.getCode() + bpu.getId());
		this.bpu = bpu;
		this.permission = permission;
	}

	/**
	 * @return the bpu
	 */
	public Bpu getBpu() {
		return bpu;
	}

	/**
	 * @return the function
	 */
	public Permission getFunction() {
		return permission;
	}
}