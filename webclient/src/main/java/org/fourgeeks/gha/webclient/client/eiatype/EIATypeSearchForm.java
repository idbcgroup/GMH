package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeSearchForm extends VLayout {

	private List<EIATypeSelectionListener> selectionListeners = new LinkedList<EIATypeSelectionListener>();

	public EIATypeSearchForm() {
		// setShowEdges(true);
		setWidth100();
		setTop(110);
		setLeft(0);
		setHeight("75%");
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
		form.setNumCols(10);
		form.setItems(codigoEIA, nombreEIA, marcaEIA, modeloEIA, fabricante);

		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		GHAButton searchButton = new GHAButton("../resources/icons/search.png");
		GHAButton cleanButton = new GHAButton("../resources/icons/clean.png");
		GHAButton cancelButton = new GHAButton("../resources/icons/cancel.png");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EIATypeSearchForm.this.animateHide(AnimationEffect.FLY);
			}
		});
		sideButtons.addMembers(searchButton, cleanButton, cancelButton);

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.addMembers(form, sideButtons);

		addMember(formLayout);
		addMember(GHAUiHelper.verticalGraySeparator("10px"));

		// //GRID
		EIATypeGrid eiaTypeGrid = new EIATypeGrid();
		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);
		gridLayout.addMembers(eiaTypeGrid);

		addMember(gridLayout);
	}

	public void AddEIATypeSelectionListener(
			EIATypeSelectionListener selecionListener) {
		selectionListeners.add(selecionListener);
	}
}
