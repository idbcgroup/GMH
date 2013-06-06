package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Equipment;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

public class EIARecord extends GHAGridRecord<Equipment> {

	public EIARecord() {
	}

	public EIARecord(EiaType eiaType) {
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

	@Override
	public Equipment toEntity() {
		// TODO Auto-generated method stub
		return null;
	}
}