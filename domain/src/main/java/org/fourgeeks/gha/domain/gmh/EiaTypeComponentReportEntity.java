package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

@Entity
public class EiaTypeComponentReportEntity extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private EiaType eiaType;
	private EiaType parentEiaType;
	private EiaTypeComponent component;

	public EiaTypeComponentReportEntity() {
		super();
	}

	public EiaTypeComponentReportEntity(EiaTypeComponent component, EiaType parent, EiaType eiaType) {
		this.component = component;
		this.parentEiaType = parent;
		this.eiaType = eiaType;
	}

	public EiaTypeComponentReportEntity(EiaTypeComponent component) {
		this.component = component;
		if (component != null) {
			this.parentEiaType = component.getParentEiaType();
			this.eiaType = component.getEiaType();
		}
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public boolean isComponentRequired() {
		return component != null ? component.isComponentRequired() : false;
	}

	public boolean isComponentReplaceable() {
		return component != null ? component.isComponentReplaceable() : false;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setComponentRequired(boolean componentRequired) {
		if (component == null)
			component = new EiaTypeComponent();
		component.setComponentRequired(componentRequired);
	}

	public void setComponentReplaceable(boolean componentReplaceable) {
		if (component == null)
			component = new EiaTypeComponent();
		component.setComponentReplaceable(componentReplaceable);
	}

	public EiaType getParentEiaType() {
		return parentEiaType;
	}

	public void setParentEiaType(EiaType parentEiaType) {
		this.parentEiaType = parentEiaType;
	}
}
