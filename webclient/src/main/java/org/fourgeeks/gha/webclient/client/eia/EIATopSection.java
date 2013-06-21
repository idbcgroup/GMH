package org.fourgeeks.gha.webclient.client.eia;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATopSection extends HLayout implements
		EIATypeSelectionListener {

	private List<EIATypeSelectionListener> selectionListeners;
	private EIATypeSearchForm eiaTypeSearchForm;
	private GHATextItem codeItem, brandItem, modelItem, manItem, nameItem,
			useAreaItem, refactorItem, codigoUMDNSItem, typeItem, subTypeItem,
			useItem;
	{
		selectionListeners = new LinkedList<EIATypeSelectionListener>();
		eiaTypeSearchForm = new EIATypeSearchForm();
		codeItem = new GHATextItem("Código", false);
		nameItem = new GHATextItem("Nombre", false);
		brandItem = new GHATextItem("Marca", false);
		modelItem = new GHATextItem("Modelo", false);
		manItem = new GHATextItem("Fabricante", false);
		typeItem = new GHATextItem("Tipo", false);
		subTypeItem = new GHATextItem("Sub-Tipo", false);
		useAreaItem = new GHATextItem("Se usa en area", false);
		useItem = new GHATextItem("para", false);
		refactorItem = new GHATextItem("Descripción", false);
		codigoUMDNSItem = new GHATextItem("Código UMDNS", false);
	}

	public EIATopSection() {
		super();
		eiaTypeSearchForm.AddEIATypeSelectionListener(this);
		setStyleName("sides-padding");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		setBackgroundColor("#E0E0E0");

		DynamicForm form = new DynamicForm();
		form.setWidth("100px");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(6);
		form.setItems(codeItem, nameItem, brandItem, modelItem, manItem,
				typeItem, subTypeItem, useAreaItem, useItem, refactorItem,
				codigoUMDNSItem);

		GHAButton cleanImg = new GHAButton("../resources/icons/clean.png");
		GHAButton canelButton = new GHAButton("../resources/icons/cancel.png");
		GHAButton searchImg = new GHAButton("../resources/icons/search.png");
		searchImg.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});

		VLayout panelBotones = new VLayout();
		panelBotones.setHeight(GHAUiHelper.V_SEPARATOR_HEIGHT + "px");
		panelBotones.setWidth(30);
		panelBotones.setLayoutMargin(5);
		panelBotones.setBackgroundColor("#E0E0E0");
		panelBotones.setMembersMargin(10);
		panelBotones.setDefaultLayoutAlign(Alignment.CENTER);
		panelBotones.addMembers(searchImg, cleanImg, canelButton);

		VLayout fill = new VLayout();
		fill.setWidth("*");

		addMembers(form, fill, panelBotones);

	}

	public void search() {
		eiaTypeSearchForm.animateShow(AnimationEffect.FLY);
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

		try {
			EiaTypeEnum type = eiaType.getType();
			Window.alert(type == null ? "type es null" : "type no es null");
			try {
				Window.alert(type.name());
			} catch (Exception e) {
				Window.alert("error en name");
			}
			try {
				Window.alert(type.toString());
			} catch (Exception e) {
				Window.alert("error en tostring");
			}
		} catch (Exception e) {
			Window.alert("error en getType");
		}

		if (eiaType.getType() != null)
			typeItem.setValue(eiaType.getType().toString());

		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().toString());

		if (eiaType.getSubtype() != null)
			subTypeItem.setValue(eiaType.getSubtype().toString());

		useAreaItem.setValue(eiaType.getUseDescription());

		// useItem = new GHASelectItem("para", false);
		// refactorItem = new GHATextItem("Descripción", false);
		// codigoUMDNSItem = new GHATextItem("Código UMDNS", false);

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
