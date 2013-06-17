package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;
import org.fourgeeks.gha.webclient.client.manufacturer.ManufacturerModel;

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

	private List<EIATypeSelectionListener> selectionListeners;
	private GHATextItem codeEIAItem, nameEIAItem, modelItem;
	private EIATypeGrid eiaTypeGrid;
	private GHASelectItem brandItem, manItem;
	{
		selectionListeners = new LinkedList<EIATypeSelectionListener>();
		codeEIAItem = new GHATextItem("Código");
		nameEIAItem = new GHATextItem("Nombre");
		brandItem = new GHASelectItem("Marca");
		modelItem = new GHATextItem("Modelo");
		manItem = new GHASelectItem("Fabricante");
	}

	public EIATypeSearchForm() {
		setWidth100();
		setTop(110);
		setLeft(0);
		setHeight("75%");
		setBackgroundColor("#E0E0E0");
		setVisibility(Visibility.HIDDEN);
		setAlign(Alignment.CENTER);
		setAnimateTime(800);
		setBorder("2px solid #484848");

		DynamicForm form = new DynamicForm();
		form.setWidth("*");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(10);
		form.setItems(codeEIAItem, nameEIAItem, brandItem, modelItem, manItem);

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
		searchForBrands();
		searchForMans();
	}

	private void searchForMans() {
		ManufacturerModel.getAll(new GHAAsyncCallback<List<Manufacturer>>() {

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
		BrandModel.getAll(new GHAAsyncCallback<List<Brand>>() {

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
			eiaType.setBrand(new Brand((Integer) brandItem.getValue(), null));
		eiaType.setModel(modelItem.getValueAsString());
		if (manItem.getValue() != null)
			eiaType.setManufacturer(new Manufacturer((Integer) manItem
					.getValue(), null));
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
