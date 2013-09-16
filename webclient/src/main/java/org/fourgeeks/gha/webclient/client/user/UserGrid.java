package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;
import org.fourgeeks.gha.webclient.client.UI.grids.tree.GHATreeGridField;

public class UserGrid extends GhaGrid<SSOUser> {

	public UserGrid() {

		setEmptyMessage("No existen usuarios que mostrar.");

		GHATreeGridField nameGridField = new GHATreeGridField("user", "Nombre de Usuario");
		GHATreeGridField passGridField = new GHATreeGridField("pass", "Contraseña");
		GHATreeGridField blockGridField = new GHATreeGridField("block", "Bloqueado");
		GHATreeGridField firstNameGridField = new GHATreeGridField("fname", "Primer Nombre");
		GHATreeGridField secondNameGridField = new GHATreeGridField("sname", "Segundo Nombre");
		GHATreeGridField lastNameGridField = new GHATreeGridField("lname", "Apellido");
		GHATreeGridField secondLastNameGridField = new GHATreeGridField("slname", "Segundo Apellido");
		GHATreeGridField idGridField = new GHATreeGridField("id", "No. Identificacion");
		GHATreeGridField genderGridField = new GHATreeGridField("gender", "Género");
		GHATreeGridField nationalityGridField = new GHATreeGridField("nac", "Nacionalidad");
		GHATreeGridField birthDateGridField = new GHATreeGridField("bday", "Fecha de Nac.");
				
		setFields(nameGridField, passGridField, blockGridField, firstNameGridField, secondNameGridField,
				  lastNameGridField, secondLastNameGridField, idGridField, genderGridField,
				  nationalityGridField, birthDateGridField);
	}

}
