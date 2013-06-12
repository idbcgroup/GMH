package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAGridRecord;

public class EIARecord extends GHAGridRecord<Eia> {

	public EIARecord() {
	}

	public EIARecord(Eia eia) {
		setId(eia.getId());

		// if (eia.getBuildingLocation() != null) {
		// setBuildingLocation(eia.getBuildingLocation().getId());
		// setBuildingLocationName(eia.getBuildingLocation().getName());
		// }
		//
		if (eia.getEiaType() != null) {
			// setEiaType(eia.getEiaType().getId());
			// setEiaTypeName(eia.getEiaType().getName());
			setCode(eia.getEiaType().getCode());
		}

		// setSerial(eia.getSerial());
		setName(eia.getEiaType().getName());
		// setFacility(eia.getFacility().getName());
		// setStatus(eia.getStatus());
	}

	public String getCode() {
		return getAttributeAsString("code");
	}

	public String getSerial() {
		return getAttributeAsString("serial");
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public String getFacility() {
		return getAttributeAsString("facility");
	}

	public String getFacilityId() {
		return getAttributeAsString("facilityId");
	}

	public String getStatus() {
		return getAttributeAsString("status");
	}

	public void setCode(String code) {
		setAttribute("code", code);
	}

	public void setSerial(String serial) {
		setAttribute("serial", serial);
	}

	public void setName(String name) {
		setAttribute("name", name);
	}

	public void setFacility(String facility) {
		setAttribute("facility", facility);
	}

	public void setFacilityId(String facilityId) {
		setAttribute("facilityId", facilityId);
	}

	public void setStatus(String status) {
		setAttribute("status", status);
	}

	@Override
	public Eia toEntity() {
		Eia eia = new Eia();
		eia.setId(getId());
		// eia.setSerial(getSerial());

		String eiaName = getName();
		if (eiaName != null) {
			EiaType eiaType = new EiaType();
			eiaType.setCode(getCode());
			eiaType.setName(getName());
			eia.setEiatype(eiaType);
		}
		String facilityName = getFacility();
		if (facilityName != null) {
			Facility facility = new Facility();
			// facility.setName(facilityName);
			eia.setFacility(facility);
		}
		// eia.setStatus(getStatus());

		return eia;
	}
}