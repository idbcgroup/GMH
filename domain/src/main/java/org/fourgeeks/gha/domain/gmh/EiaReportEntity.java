package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class EiaReportEntity extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private Eia eia;
	private EiaType eiaType;

	public EiaReportEntity() {
		super();
	}

	public EiaReportEntity(Eia eia) {
		this.eia = eia;
	}

	public EiaReportEntity(Eia eia, EiaType eiaType) {
		this.eia = eia;
		this.eiaType = eiaType;
	}

	public Eia getEia() {
		if (eia == null) {
			eia = new Eia();
			eia.setState(null);
			eia.setEiaType(eiaType);
		}
		return eia;
	}

	public void setEia(Eia eia) {
		this.eia = eia;
	}
}
