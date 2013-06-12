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

public class EIATopSection extends HLayout implements EIATypeSelectionListener {

	private List<EIATypeSelectionListener> selectionListeners = new LinkedList<EIATypeSelectionListener>();

	private GHATextItem nameField;
	private EIATypeSearchForm eiaTypeSearchForm = new EIATypeSearchForm();

	public EIATopSection() {
		super();
		eiaTypeSearchForm.AddEIATypeSelectionListener(this);
		setStyleName("sides-padding");// Esto es VUDU!
		setWidth100();
		setHeight("68px");
		// setBackgroundImage("../resources/img/tab1.jpg");
		setBackgroundColor("#E0E0E0");
		// setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);

		GHATextItem codigoEIA = new GHATextItem("Código");
		nameField = new GHATextItem("Nombre");
		GHATextItem marcaEIA = new GHATextItem("Marca");
		GHATextItem modeloEIA = new GHATextItem("Modelo");
		GHATextItem fabricante = new GHATextItem("Fabricante");
		GHASelectItem typeField = new GHASelectItem("Tipo");
		GHASelectItem subTypeField = new GHASelectItem("Sub-Tipo");
		GHASelectItem useAreaField = new GHASelectItem("Se usa en area");
		GHASelectItem useField = new GHASelectItem("para");
		// CheckboxItem isServiceCheckField = new CheckboxItem("Es servicio");
		GHATextItem descriptionField = new GHATextItem("Descripción");
		GHATextItem codigoUMDNSField = new GHATextItem("Código UMDNS");
		// GHATextItem unidsFields = new GHATextItem("Unidades");
		// GHATextItem mField = new GHATextItem("En mantenimiento");
		// GHATextItem desincorporatedField = new
		// GHATextItem("Desincorporadas");
		// GHATextItem availableField = new GHATextItem("Disponibles");
		// CheckboxItem useServiceField = new CheckboxItem("Usa servicios");
		// CheckboxItem isMovilField = new CheckboxItem("Móvil");
		// CheckboxItem isSpecialMaterialField = new CheckboxItem(
		// "Material Especial");

		DynamicForm form = new DynamicForm();
		form.setWidth("100px");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(6);
		form.setItems(codigoEIA, nameField, marcaEIA, modeloEIA, fabricante,
				typeField, subTypeField, useAreaField, useField,
				descriptionField, codigoUMDNSField);
		// form.setCellPadding(20);
		// form.setPadding(10);

		GHAButton cleanImg = new GHAButton("../resources/icons/boton3.png");
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
