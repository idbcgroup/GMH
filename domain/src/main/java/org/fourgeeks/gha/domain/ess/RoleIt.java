package org.fourgeeks.gha.domain.ess;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.JobPosition;

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
	
}
