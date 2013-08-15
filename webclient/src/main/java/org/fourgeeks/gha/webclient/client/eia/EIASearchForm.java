package org.fourgeeks.gha.webclient.client.eia;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.RoleBase;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHATitleTextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
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

public class EIASearchForm extends GHASlideInWindow {

	private List<EIASelectionListener> listeners;
	private EIAGrid grid;
	private GHATextItem actualCostItem, codeItem, fixedAssetIdentifierItem,
			serialNumberItem, realWarrantyTimeItem,
			intWarrantySinceItem, intWarrantyTimeItem;
	private GHADateItem acceptationDateItem, installationDateItem, purchaseDateItem;
	private GHASelectItem responsibleRoleItem, eiaTypeItem, buildingLocationItem, obuItem,
			stateItem, realWarrantySinceItem;
	private GHATitleTextItem garantia, intermediario;
	
	{
		listeners = new LinkedList<EIASelectionListener>();
		
		acceptationDateItem = new GHADateItem("Recibido");
		actualCostItem = new GHATextItem("Costo actual");
		responsibleRoleItem = new GHASelectItem("Responsable");
		codeItem = new GHATextItem("Código");
		eiaTypeItem = new GHASelectItem("Tipo de equipo");
		fixedAssetIdentifierItem = new GHATextItem("Identificador");
		installationDateItem = new GHADateItem("Instalación");
		
		buildingLocationItem = new GHASelectItem("Ubicación");
		
		obuItem = new GHASelectItem("Organización");
		purchaseDateItem = new GHADateItem("Fecha de compra");
		serialNumberItem = new GHATextItem("Serial");
		stateItem = new GHASelectItem("Estado");
		
		garantia = new GHATitleTextItem("Garantía:");
		
		realWarrantySinceItem = new GHASelectItem("Desde");
		realWarrantyTimeItem = new GHATextItem("Período");
		
		intermediario = new GHATitleTextItem("Intermediario:");
		
		intWarrantySinceItem = new GHATextItem("Desde");
		intWarrantyTimeItem = new GHATextItem("Período");
		
		grid = new EIAGrid();
		grid.setHeight(GHAUiHelper.getGridSize(30));
	}

	public EIASearchForm() {
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight() + "px");
		
		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		
		form.setItems(
				acceptationDateItem,actualCostItem,  
				responsibleRoleItem, codeItem, eiaTypeItem, fixedAssetIdentifierItem,
				installationDateItem, buildingLocationItem, 
				obuItem, purchaseDateItem, serialNumberItem, stateItem, garantia,
				realWarrantySinceItem, realWarrantyTimeItem, intermediario, intWarrantySinceItem, 
				intWarrantyTimeItem
		);
		
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
		acceptationDateItem.addKeyUpHandler(searchKeyUpHandler);
		actualCostItem.addKeyUpHandler(searchKeyUpHandler);
		responsibleRoleItem.addKeyUpHandler(searchKeyUpHandler);
		codeItem.addKeyUpHandler(searchKeyUpHandler);
		eiaTypeItem.addKeyUpHandler(searchKeyUpHandler);
		fixedAssetIdentifierItem.addKeyUpHandler(searchKeyUpHandler);
		installationDateItem.addKeyUpHandler(searchKeyUpHandler);
		buildingLocationItem.addKeyUpHandler(searchKeyUpHandler);
		obuItem.addKeyUpHandler(searchKeyUpHandler);
		purchaseDateItem.addKeyUpHandler(searchKeyUpHandler);
		serialNumberItem.addKeyUpHandler(searchKeyUpHandler);
		stateItem.addKeyUpHandler(searchKeyUpHandler);
		garantia.addKeyUpHandler(searchKeyUpHandler);
		realWarrantySinceItem.addKeyUpHandler(searchKeyUpHandler);
		realWarrantyTimeItem.addKeyUpHandler(searchKeyUpHandler);
		intermediario.addKeyUpHandler(searchKeyUpHandler);
		intWarrantySinceItem.addKeyUpHandler(searchKeyUpHandler);
		intWarrantyTimeItem.addKeyUpHandler(searchKeyUpHandler);
		
		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", searchClickHandler),
				new GHAImgButton("../resources/icons/clean.png"),
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

		addMember(formLayout);
		addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		
		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						selectEia(((EIARecord) grid.getSelectedRecord()).toEntity());
						hide();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"));
		
		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);
		searchForEiaTypes();
		searchForRoleBases();
		searchForBuildingLocations();
		searchForObus();
		fillExtras();
	}
	
	private void fillExtras() {
		// state
		stateItem.setValueMap(EiaStateEnum.toValueMap());
		// real warranty since
		realWarrantySinceItem.setValueMap(WarrantySinceEnum.toValueMap());
	}
	
	private void searchForEiaTypes() {
		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(List<EiaType> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (EiaType eiaType : result)
					valueMap.put(String.valueOf(eiaType.getId()), eiaType.getName());
				eiaTypeItem.setValueMap(valueMap);
			}
		});
	}
	
	private void searchForBuildingLocations() {
		GHACache.INSTANCE.getBuildingLocations(new GHAAsyncCallback<List<BuildingLocation>>() {
			
			@Override
			public void onSuccess(List<BuildingLocation> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (BuildingLocation buildingLocation : result)
					valueMap.put(buildingLocation.getCode(), buildingLocation.getName());
				buildingLocationItem.setValueMap(valueMap);
			}
		});
	}
	
	private void searchForObus() {
		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {
			
			@Override
			public void onSuccess(List<Obu> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Obu obu : result)
					valueMap.put(String.valueOf(obu.getId()), obu.getName());
				obuItem.setValueMap(valueMap);
			}
		});
	}
	
	private void searchForRoleBases() {
		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<RoleBase>>() {
			
			@Override
			public void onSuccess(List<RoleBase> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (RoleBase roleBase : result)
					valueMap.put(String.valueOf(roleBase.getId()), roleBase.getName());
				responsibleRoleItem.setValueMap(valueMap);
			}
		});
	}
	
	/**
	 * 
	 * @param eiaSelectionListener
	 */
	public void addEIASelectionListener(EIASelectionListener eiaSelectionListener) {
		listeners.add(eiaSelectionListener);
	}

	private void selectEia(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	private void search() {
		Eia eia = new Eia();
		if (acceptationDateItem.getValue() != null)
			eia.setAcceptationDate(new Date(acceptationDateItem.getValueAsDate().getTime()));
		if (actualCostItem.getValue() != null)
			eia.setActualCost(new BigDecimal(actualCostItem.getValueAsString()));
		if (responsibleRoleItem.getValue() != null)
			eia.setResponsibleRole(new RoleBase(
					Long.parseLong(responsibleRoleItem.getValueAsString()))
			);
		eia.setCode(codeItem.getValueAsString());
		if (eiaTypeItem.getValue() != null)
			eia.setEiaType(new EiaType(
					Long.parseLong(eiaTypeItem.getValueAsString()))
			);
		eia.setFixedAssetIdentifier(fixedAssetIdentifierItem.getValueAsString());
		if (installationDateItem.getValue() != null)
			eia.setInstallationDate(new Date(installationDateItem.getValueAsDate().getTime()));
		if (buildingLocationItem.getValue() != null)
			eia.setBuildingLocation(new BuildingLocation(buildingLocationItem.getValueAsString()));
		if (obuItem.getValue() != null)
			eia.setObu(new Obu(Long.parseLong(obuItem.getValueAsString())));
		if (purchaseDateItem.getValue() != null)
			eia.setPurchaseDate(new Date(purchaseDateItem.getValueAsDate().getTime()));
		eia.setSerialNumber(serialNumberItem.getValueAsString());
		if (stateItem.getValue() != null)
			eia.setState(EiaStateEnum.valueOf(stateItem.getValueAsString()));
		if (realWarrantySinceItem.getValue() != null)
			eia.setRealWarrantySince(
					WarrantySinceEnum.valueOf(realWarrantySinceItem.getValueAsString()));
		if (realWarrantyTimeItem.getValue() != null)
			eia.setRealWarrantyTime(Integer.parseInt(realWarrantyTimeItem.getValueAsString()));
		if (intWarrantySinceItem.getValue() != null)
			eia.setIntWarrantySince(
				WarrantySinceEnum.valueOf(intWarrantySinceItem.getValueAsString()));
		if (intWarrantyTimeItem.getValue() != null)
			eia.setIntWarrantyTime(Integer.parseInt(intWarrantyTimeItem.getValueAsString()));
		search(eia);
	}
	
	private void search(final Eia eia) {
		EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(List<Eia> result) {
				ListGridRecord[] array = EIAUtil.toGridRecords(result)
						.toArray(new EIARecord[]{});
				grid.setData(array);
				if (eia != null && eia.getId() != 0l)
					for (ListGridRecord listGridRecord : grid.getRecords())
						if (((EIARecord) listGridRecord).toEntity().getId() == eia.getId())
							grid.selectRecord(listGridRecord);
			}
		});
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