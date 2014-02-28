/**
 * 
 */
package org.fourgeeks.gha.domain.ess.auth;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Bpa;

/**
 * @author emiliot, vivi.torresg
 * 
 */

@Entity
@NamedQueries(value = { @NamedQuery(name = "InstanceLogon.getAll", query = "SELECT e from InstanceLogon e order by e.id") })
public class InstanceLogon extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "singleSignOnUserFk")
	private SSOUser sso;

	@ManyToOne
	@JoinColumn(name = "bpaFk")
	private Bpa bpa;

	@ManyToOne
	@JoinColumn(name = "systemInstanceFk")
	private SystemInstance systemInstance;

	@ManyToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;

	/**
	 * 
	 */
	public InstanceLogon() {
		// TODO Auto-generated constructor stub
	}

	public Bpa getBpa() {
		return bpa;
	}

	public Bpu getBpu() {
		return bpu;
	}

	public SSOUser getSso() {
		return sso;
	}

	public SystemInstance getSystemInstance() {
		return systemInstance;
	}

	public void setBpa(Bpa bpa) {
		this.bpa = bpa;
	}

	public void setBpu(Bpu bpu) {
		this.bpu = bpu;
	}

	public void setSso(SSOUser sso) {
		this.sso = sso;
	}

	public void setSystemInstance(SystemInstance systemInstance) {
		this.systemInstance = systemInstance;
	}

}
