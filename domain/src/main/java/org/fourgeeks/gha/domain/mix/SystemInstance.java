package org.fourgeeks.gha.domain.mix;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.ess.ItSystem;

@Entity
public class SystemInstance extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "itSystemFk")
	private ItSystem itSystem;

	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;
	
	@OneToMany(mappedBy = "systemInstance")
	private Collection <LegalEntityCredential> legalEntityCredentials;

	/**
	 * 
	 */
	public SystemInstance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
