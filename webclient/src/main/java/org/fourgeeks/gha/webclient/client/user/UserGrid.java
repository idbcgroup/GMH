package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

/**
 * @author alacret
 * 
 */
public class UserGrid extends GhaGrid<SSOUser> {

	/**
	 * 
	 */
	public UserGrid() {

		setEmptyMessage(GHAStrings.get("empty-user-grid"));

		final GHAGridField nameGridField = new GHAGridField("user",
				GHAStrings.get("user"));
		final GHAGridField blockGridField = new GHAGridField("block",
				GHAStrings.get("state"));
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

		setFields(nameGridField, blockGridField, firstNameGridField,
				lastNameGridField, idGridField, genderGridField,
				nationalityGridField, birthDateGridField);
	}

}
