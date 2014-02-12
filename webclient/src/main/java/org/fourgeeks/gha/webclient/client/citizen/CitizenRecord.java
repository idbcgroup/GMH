/**
 * 
 */
package org.fourgeeks.gha.webclient.client.citizen;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * @author emiliot
 * 
 */
public class CitizenRecord extends GHAGridRecord<Citizen> {
	private Citizen entity;

	/**
	 * @param entity
	 */
	public CitizenRecord(Citizen entity) {
		this.entity = entity;
		setAttribute("fname", this.entity.getFirstName());
		setAttribute("lname", this.entity.getFirstLastName());
		setAttribute("id", this.entity.getIdNumber());
		setAttribute("gender", this.entity.getGender());
		setAttribute("nac", this.entity.getNationality());
		setAttribute("bday", this.entity.getBirthDate());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord#toEntity()
	 */
	@Override
	public Citizen toEntity() {
		return this.entity;
	}

}
