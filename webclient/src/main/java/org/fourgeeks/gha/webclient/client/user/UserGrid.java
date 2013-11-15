package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SSOUser;
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

		setEmptyMessage("No existen usuarios que mostrar.");

		GHATreeGridField nameGridField = new GHATreeGridField("user",
				GHAStrings.get("user"));
		GHATreeGridField blockGridField = new GHATreeGridField("block",
				GHAStrings.get("state"));
		GHATreeGridField firstNameGridField = new GHATreeGridField("fname",
				GHAStrings.get("first-name"));
		GHATreeGridField lastNameGridField = new GHATreeGridField("lname",
				GHAStrings.get("last-name"));
		GHATreeGridField idGridField = new GHATreeGridField("id",
				GHAStrings.get("id-number"));
		GHATreeGridField genderGridField = new GHATreeGridField("gender",
				GHAStrings.get("genre"));
		GHATreeGridField nationalityGridField = new GHATreeGridField("nac",
				GHAStrings.get("nationality"));
		GHATreeGridField birthDateGridField = new GHATreeGridField("bday",
				GHAStrings.get("birthdate"));

		setFields(nameGridField, blockGridField, firstNameGridField,
				lastNameGridField, idGridField, genderGridField,
				nationalityGridField, birthDateGridField);
	}

}
