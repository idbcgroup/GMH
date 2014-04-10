package org.fourgeeks.gha.webclient.client.citizen;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author alacret
 * 
 */
public class CitizenGrid extends GhaGrid<Citizen> {

	/**
	 * 
	 */
	public CitizenGrid() {

		setEmptyMessage(GHAStrings.get("empty-citizen-grid"));

		final GHAGridField firstNameGridField = new GHAGridField("fname",
				GHAStrings.get("first-name"));
		final GHAGridField lastNameGridField = new GHAGridField("lname",
				GHAStrings.get("last-name"));
		final GHAGridField idGridField = new GHAGridField("id",
				GHAStrings.get("id-number"));
		final GHAGridField genderGridField = new GHAGridField("gender",
				GHAStrings.get("genre"));
		final GHAGridField nationalityGridField = new GHAGridField("nac",
				GHAStrings.get("nationality"));
		final GHAGridField birthDateGridField = new GHAGridField("bday",
				GHAStrings.get("birthdate"));

		setFields(firstNameGridField, lastNameGridField, idGridField,
				genderGridField, nationalityGridField, birthDateGridField);
	}

}
