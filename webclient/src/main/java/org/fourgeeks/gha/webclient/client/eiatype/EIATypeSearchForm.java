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
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeSearchForm extends GHASlideInWindow implements EIATypeSelectionListener {

	private List<EIATypeSelectionListener> selectionListeners;
	private GHATextItem codeEIAItem, nameEIAItem, modelItem, descriptionItem,
			useDescriptionItem, umdnsCodeItem;
	private EIATypeGrid eiaTypeGrid;
	private GHASelectItem brandItem, manItem, mobilityItem, typeItem,
			subTypeItem;
	private GHACheckboxItem isServiceItem;
	private EIATypeAddForm addForm;
	
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
		useDescriptionItem.setWidth(200);
		useDescriptionItem.setColSpan(2);
		umdnsCodeItem = new GHATextItem("EIAUMDNS");
		mobilityItem = new GHASelectItem("Movilidad");
		typeItem = new GHASelectItem("Tipo de Equipo");
		subTypeItem = new GHASelectItem("Subtipo");
		isServiceItem = new GHACheckboxItem("Es servicio");
		
		addForm = new EIATypeAddForm();
		addForm.addEiaTypeSelectionListener(this);
	}

	public EIATypeSearchForm() {
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight() + "px");

		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(codeEIAItem, nameEIAItem, descriptionItem, brandItem,
				modelItem, manItem, useDescriptionItem, umdnsCodeItem,
				mobilityItem, typeItem, subTypeItem, isServiceItem);

		GHAImgButton searchButton = new GHAImgButton("../resources/icons/search.png");
		// Event Handlers
		ClickHandler searchClickHandler = new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				search(null);
			}
		};
		KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getKeyName().equals("Enter")) {
					search(null);
				}
			}
		};
		searchButton.addClickHandler(searchClickHandler);
		codeEIAItem.addKeyUpHandler(searchKeyUpHandler);
		nameEIAItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionItem.addKeyUpHandler(searchKeyUpHandler);
		brandItem.addKeyUpHandler(searchKeyUpHandler);
		modelItem.addKeyUpHandler(searchKeyUpHandler);
		manItem.addKeyUpHandler(searchKeyUpHandler);
		useDescriptionItem.addKeyUpHandler(searchKeyUpHandler);
		umdnsCodeItem.addKeyUpHandler(searchKeyUpHandler);
		mobilityItem.addKeyUpHandler(searchKeyUpHandler);
		typeItem.addKeyUpHandler(searchKeyUpHandler);
		subTypeItem.addKeyUpHandler(searchKeyUpHandler);
		// ////////////////////////////

		GHAImgButton cleanButton = new GHAImgButton("../resources/icons/clean.png");
		GHAImgButton cancelButton = new GHAImgButton("../resources/icons/cancel.png");
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				hide();
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

		eiaTypeGrid = new EIATypeGrid();
		eiaTypeGrid.setHeight(GHAUiHelper.getGridSize(30));
		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);
		gridLayout.addMembers(eiaTypeGrid);

		VLayout sideGridButtons = new VLayout();
		sideGridButtons.setWidth(30);
		sideGridButtons.setLayoutMargin(5);
		sideGridButtons.setBackgroundColor("#E0E0E0");
		sideGridButtons.setMembersMargin(10);
		sideGridButtons.setDefaultLayoutAlign(Alignment.CENTER);

		GHAImgButton acceptButton = new GHAImgButton("../resources/icons/check.png");
		acceptButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				selectEiaType(((EIATypeRecord) eiaTypeGrid.getSelectedRecord())
						.toEntity());
				hide();
			}
		});
		
		GHAImgButton addButton = new GHAImgButton("../resources/icons/new.png");
		addButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				addForm.open();
			}
		});
		
		sideGridButtons
				.addMembers(acceptButton,
						GHAUiHelper.verticalGraySeparator("2px"),
						addButton);
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

	private void selectEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : selectionListeners)
			listener.select(eiaType);
	}
	
	public void AddEIATypeSelectionListener(
			EIATypeSelectionListener selecionListener) {
		selectionListeners.add(selecionListener);
	}

	@Override
	public void select(EiaType eiaType) {
		search(eiaType);
	}

	private void search(final EiaType selectedEiaType) {
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
				if (selectedEiaType != null)
					for (ListGridRecord listGridRecord : eiaTypeGrid.getRecords()) 
						if (((EIATypeRecord)listGridRecord).toEntity().getId() == selectedEiaType.getId())
							eiaTypeGrid.selectRecord(listGridRecord);
			}

		});
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() + "px");
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void hide() {
		EIATypeSearchForm.this.animateHide(AnimationEffect.FLY);
	}
}
