package org.fourgeeks.gha.domain.gmh;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Waio;

@Entity
public class SystemPeripheral extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "eiaFk")
	private Eia eia;
	
	@ManyToMany
	private Collection <Waio> waios;

}
