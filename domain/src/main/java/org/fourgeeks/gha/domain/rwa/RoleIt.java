package org.fourgeeks.gha.domain.rwa;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.oru.Bpu;

@Entity
public class RoleIt extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy = "itRoles")
	private Collection <Bpu> bpus;
	
}
