package org.fourgeeks.gha.domain.ess;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.JobPosition;
import org.fourgeeks.gha.domain.gar.Obu;

@Entity
public class RoleIt extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy = "itRoles")
	private Collection <Bpu> bpus;
	
	@OneToMany(mappedBy = "roleIt")
	private Collection <JobPosition> jobPositions;
	
	@ManyToMany(mappedBy = "itRoles")
	private Collection <ViewFunction> viewFunctions;
	
	@ManyToMany(mappedBy = "itRoles")
	private Collection <WorkingArea> workingAreas;
	
	@ManyToMany(mappedBy = "itRoles")
	private Collection <AuthorizationGroup> authGroups;
	
	@ManyToOne
	@JoinColumn(name = "baseRoleFk")
	private BaseRole baseRole;
	
	@ManyToOne
	@JoinColumn(name = "obuFk")
	private Obu obu;

	/**
	 * 
	 */
	public RoleIt() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param baseRole
	 * @param obu
	 */
	public RoleIt(BaseRole baseRole, Obu obu) {
		this.baseRole = baseRole;
		this.obu = obu;
	}

	public Collection<Bpu> getBpus() {
		return bpus;
	}

	public Collection<JobPosition> getJobPositions() {
		return jobPositions;
	}

	public Collection<ViewFunction> getViewFunctions() {
		return viewFunctions;
	}

	public Collection<WorkingArea> getWorkingAreas() {
		return workingAreas;
	}

	public Collection<AuthorizationGroup> getAuthGroups() {
		return authGroups;
	}

	public BaseRole getBaseRole() {
		return baseRole;
	}

	public Obu getObu() {
		return obu;
	}

	public void setBpus(Collection<Bpu> bpus) {
		this.bpus = bpus;
	}

	public void setJobPositions(Collection<JobPosition> jobPositions) {
		this.jobPositions = jobPositions;
	}

	public void setViewFunctions(Collection<ViewFunction> viewFunctions) {
		this.viewFunctions = viewFunctions;
	}

	public void setWorkingAreas(Collection<WorkingArea> workingAreas) {
		this.workingAreas = workingAreas;
	}

	public void setAuthGroups(Collection<AuthorizationGroup> authGroups) {
		this.authGroups = authGroups;
	}

	public void setBaseRole(BaseRole baseRole) {
		this.baseRole = baseRole;
	}

	public void setObu(Obu obu) {
		this.obu = obu;
	}
	
	
}
