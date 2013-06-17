package org.fourgeeks.gha.webclient.client.eia;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATabTopSection extends HLayout implements
		EIATypeSelectionListener {

	private List<EIATypeSelectionListener> selectionListeners = new LinkedList<EIATypeSelectionListener>();

	private GHATextItem nameItem;
	private EIATypeSearchForm eiaTypeSearchForm = new EIATypeSearchForm();
	private GHATextItem codeItem, brandItem;
	private GHATextItem modelItem;
	private GHATextItem manItem;
	private GHASelectItem typeItem;
	private GHASelectItem subTypeItem;
	private GHATextItem useAreaItem;
	private GHASelectItem useItem;
	private GHATextItem refactorItem;
	private GHATextItem codigoUMDNSItem;

	public EIATabTopSection() {
		super();
		eiaTypeSearchForm.AddEIATypeSelectionListener(this);
		setStyleName("sides-padding");// Esto es VUDU!
		setWidth100();
		setHeight("68px");
		// setBackgroundImage("../resources/img/tab1.jpg");
		setBackgroundColor("#E0E0E0");
		// setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);

		codeItem = new GHATextItem("Código", false);
		nameItem = new GHATextItem("Nombre", false);
		brandItem = new GHATextItem("Marca", false);
		modelItem = new GHATextItem("Modelo", false);
		manItem = new GHATextItem("Fabricante", false);
		typeItem = new GHASelectItem("Tipo", false);
		subTypeItem = new GHASelectItem("Sub-Tipo", false);
		useAreaItem = new GHATextItem("Se usa en area", false);
		useItem = new GHASelectItem("para", false);
		refactorItem = new GHATextItem("Descripción", false);
		codigoUMDNSItem = new GHATextItem("Código UMDNS", false);

		DynamicForm form = new DynamicForm();
		form.setWidth("100px");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(6);
		form.setItems(codeItem, nameItem, brandItem, modelItem, manItem,
				typeItem, subTypeItem, useAreaItem, useItem, refactorItem,
				codigoUMDNSItem);
		// form.setCellPadding(20);
		// form.setPadding(10);

		GHAButton cleanImg = new GHAButton("../resources/icons/clean.png");
		GHAButton searchImg = new GHAButton("../resources/icons/search.png");
		searchImg.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();

			}
		});
		VLayout panelBotones = new VLayout();
		panelBotones.setHeight("68px");
		panelBotones.setWidth(30);
		panelBotones.setLayoutMargin(5);
		panelBotones.setBackgroundColor("#E0E0E0");
		panelBotones.setMembersMargin(10);
		panelBotones.setDefaultLayoutAlign(Alignment.CENTER);
		panelBotones.addMembers(searchImg, cleanImg);

		VLayout fill = new VLayout();
		fill.setWidth("*");

		addMembers(form, fill, panelBotones);

	}

	public void search() {

		eiaTypeSearchForm.animateShow(AnimationEffect.FLY);
		// EiaType eiaType = new EiaType();
		// eiaType.setName(nameField.getValueAsString());
		// EIAModel.find(eiaType, new GHAAsyncCallback<List<EiaType>>() {
		//
		// @Override
		// public void onSuccess(List<EiaType> eiaTypes) {
		// ListGridRecord[] array = (ListGridRecord[]) EIAUtil
		// .toGridRecords(eiaTypes).toArray(new EIARecord[] {});
		// eiaTypeGrid.setData(array);
		// }
		//
		// });
	}

	@Override
	public void select(EiaType eiaType) {
		selectEiaType(eiaType);
		codeItem.setValue(eiaType.getCode());
		nameItem.setValue(eiaType.getName());

		if (eiaType.getBrand() != null)
			brandItem.setValue(eiaType.getBrand().getName());

		modelItem.setValue(eiaType.getModel());

		if (eiaType.getManufacturer() != null)
			manItem.setValue(eiaType.getManufacturer().getName());

		if (eiaType.getType() != null)
			typeItem.setValue(eiaType.getType().name());

		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().name());

		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().name());

		useAreaItem.setValue(eiaType.getUseDescription());

		useItem = new GHASelectItem("para", false);
		refactorItem = new GHATextItem("Descripción", false);
		codigoUMDNSItem = new GHATextItem("Código UMDNS", false);

	}

	public void AddEIATypeSelectionListener(
			EIATypeSelectionListener selecionListener) {
		selectionListeners.add(selecionListener);
	}

	private void selectEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : selectionListeners)
			listener.select(eiaType);

	}

}
