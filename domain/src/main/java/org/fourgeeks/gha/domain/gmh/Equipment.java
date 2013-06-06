package org.fourgeeks.gha.domain.gmh;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Waio;

@Entity
public class Equipment extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "equipment")
	private Terminal terminal;

	@ManyToOne
	@JoinColumn(name = "facilityFk")
	private Facility facility;

	@ManyToOne
	@JoinColumn(name = "eiatypeFk")
	private EiaType eiatype;

	@ManyToMany
	private Collection<Waio> waios;

	@OneToOne(mappedBy = "equipment")
	private SystemPeripheral systemPeripheral;

}
