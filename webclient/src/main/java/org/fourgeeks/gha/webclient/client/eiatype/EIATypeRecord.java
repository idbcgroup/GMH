package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

public class EIATypeRecord extends GHAGridRecord {

	public EIATypeRecord() {
	}

	public EIATypeRecord(EiaType eiaType) {
		setId(eiaType.getId());
		setName(eiaType.getName());
		setCode(eiaType.getCode());
		setBrand(eiaType.getBrand().getName());
		setManufacturer(eiaType.getManufacturer().getName());
		setModel(eiaType.getModel());
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public String getCode() {
		return getAttributeAsString("code");
	}

	public String getBrand() {
		return getAttributeAsString("brand");
	}

	public String getManufacturer() {
		return getAttributeAsString("manufacturer");
	}

	public String getModel() {
		return getAttributeAsString("model");
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public void setCode(String code) {
		setAttribute("code", code);
	}

	public void setBrand(String brand) {
		setAttribute("brand", brand);
	}

	public void setManufacturer(String manufacturer) {
		setAttribute("manufacturer", manufacturer);
	}

	public void setModel(String model) {
		setAttribute("model", model);
	}
}