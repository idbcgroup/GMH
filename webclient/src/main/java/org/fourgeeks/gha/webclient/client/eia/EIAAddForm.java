package org.fourgeeks.gha.webclient.client.eia;

import java.math.BigDecimal;
import java.sql.Date;
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
	private GHATextItem codeItem, serialItem, activeIdItem, noOrden, noFactura,
			codeAreaActual, codeAreaAtendida, costoAdq, costoAdqLoc, timeDep,
			timeVida, costoAct, timeGarantiaReal, timeGarantiaInt, codeMant,
			dirIPTextItem, macAddressTextItem, nombreMaquinaTextItem;
	private GHASelectItem depResponsableSelectItem, dirResponsable,
			eqStateSelect, providerSelect, nameAreaActual, nameAreaAtendida,
			currencyAdq, monedaLocal, metodoDepreciacion, timeDepSel,
			timeVidaSel, monedaCosto, garantiaDesde, timePeriodGarantiaReal,
			timePeriodGarantiaInt, garantiaIntDesde,nameMant,providerMant,
			typeITSelectItem;
	private GHATitleTextItem equiposTitle, adqisicionTitle,
			areaActualTitle, areaAtendidaTitle, adqCostTitle, actualCostTitle,
			tiempoDepTitle, tiempoVidaTitle, garantiaRealTitle,
			garantiaIntTitle, mantenimientoTitle, itTitleTextItem;
	private GHADateItem buyDate, recepcionDate, instalacionDate, fechaContab,
			fechaUltDeprec, fechaInic, fechaInicInt;
	private GHACheckboxItem mismaArea, isMant;
	private GHASectionForm sectionForm;
	private EiaType eiaType;
	{
		// Global
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EIASelectionListener>();
		// Information Form Items
		equiposTitle = new GHATitleTextItem("Información:");
		codeItem = new GHATextItem("Código",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		serialItem = new GHATextItem("Serial",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		activeIdItem = new GHATextItem("Id. Activo Fijo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		depResponsableSelectItem = new GHASelectItem("Departamento Responsable",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		dirResponsable = new GHASelectItem("Rol Responsable",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		eqStateSelect = new GHASelectItem("Estado Equipo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		// Adquisicion Form Items
		adqisicionTitle = new GHATitleTextItem("Adquisición:");
		buyDate = new GHADateItem("Fecha de Compra",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		providerSelect = new GHASelectItem("Proveedor",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		noOrden = new GHATextItem("No. Orden Compra",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		noFactura = new GHATextItem("No. Factura",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		recepcionDate = new GHADateItem("Recepción",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		instalacionDate = new GHADateItem("Instalación",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		// Ubicacion Form Items
		areaActualTitle = new GHATitleTextItem("Area Actual:");
		areaAtendidaTitle = new GHATitleTextItem("Area Atendida:");
		codeAreaActual = new GHATextItem("Código",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);
		nameAreaActual = new GHASelectItem("Nombre",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		codeAreaAtendida = new GHATextItem("Código",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);
		nameAreaAtendida = new GHASelectItem("Nombre",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		mismaArea = new GHACheckboxItem("Atiende a la misma area donde esta Ubicado");
		mismaArea.setColSpan(2);
		// Costos Form Items
		adqCostTitle = new GHATitleTextItem("Costo Adquisición:");
		actualCostTitle = new GHATitleTextItem("Costo Actual:");
		tiempoDepTitle = new GHATitleTextItem("Tiempo de Depreciación:");
		tiempoVidaTitle = new GHATitleTextItem("Tiempo de Vida:");
		costoAdq = new GHATextItem("Costo de Adq. del equipo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		currencyAdq = new GHASelectItem("Moneda",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		fechaContab = new GHADateItem("Fecha de Contabilización",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		costoAdqLoc = new GHATextItem("Costo de Adq. Local",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		monedaLocal = new GHASelectItem("Moneda Local",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		metodoDepreciacion = new GHASelectItem("Metodo Depreciación",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		timeDep = new GHATextItem("Duración",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		timeDepSel = new GHASelectItem("Periodo de Tiempo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		timeVida = new GHATextItem("Duración",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		timeVidaSel = new GHASelectItem("Periodo de Tiempo",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		costoAct = new GHATextItem("Costo Actual en libros",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		monedaCosto = new GHASelectItem("Moneda",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		fechaUltDeprec = new GHADateItem("Fecha Ult. Depreciación",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		// Garantias Form Items
		garantiaRealTitle = new GHATitleTextItem("Garantía Real:");
		garantiaIntTitle = new GHATitleTextItem("Garantía del Intermediario:");
		mantenimientoTitle = new GHATitleTextItem("Mantenimiento:");
		garantiaDesde = new GHASelectItem("Desde",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		timeGarantiaReal = new GHATextItem("Duración",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		timePeriodGarantiaReal = new GHASelectItem("Periodo de Tiempo",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		fechaInic = new GHADateItem("Fecha Inicio",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		garantiaIntDesde = new GHASelectItem("Desde",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		timeGarantiaInt = new GHATextItem("Duración",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		timePeriodGarantiaInt = new GHASelectItem("Periodo de Tiempo",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		fechaInicInt = new GHADateItem("Fecha Inicio",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		isMant = new GHACheckboxItem("Equipo en Mantenimiento");
		codeMant = new GHATextItem("Cod. Ubicación Mant.",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		nameMant = new GHASelectItem("Nombre Ubicación Mant.",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		providerMant = new GHASelectItem("Proveedor de Mant.",GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		// Equipos IT Form Items
		itTitleTextItem = new GHATitleTextItem("EQUIPOS IT (Sistemas):");
		typeITSelectItem = new GHASelectItem("Tipo Equipo IT",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		nombreMaquinaTextItem = new GHATextItem("Nombre Máquina",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		dirIPTextItem = new GHATextItem("Direccion IP",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		macAddressTextItem = new GHATextItem("MAC Address",GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
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
				"../resources/icons/save.png"), new GHAButton(
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

		equipoForm.setItems(equiposTitle, new GHASpacerItem(2), 
							codeItem,serialItem, activeIdItem,
							depResponsableSelectItem, dirResponsable,eqStateSelect);
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

		adquisicionForm.setItems(adqisicionTitle, new GHASpacerItem(2),
								 buyDate, recepcionDate, instalacionDate, providerSelect,
								 noOrden, noFactura);
		return adquisicionForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getUbicacionForm() {
		DynamicForm areaForm = new DynamicForm();
		areaForm.setTitleOrientation(TitleOrientation.TOP);
		areaForm.setNumCols(2);

		areaForm.setItems(areaActualTitle, new GHASpacerItem(), 
						  nameAreaActual, codeAreaActual, 
						  areaAtendidaTitle, new GHASpacerItem(), 
						  mismaArea, 
						  nameAreaAtendida, codeAreaAtendida);
		return areaForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getCostosTab() {
		DynamicForm costosForm = new DynamicForm();
		costosForm.setTitleOrientation(TitleOrientation.TOP);
		costosForm.setNumCols(3);

		costosForm.setItems(adqCostTitle, new GHASpacerItem(2), 
							costoAdq, currencyAdq, fechaContab, 
							costoAdqLoc, monedaLocal,new GHASpacerItem(),
							actualCostTitle, new GHASpacerItem(2),
							metodoDepreciacion, fechaUltDeprec, new GHASpacerItem(), 
							costoAct, monedaCosto,new GHASpacerItem(),
							tiempoDepTitle,  new GHASpacerItem(2),
							timeDep, timeDepSel,new GHASpacerItem(),
							tiempoVidaTitle, new GHASpacerItem(2),							
							timeVida, timeVidaSel);

		return costosForm;
	}
	
	/**
	 * @return
	 */
	private DynamicForm getGarantiasMantForm() {
		DynamicForm garantiasMamtenimientoForm = new DynamicForm();
		garantiasMamtenimientoForm.setTitleOrientation(TitleOrientation.TOP);
		garantiasMamtenimientoForm.setNumCols(3);

		garantiasMamtenimientoForm.setItems(garantiaRealTitle,new GHASpacerItem(2), 
											garantiaDesde, fechaInic, new GHASpacerItem(),
											timeGarantiaReal,timePeriodGarantiaReal, new GHASpacerItem(),
											garantiaIntTitle,new GHASpacerItem(2), 
											garantiaIntDesde, fechaInicInt, new GHASpacerItem(),
											timeGarantiaInt,timePeriodGarantiaInt, new GHASpacerItem(),
											new GHASpacerItem(3),
											mantenimientoTitle, new GHASpacerItem(2), 
											isMant, new GHASpacerItem(2),
											nameMant, codeMant, providerMant);

		return garantiasMamtenimientoForm;
	}
	
	private DynamicForm getEquiposIT() {
		DynamicForm equiposITForm = new DynamicForm();
		equiposITForm.setTitleOrientation(TitleOrientation.TOP);
		equiposITForm.setNumCols(2);

		equiposITForm.setItems(itTitleTextItem, new GHASpacerItem(),
							   typeITSelectItem, nombreMaquinaTextItem, 
							   dirIPTextItem,  macAddressTextItem);

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
				depResponsableSelectItem.setValueMap(valueMap);
			}
		});

		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<BaseRole>>() {
			@Override
			public void onSuccess(List<BaseRole> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (BaseRole entity : result)
					valueMap.put(entity.getId() + "", entity.getName() + "");
				dirResponsable.setValueMap(valueMap);
			}
		});

		eqStateSelect.setValueMap(EiaStateEnum.toValueMap());
		eqStateSelect.setValue(EiaStateEnum.CREATED.name());
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
						providerSelect.setValueMap(valueMap);
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
							valueMapActual.put(entity.getId() + "",
									entity.getName());
							valueMapAtendida.put(entity.getId() + "",
									entity.getName());
						}

						nameAreaActual.setValueMap(valueMapActual);
						nameAreaAtendida.setValueMap(valueMapAtendida);
					}
				});
	}

	private void fillCostsSelects() {
		currencyAdq.setValueMap(CurrencyTypeEnum.toValueMap());
		currencyAdq.setValue(CurrencyTypeEnum.BS.name());
		monedaLocal.setValueMap(CurrencyTypeEnum.toValueMap());
		monedaLocal.setValue(CurrencyTypeEnum.BS.name());

		metodoDepreciacion.setValueMap(DepreciationMethodEnum.toValueMap());
		metodoDepreciacion.setValue(DepreciationMethodEnum.METHOD_1.name());

		timeDepSel.setValueMap(TimePeriodEnum.toValueMap());
		timeDepSel.setValue(TimePeriodEnum.HOURS.name());
		timeVidaSel.setValueMap(TimePeriodEnum.toValueMap());
		timeVidaSel.setValue(TimePeriodEnum.HOURS.name());

		monedaCosto.setValueMap(CurrencyTypeEnum.toValueMap());
		monedaCosto.setValue(CurrencyTypeEnum.BS.name());
	}

	private void fillWarrantySelects() {
		garantiaDesde.setValueMap(WarrantySinceEnum.toValueMap());
		garantiaDesde.setValue(WarrantySinceEnum.PURCHASE.name());
		garantiaIntDesde.setValueMap(WarrantySinceEnum.toValueMap());
		garantiaIntDesde.setValue(WarrantySinceEnum.PURCHASE.name());
		
		timePeriodGarantiaReal.setValueMap(TimePeriodEnum.toValueMap());
		timePeriodGarantiaReal.setValue(TimePeriodEnum.HOURS.name());
		timePeriodGarantiaInt.setValueMap(TimePeriodEnum.toValueMap());
		timePeriodGarantiaInt.setValue(TimePeriodEnum.HOURS.name());
		
		
		GHACache.INSTANCE
		.getBuildingLocations(new GHAAsyncCallback<List<BuildingLocation>>() {
			@Override
			public void onSuccess(List<BuildingLocation> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (BuildingLocation entity : result)
					valueMap.put(entity.getId() + "",entity.getName());
				nameMant.setValueMap(valueMap);
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
				providerMant.setValueMap(valueMap);
			}
		});
	}

	private void fillITEquipmentsSelects() {
		typeITSelectItem.setValueMap(ItSystemEnum.toValueMap());
		typeITSelectItem.setValue(ItSystemEnum.COMPUTER.name());
	}

	/////Funcionalities
	private void buildingLocFuncionalities(){
		nameAreaActual.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				//TODO: 
//				codeAreaActual.setValue(value);
			}
		});
		
		nameAreaAtendida.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				//TODO: 
//				codeAreaActual.setValue(value);
			}
		});
		
		mismaArea.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				if(event.getValue().equals(true)){
					nameAreaAtendida.setDisabled(true);
					nameAreaAtendida.setValue(nameAreaActual.getValue());
					codeAreaAtendida.setValue(codeAreaActual.getValue());
				}else{
					nameAreaAtendida.setDisabled(false);
					nameAreaAtendida.clearValue();
					codeAreaAtendida.clearValue();
				}
			}
		});
	}
	
	private void warrantyFunctionalities(){
		isMant.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				if(event.getValue().equals(true)){
					nameMant.setDisabled(false);
					providerMant.setDisabled(false);
				}else{
					nameMant.setDisabled(true);
					providerMant.setDisabled(true);
					nameMant.clearValue();
					codeMant.clearValue();
					providerMant.clearValue();
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
		//TODO: validar eiatype seleccionado antes de todo
		
		
		final Eia eia = new Eia();
		
		eia.setEiaType(this.eiaType);
		
		//basic information
		eia.setCode(codeItem.getValueAsString());
		eia.setSerialNumber(serialItem.getValueAsString());
		eia.setFixedAssetIdentifier(activeIdItem.getValueAsString());
		if(depResponsableSelectItem.getValue() != null){
			Obu obu = new Obu();
			obu.setId(Integer.valueOf(depResponsableSelectItem.getValueAsString()));
			eia.setObu(obu);
		}
		
		if(dirResponsable.getValue() != null){
			BaseRole baseRole = new BaseRole();
			baseRole.setId(Integer.valueOf(dirResponsable.getValueAsString()));
			eia.setResponsibleRole(baseRole);
		}
		eia.setState(EiaStateEnum.getByString(eqStateSelect.getValueAsString()));
		
		//adquisition
		eia.setPurchaseDate(new Date(buyDate.getValueAsDate().getTime()));
		eia.setReceptionDate(new Date(recepcionDate.getValueAsDate().getTime()));
		eia.setInstallationDate(new Date(instalacionDate.getValueAsDate().getTime()));
		eia.setProvider(new ExternalProvider(Integer.valueOf(providerSelect.getValueAsString())));
		eia.setPurchaseOrderNumber(noOrden.getValueAsString());
		eia.setPurchaseInvoiceNumber(noFactura.getValueAsString());
		
		//ubication
		eia.setBuildingLocation(new BuildingLocation(Integer.valueOf(nameAreaActual.getValueAsString())));
		if(mismaArea.getValueAsBoolean()){
			eia.setAttendedLocation(new BuildingLocation(Integer.valueOf(nameAreaActual.getValueAsString())));
		}else{
			eia.setAttendedLocation(new BuildingLocation(Integer.valueOf(nameAreaAtendida.getValueAsString())));
		}
		
		//costs
		eia.setAdquisitionCost(BigDecimal.valueOf(Double.valueOf(costoAdq.getValueAsString())));
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
		//itEquipments
		
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
