/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Bpa;
import org.fourgeeks.gha.domain.mix.SystemInstance;

/**
 * @author emiliot
 *
 */

@Entity
public class InstanceLogon extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "singleSignOnUserFk")
	private SingleSignOnUser sso;
	
	@ManyToOne
	@JoinColumn(name = "bpaFk")
	private Bpa bpa;
	
	@ManyToOne
	@JoinColumn(name = "systemInstanceFk")
	private SystemInstance systemInstance;
	
	@ManyToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;

}