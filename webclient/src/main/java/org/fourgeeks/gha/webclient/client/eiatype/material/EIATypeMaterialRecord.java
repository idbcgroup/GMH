package org.fourgeeks.gha.webclient.client.eiatype.material;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;

import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

public class EIATypeMaterialRecord 
	extends GHAGridRecord<EiaTypeMaterial> {
	
	private EiaTypeMaterial eiaTypeMaterial;

	public EIATypeMaterialRecord(EiaTypeMaterial eiaTypeMaterial) {
		setEiaTypeMaterial(eiaTypeMaterial);
	}
	
	public void setEiaTypeMaterial(EiaTypeMaterial eiaTypeMaterial) {
		this.eiaTypeMaterial = eiaTypeMaterial;
		Material material = eiaTypeMaterial.getMaterial();
		setId(this.eiaTypeMaterial.getId());
		setCode(material.getCode());
		setName(material.getName());
		if (material.getType() != null)
			setType(material.getType().toString());
		setModel(material.getModel());
	}
	
	public void setCode(String code) {
		setAttribute("code", code);
	}

	public String getCode() {
		return getAttributeAsString("code");
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public void setType(String type) {
		setAttribute("type", type);
	}

	public String getType() {
		return getAttributeAsString("type");
	}

	public void setModel(String model) {
		setAttribute("model", model);
	}

	public String getModel() {
		return getAttributeAsString("model");
	}
	
	@Override
	public EiaTypeMaterial toEntity() {
		return eiaTypeMaterial;
	}

}
