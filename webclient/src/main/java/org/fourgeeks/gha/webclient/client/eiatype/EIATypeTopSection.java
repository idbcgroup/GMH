package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeTopSection extends HLayout implements
		EIATypeSelectionListener {

	private List<EIATypeSelectionListener> selectionListeners;
	private EIATypeSearchForm eiaTypeSearchForm;
	private GHATextItem nameItem, codeItem, brandItem, modelItem, manItem;

	{
		selectionListeners = new LinkedList<EIATypeSelectionListener>();
		eiaTypeSearchForm = new EIATypeSearchForm();
		codeItem = new GHATextItem("Código", false);
		nameItem = new GHATextItem("Nombre", false);
		brandItem = new GHATextItem("Marca", false);
		modelItem = new GHATextItem("Modelo", false);
		manItem = new GHATextItem("Fabricante", false);
	}

	public EIATypeTopSection() {
		super();
		eiaTypeSearchForm.AddEIATypeSelectionListener(this);
		setStyleName("sides-padding");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		setBackgroundColor("#E0E0E0");

		DynamicForm form = new DynamicForm();
		form.setWidth("*");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(10);
		form.setItems(codeItem, nameItem, brandItem, modelItem, manItem);

		VLayout panelBotones = new VLayout();
		panelBotones.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		panelBotones.setWidth(30);
		panelBotones.setLayoutMargin(5);
		panelBotones.setBackgroundColor("#E0E0E0");
		panelBotones.setMembersMargin(10);
		panelBotones.setDefaultLayoutAlign(Alignment.CENTER);

		GHAButton cleanImg = new GHAButton("../resources/icons/clean.png");
		cleanImg.setSize("20px", "20px");
		cleanImg.setHoverStyle("boxed");
		GHAButton searchImg = new GHAButton("../resources/icons/search.png");
		searchImg.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				eiaTypeSearchForm.animateShow(AnimationEffect.FLY);
			}
		});
		GHAButton cancelButton = new GHAButton("../resources/icons/cancel.png");
		panelBotones.addMembers(searchImg, cleanImg, cancelButton);

		addMembers(form, panelBotones);
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

		// if (eiaType.getType() != null)
		// typeItem.setValue(eiaType.getType().toString());
		//
		// if (eiaType.getSubtype() != null)
		// subTypeItem.setValue(eiaType.getSubtype().toString());
		//
		// if (eiaType.getSubtype() != null)
		// subTypeItem.setValue(eiaType.getSubtype().toString());
		//
		// useAreaItem.setValue(eiaType.getUseDescription());

		// useItem = new GHASelectItem("para", false);
		// refactorItem = new GHATextItem("Descripción", false);
		// codigoUMDNSItem = new GHATextItem("Código UMDNS", false);

	}

	public void search() {
		eiaTypeSearchForm.animateShow(AnimationEffect.FLY);
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
