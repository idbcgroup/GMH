package org.fourgeeks.gha.webclient.client.eiatype;

import org.fourgeeks.gha.webclient.client.UI.GHATextItem;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeForm extends VLayout {

	public EIATypeForm() {
		// setShowEdges(true);
		setStyleName("exampleTitle");
		setPadding(10);
		setWidth100();
		setTop(197);
		setLeft(0);
		setHeight("320px");
		setBackgroundColor("#E0E0E0");
		setVisibility(Visibility.HIDDEN);
		setAlign(Alignment.CENTER);
		setAnimateTime(800);

		GHATextItem codigoEIA = new GHATextItem("Código");

		GHATextItem nombreEIA = new GHATextItem("Nombre");

		GHATextItem marcaEIA = new GHATextItem("Marca");

		GHATextItem modeloEIA = new GHATextItem("Modelo");

		GHATextItem fabricante = new GHATextItem("Fabricante");

		DynamicForm form = new DynamicForm();
		form.setWidth("*");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(1);
		form.setItems(codigoEIA, nombreEIA, marcaEIA, modeloEIA, fabricante);
		addMember(form);
	}
}
