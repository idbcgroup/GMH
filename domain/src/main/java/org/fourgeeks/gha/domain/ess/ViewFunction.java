package org.fourgeeks.gha.domain.ess;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class ViewFunction extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany
	private Collection<RoleIt> itRoles;
	
	@ManyToOne
	@JoinColumn(name = "viewFk")
	private View view;
	
	@ManyToMany(mappedBy = "viewFunctions")
	private Collection <WorkingArea> workingAreas;

}
