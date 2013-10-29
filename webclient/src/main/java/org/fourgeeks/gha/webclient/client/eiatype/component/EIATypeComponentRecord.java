package org.fourgeeks.gha.webclient.client.eiatype.component;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class EIATypeComponentRecord extends GHAGridRecord<EiaTypeComponent> {

	private EiaTypeComponent eiaTypeComponent;

	public EIATypeComponentRecord(EiaTypeComponent eiaTypeComponent) {
		setEiaTypeComponent(eiaTypeComponent);
	}

	public void setEiaTypeComponent(EiaTypeComponent eiaTypeComponent) {
		this.eiaTypeComponent = eiaTypeComponent;
		setAttribute("parent", eiaTypeComponent.getParentEiaType().getName());
		EiaType eiaType = this.eiaTypeComponent.getEiaType();
		setCode(eiaType.getCode());
		setName(eiaType.getName());
		if (eiaType.getBrand() != null)
			setBrand(eiaType.getBrand().getName());
		setModel(eiaType.getModel());
		// if (eiaType.getManufacturer() != null)
		// setManufacturer(eiaType.getManufacturer().getName());
		setRequired(this.eiaTypeComponent.isComponentRequired());
		setReplaceable(this.eiaTypeComponent.isComponentReplaceable());
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public void setCode(String code) {
		setAttribute("code", code);
	}

	public String getCode() {
		return getAttributeAsString("code");
	}

	public void setBrand(String brand) {
		setAttribute("brand", brand);
	}

	public String getBrand() {
		return getAttributeAsString("brand");
	}

	public void setModel(String model) {
		setAttribute("model", model);
	}

	public String getModel() {
		return getAttributeAsString("model");
	}

	public void setManufacturer(String manufacturer) {
		setAttribute("manufacturer", manufacturer);
	}

	public String getManufacturer() {
		return getAttributeAsString("manufacturer");
	}

	public void setRequired(boolean required) {
		setAttribute("required", required);
	}

	public String getRequired() {
		return getAttributeAsString("required");
	}

	public void setReplaceable(boolean replaceable) {
		setAttribute("replaceable", replaceable);
	}

	public String getReplaceable() {
		return getAttributeAsString("replaceable");
	}

	@Override
	public EiaTypeComponent toEntity() {
		return eiaTypeComponent;
	}

}
