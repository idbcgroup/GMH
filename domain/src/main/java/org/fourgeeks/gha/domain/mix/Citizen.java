package org.fourgeeks.gha.domain.mix;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Bpu;

@Entity
public class Citizen extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "citizen")
	private Collection<CitizenEducation> citizenEducation;

	@OneToMany(mappedBy = "citizen")
	private Collection<CitizenPicture> citizenPictures;

	@ManyToMany
	private Collection<CitizenContact> citizenContacts;

	@OneToMany(mappedBy = "citizen")
	private Collection<CitizenNotification> citizenNotifications;

	@OneToMany(mappedBy = "citizen")
	private Collection<PhysicalCharacteristicsCode> physicalCharacteristicsCodes;
	
	@OneToOne
	@JoinColumn(name = "bpuFk")
	private Bpu bpu;

	/**
	 * Constructor
	 */
	public Citizen() {
		// super();
		// TODO Auto-generated constructor stub
	}

}
