package org.fourgeeks.gha.webclient.client.eiatype.caracteristicas;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeCaracteristicasForm extends VLayout implements
		EIATypeSelectionListener {

	private GHATextItem codeItem, nameItem, brandItem, modelItem, manItem;

	{
		codeItem = new GHATextItem("Código");
		nameItem = new GHATextItem("Nombre");
		brandItem = new GHATextItem("Marca");
		modelItem = new GHATextItem("Modelo");
		manItem = new GHATextItem("Fabricante");
	}

	public EIATypeCaracteristicasForm() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		setAlign(Alignment.CENTER);

		Label title = new Label("<h3>Caracteristicas del EIA Type</h3>");
		title.setHeight(30);
		title.setWidth100();
		title.setStyleName("title-label");
		addMember(title);

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codeItem, nameItem, brandItem, modelItem, manItem);

		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		GHAButton addButton = new GHAButton("../resources/icons/new.png");
		GHAButton editButton = new GHAButton("../resources/icons/edit.png");
		GHAButton cancelButton = new GHAButton("../resources/icons/delete.png");

		sideButtons.addMembers(addButton, editButton, cancelButton);

		HLayout fill = new HLayout();
		fill.setWidth("*");

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(form, fill, sideButtons);

		addMember(gridPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		codeItem.setValue(eiaType.getCode());
		nameItem.setValue(eiaType.getName());

		if (eiaType.getBrand() != null)
			brandItem.setValue(eiaType.getBrand().getName());

		modelItem.setValue(eiaType.getModel());

		if (eiaType.getManufacturer() != null)
			manItem.setValue(eiaType.getManufacturer().getName());

	}
}
