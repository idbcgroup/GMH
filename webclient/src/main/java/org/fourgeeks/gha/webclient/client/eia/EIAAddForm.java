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
public class EIAAddForm extends GHASlideInWindow implements ResizeHandler,
		EIATypeSelectionListener {

	private List<EIASelectionListener> listeners;
	private GHATextItem codeTextItem, serialTextItem, fixedAssetIdTextItem,
			purchaseOrderNumTextItem, purchaseInvoiceNumTextItem,
			buildingLocationCodeTextItem, attendedLocationCodeTextItem,
			adquisitionCostTextItem, adquisitionCostLocalTextItem,
			depreciationTimeTextItem, lifeTimeTextItem, actualCostTextItem,
			realWarrantyTimeTextItem, intWarrantyTimeTextItem,
			codeMant_WarrMant_TextItem, ipAddresTextItem, macAddressTextItem,
			machineNameTextItem;
	private GHASelectItem obuSelectItem, baseRoleSelectItem, stateSelectItem,
			providerSelectItem, buildingLocationSelectItem,
			attendedLocationSelectItem, adquisitionCostCurrencySelectItem,
			adquisitionCostCurrencyLocalSelectItem,
			depreciationMethodSelectItem, depreciationTimePotSelectItem,
			lifeTimePotSelectItem, actualCostCurrencySelectItem,
			realWarrantySinceSelectItem, realWarrantyPotSelectItem,
			intWarrantyPotSelectItem, intWarrantySinceSelectItem,
			maintenanceLocationSelectItem, maintenanceProviderSelectItem,
			itTypeSelectItem;
	private GHATitleTextItem information_TitleItem, adqisition_TitleItem,
			actualArea_TitleItem, attendedArea_TitleItem, adqCost_TitleItem,
			actualCost_TitleItem, depTime_TitleItem, lifeTime_TitleItem,
			realWarranty_TitleItem, intermedWarranty_TitleItem,
			maintenance_TitleItem, it_TitleItem;
	private GHADateItem purchaseDateItem, receptionDateItem,
			installationDateItem, contabilizationDateItem,
			lastDepreciationDate, realWarrantyBeginDate, intWarrantyBeginDate;
	private GHACheckboxItem sameLocationAttendedItem, isInMaintenanceItem;
	private GHASectionForm sectionForm;
	private EiaType eiaType;
	{
		// Global
		sectionForm = new GHASectionForm();
		listeners = new ArrayList<EIASelectionListener>();

		// Information Form Items
		information_TitleItem = new GHATitleTextItem("Información:");
		codeTextItem = new GHATextItem("Código",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		serialTextItem = new GHATextItem("Serial",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		fixedAssetIdTextItem = new GHATextItem("Id. Activo Fijo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		obuSelectItem = new GHASelectItem("Departamento Responsable",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		baseRoleSelectItem = new GHASelectItem("Rol Responsable",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		stateSelectItem = new GHASelectItem("Estado Equipo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		// Adquisicion Form Itemsxt
		adqisition_TitleItem = new GHATitleTextItem("Adquisición:");
		purchaseDateItem = new GHADateItem("Fecha de Compra",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		providerSelectItem = new GHASelectItem("Proveedor",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		purchaseOrderNumTextItem = new GHATextItem("No. Orden Compra",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		purchaseInvoiceNumTextItem = new GHATextItem("No. Factura",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		receptionDateItem = new GHADateItem("Recepción",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		installationDateItem = new GHADateItem("Instalación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		// Ubicacion Form Items
		actualArea_TitleItem = new GHATitleTextItem("Area Actual:");
		attendedArea_TitleItem = new GHATitleTextItem("Area Atendida:");
		buildingLocationCodeTextItem = new GHATextItem("Código",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);
		buildingLocationSelectItem = new GHASelectItem("Nombre",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		attendedLocationCodeTextItem = new GHATextItem("Código",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE, false);
		attendedLocationSelectItem = new GHASelectItem("Nombre",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		sameLocationAttendedItem = new GHACheckboxItem(
				"Atiende a la misma area donde esta Ubicado");
		sameLocationAttendedItem.setColSpan(2);

		// Costos Form Items
		adqCost_TitleItem = new GHATitleTextItem("Costo Adquisición:");
		actualCost_TitleItem = new GHATitleTextItem("Costo Actual:");
		depTime_TitleItem = new GHATitleTextItem("Tiempo de Depreciación:");
		lifeTime_TitleItem = new GHATitleTextItem("Tiempo de Vida:");
		adquisitionCostTextItem = new GHATextItem("Costo de Adq. del equipo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		adquisitionCostCurrencySelectItem = new GHASelectItem("Moneda",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		contabilizationDateItem = new GHADateItem("Fecha de Contabilización",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		adquisitionCostLocalTextItem = new GHATextItem("Costo de Adq. Local",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		adquisitionCostCurrencyLocalSelectItem = new GHASelectItem(
				"Moneda Local", GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		depreciationMethodSelectItem = new GHASelectItem("Metodo Depreciación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		depreciationTimeTextItem = new GHATextItem("Duración",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		depreciationTimePotSelectItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lastDepreciationDate = new GHADateItem("Fecha Ult. Depreciación",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lifeTimeTextItem = new GHATextItem("Duración",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		lifeTimePotSelectItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		actualCostTextItem = new GHATextItem("Costo Actual en libros",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		actualCostCurrencySelectItem = new GHASelectItem("Moneda",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);

		// Garantias Form Items
		realWarranty_TitleItem = new GHATitleTextItem("Garantía Real:");
		intermedWarranty_TitleItem = new GHATitleTextItem(
				"Garantía del Intermediario:");
		maintenance_TitleItem = new GHATitleTextItem("Mantenimiento:");
		realWarrantySinceSelectItem = new GHASelectItem("Desde",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		realWarrantyTimeTextItem = new GHATextItem("Duración",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		realWarrantyPotSelectItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		realWarrantyBeginDate = new GHADateItem("Fecha Inicio",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intWarrantySinceSelectItem = new GHASelectItem("Desde",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intWarrantyTimeTextItem = new GHATextItem("Duración",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intWarrantyPotSelectItem = new GHASelectItem("Periodo de Tiempo",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		intWarrantyBeginDate = new GHADateItem("Fecha Inicio",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		isInMaintenanceItem = new GHACheckboxItem("Equipo en Mantenimiento");
		codeMant_WarrMant_TextItem = new GHATextItem("Cod. Ubicación Mant.",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		maintenanceLocationSelectItem = new GHASelectItem(
				"Nombre Ubicación Mant.",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		maintenanceProviderSelectItem = new GHASelectItem("Proveedor de Mant.",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		// Equipos IT Form Items
		it_TitleItem = new GHATitleTextItem("EQUIPOS IT (Sistemas):");
		itTypeSelectItem = new GHASelectItem("Tipo Equipo IT",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		machineNameTextItem = new GHATextItem("Nombre Máquina",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		ipAddresTextItem = new GHATextItem("Direccion IP",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
		macAddressTextItem = new GHATextItem("MAC Address",
				GHAUiHelper.THREE_COLUMN_FORMITEM_SIZE);
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
				}), new GHAButton("../resources/icons/set.png"), new GHAButton(
				"../resources/icons/cancel.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						cancel();
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

		// Funcionalities
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
				codeTextItem, serialTextItem, fixedAssetIdTextItem,
				obuSelectItem, baseRoleSelectItem, stateSelectItem);
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
				purchaseDateItem, receptionDateItem, installationDateItem,
				providerSelectItem, purchaseOrderNumTextItem,
				purchaseInvoiceNumTextItem);
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
				buildingLocationSelectItem, buildingLocationCodeTextItem,
				attendedArea_TitleItem, new GHASpacerItem(),
				sameLocationAttendedItem, attendedLocationSelectItem,
				attendedLocationCodeTextItem);
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
				adquisitionCostTextItem, adquisitionCostCurrencySelectItem,
				contabilizationDateItem, adquisitionCostLocalTextItem,
				adquisitionCostCurrencyLocalSelectItem, new GHASpacerItem(),
				actualCost_TitleItem, new GHASpacerItem(2),
				depreciationMethodSelectItem, lastDepreciationDate,
				new GHASpacerItem(), actualCostTextItem,
				actualCostCurrencySelectItem, new GHASpacerItem(),
				depTime_TitleItem, new GHASpacerItem(2),
				depreciationTimeTextItem, depreciationTimePotSelectItem,
				new GHASpacerItem(), lifeTime_TitleItem, new GHASpacerItem(2),
				lifeTimeTextItem, lifeTimePotSelectItem);

		return costosForm;
	}

	/**
	 * @return
	 */
	private DynamicForm getGarantiasMantForm() {
		DynamicForm garantiasMamtenimientoForm = new DynamicForm();
		garantiasMamtenimientoForm.setTitleOrientation(TitleOrientation.TOP);
		garantiasMamtenimientoForm.setNumCols(3);

		garantiasMamtenimientoForm.setItems(realWarranty_TitleItem,
				new GHASpacerItem(2), realWarrantySinceSelectItem,
				realWarrantyBeginDate, new GHASpacerItem(),
				realWarrantyTimeTextItem, realWarrantyPotSelectItem,
				new GHASpacerItem(), intermedWarranty_TitleItem,
				new GHASpacerItem(2), intWarrantySinceSelectItem,
				intWarrantyBeginDate, new GHASpacerItem(),
				intWarrantyTimeTextItem, intWarrantyPotSelectItem,
				new GHASpacerItem(), new GHASpacerItem(3),
				maintenance_TitleItem, new GHASpacerItem(2),
				isInMaintenanceItem, new GHASpacerItem(2),
				maintenanceLocationSelectItem, codeMant_WarrMant_TextItem,
				maintenanceProviderSelectItem);

		return garantiasMamtenimientoForm;
	}

	private DynamicForm getEquiposIT() {
		DynamicForm equiposITForm = new DynamicForm();
		equiposITForm.setTitleOrientation(TitleOrientation.TOP);
		equiposITForm.setNumCols(2);

		equiposITForm.setItems(it_TitleItem, new GHASpacerItem(),
				itTypeSelectItem, machineNameTextItem, ipAddresTextItem,
				macAddressTextItem);

		return equiposITForm;
	}

	// //Fillers
	private void fillInformationSelects() {
		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {
			@Override
			public void onSuccess(List<Obu> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (Obu entity : result) {
					valueMap.put(entity.getId() + "", entity.getName() + "");
				}
				obuSelectItem.setValueMap(valueMap);
			}
		});

		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<BaseRole>>() {
			@Override
			public void onSuccess(List<BaseRole> result) {
				LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (BaseRole entity : result)
					valueMap.put(entity.getId() + "", entity.getName() + "");
				baseRoleSelectItem.setValueMap(valueMap);
			}
		});

		stateSelectItem.setValueMap(EiaStateEnum.toValueMap());
		stateSelectItem.setValue(EiaStateEnum.CREATED.name());
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
						providerSelectItem.setValueMap(valueMap);
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

						buildingLocationSelectItem.setValueMap(valueMapActual);
						attendedLocationSelectItem
								.setValueMap(valueMapAtendida);
					}
				});
	}

	private void fillCostsSelects() {
		adquisitionCostCurrencySelectItem.setValueMap(CurrencyTypeEnum
				.toValueMap());
		adquisitionCostCurrencySelectItem.setValue(CurrencyTypeEnum.BS.name());
		adquisitionCostCurrencyLocalSelectItem.setValueMap(CurrencyTypeEnum
				.toValueMap());
		adquisitionCostCurrencyLocalSelectItem.setValue(CurrencyTypeEnum.BS
				.name());

		depreciationMethodSelectItem.setValueMap(DepreciationMethodEnum
				.toValueMap());
		depreciationMethodSelectItem.setValue(DepreciationMethodEnum.METHOD_1
				.name());

		depreciationTimePotSelectItem.setValueMap(TimePeriodEnum.toValueMap());
		depreciationTimePotSelectItem.setValue(TimePeriodEnum.HOURS.name());
		lifeTimePotSelectItem.setValueMap(TimePeriodEnum.toValueMap());
		lifeTimePotSelectItem.setValue(TimePeriodEnum.HOURS.name());

		actualCostCurrencySelectItem.setValueMap(CurrencyTypeEnum.toValueMap());
		actualCostCurrencySelectItem.setValue(CurrencyTypeEnum.BS.name());
	}

	private void fillWarrantySelects() {
		realWarrantySinceSelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		realWarrantySinceSelectItem.setValue(WarrantySinceEnum.PURCHASE.name());
		intWarrantySinceSelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		intWarrantySinceSelectItem.setValue(WarrantySinceEnum.PURCHASE.name());

		realWarrantyPotSelectItem.setValueMap(TimePeriodEnum.toValueMap());
		realWarrantyPotSelectItem.setValue(TimePeriodEnum.HOURS.name());
		intWarrantyPotSelectItem.setValueMap(TimePeriodEnum.toValueMap());
		intWarrantyPotSelectItem.setValue(TimePeriodEnum.HOURS.name());

		GHACache.INSTANCE
				.getBuildingLocations(new GHAAsyncCallback<List<BuildingLocation>>() {
					@Override
					public void onSuccess(List<BuildingLocation> result) {
						LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
						for (BuildingLocation entity : result)
							valueMap.put(entity.getCode() + "",
									entity.getName());
						maintenanceLocationSelectItem.setValueMap(valueMap);
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
						maintenanceProviderSelectItem.setValueMap(valueMap);
					}
				});
	}

	private void fillITEquipmentsSelects() {
		itTypeSelectItem.setValueMap(ItSystemEnum.toValueMap());
		itTypeSelectItem.setValue(ItSystemEnum.COMPUTER.name());
	}

	// ///Funcionalities
	private void buildingLocFuncionalities() {
		buildingLocationSelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// TODO:
				buildingLocationCodeTextItem.setValue(event.getValue());
				if (sameLocationAttendedItem.getValueAsBoolean()) {
					attendedLocationSelectItem.setValue(event.getValue());
					attendedLocationCodeTextItem.setValue(event.getValue());
				}
			}
		});

		attendedLocationSelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// TODO:
				attendedLocationCodeTextItem.setValue(event.getValue());
			}
		});

		sameLocationAttendedItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				if (event.getValue().equals(true)) {
					attendedLocationSelectItem.setDisabled(true);
					attendedLocationSelectItem
							.setValue(buildingLocationSelectItem.getValue());
					attendedLocationCodeTextItem
							.setValue(buildingLocationCodeTextItem.getValue());
				} else {
					attendedLocationSelectItem.setDisabled(false);
					attendedLocationSelectItem.clearValue();
					attendedLocationCodeTextItem.clearValue();
				}
			}
		});
	}

	private void warrantyFunctionalities() {
		isInMaintenanceItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// TODO Auto-generated method stub
				if (event.getValue().equals(true)) {
					maintenanceLocationSelectItem.setDisabled(false);
					maintenanceProviderSelectItem.setDisabled(false);
				} else {
					maintenanceLocationSelectItem.setDisabled(true);
					maintenanceProviderSelectItem.setDisabled(true);
					maintenanceLocationSelectItem.clearValue();
					codeMant_WarrMant_TextItem.clearValue();
					maintenanceProviderSelectItem.clearValue();
				}
			}
		});

		maintenanceLocationSelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				codeMant_WarrMant_TextItem.setValue(event.getValue());
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
	private void save() {
		final Eia eia = new Eia();

		eia.setEiaType(this.eiaType);
		if (this.eiaType == null) {
			//TODO: Use gha info Window.alert("Select EiaType First");
			return;
		}

		// basic information
		eia.setCode(codeTextItem.getValueAsString());
		eia.setSerialNumber(serialTextItem.getValueAsString());
		eia.setFixedAssetIdentifier(fixedAssetIdTextItem.getValueAsString());
		if (obuSelectItem.getValue() != null) {
			Obu obu = new Obu();
			obu.setId(Integer.valueOf(obuSelectItem.getValueAsString()));
			eia.setObu(obu);
		} else {
			//TODO Use gha info Window.alert("Select Obu First");
			return;
		}

		if (baseRoleSelectItem.getValue() != null) {
			BaseRole baseRole = new BaseRole();
			baseRole.setId(Integer.valueOf(baseRoleSelectItem
					.getValueAsString()));
			eia.setResponsibleRole(baseRole);
		} else {
			//TODO Use gha info Window.alert("Select BaseRole First");
			return;
		}

		if (stateSelectItem.getValue() != null) {
			eia.setState(EiaStateEnum.valueOf(stateSelectItem
					.getValueAsString()));
		} else {
			//TODO Use gha info Window.alert("Select EiaState First");
			return;
		}

		// adquisition
		if (purchaseDateItem.getValue() != null)
			eia.setPurchaseDate(new Date(purchaseDateItem.getValueAsDate()
					.getTime()));

		if (receptionDateItem.getValue() != null)
			eia.setReceptionDate(new Date(receptionDateItem.getValueAsDate()
					.getTime()));

		if (installationDateItem.getValue() != null)
			eia.setInstallationDate(new Date(installationDateItem
					.getValueAsDate().getTime()));

		if (providerSelectItem.getValue() != null) {
			eia.setProvider(new ExternalProvider(Integer
					.valueOf(providerSelectItem.getValueAsString())));
		} else {
			//TODO Use gha info Window.alert("Select Provider First");
			return;
		}

		eia.setPurchaseOrderNumber(purchaseOrderNumTextItem.getValueAsString());
		eia.setPurchaseInvoiceNumber(purchaseInvoiceNumTextItem
				.getValueAsString());

		// ubication
		if (buildingLocationSelectItem.getValue() != null) {
			eia.setBuildingLocation(new BuildingLocation(
					buildingLocationSelectItem.getValueAsString()));
			if (sameLocationAttendedItem.getValueAsBoolean()) {
				eia.setAttendedLocation(new BuildingLocation(
						buildingLocationSelectItem.getValueAsString()));
			} else {
				if (attendedLocationSelectItem.getValue() != null) {
					eia.setAttendedLocation(new BuildingLocation(
							attendedLocationSelectItem.getValueAsString()));
				} else {
					//TODO Use gha info Window.alert("Select Attended Building Location First");
					return;
				}
			}
		} else {
			//TODO Use gha info Window.alert("Select Building Location first");
			return;
		}

		// costs
		if (adquisitionCostTextItem.getValue() != null)
			eia.setAdquisitionCost(BigDecimal.valueOf(Double
					.valueOf(adquisitionCostTextItem.getValueAsString())));

		eia.setAdquisitionCostCurrency(CurrencyTypeEnum
				.valueOf(adquisitionCostCurrencySelectItem.getValueAsString()));

		if (contabilizationDateItem.getValue() != null)
			eia.setContabilizationDate(new Date(contabilizationDateItem
					.getValueAsDate().getTime()));

		if (adquisitionCostLocalTextItem.getValue() != null)
			eia.setAdquisitionCostLocal(BigDecimal.valueOf(Double
					.valueOf(adquisitionCostLocalTextItem.getValueAsString())));

		eia.setAdquisitionCostCurrencyLocal(CurrencyTypeEnum
				.valueOf(adquisitionCostCurrencyLocalSelectItem
						.getValueAsString()));
		eia.setDepreciationMethod(DepreciationMethodEnum
				.valueOf(depreciationMethodSelectItem.getValueAsString()));

		if (lastDepreciationDate.getValue() != null)
			eia.setDateLastDepreciation(new Date(lastDepreciationDate
					.getValueAsDate().getTime()));

		if (actualCostTextItem.getValue() != null)
			eia.setActualCost(BigDecimal.valueOf(Double
					.valueOf(actualCostTextItem.getValueAsString())));
		eia.setActualCostCurrency(CurrencyTypeEnum
				.valueOf(actualCostCurrencySelectItem.getValueAsString()));

		if (depreciationTimeTextItem.getValue() != null)
			eia.setDepreciationTime(Integer.valueOf(depreciationTimeTextItem
					.getValueAsString()));
		eia.setDepreciationTimePoT(TimePeriodEnum
				.valueOf(depreciationTimePotSelectItem.getValueAsString()));

		if (lifeTimeTextItem.getValue() != null)
			eia.setLifeTime(Integer.valueOf(lifeTimeTextItem.getValueAsString()));
		eia.setLifeTimePoT(TimePeriodEnum.valueOf(lifeTimePotSelectItem
				.getValueAsString()));

		// guarantees
		if (realWarrantyBeginDate.getValue() != null)
			eia.setRealWarrantyBegin(new Date(realWarrantyBeginDate
					.getValueAsDate().getTime()));

		eia.setRealWarrantyPoT(TimePeriodEnum.valueOf(realWarrantyPotSelectItem
				.getValueAsString()));
		eia.setRealWarrantySince(WarrantySinceEnum
				.valueOf(realWarrantySinceSelectItem.getValueAsString()));

		if (realWarrantyTimeTextItem.getValue() != null)
			eia.setRealWarrantyTime(Integer.valueOf(realWarrantyTimeTextItem
					.getValueAsString()));

		if (intWarrantyBeginDate.getValue() != null)
			eia.setIntWarrantyBegin(new Date(intWarrantyBeginDate
					.getValueAsDate().getTime()));

		eia.setIntWarrantyPoT(TimePeriodEnum.valueOf(intWarrantyPotSelectItem
				.getValueAsString()));
		eia.setIntWarrantySince(WarrantySinceEnum
				.valueOf(intWarrantySinceSelectItem.getValueAsString()));

		if (intWarrantyTimeTextItem.getValue() != null)
			eia.setIntWarrantyTime(Integer.valueOf(intWarrantyTimeTextItem
					.getValueAsString()));

		if (isInMaintenanceItem.getValueAsBoolean()) {
			eia.setMaintenanceLocation(new BuildingLocation(
					maintenanceLocationSelectItem.getValueAsString()));
			eia.setMaintenanceProvider(new ExternalProvider(Integer
					.valueOf(maintenanceProviderSelectItem.getValueAsString())));
		}

		// itEquipments
		eia.setItType(ItSystemEnum.valueOf(itTypeSelectItem.getValueAsString()));
		eia.setMachineName(machineNameTextItem.getValueAsString());
		eia.setIpAddress(ipAddresTextItem.getValueAsString());
		eia.setMacAddress(macAddressTextItem.getValueAsString());
		EIAModel.save(eia, new GHAAsyncCallback<Eia>() {
			@Override
			public void onSuccess(Eia result) {
				select(result);
				cancel();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	/**
	 * 
	 */
	private void cancel() {

		// clean text fields
		codeTextItem.setValue("");
		serialTextItem.setValue("");
		fixedAssetIdTextItem.setValue("");
		purchaseOrderNumTextItem.setValue("");
		purchaseInvoiceNumTextItem.setValue("");
		buildingLocationCodeTextItem.setValue("");
		attendedLocationCodeTextItem.setValue("");
		adquisitionCostTextItem.setValue("");
		adquisitionCostLocalTextItem.setValue("");
		depreciationTimeTextItem.setValue("");
		lifeTimeTextItem.setValue("");
		actualCostTextItem.setValue("");
		realWarrantyTimeTextItem.setValue("");
		intWarrantyTimeTextItem.setValue("");
		codeMant_WarrMant_TextItem.setValue("");
		ipAddresTextItem.setValue("");
		macAddressTextItem.setValue("");
		machineNameTextItem.setValue("");

		// clean select fields
		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
		stateSelectItem.clearValue();
		providerSelectItem.clearValue();
		buildingLocationSelectItem.clearValue();
		attendedLocationSelectItem.clearValue();
		adquisitionCostCurrencySelectItem.clearValue();
		adquisitionCostCurrencyLocalSelectItem.clearValue();
		depreciationMethodSelectItem.clearValue();
		depreciationTimePotSelectItem.clearValue();
		lifeTimePotSelectItem.clearValue();
		actualCostCurrencySelectItem.clearValue();
		realWarrantySinceSelectItem.clearValue();
		realWarrantyPotSelectItem.clearValue();
		intWarrantyPotSelectItem.clearValue();
		intWarrantySinceSelectItem.clearValue();
		maintenanceLocationSelectItem.clearValue();
		maintenanceProviderSelectItem.clearValue();
		itTypeSelectItem.clearValue();

		purchaseDateItem.setValue("");
		receptionDateItem.setValue("");
		installationDateItem.setValue("");
		contabilizationDateItem.setValue("");
		lastDepreciationDate.setValue("");
		realWarrantyBeginDate.setValue("");
		intWarrantyBeginDate.setValue("");
		
		sameLocationAttendedItem.setValue(false);
		isInMaintenanceItem.setValue(false);

		EIAAddForm.this.animateHide(AnimationEffect.FLY);
		sectionForm.deactivate();
	}

}
