package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SingleSignOnUser;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGridField;

public class UserGrid extends GhaGrid<SingleSignOnUser> {

	public UserGrid() {

		setEmptyMessage("No existen usuarios que mostrar.");

		GHATreeGridField idGridField = new GHATreeGridField("id", "No.");
		GHATreeGridField nameGridField = new GHATreeGridField("name", "Nombre");
		GHATreeGridField passGridField = new GHATreeGridField("pass", "Contraseña");
				
		setFields(idGridField, nameGridField, passGridField);
	}

}
