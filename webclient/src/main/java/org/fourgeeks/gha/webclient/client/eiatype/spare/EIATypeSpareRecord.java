package org.fourgeeks.gha.webclient.client.eiatype.spare;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

public class EIATypeSpareRecord 
	extends GHAGridRecord<EiaTypeSpare> {

	private EiaTypeSpare eiaTypeSpare;
	
	public EIATypeSpareRecord(EiaTypeSpare eiaTypeSpare) {
		setEiaTypeSpare(eiaTypeSpare);
	}
	
	public void setEiaTypeSpare(EiaTypeSpare eiaTypeSpare) {
		this.eiaTypeSpare = eiaTypeSpare;
		EiaType spare = this.eiaTypeSpare.getSpare();
		setId(this.eiaTypeSpare.getId());
		setCode(spare.getCode());
		setName(spare.getName());
		if (spare.getBrand() != null)
			setBrand(spare.getBrand().getName());
		setModel(spare.getModel());
		if (spare.getManufacturer() != null)
			setManufacturer(spare.getManufacturer().getName());
		setUse(spare.getUseDescription());
			
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

	public void setUse(String use) {
		setAttribute("use", use);
	}
	
	public String getUse() {
		return getAttributeAsString("use");
	}
	
	@Override
	public EiaTypeSpare toEntity() {
		return eiaTypeSpare;
	} 
	
}
