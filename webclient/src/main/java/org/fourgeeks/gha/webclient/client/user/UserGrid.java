package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.auth.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGridField;

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

		final GHATreeGridField nameGridField = new GHATreeGridField("user",
				GHAStrings.get("user"));
		final GHATreeGridField blockGridField = new GHATreeGridField("block",
				GHAStrings.get("state"));
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

		setFields(nameGridField, blockGridField, firstNameGridField,
				lastNameGridField, idGridField, genderGridField,
				nationalityGridField, birthDateGridField);
	}

}
