package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeSearchForm extends VLayout {

	private List<EIATypeSelectionListener> selectionListeners = new LinkedList<EIATypeSelectionListener>();
	private GHATextItem codeEIAItem;
	private GHATextItem nameEIAItem;
	private EIATypeGrid eiaTypeGrid;

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

		codeEIAItem = new GHATextItem("Código");
		nameEIAItem = new GHATextItem("Nombre");
		GHATextItem marcaEIA = new GHATextItem("Marca");
		GHATextItem modeloEIA = new GHATextItem("Modelo");
		GHATextItem fabricante = new GHATextItem("Fabricante");

		DynamicForm form = new DynamicForm();
		form.setWidth("*");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(10);
		form.setItems(codeEIAItem, nameEIAItem, marcaEIA, modeloEIA, fabricante);

		GHAButton searchButton = new GHAButton("../resources/icons/search.png");
		searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();

			}
		});
		GHAButton cleanButton = new GHAButton("../resources/icons/clean.png");
		GHAButton cancelButton = new GHAButton("../resources/icons/cancel.png");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				EIATypeSearchForm.this.animateHide(AnimationEffect.FLY);
			}
		});
		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);
		sideButtons.addMembers(searchButton, cleanButton, cancelButton);

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.addMembers(form, sideButtons);

		addMember(formLayout);
		addMember(GHAUiHelper.verticalGraySeparator("10px"));

		eiaTypeGrid = new EIATypeGrid();
		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);
		gridLayout.addMembers(eiaTypeGrid);

		VLayout sideGridButtons = new VLayout();
		sideGridButtons.setWidth(30);
		sideGridButtons.setLayoutMargin(5);
		sideGridButtons.setBackgroundColor("#E0E0E0");
		sideGridButtons.setMembersMargin(10);
		sideGridButtons.setDefaultLayoutAlign(Alignment.CENTER);

		GHAButton acceptButton = new GHAButton("../resources/icons/check.png");
		acceptButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				selectEiaType(((EIATypeRecord) eiaTypeGrid.getSelectedRecord())
						.toEntity());
				EIATypeSearchForm.this.animateHide(AnimationEffect.FLY);
			}
		});
		Img editButton = new Img("../resources/icons/edit.png");
		editButton.setSize("20px", "20px");
		Img deleteButton = new Img("../resources/icons/delete.png");
		deleteButton.setSize("20px", "20px");

		sideGridButtons.addMembers(acceptButton, editButton, deleteButton);
		gridLayout.addMember(sideGridButtons);

		addMember(gridLayout);
	}

	public void AddEIATypeSelectionListener(
			EIATypeSelectionListener selecionListener) {
		selectionListeners.add(selecionListener);
	}

	private void selectEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : selectionListeners)
			listener.select(eiaType);

	}

	private void search() {
		EiaType eiaType = new EiaType();
		eiaType.setName(nameEIAItem.getValueAsString());
		EIATypeModel.find(eiaType, new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> eiaTypes) {
				ListGridRecord[] array = EIATypeUtil.toGridRecords(eiaTypes)
						.toArray(new EIATypeRecord[] {});
				eiaTypeGrid.setData(array);
			}

		});
	}

}
