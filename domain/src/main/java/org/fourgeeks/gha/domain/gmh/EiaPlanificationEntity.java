package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * 
 * @author caparicio
 * 
 */
@Entity
public class EiaPlanificationEntity extends AbstractEntity {

	private static final long serialVersionUID = 3168114351428700906L;
	private Eia eia;
	private EiaMaintenancePlanification emp;

	public EiaPlanificationEntity() {
		super();
	}

	public EiaPlanificationEntity(Eia eia, EiaMaintenancePlanification emp) {
		super();
		this.eia = eia;
		this.emp = emp;
	}

	public Eia getEia() {
		return eia;
	}

	public void setEia(Eia eia) {
		this.eia = eia;
	}

	public EiaMaintenancePlanification getEmp() {
		return emp;
	}

	public void setEmp(EiaMaintenancePlanification emp) {
		this.emp = emp;
	}
}
