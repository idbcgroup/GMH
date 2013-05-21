package org.fourgeeks.gha.domain.oru;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class BpuDelegate extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@OneToOne(mappedBy = "bpuDelegate")
	private Bpu bpu;
}
