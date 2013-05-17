package org.fourgeeks.gha.domain.mix;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.vcf.ItSystem;

@Entity
public class SystemInstance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "itSystemFk")
	private ItSystem itSystem;

	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;

	// TODO: customPidGenerator
	// TODO: pidGenerationCriteria

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
