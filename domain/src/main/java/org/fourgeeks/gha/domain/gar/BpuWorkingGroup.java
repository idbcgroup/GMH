package org.fourgeeks.gha.domain.gar;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class BpuWorkingGroup extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy = "bpuWorkingGroups")
	private Collection <Bpu> bpus;
}
