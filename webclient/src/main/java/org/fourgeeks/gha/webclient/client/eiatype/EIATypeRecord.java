package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

public class EIATypeRecord extends GHAGridRecord<EiaType> {

	public EIATypeRecord() {
	}

	public EIATypeRecord(EiaType eiaType) {
		setId(eiaType.getId());
		setName(eiaType.getName());
		setCode(eiaType.getCode());
		if (eiaType.getBrand() != null) {
			setBrand(eiaType.getBrand().getName());
			setBrandId(eiaType.getBrand().getId());
		}
		if (eiaType.getManufacturer() != null) {
			setManufacturer(eiaType.getManufacturer().getName());
			setManufacturerId(eiaType.getManufacturer().getId());
		}
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

	public String getBrandId() {
		return getAttributeAsString("brandId");
	}

	public String getManufacturer() {
		return getAttributeAsString("manufacturer");
	}

	public String getManufacturerId() {
		return getAttributeAsString("manufacturerId");
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

	public void setBrandId(long brandId) {
		setAttribute("brandId", brandId);
	}

	public void setManufacturer(String manufacturer) {
		setAttribute("manufacturer", manufacturer);
	}

	public void setManufacturerId(long manufacturerId) {
		setAttribute("manufacturerId", manufacturerId);
	}

	public void setModel(String model) {
		setAttribute("model", model);
	}

	@Override
	public EiaType toEntity() {
		EiaType eiaType = new EiaType();
		eiaType.setCode(getCode());
		eiaType.setId(getId());
		String brandName = getBrand();
		if (brandName != null) {
			Brand brand = new Brand();
			brand.setName(brandName);
			eiaType.setBrand(brand);
		}
		String manName = getManufacturer();
		if (manName != null) {
			Manufacturer man = new Manufacturer();
			man.setName(manName);
			eiaType.setManufacturer(man);
		}
		eiaType.setModel(getModel());
		eiaType.setName(getName());
		return eiaType;
	}
}