package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.webclient.client.UI.GHATab;

import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;

public class EIATab extends GHATab {

	public EIATab() {
		super();
		setID(EIAPlace.ID);
		CheckboxItem checkboxItem = new CheckboxItem();
		checkboxItem.setTitle("TEST");
		DynamicForm form = new DynamicForm();
		form.setItems(checkboxItem);
		setPane(form);
	}

	@Override
	public void onClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getId() {
		return EIAPlace.ID;
	}

	@Override
	public String getTitle() {
		return "Tipos de equipo";
	}

}
