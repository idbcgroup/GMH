package org.fourgeeks.gha.webclient.client.eia.component;

import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

import com.google.gwt.user.client.Window;


/**
 * @author sizturriaga
 * 
 */
public class EIAComponentRecord extends GHAGridRecord<EiaComponent> {
	
	private EiaComponent eiaComponent;
	
	public EIAComponentRecord(EiaComponent eiaComponent){
		setEIAComponentRecord(eiaComponent);
	}

	private void setEIAComponentRecord(EiaComponent eiaComponent) {
		// TODO Auto-generated method stub
		this.eiaComponent = eiaComponent;
		EiaType eiaType = this.eiaComponent.getEia().getEiaType();
		setCode(eiaType.getCode());
		setName(eiaType.getName());
		if (eiaType.getBrand() != null)
			setBrand(eiaType.getBrand().getName());
		setModel(eiaType.getModel());
		if (eiaType.getManufacturer() != null)
			setManufacturer(eiaType.getManufacturer().getName());
		setComponentObs(eiaComponent.getComponentObs());
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

	public void setComponentObs(String componentObs) {
		// TODO Auto-generated method stub
		setAttribute("componentobs", componentObs);
	}
	
	public String getComponentObs(){
		return getAttributeAsString("componentobs");
	}

	@Override
	public EiaComponent toEntity() {
		// TODO Auto-generated method stub
		return eiaComponent;
	}

}
