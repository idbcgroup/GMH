package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.equipos.EIATypeEquiposGrid;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeSearchForm extends VLayout {

	private List<EIATypeSelectionListener> selectionListeners;
	private GHATextItem codeEIAItem, nameEIAItem, modelItem, descriptionItem, useDescriptionItem, umdnsCodeItem;
	private EIATypeEquiposGrid eiaTypeGrid;
	private GHASelectItem brandItem, manItem, mobilityItem, typeItem, subTypeItem;
//	private GHACheckboxItem isServiceItem, hasPartsItem, hasComponentsItem; 
	{
		selectionListeners = new LinkedList<EIATypeSelectionListener>();
		codeEIAItem = new GHATextItem("Código");
		nameEIAItem = new GHATextItem("Nombre");
		brandItem = new GHASelectItem("Marca");
		modelItem = new GHATextItem("Modelo");
		manItem = new GHASelectItem("Fabricante");
		descriptionItem = new GHATextItem("Descripción");
		descriptionItem.setWidth(200);
		descriptionItem.setColSpan(2);
		useDescriptionItem = new GHATextItem("Uso");
		umdnsCodeItem = new GHATextItem("EIAUMDNS");
		mobilityItem = new GHASelectItem("Movilidad");
		typeItem = new GHASelectItem("Tipo de Equipo");
		subTypeItem = new GHASelectItem("Subtipo");
//		isServiceItem = new GHACheckboxItem("Es servicio");
//		hasPartsItem = new GHACheckboxItem("Tiene partes");
//		hasComponentsItem = new GHACheckboxItem("Tiene componentes");
	}

	public EIATypeSearchForm() {
		setWidth100(/* Window.getClientWidth() - 100 */);
		setTop(110);
		setLeft(-10);
		setHeight(GHAUiHelper.calculateTabHeight() + "px");
		setBackgroundColor("#E0E0E0");
		setVisibility(Visibility.HIDDEN);
		setAlign(Alignment.CENTER);
		setAnimateTime(800);
		addStyleName("box");

		DynamicForm form = new DynamicForm();
		form.setWidth("*");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(6);
		form.setItems(codeEIAItem, nameEIAItem, descriptionItem, brandItem, modelItem, 
					  manItem, useDescriptionItem, umdnsCodeItem, mobilityItem, typeItem, subTypeItem);

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
		formLayout.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMember(formLayout);
		addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));

		eiaTypeGrid = new EIATypeEquiposGrid();
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
		sideGridButtons
				.addMembers(acceptButton/* , editButton, deleteButton */);
		gridLayout.addMember(sideGridButtons);

		addMember(gridLayout);
		searchForBrands();
		searchForMans();
		fillExtras();
	}
	
	private void fillExtras() {
		// types
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (EiaTypeEnum eiaTypeEnum : EiaTypeEnum.values())
			valueMap.put(eiaTypeEnum.ordinal() + "", eiaTypeEnum.toString());
		typeItem.setValueMap(valueMap);
		// subtypes
		valueMap = new LinkedHashMap<String, String>();
		for (EiaSubTypeEnum subtype : EiaSubTypeEnum.values())
			valueMap.put(subtype.ordinal() + "", subtype.toString());
		subTypeItem.setValueMap(valueMap);
		// mobility
		valueMap = new LinkedHashMap<String, String>();
		for (EiaMobilityEnum mobility : EiaMobilityEnum.values())
			valueMap.put(mobility.ordinal() + "", mobility.toString());
		mobilityItem.setValueMap(valueMap);
	}
	
	private void searchForMans() {
		GHACache.INSTANCE
				.getManufacturesrs(new GHAAsyncCallback<List<Manufacturer>>() {

					@Override
					public void onSuccess(List<Manufacturer> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (Manufacturer manufacturer : result)
							valueMap.put(manufacturer.getId() + "",
									manufacturer.getName());
						manItem.setValueMap(valueMap);

					}
				});

	}

	private void searchForBrands() {
		GHACache.INSTANCE.getBrands(new GHAAsyncCallback<List<Brand>>() {

			@Override
			public void onSuccess(List<Brand> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Brand brand : result)
					valueMap.put(brand.getId() + "", brand.getName());
				brandItem.setValueMap(valueMap);

			}
		});

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
		eiaType.setCode(codeEIAItem.getValueAsString());
		eiaType.setName(nameEIAItem.getValueAsString());
		if (brandItem.getValue() != null)
			eiaType.setBrand(new Brand(Integer.valueOf(brandItem
					.getValueAsString()), null));
		eiaType.setModel(modelItem.getValueAsString());
		if (manItem.getValue() != null)
			eiaType.setManufacturer(new Manufacturer(Integer.valueOf(manItem
					.getValueAsString()), null));

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
