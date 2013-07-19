package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.DepreciationMethodEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.ItSystemEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.BaseRole;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAButton;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHACheckboxItem;
import org.fourgeeks.gha.webclient.client.UI.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.GHASectionForm;
import org.fourgeeks.gha.webclient.client.UI.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.UI.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.GHATitleTextItem;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret Add Eia Form
 * 
 */
public class EIAAddForm extends GHASlideInWindow implements ResizeHandler, EIATypeSelectionListener {

	private List<EIASelectionListener> listeners;
	private GHATextItem codeEia_Info_TextItem, serialEia_Info_TextItem, activeId_Info_TextItem, orderNo_Adq_TextItem, invoiceNo_Adq_TextItem,
			codeActualArea_Loc_TextItem, codeAttendedArea_Loc_TextItem, adqCost_Cost_TextItem, locAdqCost_Cost_TextItem, deprecTime_Cost_TextItem,
			lifeTime_Cost_TextItem, actualCost_Cost_TextItem, realWarrantyTime_WarrMant_TextItem, timeIntermedWarranty_WarrMant_TextItem, codeMant_WarrMant_TextItem,
			ipDir_IT_TextItem, macAddress_IT_TextItem, machineName_IT_TextItem;
	private GHASelectItem obu_Info_SelectItem, baseRole_Info_SelectItem,
			eqState_Info_SelectItem, adqProvider_Adq_SelectItem, nameActualArea_Loc_SelectItem, nameAttendedArea_Loc_SelectItem,
			adqCurrency_Cost_SelectItem, localCurrency_Cost_SelectItem, deprecationMethod_Cost_SelectItem, deprecTime_Cost_SelectItem,
			lifeTime_Cost_SelectItem, costCurrency_Cost_SelectItem, realWarrantySince_WarrMant_SelectItem, realWarrantyTimePeriod_WarrMant_SelectItem,
			intermedWarrantyTimePeriod, intermedWarrantySince_WarrMant_SelectItem,nameMant_WarrMant_SelectItem,providerMant_WarrMant_SelectItem,
			typeIT_IT_SelectItem;
	private GHATitleTextItem information_TitleItem, adqisition_TitleItem,
			actualArea_TitleItem, attendedArea_TitleItem, adqCost_TitleItem, actualCost_TitleItem,
			depTime_TitleItem, lifeTime_TitleItem, realWarranty_TitleItem,
			intermedWarranty_TitleItem, maintenance_TitleItem, it_TitleItem;
	private GHADateItem buyDate_Adq_DateItem, receptionDate_Adq_DateItem, installationDate_Adq_DateItem, contabDate_Cost_DateItem,
			lastDeprecDate_Cost_DateItem, realInitDate_WarrMant_DateItem, intermedInitDate_WarrMant_DateItem;
	private GHACheckboxItem sameArea_Loc_CheckboxItem, isMant_WarrMant_CheckboxItem;
	private GHASectionForm sectionForm;
	private EiaType eiaType;
	{
		// Global
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EIASelectionListener>();
		
		// Information Form Items
		information_TitleItem = new GHATitleTextItem("Información:");
		codeEia_Info_TextItem = new GHATextItem("Código",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		serialEia_Info_TextItem = new GHATextItem("Serial",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		activeId_Info_TextItem = new GHATextItem("Id. Activo Fijo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		obu_Info_SelectItem = new GHASelectItem("Departamento Responsable",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		baseRole_Info_SelectItem = new GHASelectItem("Rol Responsable",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eqState_Info_SelectItem = new GHASelectItem("Estado Equipo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		
		// Adquisicion Form Itemsxt
		adqisition_TitleItem = new GHATitleTextItem("Adquisición:");
		buyDate_Adq_DateItem = new GHADateItem("Fecha de Compra",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		adqProvider_Adq_SelectItem = new GHASelectItem("Proveedor",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		orderNo_Adq_TextItem = new GHATextItem("No. Orden Compra",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		invoiceNo_Adq_TextItem = new GHATextItem("No. Factura",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		receptionDate_Adq_DateItem = new GHADateItem("Recepción",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		installationDate_Adq_DateItem = new GHADateItem("Instalación",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		
		// Ubicacion Form Items
		actualArea_TitleItem = new GHATitleTextItem("Area Actual:");
		attendedArea_TitleItem = new GHATitleTextItem("Area Atendida:");
		codeActualArea_Loc_TextItem = new GHATextItem("Código",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);
		nameActualArea_Loc_SelectItem = new GHASelectItem("Nombre",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		codeAttendedArea_Loc_TextItem = new GHATextItem("Código",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);
		nameAttendedArea_Loc_SelectItem = new GHASelectItem("Nombre",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		sameArea_Loc_CheckboxItem = new GHACheckboxItem("Atiende a la misma area donde esta Ubicado");
		sameArea_Loc_CheckboxItem.setColSpan(2);
		
		// Costos Form Items
		adqCost_TitleItem = new GHATitleTextItem("Costo Adquisición:");
		actualCost_TitleItem = new GHATitleTextItem("Costo Actual:");
		depTime_TitleItem = new GHATitleTextItem("Tiempo de Depreciación:");
		lifeTime_TitleItem = new GHATitleTextItem("Tiempo de Vida:");
		adqCost_Cost_TextItem = new GHATextItem("Costo de Adq. del equipo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		adqCurrency_Cost_SelectItem = new GHASelectItem("Moneda",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		contabDate_Cost_DateItem = new GHADateItem("Fecha de Contabilización",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		locAdqCost_Cost_TextItem = new GHATextItem("Costo de Adq. Local",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		localCurrency_Cost_SelectItem = new GHASelectItem("Moneda Local",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		deprecationMethod_Cost_SelectItem = new GHASelectItem("Metodo Depreciación",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		deprecTime_Cost_TextItem = new GHATextItem("Duración",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		deprecTime_Cost_SelectItem = new GHASelectItem("Periodo de Tiempo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lastDeprecDate_Cost_DateItem = new GHADateItem("Fecha Ult. Depreciación",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lifeTime_Cost_TextItem = new GHATextItem("Duración",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lifeTime_Cost_SelectItem = new GHASelectItem("Periodo de Tiempo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		actualCost_Cost_TextItem = new GHATextItem("Costo Actual en libros",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		costCurrency_Cost_SelectItem = new GHASelectItem("Moneda",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		
		// Garantias Form Items
		realWarranty_TitleItem = new GHATitleTextItem("Garantía Real:");
		intermedWarranty_TitleItem = new GHATitleTextItem("Garantía del Intermediario:");
		maintenance_TitleItem = new GHATitleTextItem("Mantenimiento:");
		realWarrantySince_WarrMant_SelectItem = new GHASelectItem("Desde",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		realWarrantyTime_WarrMant_TextItem = new GHATextItem("Duración",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		realWarrantyTimePeriod_WarrMant_SelectItem = new GHASelectItem("Periodo de Tiempo",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		realInitDate_WarrMant_DateItem = new GHADateItem("Fecha Inicio",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intermedWarrantySince_WarrMant_SelectItem = new GHASelectItem("Desde",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		timeIntermedWarranty_WarrMant_TextItem = new GHATextItem("Duración",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intermedWarrantyTimePeriod = new GHASelectItem("Periodo de Tiempo",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intermedInitDate_WarrMant_DateItem = new GHADateItem("Fecha Inicio",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		isMant_WarrMant_CheckboxItem = new GHACheckboxItem("Equipo en Mantenimiento");
		codeMant_WarrMant_TextItem = new GHATextItem("Cod. Ubicación Mant.",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		nameMant_WarrMant_SelectItem = new GHASelectItem("Nombre Ubicación Mant.",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		providerMant_WarrMant_SelectItem = new GHASelectItem("Proveedor de Mant.",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		// Equipos IT Form Items
		it_TitleItem = new GHATitleTextItem("EQUIPOS IT (Sistemas):");
		typeIT_IT_SelectItem = new GHASelectItem("Tipo Equipo IT",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		machineName_IT_TextItem = new GHATextItem("Nombre Máquina",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		ipDir_IT_TextItem = new GHATextItem("Direccion IP",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		macAddress_IT_TextItem = new GHATextItem("MAC Address",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
	}

	/**
	 * 
	 */
	public EIAAddForm() {
		GHAUiHelper.addResizeHandler(this);
		setHeight(GHAUiHelper.getBottomSectionHeight());
		setTop(240);

		sectionForm.addSection("Información Básica", getInfoBasicaForm(), true);
		sectionForm.addSectionSeparator();
		sectionForm.addSection("Adquisicion", getAdquisicionForm(), false);
		sectionForm.addSection("Ubicación", getUbicacionForm(), false);
		sectionForm.addSection("Costos", getCostosTab(), false);
		sectionForm.addSection("Garantias", getGarantiasMantForm(), false);
		sectionForm.addSection("EquiposIT", getEquiposIT(), false);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAButton(
				"../resources/icons/save.png", new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						save();
						
					}
				}), new GHAButton(
				"../resources/icons/set.png"), new GHAButton(
				"../resources/icons/cancel.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						EIAAddForm.this.animateHide(AnimationEffect.FLY);
						sectionForm.deactivate();

					}
				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMember(sectionForm);
		mainLayout.addMembers(new LayoutSpacer(), sideButtons);
		Label title = new Label("<h3>Nuevo equipo</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		addMember(title);
		addMember(mainLayout);

		// Fillers
		fillInformationSelects();
		fillAdquisitionSelects();
		fillBuildinglocationsSelects();
		fillCostsSelects();
		fillWarrantySelects();
		fillITEquipmentsSelects();
		
		//Funcionalities
		buildingLocFuncionalities();
		warrantyFunctionalities();
	}

	// //Form Creating Functions
	/**
	 * @return
	 */
	private DynamicForm getInfoBasicaForm() {
		DynamicForm equipoForm = new DynamicForm();
		equipoForm.setTitleOrientation(TitleOrientation.TOP);
		equipoForm.setNumCols(3);

		equipoForm.setItems(information_TitleItem, new GHASpacerItem(2), 
							codeEia_Info_TextItem,serialEia_Info_TextItem, activeId_Info_TextItem,
							obu_Info_SelectItem, baseRole_Info_SelectItem,eqState_Info_SelectItem);
		return equipoForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getAdquisicionForm() {
		// //////Adquisicion Form
		DynamicForm adquisicionForm = new DynamicForm();
		adquisicionForm.setTitleOrientation(TitleOrientation.TOP);
		adquisicionForm.setNumCols(3);

		adquisicionForm.setItems(adqisition_TitleItem, new GHASpacerItem(2),
								 buyDate_Adq_DateItem, receptionDate_Adq_DateItem, installationDate_Adq_DateItem, adqProvider_Adq_SelectItem,
								 orderNo_Adq_TextItem, invoiceNo_Adq_TextItem);
		return adquisicionForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getUbicacionForm() {
		DynamicForm areaForm = new DynamicForm();
		areaForm.setTitleOrientation(TitleOrientation.TOP);
		areaForm.setNumCols(2);

		areaForm.setItems(actualArea_TitleItem, new GHASpacerItem(), 
						  nameActualArea_Loc_SelectItem, codeActualArea_Loc_TextItem, 
						  attendedArea_TitleItem, new GHASpacerItem(), 
						  sameArea_Loc_CheckboxItem, 
						  nameAttendedArea_Loc_SelectItem, codeAttendedArea_Loc_TextItem);
		return areaForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getCostosTab() {
		DynamicForm costosForm = new DynamicForm();
		costosForm.setTitleOrientation(TitleOrientation.TOP);
		costosForm.setNumCols(3);

		costosForm.setItems(adqCost_TitleItem, new GHASpacerItem(2), 
							adqCost_Cost_TextItem, adqCurrency_Cost_SelectItem, contabDate_Cost_DateItem, 
							locAdqCost_Cost_TextItem, localCurrency_Cost_SelectItem,new GHASpacerItem(),
							actualCost_TitleItem, new GHASpacerItem(2),
							deprecationMethod_Cost_SelectItem, lastDeprecDate_Cost_DateItem, new GHASpacerItem(), 
							actualCost_Cost_TextItem, costCurrency_Cost_SelectItem,new GHASpacerItem(),
							depTime_TitleItem,  new GHASpacerItem(2),
							deprecTime_Cost_TextItem, deprecTime_Cost_SelectItem,new GHASpacerItem(),
							lifeTime_TitleItem, new GHASpacerItem(2),							
							lifeTime_Cost_TextItem, lifeTime_Cost_SelectItem);

		return costosForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getGarantiasMantForm() {
		DynamicForm garantiasMamtenimientoForm = new DynamicForm();
		garantiasMamtenimientoForm.setTitleOrientation(TitleOrientation.TOP);
		garantiasMamtenimientoForm.setNumCols(3);

		garantiasMamtenimientoForm.setItems(realWarranty_TitleItem,new GHASpacerItem(2), 
											realWarrantySince_WarrMant_SelectItem, realInitDate_WarrMant_DateItem, new GHASpacerItem(),
											realWarrantyTime_WarrMant_TextItem,realWarrantyTimePeriod_WarrMant_SelectItem, new GHASpacerItem(),
											intermedWarranty_TitleItem,new GHASpacerItem(2), 
											intermedWarrantySince_WarrMant_SelectItem, intermedInitDate_WarrMant_DateItem, new GHASpacerItem(),
											timeIntermedWarranty_WarrMant_TextItem,intermedWarrantyTimePeriod, new GHASpacerItem(),
											new GHASpacerItem(3),
											maintenance_TitleItem, new GHASpacerItem(2), 
											isMant_WarrMant_CheckboxItem, new GHASpacerItem(2),
											nameMant_WarrMant_SelectItem, codeMant_WarrMant_TextItem, providerMant_WarrMant_SelectItem);

		return garantiasMamtenimientoForm;
	}
	
	private DynamicForm getEquiposIT() {
		DynamicForm equiposITForm = new DynamicForm();
		equiposITForm.setTitleOrientation(TitleOrientation.TOP);
		equiposITForm.setNumCols(2);

		equiposITForm.setItems(it_TitleItem, new GHASpacerItem(),
							   typeIT_IT_SelectItem, machineName_IT_TextItem, 
							   ipDir_IT_TextItem,  macAddress_IT_TextItem);

		return equiposITForm;
	}
	
	// //Fillers
	private void fillInformationSelects() {
		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {
			@Override
			public void onSuccess(List<Obu> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Obu entity : result){
					valueMap.put(entity.getId() + "", entity.getName() + "");
				}
				obu_Info_SelectItem.setValueMap(valueMap);
			}
		});

		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<BaseRole>>() {
			@Override
			public void onSuccess(List<BaseRole> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (BaseRole entity : result)
					valueMap.put(entity.getId() + "", entity.getName() + "");
				baseRole_Info_SelectItem.setValueMap(valueMap);
			}
		});

		eqState_Info_SelectItem.setValueMap(EiaStateEnum.toValueMap());
		eqState_Info_SelectItem.setValue(EiaStateEnum.CREATED.name());
	}

	private void fillAdquisitionSelects() {
		GHACache.INSTANCE
				.getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>() {
					@Override
					public void onSuccess(List<ExternalProvider> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (ExternalProvider entity : result)
							valueMap.put(entity.getId() + "", entity
									.getInstitution().getName() + "");
						adqProvider_Adq_SelectItem.setValueMap(valueMap);
					}
				});
	}

	private void fillBuildinglocationsSelects() {
		GHACache.INSTANCE
				.getBuildingLocations(new GHAAsyncCallback<List<BuildingLocation>>() {
					@Override
					public void onSuccess(List<BuildingLocation> result) {
						LinkedHashMap<String, String> valueMapActual = new LinkedHashMap<String, String>();
						LinkedHashMap<String, String> valueMapAtendida = new LinkedHashMap<String, String>();

						for (BuildingLocation entity : result) {
							valueMapActual.put(entity.getCode() + "",
									entity.getName());
							valueMapAtendida.put(entity.getCode() + "",
									entity.getName());
						}

						nameActualArea_Loc_SelectItem.setValueMap(valueMapActual);
						nameAttendedArea_Loc_SelectItem.setValueMap(valueMapAtendida);
					}
				});
	}

	private void fillCostsSelects() {
		adqCurrency_Cost_SelectItem.setValueMap(CurrencyTypeEnum.toValueMap());
		adqCurrency_Cost_SelectItem.setValue(CurrencyTypeEnum.BS.name());
		localCurrency_Cost_SelectItem.setValueMap(CurrencyTypeEnum.toValueMap());
		localCurrency_Cost_SelectItem.setValue(CurrencyTypeEnum.BS.name());

		deprecationMethod_Cost_SelectItem.setValueMap(DepreciationMethodEnum.toValueMap());
		deprecationMethod_Cost_SelectItem.setValue(DepreciationMethodEnum.METHOD_1.name());

		deprecTime_Cost_SelectItem.setValueMap(TimePeriodEnum.toValueMap());
		deprecTime_Cost_SelectItem.setValue(TimePeriodEnum.HOURS.name());
		lifeTime_Cost_SelectItem.setValueMap(TimePeriodEnum.toValueMap());
		lifeTime_Cost_SelectItem.setValue(TimePeriodEnum.HOURS.name());

		costCurrency_Cost_SelectItem.setValueMap(CurrencyTypeEnum.toValueMap());
		costCurrency_Cost_SelectItem.setValue(CurrencyTypeEnum.BS.name());
	}

	private void fillWarrantySelects() {
		realWarrantySince_WarrMant_SelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		realWarrantySince_WarrMant_SelectItem.setValue(WarrantySinceEnum.PURCHASE.name());
		intermedWarrantySince_WarrMant_SelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		intermedWarrantySince_WarrMant_SelectItem.setValue(WarrantySinceEnum.PURCHASE.name());
		
		realWarrantyTimePeriod_WarrMant_SelectItem.setValueMap(TimePeriodEnum.toValueMap());
		realWarrantyTimePeriod_WarrMant_SelectItem.setValue(TimePeriodEnum.HOURS.name());
		intermedWarrantyTimePeriod.setValueMap(TimePeriodEnum.toValueMap());
		intermedWarrantyTimePeriod.setValue(TimePeriodEnum.HOURS.name());
		
		
		GHACache.INSTANCE
		.getBuildingLocations(new GHAAsyncCallback<List<BuildingLocation>>() {
			@Override
			public void onSuccess(List<BuildingLocation> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (BuildingLocation entity : result)
					valueMap.put(entity.getCode() + "",entity.getName());
				nameMant_WarrMant_SelectItem.setValueMap(valueMap);
			}
		});
		
		GHACache.INSTANCE
		.getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>() {
			@Override
			public void onSuccess(List<ExternalProvider> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (ExternalProvider entity : result)
					valueMap.put(entity.getId() + "", entity
							.getInstitution().getName() + "");
				providerMant_WarrMant_SelectItem.setValueMap(valueMap);
			}
		});
	}

	private void fillITEquipmentsSelects() {
		typeIT_IT_SelectItem.setValueMap(ItSystemEnum.toValueMap());
		typeIT_IT_SelectItem.setValue(ItSystemEnum.COMPUTER.name());
	}

	/////Funcionalities
	private void buildingLocFuncionalities(){
		nameActualArea_Loc_SelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				//TODO: 
//				codeAreaActual.setValue(event.getValue());
				if(sameArea_Loc_CheckboxItem.getValue().equals(true)){
					nameAttendedArea_Loc_SelectItem.setValue(event.getValue());
//					codeAreaAtendida.setValue(codeAreaActual.getValue());
				}
			}
		});
		
		nameAttendedArea_Loc_SelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				//TODO: 
//				codeAreaActual.setValue(value);
			}
		});
		
		sameArea_Loc_CheckboxItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				if(event.getValue().equals(true)){
					nameAttendedArea_Loc_SelectItem.setDisabled(true);
					nameAttendedArea_Loc_SelectItem.setValue(nameActualArea_Loc_SelectItem.getValue());
					codeAttendedArea_Loc_TextItem.setValue(codeActualArea_Loc_TextItem.getValue());
				}else{
					nameAttendedArea_Loc_SelectItem.setDisabled(false);
					nameAttendedArea_Loc_SelectItem.clearValue();
					codeAttendedArea_Loc_TextItem.clearValue();
				}
			}
		});
	}
	
	private void warrantyFunctionalities(){
		isMant_WarrMant_CheckboxItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				if(event.getValue().equals(true)){
					nameMant_WarrMant_SelectItem.setDisabled(false);
					providerMant_WarrMant_SelectItem.setDisabled(false);
				}else{
					nameMant_WarrMant_SelectItem.setDisabled(true);
					providerMant_WarrMant_SelectItem.setDisabled(true);
					nameMant_WarrMant_SelectItem.clearValue();
					codeMant_WarrMant_TextItem.clearValue();
					providerMant_WarrMant_SelectItem.clearValue();
				}
			}
		});
	}
	
	// //Implementations

	protected void select(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}
	
	/**
	 * Save the new element to database
	 */
	private void save(){
		if(this.eiaType == null){
			Window.alert("Select EiaType First");
			return;
		}		
		
		final Eia eia = new Eia();
		
		eia.setEiaType(this.eiaType);
		//Window.alert("setting eiatype");
		
		//basic information
		eia.setCode(codeEia_Info_TextItem.getValueAsString());
		eia.setSerialNumber(serialEia_Info_TextItem.getValueAsString());
		eia.setFixedAssetIdentifier(activeId_Info_TextItem.getValueAsString());
		if(obu_Info_SelectItem.getValue() != null){
			Obu obu = new Obu();
			obu.setId(Integer.valueOf(obu_Info_SelectItem.getValueAsString()));
			eia.setObu(obu);
			//Window.alert("setting obu");
		}else{
			Window.alert("Select Obu First");
			return;
		}
		
		if(baseRole_Info_SelectItem.getValue() != null){
			BaseRole baseRole = new BaseRole();
			baseRole.setId(Integer.valueOf(baseRole_Info_SelectItem.getValueAsString()));
			eia.setResponsibleRole(baseRole);
			//Window.alert("setting brole");
		}else{
			Window.alert("Select BaseRole First");
			return;
		}
		
		if(eqState_Info_SelectItem.getValue() != null){
			eia.setState(EiaStateEnum.ACQUIRED);//TODO: eia.setState(EiaStateEnum.getByString(eqStateSelect.getValueAsString()));
			//Window.alert("setting state");
		}else{
			Window.alert("Select EiaState First");
			return;
		}
		
		//adquisition
		/*eia.setPurchaseDate(new Date(buyDate.getValueAsDate().getTime()));
		eia.setReceptionDate(new Date(recepcionDate.getValueAsDate().getTime()));
		eia.setInstallationDate(new Date(instalacionDate.getValueAsDate().getTime()));*/
		
		if(adqProvider_Adq_SelectItem.getValue() != null){
			eia.setProvider(new ExternalProvider(Integer.valueOf(adqProvider_Adq_SelectItem.getValueAsString())));
			//Window.alert("setting provider");
		}else{
			Window.alert("Select Provider First");
			return;
		}
		
		/*eia.setPurchaseOrderNumber(noOrden.getValueAsString());
		eia.setPurchaseInvoiceNumber(noFactura.getValueAsString());*/
		
		//ubication
		if(nameActualArea_Loc_SelectItem.getValue() != null){
			eia.setBuildingLocation(new BuildingLocation(nameActualArea_Loc_SelectItem.getValueAsString()));
			//Window.alert("setting blocation");
			if(sameArea_Loc_CheckboxItem.getValueAsBoolean()){
				eia.setAttendedLocation(new BuildingLocation(nameActualArea_Loc_SelectItem.getValueAsString()));
				//Window.alert("setting attended location");
			}else{
				if(nameAttendedArea_Loc_SelectItem.getValue() != null){
					eia.setAttendedLocation(new BuildingLocation(nameAttendedArea_Loc_SelectItem.getValueAsString()));
					//Window.alert("setting attended location");
				}else{
					Window.alert("Select Attended Building Location First");
					return;
				}
			}
		}else{
			Window.alert("Select Building Location first");
			return;
		}
		
		//costs
		/*eia.setAdquisitionCost(BigDecimal.valueOf(Double.valueOf(costoAdq.getValueAsString())));
		eia.setAdquisitionCostCurrency(CurrencyTypeEnum.getByString(currencyAdq.getValueAsString()));
		eia.setContabilizationDate(new Date(fechaContab.getValueAsDate().getTime()));
		
		eia.setAdquisitionCostLocal(BigDecimal.valueOf(Double.valueOf(costoAdqLoc.getValueAsString())));
		eia.setAdquisitionCostCurrencyLocal(CurrencyTypeEnum.getByString(monedaLocal.getValueAsString()));
		
		eia.setDepreciationMethod(DepreciationMethodEnum.getByString(metodoDepreciacion.getValueAsString()));
		eia.setDateLastDepreciation(new Date(fechaUltDeprec.getValueAsDate().getTime()));
		eia.setActualCost(BigDecimal.valueOf(Double.valueOf(costoAct.getValueAsString())));
		eia.setActualCostCurrency(CurrencyTypeEnum.getByString(monedaCosto.getValueAsString()));
		
		eia.setDepreciationTime(Integer.valueOf(timeDep.getValueAsString()));
		eia.setDepreciationTimePot(TimePeriodEnum.getByString(timeDepSel.getValueAsString()));
		
		eia.setLifeTime(Integer.valueOf(timeVida.getValueAsString()));
		eia.setLifeTimePot(TimePeriodEnum.getByString(timeVidaSel.getValueAsString()));
		
		//guarantees
		eia.setRealWarrantyBegin(new Date(fechaInic.getValueAsDate().getTime()));
		eia.setRealWarrantyPDT(TimePeriodEnum.getByString(timePeriodGarantiaReal.getValueAsString()));
		eia.setRealWarrantySince(WarrantySinceEnum.getByString(garantiaDesde.getValueAsString()));
		eia.setRealWarrantyTime(Integer.valueOf(timeGarantiaReal.getValueAsString()));
		
		eia.setIntWarrantyBegin(new Date(fechaInicInt.getValueAsDate().getTime()));
		eia.setIntWarrantyPDT(TimePeriodEnum.getByString(timePeriodGarantiaInt.getValueAsString()));
		eia.setIntWarrantySince(WarrantySinceEnum.getByString(garantiaIntDesde.getValueAsString()));
		eia.setIntWarrantyTime(Integer.valueOf(timeGarantiaInt.getValueAsString()));
		
		if(isMant.getValueAsBoolean()){
			eia.setMaintenanceLocation(new BuildingLocation(nameMant.getValueAsString()));
			eia.setMaintenanceProvider(new ExternalProvider(Integer.valueOf(providerMant.getValueAsString())));
		}
		
		//itEquipments
		eia.setItType(ItSystemEnum.getByString(typeITSelectItem.getValueAsString()));
		eia.setMachineName(nombreMaquinaTextItem.getValueAsString());
		eia.setIpAddress(dirIPTextItem.getValueAsString());
		eia.setMacAddress(macAddressTextItem.getValueAsString());*/
		
		//Window.alert("Save");
		
		EIAModel.save(eia, new GHAAsyncCallback<Eia>() {
			@Override
			public void onSuccess(Eia result) {
				select(result);
				Window.alert(result.toString());
				//TODO: cancel();
			}
		});
	}

	/**
	 * @param listener
	 */
	public void addEiaSelectionListener(EIASelectionListener listener) {
		listeners.add(listener);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getBottomSectionHeight());
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select(org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
	}

}
