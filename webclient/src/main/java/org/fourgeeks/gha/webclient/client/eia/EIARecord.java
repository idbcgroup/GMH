package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.EiaType;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class EIARecord extends ListGridRecord {

	public EIARecord() {
	}

	public EIARecord(EiaType eiaType) {
		setName(eiaType.getName());
		setCode(eiaType.getCode());
		setBrand(eiaType.getBrand());
		setManufacturer(eiaType.getManufacturer());
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