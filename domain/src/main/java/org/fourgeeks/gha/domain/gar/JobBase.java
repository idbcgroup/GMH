/**
 * 
 */
package org.fourgeeks.gha.domain.gar;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.RoleBase;

/**
 * @author emiliot
 *
 */

@Entity
public class JobBase extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "roleBaseFk")
	private RoleBase roleBase;
	
	@ManyToOne
	@JoinColumn(name = "obuBaseFk")
	private ObuBase obuBase;

	/**
	 * 
	 */
	public JobBase() {
		// TODO Auto-generated constructor stub
	}

	public RoleBase getRoleBase() {
		return roleBase;
	}

	public ObuBase getObuBase() {
		return obuBase;
	}

	public void setRoleBase(RoleBase roleBase) {
		this.roleBase = roleBase;
	}

	public void setObuBase(ObuBase obuBase) {
		this.obuBase = obuBase;
	}
	
	

}
