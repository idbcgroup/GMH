package org.fourgeeks.gha.webclient.client.citizen;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGridField;

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

		final GHATreeGridField firstNameGridField = new GHATreeGridField(
				"fname", GHAStrings.get("first-name"));
		final GHATreeGridField lastNameGridField = new GHATreeGridField(
				"lname", GHAStrings.get("last-name"));
		final GHATreeGridField idGridField = new GHATreeGridField("id",
				GHAStrings.get("id-number"));
		final GHATreeGridField genderGridField = new GHATreeGridField("gender",
				GHAStrings.get("genre"));
		final GHATreeGridField nationalityGridField = new GHATreeGridField(
				"nac", GHAStrings.get("nationality"));
		final GHATreeGridField birthDateGridField = new GHATreeGridField(
				"bday", GHAStrings.get("birthdate"));

		setFields(firstNameGridField, lastNameGridField, idGridField,
				genderGridField, nationalityGridField, birthDateGridField);
	}

}
