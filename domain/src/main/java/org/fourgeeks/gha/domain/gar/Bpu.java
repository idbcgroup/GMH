package org.fourgeeks.gha.domain.gar;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.RoleIt;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Citizen;

@Entity
public class Bpu extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToMany
	private Collection<Bpi> bpis;
	
	@OneToOne(mappedBy = "bpu")
	private Citizen citizen;
	
	@OneToMany(mappedBy = "bpu")
	private Collection <Shift> shifts;
	
	@OneToOne(mappedBy = "bpu")
	private JobPosition jobPosition;
	
	@ManyToMany
	private Collection <RoleIt> itRoles;
	
	@ManyToOne
	@JoinColumn(name = "roleItFk")
	private RoleIt primaryRole;
	
	@ManyToMany
	private Collection <BpuWorkingGroup> bpuWorkingGroups;
	
	@OneToOne
	@JoinColumn(name = "bpuDelegateFk")
	private BpuDelegate bpuDelegate;
	
	@ManyToMany
	private Collection <Waio> waios;

	/**
	 * 
	 */
	public Bpu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
