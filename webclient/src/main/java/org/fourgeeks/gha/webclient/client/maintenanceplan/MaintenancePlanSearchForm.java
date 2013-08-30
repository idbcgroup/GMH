package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MaintenancePlanSearchForm extends GHASlideInWindow {

//	private List<EIASelectionListener> listeners;
	private MaintenancePlanGrid grid;
	private GHATextItem codeItem, descriptionItem, frequencyItem, usesItem;
	private GHASelectItem periodOfTimeSelectItem;
	
	private MaintenancePlanAddForm addForm;

	{
//		listeners = new LinkedList<EIASelectionListener>();
		codeItem = new GHATextItem("Código");
		frequencyItem = new GHATextItem("Frecuencia");
		periodOfTimeSelectItem = new GHASelectItem("Periodo de Tiempo");
		usesItem = new GHATextItem("Usos");
		descriptionItem = new GHATextItem("Descripción",420);
		descriptionItem.setColSpan(4);
		
		grid = new MaintenancePlanGrid();
		
		addForm = new MaintenancePlanAddForm();
	}

	public MaintenancePlanSearchForm() {
		super(1);
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight() + "px");

		Label title = new Label("<h3>Busqueda de Planes de Mantenimiento</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		addMember(title);
		
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);

		form.setItems(codeItem,frequencyItem,periodOfTimeSelectItem,usesItem,
					  descriptionItem);

		// Event Handlers
		ClickHandler searchClickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		};
		KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getKeyName().equals("Enter")) {
					search();
				}
			}
		};
		codeItem.addKeyUpHandler(searchKeyUpHandler);
		frequencyItem.addKeyUpHandler(searchKeyUpHandler);
		periodOfTimeSelectItem.addKeyUpHandler(searchKeyUpHandler);
		usesItem.addKeyUpHandler(searchKeyUpHandler);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", searchClickHandler),
				new GHAImgButton("../resources/icons/clean.png",new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						form.clearValues();
						grid.setData(new ListGridRecord[0]);
					}
				}),
				new GHAImgButton("../resources/icons/cancel.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								hide();
							}
						}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(title,
				   formLayout,
				   GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));

		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
//						selectEia(((EIARecord) grid.getSelectedRecord())
//								.toEntity());
						hide();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						addForm.open();
					}
				}));

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);
				
//		searchForEiaTypes();
//		searchForRoleBases();
//		searchForBuildingLocations();
//		searchForObus();
//		fillExtras();
	}

	private void fillExtras() {
		// state
//		stateItem.setValueMap(EiaStateEnum.toValueMap());
	}

	private void searchForEiaTypes() {
//		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {
//
//			@Override
//			public void onSuccess(List<EiaType> result) {
//				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
//				for (EiaType eiaType : result)
//					valueMap.put(eiaType.getCode(), eiaType.getName());
//				eiaTypeItem.setValueMap(valueMap);
//			}
//		}, false);
	}

	private void searchForBuildingLocations() {
//		GHACache.INSTANCE
//				.getBuildingLocations(new GHAAsyncCallback<List<BuildingLocation>>() {
//
//					@Override
//					public void onSuccess(List<BuildingLocation> result) {
//						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
//						for (BuildingLocation buildingLocation : result)
//							valueMap.put(buildingLocation.getCode(),
//									buildingLocation.getName());
//						buildingLocationItem.setValueMap(valueMap);
//					}
//				});
	}

	private void searchForObus() {
//		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {
//
//			@Override
//			public void onSuccess(List<Obu> result) {
//				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
//				for (Obu obu : result)
//					valueMap.put(String.valueOf(obu.getId()), obu.getName());
//				obuItem.setValueMap(valueMap);
//			}
//		});
	}

	private void searchForRoleBases() {
//		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<RoleBase>>() {
//
//			@Override
//			public void onSuccess(List<RoleBase> result) {
//				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
//				for (RoleBase roleBase : result)
//					valueMap.put(String.valueOf(roleBase.getId()),
//							roleBase.getName());
//				responsibleRoleItem.setValueMap(valueMap);
//			}
//		});
	}

	/**
	 * 
	 * @param eiaSelectionListener
	 */
	public void addEIASelectionListener(
			EIASelectionListener eiaSelectionListener) {
//		listeners.add(eiaSelectionListener);
	}

	private void selectEia(Eia eia) {
//		for (EIASelectionListener listener : listeners)
//			listener.select(eia);
	}

	private void search() {
//		Eia eia = new Eia();
//		if (actualCostItem.getValue() != null)
//			eia.setActualCost(new BigDecimal(actualCostItem.getValueAsString()));
//		if (responsibleRoleItem.getValue() != null)
//			eia.setResponsibleRole(new RoleBase(Long
//					.parseLong(responsibleRoleItem.getValueAsString())));
//		eia.setCode(codeItem.getValueAsString());
//		if (eiaTypeItem.getValue() != null)
//			eia.setEiaType(new EiaType(eiaTypeItem.getValueAsString()));
//		eia.setFixedAssetIdentifier(fixedAssetIdentifierItem.getValueAsString());
////		if (buildingLocationItem.getValue() != null)
////			eia.setBuildingLocation(new BuildingLocation(buildingLocationItem
////					.getValueAsString()));
//		if (obuItem.getValue() != null)
//			eia.setObu(new Obu(Long.parseLong(obuItem.getValueAsString())));
//		eia.setSerialNumber(serialNumberItem.getValueAsString());
//		if (stateItem.getValue() != null)
//			eia.setState(EiaStateEnum.valueOf(stateItem.getValueAsString()));
//		search(eia);
	}

	private void search(final Eia eia) {
//		EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {
//
//			@Override
//			public void onSuccess(List<Eia> result) {
//				ListGridRecord[] array = EIAUtil.toGridRecords(result).toArray(
//						new EIARecord[] {});
//				grid.setData(array);
//				if (eia != null && eia.getId() != 0l)
//					for (ListGridRecord listGridRecord : grid.getRecords())
//						if (((EIARecord) listGridRecord).toEntity().getId() == eia
//								.getId())
//							grid.selectRecord(listGridRecord);
//			}
//		});
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() + "px");
	}

}
