package org.fourgeeks.gha.webclient.client.eia;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.CurrencyTypeEnum;
import org.fourgeeks.gha.domain.enu.DepreciationMethodEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHACache;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitletextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABspSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASectionForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
//import org.fourgeeks.gha.domain.gar.BuildingLocation;
//import org.fourgeeks.gha.webclient.client.UI.GHACheckboxItem;
//import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

//import org.fourgeeks.gha.domain.enu.ItSystemEnum;

/**
 * @author alacret, vivi.torresg, emiliot
 * 
 */
public class EIAForm extends GHAForm<Eia> implements EIATypeSelectionListener,
EiaSelectionProducer {
	private GHATextItem codeTextItem, serialTextItem, fixedAssetIdTextItem,
	purchaseOrderNumTextItem, purchaseInvoiceNumTextItem,
	workingAreaLocationCodeTextItem, facilityLocationCodeTextItem,
	adquisitionCostTextItem, adquisitionCostLocalTextItem,
	depreciationTimeTextItem, lifeTimeTextItem, actualCostTextItem,
	realWarrantyTimeTextItem, intWarrantyTimeTextItem;
	// codeMant_WarrMant_TextItem;
	private GHASelectItem obuSelectItem, baseRoleSelectItem, stateSelectItem,
	adqisitionProviderSelectItem, locationTypeSelectItem,
	workingAreaLocationSelectItem, facilityLocationSelectItem,
	adquisitionCostCurrencySelectItem,
	adquisitionCostCurrencyLocalSelectItem,
	depreciationMethodSelectItem, actualCostCurrencySelectItem,
	realWarrantySinceSelectItem, intWarrantySinceSelectItem,
	// maintenanceLocationSelectItem,
	eiaTypeSelectItem, installationProviderSelectItem;
	private GHATitletextItem information_TitleItem, adquisition_TitleItem,
	location_TitleItem, workingArea_TitleItem, facility_TitleItem,
	adqCost_TitleItem, actualCost_TitleItem, depTime_TitleItem,
	lifeTime_TitleItem, realWarranty_TitleItem,
	intermedWarranty_TitleItem, providers_TitleItem;
	private GHADateItem acceptationDateItem, purchaseDateItem,
	purchaseInvoiceDateItem, purchaseOrderDateItem, receptionDateItem,
	installationDateItem, contabilizationDateItem,
	lastDepreciationDate, realWarrantyBeginDate, intWarrantyBeginDate;
	// private GHACheckboxItem sameLocationAttendedItem, isInMaintenanceItem;
	private GHAPeriodOfTimeSelectItem depreciationTimePotSelectItem,
	lifeTimePotSelectItem, intWarrantyPotSelectItem,
	realWarrantyPotSelectItem;
	private GHABspSelectItem maintenanceProviderSelectItem;
	private GHASectionForm sectionForm;
	private final GHADynamicForm infoBasicaForm;
	private final GHADynamicForm adquisicionForm;
	private final GHADynamicForm ubicacionForm;
	private final GHADynamicForm costosForm;
	// private GHADynamicForm garantiasMantForm;

	private List<EIASelectionListener> listeners;

	{ // Global
		sectionForm = new GHASectionForm(GHAStrings.get("eia"));
		listeners = new ArrayList<EIASelectionListener>();

		// Information Form Items
		eiaTypeSelectItem = new GHASelectItem(GHAStrings.get("type"), true,
				changedHandler);
		information_TitleItem = new GHATitletextItem(
				GHAStrings.get("information"), 3);
		codeTextItem = new GHATextItem(GHAStrings.get("code"), false,
				changedHandler);
		codeTextItem.setLength(20);
		// codeTextItem.setMask("####################");
		serialTextItem = new GHATextItem(GHAStrings.get("serial"), true,
				changedHandler);
		serialTextItem.setLength(20);
		// serialTextItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		serialTextItem.setRequired(true);

		fixedAssetIdTextItem = new GHATextItem(
				GHAStrings.get("fixed-asset-identifier"), true, changedHandler);
		fixedAssetIdTextItem.setLength(19);
		fixedAssetIdTextItem.setRequired(true);
		obuSelectItem = new GHASelectItem(GHAStrings.get("obu"), true,
				changedHandler);
		obuSelectItem.setRequired(true);
		baseRoleSelectItem = new GHASelectItem(
				GHAStrings.get("responsible-role"), true, changedHandler);
		baseRoleSelectItem.setRequired(true);
		stateSelectItem = new GHASelectItem(GHAStrings.get("state"), false,
				changedHandler);
		stateSelectItem.setDefaultValue(EiaStateEnum.CREATED.name());
		stateSelectItem.setAllowEmptyValue(false);
		providers_TitleItem = new GHATitletextItem(GHAStrings.get("providers"),
				3);
		adqisitionProviderSelectItem = new GHASelectItem(
				GHAStrings.get("adquisition-provider"), true, changedHandler);
		maintenanceProviderSelectItem = new GHABspSelectItem(
				GHAStrings.get("maintenance-provider"), true, changedHandler);

		// Adquisicion & Garantias Form Items
		// Adquisicion
		adquisition_TitleItem = new GHATitletextItem(
				GHAStrings.get("eia-adquisicion"), 4);
		purchaseDateItem = new GHADateItem(GHAStrings.get("eia-fecha-compra"),
				true);
		purchaseDateItem.addChangedHandler(changedHandler);
		purchaseDateItem.setUseMask(true);

		purchaseOrderNumTextItem = new GHATextItem(
				GHAStrings.get("eia-nro-orden-compra"), false, changedHandler);
		purchaseOrderNumTextItem.setLength(20);
		// purchaseOrderNumTextItem.setMask("####################");
		purchaseInvoiceNumTextItem = new GHATextItem(
				GHAStrings.get("eia-nro-factura"), false, changedHandler);
		purchaseInvoiceNumTextItem.setLength(20);
		// purchaseInvoiceNumTextItem.setMask("####################");
		receptionDateItem = new GHADateItem(GHAStrings.get("eia-recepcion"),
				true);
		receptionDateItem.addChangedHandler(changedHandler);

		acceptationDateItem = new GHADateItem(
				GHAStrings.get("eia-fecha-aceptacion"), true);
		acceptationDateItem.addChangedHandler(changedHandler);

		installationDateItem = new GHADateItem(
				GHAStrings.get("eia-instalation"), true);
		installationDateItem.addChangedHandler(changedHandler);
		purchaseInvoiceDateItem = new GHADateItem(
				GHAStrings.get("eia-fecha-factura"), true);
		purchaseInvoiceDateItem.addChangedHandler(changedHandler);
		purchaseOrderDateItem = new GHADateItem(
				GHAStrings.get("eia-fecha-orden-compra"), true);
		purchaseOrderDateItem.addChangedHandler(changedHandler);

		installationProviderSelectItem = new GHASelectItem(
				GHAStrings.get("eia-proveedor-institution"), false,
				changedHandler);

		// Garantia
		realWarranty_TitleItem = new GHATitletextItem(
				GHAStrings.get("eia-garantia-real"), 4);
		intermedWarranty_TitleItem = new GHATitletextItem(
				GHAStrings.get("intermediate-warranty"), 4);
		realWarrantySinceSelectItem = new GHASelectItem(
				GHAStrings.get("eia-select-desde"), false, changedHandler);

		realWarrantyTimeTextItem = new GHATextItem(
				GHAStrings.get("eia-duracion"), false, changedHandler);
		realWarrantyTimeTextItem.setLength(3);
		// realWarrantyTimeTextItem.setMask("###");
		realWarrantyPotSelectItem = new GHAPeriodOfTimeSelectItem(false,
				changedHandler);
		realWarrantyBeginDate = new GHADateItem(
				GHAStrings.get("eia-fecha-inicio"), true);
		realWarrantyBeginDate.addChangedHandler(changedHandler);

		intWarrantySinceSelectItem = new GHASelectItem(
				GHAStrings.get("eia-select-desde"), false, changedHandler);
		intWarrantyTimeTextItem = new GHATextItem(
				GHAStrings.get("eia-duracion"), false, changedHandler);
		intWarrantyTimeTextItem.setLength(3);
		// intWarrantyTimeTextItem.setMask("###");
		intWarrantyPotSelectItem = new GHAPeriodOfTimeSelectItem(false,
				changedHandler);
		intWarrantyBeginDate = new GHADateItem(
				GHAStrings.get("eia-fecha-inicio"), true);
		intWarrantyBeginDate.addChangedHandler(changedHandler);

		// Ubicacion Form Items
		location_TitleItem = new GHATitletextItem(
				GHAStrings.get("eia-ubicacion"), 3);
		locationTypeSelectItem = new GHASelectItem(
				GHAStrings.get("eia-tipo-ubicacion"), false, changedHandler);

		workingArea_TitleItem = new GHATitletextItem(
				GHAStrings.get("eia-area-trabajo"), 3);
		facility_TitleItem = new GHATitletextItem(
				GHAStrings.get("eia-servicio-instalacion"), 3);
		workingAreaLocationCodeTextItem = new GHATextItem(
				GHAStrings.get("eia-codigo"), false);
		workingAreaLocationSelectItem = new GHASelectItem(
				GHAStrings.get("eia-nombre"), false, changedHandler);
		workingAreaLocationSelectItem.setDisabled(true);

		facilityLocationCodeTextItem = new GHATextItem(
				GHAStrings.get("eia-codigo"), false);
		facilityLocationSelectItem = new GHASelectItem(
				GHAStrings.get("eia-nombre"), false, changedHandler);
		facilityLocationSelectItem.setDisabled(true);

		// Costos Form Items
		adqCost_TitleItem = new GHATitletextItem(
				GHAStrings.get("eia-costo-adquision"), 4);
		actualCost_TitleItem = new GHATitletextItem(
				GHAStrings.get("eia-costo-actual"), 4);
		depTime_TitleItem = new GHATitletextItem(
				GHAStrings.get("eia-tiempo-depreciacion"));
		lifeTime_TitleItem = new GHATitletextItem(
				GHAStrings.get("eia-tiempo-vida"));
		adquisitionCostTextItem = new GHATextItem(
				GHAStrings.get("eia-costo-adquiriencia"), false, changedHandler);
		adquisitionCostTextItem.setLength(16);
		// adquisitionCostTextItem.setMask("################");
		adquisitionCostCurrencySelectItem = new GHASelectItem(
				GHAStrings.get("eia-moneda"), false, changedHandler);
		contabilizationDateItem = new GHADateItem(
				GHAStrings.get("eia-date-contabilizacion"), true);
		contabilizationDateItem.addChangedHandler(changedHandler);

		adquisitionCostLocalTextItem = new GHATextItem(
				GHAStrings.get("eia-costo-adq"), false, changedHandler);
		adquisitionCostLocalTextItem.setLength(16);
		// adquisitionCostLocalTextItem.setMask("################");
		adquisitionCostCurrencyLocalSelectItem = new GHASelectItem(
				GHAStrings.get("eia-moneda-local"), false, changedHandler);
		depreciationMethodSelectItem = new GHASelectItem(
				GHAStrings.get("eia-metodo-depreciacion"), false,
				changedHandler);
		depreciationTimeTextItem = new GHATextItem(
				GHAStrings.get("eia-duracion"), false, changedHandler);
		depreciationTimeTextItem.setLength(3);
		// depreciationTimeTextItem.setMask("###");
		depreciationTimePotSelectItem = new GHAPeriodOfTimeSelectItem(false,
				changedHandler);
		lastDepreciationDate = new GHADateItem(
				GHAStrings.get("eia-fecha-ultma-depreciacion"), true);
		lastDepreciationDate.addChangedHandler(changedHandler);
		lifeTimeTextItem = new GHATextItem(GHAStrings.get("eia-duracion"),
				false, changedHandler);
		lifeTimeTextItem.setLength(3);
		// lifeTimeTextItem.setMask("###");
		lifeTimePotSelectItem = new GHAPeriodOfTimeSelectItem(false,
				changedHandler);
		actualCostTextItem = new GHATextItem(
				GHAStrings.get("eia-costo-actual-libros"), false,
				changedHandler);
		actualCostTextItem.setLength(16);
		// actualCostTextItem.setMask("################");
		actualCostCurrencySelectItem = new GHASelectItem(
				GHAStrings.get("eia-moneda"), false, changedHandler);

		// Regex!

		serialTextItem.validateSerial();

		// codeTextItem.validateCodes();

		adquisitionCostTextItem.validateNumbers();

		adquisitionCostTextItem.validateNumbers();

		purchaseOrderNumTextItem.validateNumbers();

		purchaseInvoiceNumTextItem.validateNumbers();

		adquisitionCostTextItem.validateNumbers();

		adquisitionCostLocalTextItem.validateNumbers();

		depreciationTimeTextItem.validateNumbers();

		lifeTimeTextItem.validateNumbers();

		actualCostTextItem.validateNumbers();

		realWarrantyTimeTextItem.validateNumbers();

		intWarrantyTimeTextItem.validateNumbers();

		// TOOL_TIPS

		eiaTypeSelectItem.setTooltip(GHAStrings.get("eia-tooltip-type"));

		codeTextItem.setTooltip(GHAStrings.get("eia-tooltip-code"));

		serialTextItem.setTooltip(GHAStrings.get("eia-tooltip-serial"));

		fixedAssetIdTextItem.setTooltip(GHAStrings
				.get("eia-tooltip-fixed-asset-identifier"));

		obuSelectItem.setTooltip(GHAStrings.get("eia-tooltip-obu"));

		baseRoleSelectItem.setTooltip(GHAStrings
				.get("eia-tooltip-responsible-role"));

		stateSelectItem.setTooltip(GHAStrings.get("eia-tooltip-state"));

		adqisitionProviderSelectItem.setTooltip(GHAStrings
				.get("eia-tooltip-adquisition-provider"));

		maintenanceProviderSelectItem.setTooltip(GHAStrings
				.get("eia-tooltip-maintenance-provider"));

		// Adquisicion & Garantias Form Items
		// Adquisicion

		purchaseDateItem.setTooltip(GHAStrings.get("eia-tooltip-fecha-compra"));

		purchaseOrderDateItem.setTooltip(GHAStrings
				.get("eia-tooltip-date-orden-compra"));

		purchaseOrderNumTextItem.setTooltip(GHAStrings
				.get("eia-tooltip-orden-compra"));

		purchaseInvoiceNumTextItem.setTooltip(GHAStrings
				.get("eia-tooltip-orden-factura"));

		receptionDateItem.setTooltip(GHAStrings
				.get("eia-tooltip-date-reception"));

		acceptationDateItem.setTooltip(GHAStrings
				.get("eia-tooltip-date-aceptacion"));

		installationDateItem.setTooltip(GHAStrings
				.get("eia-tooltip-date-installation"));
		purchaseInvoiceDateItem.setTooltip(GHAStrings
				.get("eia-tooltip-date-factura"));

		installationProviderSelectItem.setTooltip(GHAStrings
				.get("eia-tooltip-provider-inst"));

		// // Garantia
		// intermedWarranty_TitleItem = new GHATitletextItem(
		// GHAStrings.get("intermediate-warranty"), 4);
		// realWarrantySinceSelectItem = new GHASelectItem("Desde", false,
		// changedHandler);
		// realWarrantyTimeTextItem = new GHATextItem("Duración", false,
		// changedHandler);
		// realWarrantyTimeTextItem.setLength(3);
		// // realWarrantyTimeTextItem.setMask("###");
		// realWarrantyPotSelectItem = new GHAPeriodOfTimeSelectItem(false,
		// changedHandler);
		// realWarrantyBeginDate = new GHADateItem("Fecha Inicio", true);
		// realWarrantyBeginDate.addChangedHandler(changedHandler);
		//
		// intWarrantySinceSelectItem = new GHASelectItem("Desde", false,
		// changedHandler);
		// intWarrantyTimeTextItem = new GHATextItem("Duración", false,
		// changedHandler);
		// intWarrantyTimeTextItem.setLength(3);
		// // intWarrantyTimeTextItem.setMask("###");
		// intWarrantyPotSelectItem = new GHAPeriodOfTimeSelectItem(false,
		// changedHandler);
		// intWarrantyBeginDate = new GHADateItem("Fecha Inicio", true);
		// intWarrantyBeginDate.addChangedHandler(changedHandler);

		// // TOOLSTIPs Ubicacion Form Items

		locationTypeSelectItem.setTooltip(GHAStrings
				.get("eia-tooltip-type-location"));

		workingAreaLocationSelectItem.setTooltip(GHAStrings
				.get("eia-tooltip-working-area-select"));
		workingAreaLocationCodeTextItem.setTooltip(GHAStrings
				.get("eia-tooltip-code-working-area-select"));
		// TOOlTIP servicio o intalacion
		facilityLocationSelectItem.setTooltip(GHAStrings
				.get("eia-tooltip-facility-service-select"));
		facilityLocationCodeTextItem.setTooltip(GHAStrings
				.get("eia-tooltip-facility-service-code"));

		selectDate(realWarrantySinceSelectItem, realWarrantyBeginDate);

		selectDate(intWarrantySinceSelectItem, intWarrantyBeginDate);

	}

	/**
	 * 
	 */
	public EIAForm() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);

		// if (eiatypeSelected) {
		// eiatypeSel = eiatypeSelected;
		// // eiaTypeSelectItem.setRequired(false);
		// eiaTypeSelectItem.setDisabled(true);
		// }

		infoBasicaForm = getInfoBasicaForm();
		adquisicionForm = getAdquisicionForm();
		ubicacionForm = getUbicacionForm();
		costosForm = getCostosForm();
		// garantiasMantForm = getGarantiasMantForm();

		sectionForm.addSection("Información Básica", infoBasicaForm);
		sectionForm.addSection("Adq. y garantias", adquisicionForm);
		sectionForm.addSection("Ubicación", ubicacionForm);
		sectionForm.addSection("Costos", costosForm);
		// sectionForm.addSection("Garantias", garantiasMantForm);
		// sectionForm.addSection("EquiposIT", getEquiposIT(), false);

		addMember(sectionForm);

		// Fillers
		fillInformationSelects();
		fillAdquisitionSelects();
		fillLocationTypeSelect();
		fillLocationsSelects();
		fillCostsSelects();
		fillWarrantySelects();
		// fillITEquipmentsSelects();

		// Funcionalities
		buildingLocFuncionalities();
		// warrantyFunctionalities();

		sectionForm.openFirst();
	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.add(eiaSelectionListener);
	}

	// ///Functionalities
	private void buildingLocFuncionalities() {
		workingAreaLocationSelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				workingAreaLocationCodeTextItem.setValue(event.getValue());
			}
		});

		facilityLocationSelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				facilityLocationCodeTextItem.setValue(event.getValue());
			}
		});

		locationTypeSelectItem.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				// value == 0 WorkingArea type selected, value == 1 Facility
				// type selected
				if (event.getValue().equals("0")) {
					workingAreaLocationSelectItem.setDisabled(false);
					facilityLocationSelectItem.setDisabled(true);
					facilityLocationSelectItem.clearValue();
					facilityLocationCodeTextItem.clearValue();
				} else if (event.getValue().equals("1")) {
					facilityLocationSelectItem.setDisabled(false);
					workingAreaLocationSelectItem.setDisabled(true);
					workingAreaLocationSelectItem.clearValue();
					workingAreaLocationCodeTextItem.clearValue();
				}
			}
		});
	}

	@Override
	public void clear() {
		super.clear();
		// clean text fields
		codeTextItem.clearValue();
		serialTextItem.clearValue();
		serialTextItem.validate();
		fixedAssetIdTextItem.clearValue();
		purchaseOrderNumTextItem.clearValue();
		purchaseInvoiceNumTextItem.clearValue();
		workingAreaLocationCodeTextItem.clearValue();
		facilityLocationCodeTextItem.clearValue();
		adquisitionCostTextItem.clearValue();
		adquisitionCostLocalTextItem.clearValue();
		actualCostTextItem.clearValue();
		depreciationTimeTextItem.clearValue();
		lifeTimeTextItem.clearValue();
		realWarrantyTimeTextItem.clearValue();
		intWarrantyTimeTextItem.clearValue();
		// codeMant_WarrMant_TextItem.clearValue();
		// ipAddresTextItem.clearValue();
		// macAddressTextItem.clearValue();
		// machineNameTextItem.clearValue();

		// clean select fields
		eiaTypeSelectItem.clearValue();
		stateSelectItem.clearValue();
		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
		adqisitionProviderSelectItem.clearValue();
		installationProviderSelectItem.clearValue();
		locationTypeSelectItem.clearValue();
		workingAreaLocationSelectItem.clearValue();
		facilityLocationSelectItem.clearValue();
		adquisitionCostCurrencySelectItem.clearValue();
		adquisitionCostCurrencyLocalSelectItem.clearValue();
		depreciationMethodSelectItem.clearValue();
		actualCostCurrencySelectItem.clearValue();
		depreciationTimePotSelectItem.clearValue();
		lifeTimePotSelectItem.clearValue();
		realWarrantySinceSelectItem.clearValue();
		realWarrantyPotSelectItem.clearValue();
		intWarrantySinceSelectItem.clearValue();
		intWarrantyPotSelectItem.clearValue();
		// maintenanceLocationSelectItem.clearValue();
		maintenanceProviderSelectItem.clearValue();
		// itTypeSelectItem.clearValue();

		acceptationDateItem.clearValue();
		purchaseDateItem.clearValue();
		receptionDateItem.clearValue();
		installationDateItem.clearValue();
		purchaseInvoiceDateItem.clearValue();
		purchaseOrderDateItem.clearValue();
		contabilizationDateItem.clearValue();
		lastDepreciationDate.clearValue();
		realWarrantyBeginDate.clearValue();
		intWarrantyBeginDate.clearValue();
		purchaseInvoiceDateItem.clearValue();
		purchaseOrderDateItem.clearValue();
		acceptationDateItem.clearValue();
		// sameLocationAttendedItem.setValue(false);
		// isInMaintenanceItem.setValue(false);
		infoBasicaForm.clearErrors(true);
		adquisicionForm.clearErrors(true);
		ubicacionForm.clearErrors(true);
		costosForm.clearErrors(true);
	}

	@Override
	public void deactivate() {
		toggleForm(false);
	}

	private Eia extract() {
		Eia eia;
		final List<String> violationsList = new ArrayList<String>();
		if (this.originalEntity == null)
			eia = new Eia();
		else
			eia = this.originalEntity;

		// EXTRAYENDO LOS DATOS

		// basic information
		if (eiaTypeSelectItem.getValue() != null)
			eia.setEiaType(new EiaType(eiaTypeSelectItem.getValueAsString()));
		eia.setCode(codeTextItem.getValueAsString());
		eia.setSerialNumber(serialTextItem.getValueAsString());
		eia.setFixedAssetIdentifier(fixedAssetIdTextItem.getValueAsString());
		if (obuSelectItem.getValue() != null) {
			final Obu obu = new Obu();
			obu.setId(Integer.valueOf(obuSelectItem.getValueAsString()));
			eia.setObu(obu);
		}
		if (baseRoleSelectItem.getValue() != null) {
			final Role baseRole = new Role();
			baseRole.setId(Integer.valueOf(baseRoleSelectItem
					.getValueAsString()));
			eia.setResponsibleRole(baseRole);
		}
		if (stateSelectItem.getValue() != null)
			eia.setState(EiaStateEnum.valueOf(stateSelectItem
					.getValueAsString()));
		if (acceptationDateItem.getValue() != null)
			eia.setAcceptationDate(new Date(acceptationDateItem
					.getValueAsDate().getTime()));

		// acquisition
		eia.setPurchaseDate(purchaseDateItem.getValue() == null ? null
				: EIAUtil.getLogicalDate(purchaseDateItem.getValueAsDate()));

		eia.setReceptionDate(receptionDateItem.getValue() == null ? null
				: EIAUtil.getLogicalDate(receptionDateItem.getValueAsDate()));

		eia.setInstallationDate(installationDateItem.getValue() == null ? null
				: EIAUtil.getLogicalDate(installationDateItem.getValueAsDate()));

		if (adqisitionProviderSelectItem.getValue() != null) {
			eia.setProvider(new ExternalProvider(Long
					.valueOf(adqisitionProviderSelectItem.getValueAsString())));
		}

		eia.setPurchaseOrderNumber(purchaseOrderNumTextItem.getValueAsString());

		eia.setPurchaseInvoiceNumber(purchaseInvoiceNumTextItem
				.getValueAsString());

		eia.setPurchaseInvoiceDate(purchaseInvoiceDateItem.getValue() == null ? null
				: EIAUtil.getLogicalDate(purchaseInvoiceDateItem
						.getValueAsDate()));

		eia.setPurchaseOrderDate(purchaseOrderDateItem.getValue() == null ? null
				: EIAUtil.getLogicalDate(purchaseOrderDateItem.getValueAsDate()));

		if (installationProviderSelectItem.getValue() != null) {
			eia.setInstallationProvider(new ExternalProvider(Integer
					.valueOf(installationProviderSelectItem.getValueAsString())));
		}

		if (locationTypeSelectItem.getValue() != null) {
			if (locationTypeSelectItem.getValue().equals("0")) {
				if (workingAreaLocationSelectItem.getValue() != null) {
					eia.setFacility(null);
					eia.setWorkingArea(new WorkingArea(Integer
							.valueOf(workingAreaLocationSelectItem
									.getValueAsString())));
				}
			} else if (locationTypeSelectItem.getValue().equals("1")) {
				if (facilityLocationSelectItem.getValue() != null) {
					eia.setWorkingArea(null);
					eia.setFacility(new Facility(Integer
							.valueOf(facilityLocationSelectItem
									.getValueAsString())));
				}
			}
		}
		// if (buildingLocationSelectItem.getValue() != null) {
		// eia.setBuildingLocation(new BuildingLocation(
		// buildingLocationSelectItem.getValueAsString()));
		// if (sameLocationAttendedItem.getValueAsBoolean()) {
		// eia.setAttendedLocation(new BuildingLocation(
		// buildingLocationSelectItem.getValueAsString()));
		// } else {
		// if (attendedLocationSelectItem.getValue() != null) {
		// eia.setAttendedLocation(new BuildingLocation(
		// attendedLocationSelectItem.getValueAsString()));
		// }
		// }
		// }

		// costs
		if (adquisitionCostTextItem.getValue() != null) {
			eia.setAdquisitionCost(BigDecimal.valueOf(Double
					.valueOf(adquisitionCostTextItem.getValueAsString())));
		}
		if (adquisitionCostCurrencySelectItem.getValue() != null)
			eia.setAdquisitionCostCurrency(CurrencyTypeEnum
					.valueOf(adquisitionCostCurrencySelectItem
							.getValueAsString()));

		if (contabilizationDateItem.getValue() != null)
			eia.setContabilizationDate(new Date(contabilizationDateItem
					.getValueAsDate().getTime()));

		if (adquisitionCostLocalTextItem.getValue() != null) {
			eia.setAdquisitionCostLocal(BigDecimal.valueOf(Double
					.valueOf(adquisitionCostLocalTextItem.getValueAsString())));
		}

		if (adquisitionCostCurrencyLocalSelectItem.getValue() != null)
			eia.setAdquisitionCostCurrencyLocal(CurrencyTypeEnum
					.valueOf(adquisitionCostCurrencyLocalSelectItem
							.getValueAsString()));

		if (depreciationMethodSelectItem.getValue() != null)
			eia.setDepreciationMethod(DepreciationMethodEnum
					.valueOf(depreciationMethodSelectItem.getValueAsString()));

		if (lastDepreciationDate.getValue() != null)
			eia.setDateLastDepreciation(new Date(lastDepreciationDate
					.getValueAsDate().getTime()));
		if (actualCostTextItem.getValue() != null)
			eia.setActualCost(BigDecimal.valueOf(Double
					.valueOf(actualCostTextItem.getValueAsString())));

		if (actualCostCurrencySelectItem.getValue() != null)
			eia.setActualCostCurrency(CurrencyTypeEnum
					.valueOf(actualCostCurrencySelectItem.getValueAsString()));

		if (depreciationTimeTextItem.getValue() != null)
			eia.setDepreciationTime(Integer.valueOf(depreciationTimeTextItem
					.getValueAsString()));

		if (depreciationTimePotSelectItem.getValue() != null)
			eia.setDepreciationTimePoT(TimePeriodEnum
					.valueOf(depreciationTimePotSelectItem.getValueAsString()));

		if (lifeTimeTextItem.getValue() != null)
			eia.setLifeTime(Integer.valueOf(lifeTimeTextItem.getValueAsString()));

		if (lifeTimePotSelectItem.getValue() != null)
			eia.setLifeTimePoT(TimePeriodEnum.valueOf(lifeTimePotSelectItem
					.getValueAsString()));

		// guarantees
		eia.setRealWarrantyBegin(realWarrantyBeginDate.getValue() == null ? null
				: EIAUtil.getLogicalDate(realWarrantyBeginDate.getValueAsDate()));

		eia.setIntWarrantyBegin(intWarrantyBeginDate.getValue() == null ? null
				: EIAUtil.getLogicalDate(intWarrantyBeginDate.getValueAsDate()));

		if (realWarrantyPotSelectItem.getValue() != null)
			eia.setRealWarrantyPoT(TimePeriodEnum
					.valueOf(realWarrantyPotSelectItem.getValueAsString()));

		if (realWarrantySinceSelectItem.getValue() != null)
			eia.setRealWarrantySince(WarrantySinceEnum
					.valueOf(realWarrantySinceSelectItem.getValueAsString()));

		if (realWarrantyTimeTextItem.getValue() != null)
			eia.setRealWarrantyTime(Integer.valueOf(realWarrantyTimeTextItem
					.getValueAsString()));

		if (intWarrantyPotSelectItem.getValue() != null)
			eia.setIntWarrantyPoT(TimePeriodEnum
					.valueOf(intWarrantyPotSelectItem.getValueAsString()));
		if (intWarrantySinceSelectItem.getValue() != null)
			eia.setIntWarrantySince(WarrantySinceEnum
					.valueOf(intWarrantySinceSelectItem.getValueAsString()));

		if (intWarrantyTimeTextItem.getValue() != null)
			eia.setIntWarrantyTime(Integer.valueOf(intWarrantyTimeTextItem
					.getValueAsString()));
		// if (isInMaintenanceItem.getValueAsBoolean()) {
		// eia.setMaintenanceLocation(new BuildingLocation(
		// maintenanceLocationSelectItem.getValueAsString()));
		if (maintenanceProviderSelectItem.getValue() != null) {
			final Bsp bsp = new Bsp();
			bsp.setId(Long.valueOf(maintenanceProviderSelectItem
					.getValueAsString()));
			eia.setMaintenanceProvider(bsp);
		}

		// itEquipments
		// eia.setItType(ItSystemEnum.valueOf(itTypeSelectItem.getValueAsString()));
		// eia.setMachineName(machineNameTextItem.getValueAsString());
		// eia.setIpAddress(ipAddresTextItem.getValueAsString());
		// eia.setMacAddress(macAddressTextItem.getValueAsString());

		// --------------------------------------------------------------------

		// VALIDANDO LOS DATOS
		final Set<ConstraintViolation<Eia>> violations = validator
				.validate(eia);

		if (infoBasicaForm.validate() && adquisicionForm.validate()
				&& ubicacionForm.validate() && costosForm.validate()
				&& /* garantiasMantForm.validate() && */violations.isEmpty()) {
			return eia;
		} else {

			for (final Iterator<ConstraintViolation<Eia>> it = violations
					.iterator(); it.hasNext();) {
				violationsList.add(it.next().getMessage());
			}

			if (violationsList.contains("type-not-null")) {
				GHAErrorMessageProcessor.alert("type-not-null");
				return null;
			}
			if (violationsList.contains("serial-not-null")) {
				GHAErrorMessageProcessor.alert("serial-not-null");
				return null;
			}
			if (violationsList.contains("asset-id-not-null")) {
				GHAErrorMessageProcessor.alert("asset-id-not-null");
				return null;
			}
			if (violationsList.contains("obu-not-null")) {
				GHAErrorMessageProcessor.alert("obu-not-null");
				return null;
			}
			if (violationsList.contains("base-role-not-null")) {
				GHAErrorMessageProcessor.alert("base-role-not-null");
				return null;
			}
			if (violationsList.contains("external-provider-not-null")) {
				GHAErrorMessageProcessor.alert("external-provider-not-null");
				return null;
			}
			if (violationsList.contains("maintenance-provider-not-null")) {
				GHAErrorMessageProcessor.alert("maintenance-provider-not-null");
				return null;
			}

			// 1 type-not-null
			// 2 serial-not-null
			// 3 asset-id-not-null
			// 4 obu-not-null
			// 5 base-role-not-null
			// 6 external-provider-not-null
			// 7 maintenance-provider-not-null
			// GHAAlertManager.alert(violationsList);
			// GHAAlertManager.alert(violationsList.get(0));
		}
		return null;
	}

	private void fillAdquisitionSelects() {
		GHACache.INSTANCE
		.getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>() {
			@Override
			public void onSuccess(List<ExternalProvider> result) {
				final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (final ExternalProvider entity : result)
					valueMap.put(entity.getId() + "", entity
							.getInstitution().getName() + "");
				adqisitionProviderSelectItem.setValueMap(valueMap);
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

		actualCostCurrencySelectItem.setValueMap(CurrencyTypeEnum.toValueMap());
		actualCostCurrencySelectItem.setValue(CurrencyTypeEnum.BS.name());
	}

	// //Fillers
	private void fillInformationSelects() {
		GHACache.INSTANCE.getEiaTypes(new GHAAsyncCallback<List<EiaType>>() {
			@Override
			public void onSuccess(List<EiaType> result) {
				final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (final EiaType entity : result) {
					valueMap.put(entity.getCode() + "", entity.getName() + "");
				}
				eiaTypeSelectItem.setValueMap(valueMap);
			}
		}, false);

		GHACache.INSTANCE.getObus(new GHAAsyncCallback<List<Obu>>() {
			@Override
			public void onSuccess(List<Obu> result) {
				final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (final Obu entity : result) {
					valueMap.put(entity.getId() + "", entity.getName() + "");
				}
				obuSelectItem.setValueMap(valueMap);
			}
		});

		GHACache.INSTANCE.getBaseRoles(new GHAAsyncCallback<List<Role>>() {
			@Override
			public void onSuccess(List<Role> result) {
				final LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
				for (final Role entity : result)
					valueMap.put(entity.getId() + "", entity.getName() + "");
				baseRoleSelectItem.setValueMap(valueMap);
			}
		});

		stateSelectItem.setValueMap(EiaStateEnum.toValueMap());
		// stateSelectItem.setValue(EiaStateEnum.CREATED.name());
	}

	private void fillLocationsSelects() {
		GHACache.INSTANCE
		.getWorkingAreas(new GHAAsyncCallback<List<WorkingArea>>() {
			@Override
			public void onSuccess(List<WorkingArea> result) {
				final LinkedHashMap<String, String> valueMapWorkingArea = new LinkedHashMap<String, String>();

				for (final WorkingArea entity : result) {
					valueMapWorkingArea.put(entity.getId() + "",
							entity.getName());
				}

				workingAreaLocationSelectItem
				.setValueMap(valueMapWorkingArea);
			}
		});
		GHACache.INSTANCE.getFacilities(new GHAAsyncCallback<List<Facility>>() {
			@Override
			public void onSuccess(List<Facility> result) {
				final LinkedHashMap<String, String> valueMapFacility = new LinkedHashMap<String, String>();

				for (final Facility entity : result) {
					valueMapFacility.put(entity.getId() + "", entity.getName());
				}

				facilityLocationSelectItem.setValueMap(valueMapFacility);
			}
		});

	}

	private void fillLocationTypeSelect() {
		final LinkedHashMap<String, String> valueMapLocationType = new LinkedHashMap<String, String>();
		valueMapLocationType.put("0", "Área de Trabajo");
		valueMapLocationType.put("1", "Servicio/Instalación");
		locationTypeSelectItem.setValueMap(valueMapLocationType);
	}

	private void fillWarrantySelects() {
		realWarrantySinceSelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		// realWarrantySinceSelectItem.setValue(WarrantySinceEnum.PURCHASE.name());
		intWarrantySinceSelectItem.setValueMap(WarrantySinceEnum.toValueMap());
		// intWarrantySinceSelectItem.setValue(WarrantySinceEnum.PURCHASE.name());

		// GHACache.INSTANCE
		// .getBuildingLocations(new GHAAsyncCallback<List<BuildingLocation>>()
		// {
		// @Override
		// public void onSuccess(List<BuildingLocation> result) {
		// LinkedHashMap<String, String> valueMap = new LinkedHashMap<String,
		// String>();
		// for (BuildingLocation entity : result)
		// valueMap.put(entity.getCode() + "",
		// entity.getName());
		// maintenanceLocationSelectItem.setValueMap(valueMap);
		// }
		// });

		// GHACache.INSTANCE
		// .getExternalProviders(new GHAAsyncCallback<List<ExternalProvider>>()
		// {
		// @Override
		// public void onSuccess(List<ExternalProvider> result) {
		// final LinkedHashMap<String, String> valueMap = new
		// LinkedHashMap<String, String>();
		// for (final ExternalProvider entity : result) {
		// valueMap.put(entity.getId() + "", entity
		// .getInstitution().getName() + "");
		// }
		// maintenanceProviderSelectItem.setValueMap(valueMap);
		// installationProviderSelectItem.setValueMap(valueMap);
		// }
		// });
	}

	// private DynamicForm getEquiposIT() {
	// DynamicForm equiposITForm = new DynamicForm();
	// equiposITForm.setTitleOrientation(TitleOrientation.TOP);
	// equiposITForm.setNumCols(2);
	//
	// equiposITForm.setItems(it_TitleItem, new GHASpacerItem(),
	// itTypeSelectItem, machineNameTextItem, ipAddresTextItem,
	// macAddressTextItem);
	//
	// return equiposITForm;
	// }

	/**
	 * @return
	 */
	private GHADynamicForm getAdquisicionForm() {
		// //////Adquisicion Form
		final GHADynamicForm adquisicionForm = new GHADynamicForm(4,
				FormType.SECTIONFORM_FORM);

		adquisicionForm.setItems(adquisition_TitleItem, purchaseDateItem,
				receptionDateItem, installationDateItem, new GHASpacerItem(),
				purchaseInvoiceNumTextItem, purchaseInvoiceDateItem,
				installationProviderSelectItem, new GHASpacerItem(),
				purchaseOrderNumTextItem, purchaseOrderDateItem,
				acceptationDateItem, new GHASpacerItem(),
				realWarranty_TitleItem, realWarrantySinceSelectItem,
				realWarrantyBeginDate, realWarrantyTimeTextItem,
				realWarrantyPotSelectItem, intermedWarranty_TitleItem,
				intWarrantySinceSelectItem, intWarrantyBeginDate,
				intWarrantyTimeTextItem, intWarrantyPotSelectItem);

		return adquisicionForm;
	}

	/**
	 * @return
	 */
	private GHADynamicForm getCostosForm() {
		final GHADynamicForm res = new GHADynamicForm(4,
				FormType.SECTIONFORM_FORM);

		res.setItems(adqCost_TitleItem, adquisitionCostTextItem,
				adquisitionCostCurrencySelectItem, contabilizationDateItem,
				new GHASpacerItem(1), adquisitionCostLocalTextItem,
				adquisitionCostCurrencyLocalSelectItem, new GHASpacerItem(2),
				actualCost_TitleItem, lastDepreciationDate,
				depreciationMethodSelectItem, actualCostTextItem,
				actualCostCurrencySelectItem, depTime_TitleItem,
				depreciationTimeTextItem, depreciationTimePotSelectItem,
				new GHASpacerItem(1), lifeTime_TitleItem, lifeTimeTextItem,
				lifeTimePotSelectItem);

		return res;
	}

	/**
	 * @return
	 */
	private GHADynamicForm getInfoBasicaForm() {
		final GHADynamicForm equipoForm = new GHADynamicForm(3,
				FormType.SECTIONFORM_FORM);

		equipoForm.setItems(information_TitleItem, eiaTypeSelectItem,
				new GHASpacerItem(2), codeTextItem, serialTextItem,
				fixedAssetIdTextItem, obuSelectItem, baseRoleSelectItem,
				stateSelectItem, new GHASpacerItem(), providers_TitleItem,
				adqisitionProviderSelectItem, maintenanceProviderSelectItem);

		return equipoForm;
	}

	/**
	 * @return
	 */
	private GHADynamicForm getUbicacionForm() {
		final GHADynamicForm areaForm = new GHADynamicForm(3,
				FormType.SECTIONFORM_FORM);

		areaForm.setItems(location_TitleItem, locationTypeSelectItem,
				new GHASpacerItem(2), workingArea_TitleItem,
				workingAreaLocationSelectItem, workingAreaLocationCodeTextItem,
				facility_TitleItem, facilityLocationSelectItem,
				facilityLocationCodeTextItem);
		return areaForm;
	}

	@Override
	public void hide() {
		super.hide();
		sectionForm.hide();
	}

	@Override
	public void notifyEia(Eia eia) {
		for (final EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		infoBasicaForm.resize();
		adquisicionForm.resize();
		ubicacionForm.resize();
		costosForm.resize();
		// garantiasMantForm.resize(GHAUiHelper.getSectionFormFormWidth(30),3);
	}

	// /**
	// * @return
	// */
	// private GHADynamicForm getGarantiasMantForm() {
	// GHADynamicForm garantiasMantenimientoForm = new
	// GHADynamicForm(GHAUiHelper.getSectionFormFormWidth(30),3);
	//
	// garantiasMantenimientoForm.setItems(realWarranty_TitleItem,new
	// GHASpacerItem(2),
	// realWarrantySinceSelectItem,realWarrantyBeginDate, new GHASpacerItem(),
	// realWarrantyTimeTextItem, realWarrantyPotSelectItem,new GHASpacerItem(),
	// intermedWarranty_TitleItem, new GHASpacerItem(2),
	// intWarrantySinceSelectItem,intWarrantyBeginDate, new GHASpacerItem(),
	// intWarrantyTimeTextItem, intWarrantyPotSelectItem);
	//
	// return garantiasMantenimientoForm;
	// }

	// //Form Creating Functions

	/*
	 * private void warrantyFunctionalities() {
	 * isInMaintenanceItem.addChangeHandler(new ChangeHandler() {
	 * 
	 * @Override public void onChange(ChangeEvent event) { if
	 * (event.getValue().equals(true)) {
	 * maintenanceLocationSelectItem.setDisabled(false);
	 * maintenanceProviderSelectItem.setDisabled(false); } else {
	 * maintenanceLocationSelectItem.setDisabled(true);
	 * maintenanceProviderSelectItem.setDisabled(true);
	 * maintenanceLocationSelectItem.clearValue();
	 * codeMant_WarrMant_TextItem.clearValue();
	 * maintenanceProviderSelectItem.clearValue(); } } });
	 * 
	 * maintenanceLocationSelectItem.addChangeHandler(new ChangeHandler() {
	 * 
	 * @Override public void onChange(ChangeEvent event) {
	 * codeMant_WarrMant_TextItem.setValue(event.getValue()); } }); }
	 */

	public void openFirstSection() {
		sectionForm.openFirst();
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.remove(eiaSelectionListener);
	}

	@Override
	public void save(final GHAAsyncCallback<Eia> callback) {
		final Eia eia = extract();
		if (eia != null) {
			EIAModel.save(eia, new GHAAsyncCallback<Eia>() {
				@Override
				public void onSuccess(Eia result) {
					hasUnCommittedChanges = false;
					notifyEia(result);
					clear();
					if (callback != null)
						callback.onSuccess(result);
				}
			});
		}
	}

	@Override
	public void select(EiaType eiaType) {
		if (eiaType != null) {
			eiaTypeSelectItem.setValue(eiaType.getCode());
			eiaTypeSelectItem.disable();
		}
	}

	public void selectDate(final GHASelectItem selectDateItem,
			final GHADateItem dateItem) {

		selectDateItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				changedHandler.onChanged(event);

				if (selectDateItem.getValueAsString().equalsIgnoreCase(
						"PURCHASE")) {
					if (purchaseDateItem.getValue() == null) {
						GHAErrorMessageProcessor
						.alert("VEM-WARNING", "Advertencia",
								"Debe Ingresar o Seleccionar una Fecha de Compra");

					} else {
						dateItem.setDisabled(true);
						dateItem.setValue(purchaseDateItem.getValue());
					}
				} else if (selectDateItem.getValueAsString().equalsIgnoreCase(
						"RECEPTION")) {
					if (receptionDateItem.getValue() == null) {
						GHAErrorMessageProcessor
						.alert("VEM-WARNING", "Advertencia",
								"Debe Ingresar o Seleccionar una Fecha de Recepción");

					} else {
						dateItem.setDisabled(true);
						dateItem.setValue(receptionDateItem.getValue());
					}
				} else if (selectDateItem.getValueAsString().equalsIgnoreCase(
						"INSTALATION")) {
					if (installationDateItem.getValue() == null) {
						GHAErrorMessageProcessor
						.alert("VEM-WARNING", "Advertencia",
								"Debe Ingresar o Seleccionar una Fecha de Instalación");

					} else {
						dateItem.setDisabled(true);
						dateItem.setValue(installationDateItem.getValue());
					}
				} else if (selectDateItem.getValueAsString().equalsIgnoreCase(
						"ACCEPTATION")) {

					if (acceptationDateItem.getValue() == null) {
						GHAErrorMessageProcessor
						.alert("VEM-WARNING", "Advertencia",
								"Debe Ingresar o Seleccionar una Fecha de Aceptación");

					} else {
						dateItem.setDisabled(true);
						dateItem.setValue(acceptationDateItem.getValue());
					}
				} else if (selectDateItem.getValueAsString().equalsIgnoreCase(
						"OTHER")) {
					dateItem.setDisabled(false);
					dateItem.clearValue();

				}

			}
		});

		purchaseDateItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				if (selectDateItem.getValueAsString().equalsIgnoreCase(
						"PURCHASE")) {
					dateItem.setDisabled(true);
					dateItem.setValue(purchaseDateItem.getValue());
				}
			}
		});

		acceptationDateItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				if (selectDateItem.getValueAsString().equalsIgnoreCase(
						"ACCEPTATION")) {
					dateItem.setDisabled(true);
					dateItem.setValue(acceptationDateItem.getValue());
				}
			}
		});

		installationDateItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				if (selectDateItem.getValueAsString().equalsIgnoreCase(
						"INSTALATION")) {
					dateItem.setDisabled(true);
					dateItem.setValue(installationDateItem.getValue());
				}
			}
		});

		receptionDateItem.addChangedHandler(new ChangedHandler() {

			@Override
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				if (selectDateItem.getValueAsString().equalsIgnoreCase(
						"RECEPTION")) {
					dateItem.setDisabled(true);
					dateItem.setValue(receptionDateItem.getValue());
				}
			}
		});
	}

	/**
	 * @param eia
	 */
	@Override
	public void set(Eia eia) {
		this.originalEntity = eia;
		// basic information
		codeTextItem.setValue(eia.getCode());
		serialTextItem.setValue(eia.getSerialNumber());
		fixedAssetIdTextItem.setValue(eia.getFixedAssetIdentifier());
		acceptationDateItem.setValue(eia.getAcceptationDate());

		eiaTypeSelectItem.clearValue();
		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
		stateSelectItem.clearValue();

		if (eia.getEiaType() != null)
			eiaTypeSelectItem.setValue(eia.getEiaType().getCode());
		if (eia.getObu() != null)
			obuSelectItem.setValue(eia.getObu().getId());
		if (eia.getResponsibleRole() != null)
			baseRoleSelectItem.setValue(eia.getResponsibleRole().getId());
		if (eia.getState() != null)
			stateSelectItem.setValue(eia.getState().name());

		// acquisition
		purchaseDateItem.setValue(eia.getPurchaseDate());
		receptionDateItem.setValue(eia.getReceptionDate());
		installationDateItem.setValue(eia.getInstallationDate());
		purchaseInvoiceDateItem.setValue(eia.getPurchaseInvoiceDate());
		purchaseOrderDateItem.setValue(eia.getPurchaseOrderDate());
		purchaseOrderNumTextItem.setValue(eia.getPurchaseOrderNumber());
		purchaseInvoiceNumTextItem.setValue(eia.getPurchaseInvoiceNumber());

		adqisitionProviderSelectItem.clearValue();
		installationProviderSelectItem.clearValue();

		if (eia.getProvider() != null)
			adqisitionProviderSelectItem.setValue(eia.getProvider().getId());

		if (eia.getInstallationProvider() != null) {
			final ExternalProvider installProv = eia.getInstallationProvider();
			installationProviderSelectItem.setValue(installProv.getId());
		}

		// // ubication
		// boolean flag = true;
		// if (eia.getBuildingLocation() != null)
		// buildingLocationSelectItem.setValue(eia.getBuildingLocation()
		// .getCode());
		// else
		// flag = false;
		// if (eia.getAttendedLocation() != null)
		// attendedLocationSelectItem.setValue(eia.getAttendedLocation()
		// .getCode());
		// else
		// flag = false;
		// if (flag
		// && eia.getBuildingLocation().getCode() == eia
		// .getAttendedLocation().getCode()) {
		// sameLocationAttendedItem.setValue(true);
		// } else {
		// sameLocationAttendedItem.setValue(false);
		// }
		locationTypeSelectItem.clearValue();
		workingAreaLocationSelectItem.clearValue();
		facilityLocationSelectItem.clearValue();

		if (eia.getWorkingArea() != null) {
			workingAreaLocationSelectItem.setDisabled(false);
			workingAreaLocationSelectItem
			.setValue(eia.getWorkingArea().getId());
			workingAreaLocationCodeTextItem.setValue(eia.getWorkingArea()
					.getId());
			locationTypeSelectItem.setValue("0");
		}
		if (eia.getFacility() != null) {
			facilityLocationSelectItem.setDisabled(false);
			facilityLocationSelectItem.setValue(eia.getFacility().getId());
			facilityLocationCodeTextItem.setValue(eia.getFacility().getId());
			locationTypeSelectItem.setValue("1");
		}

		// costs
		adquisitionCostTextItem.setValue(eia.getAdquisitionCost());
		contabilizationDateItem.setValue(eia.getContabilizationDate());
		adquisitionCostLocalTextItem.setValue(eia.getAdquisitionCostLocal());
		lastDepreciationDate.setValue(eia.getDateLastDepreciation());
		actualCostTextItem.setValue(eia.getActualCost());
		depreciationTimeTextItem.setValue(eia.getDepreciationTime());
		lifeTimeTextItem.setValue(eia.getLifeTime());
		realWarrantyBeginDate.setValue(eia.getRealWarrantyBegin());
		realWarrantyTimeTextItem.setValue(eia.getRealWarrantyTime());
		intWarrantyTimeTextItem.setValue(eia.getIntWarrantyTime());
		intWarrantyBeginDate.setValue(eia.getIntWarrantyBegin());

		adquisitionCostCurrencySelectItem.clearValue();
		adquisitionCostCurrencyLocalSelectItem.clearValue();
		depreciationMethodSelectItem.clearValue();
		actualCostCurrencySelectItem.clearValue();
		depreciationTimePotSelectItem.clearValue();
		realWarrantySinceSelectItem.clearValue();
		intWarrantySinceSelectItem.clearValue();
		maintenanceProviderSelectItem.clearValue();

		if (eia.getAdquisitionCostCurrency() != null)
			adquisitionCostCurrencySelectItem.setValue(eia
					.getAdquisitionCostCurrency().name());

		if (eia.getAdquisitionCostCurrencyLocal() != null)
			adquisitionCostCurrencyLocalSelectItem.setValue(eia
					.getAdquisitionCostCurrencyLocal().name());

		if (eia.getDepreciationMethod() != null)
			depreciationMethodSelectItem.setValue(eia.getDepreciationMethod()
					.name());

		if (eia.getActualCostCurrency() != null)
			actualCostCurrencySelectItem.setValue(eia.getActualCostCurrency()
					.name());

		if (eia.getDepreciationTimePoT() != null)
			depreciationTimePotSelectItem.setValue(eia.getDepreciationTimePoT()
					.name());

		if (eia.getLifeTimePoT() != null)
			lifeTimePotSelectItem.setValue(eia.getLifeTimePoT().name());

		// guarantees if (eia.getRealWarrantyBegin() != null)
		if (eia.getRealWarrantySince() != null)
			realWarrantySinceSelectItem.setValue(eia.getRealWarrantySince()
					.name());

		if (eia.getRealWarrantyPoT() != null)
			realWarrantyPotSelectItem.setValue(eia.getRealWarrantyPoT().name());

		if (eia.getIntWarrantySince() != null)
			intWarrantySinceSelectItem.setValue(eia.getIntWarrantySince()
					.name());

		if (eia.getIntWarrantyPoT() != null)
			intWarrantyPotSelectItem.setValue(eia.getIntWarrantyPoT().name());

		// if (eia.getMaintenanceLocation() != null) {
		// isInMaintenanceItem.setValue(true);
		// if (eia.getMaintenanceLocation() != null)
		// maintenanceLocationSelectItem.setValue(eia
		// .getMaintenanceLocation().getCode());
		if (eia.getMaintenanceProvider() != null)
			maintenanceProviderSelectItem.setValue(eia.getMaintenanceProvider()
					.getId());
		// }

		// itEquipments
		// if (eia.getItType() != null)
		// itTypeSelectItem.setValue(eia.getItType().name());
		// if (eia.getMachineName() != null)
		// machineNameTextItem.setValue(eia.getMachineName());
		// if (eia.getIpAddress() != null)
		// ipAddresTextItem.setValue(eia.getIpAddress());
		// if (eia.getMacAddress() != null)
		// macAddressTextItem.setValue(eia.getMacAddress());
	}

	@Override
	public void show() {
		sectionForm.show();
		super.show();
	}

	private void toggleForm(boolean activate) {
		codeTextItem.setDisabled(!activate);
		serialTextItem.setDisabled(!activate);
		fixedAssetIdTextItem.setDisabled(!activate);
		purchaseOrderNumTextItem.setDisabled(!activate);
		purchaseInvoiceNumTextItem.setDisabled(!activate);
		workingAreaLocationCodeTextItem.setDisabled(!activate);
		facilityLocationCodeTextItem.setDisabled(!activate);
		adquisitionCostTextItem.setDisabled(!activate);
		adquisitionCostLocalTextItem.setDisabled(!activate);
		actualCostTextItem.setDisabled(!activate);
		depreciationTimeTextItem.setDisabled(!activate);
		lifeTimeTextItem.setDisabled(!activate);
		realWarrantyTimeTextItem.setDisabled(!activate);
		intWarrantyTimeTextItem.setDisabled(!activate);
		// codeMant_WarrMant_TextItem.setDisabled(!activate);
		// ipAddresTextItem.setDisabled(!activate);
		// macAddressTextItem.setDisabled(!activate);
		// machineNameTextItem.setDisabled(!activate);

		// clean select fields
		// if (!eiatypeSel)
		eiaTypeSelectItem.setDisabled(!activate);

		obuSelectItem.setDisabled(!activate);
		baseRoleSelectItem.setDisabled(!activate);
		stateSelectItem.setDisabled(!activate);
		adqisitionProviderSelectItem.setDisabled(!activate);
		installationProviderSelectItem.setDisabled(!activate);
		locationTypeSelectItem.setDisabled(!activate);
		workingAreaLocationSelectItem.setDisabled(!activate);
		facilityLocationSelectItem.setDisabled(!activate);
		adquisitionCostCurrencySelectItem.setDisabled(!activate);
		adquisitionCostCurrencyLocalSelectItem.setDisabled(!activate);
		depreciationMethodSelectItem.setDisabled(!activate);
		actualCostCurrencySelectItem.setDisabled(!activate);
		depreciationTimePotSelectItem.setDisabled(!activate);
		lifeTimePotSelectItem.setDisabled(!activate);
		realWarrantySinceSelectItem.setDisabled(!activate);
		realWarrantyPotSelectItem.setDisabled(!activate);
		intWarrantySinceSelectItem.setDisabled(!activate);
		intWarrantyPotSelectItem.setDisabled(!activate);
		// maintenanceLocationSelectItem.setDisabled(!activate);
		maintenanceProviderSelectItem.setDisabled(!activate);
		// itTypeSelectItem.setDisabled(!activate);

		acceptationDateItem.setDisabled(!activate);
		purchaseDateItem.setDisabled(!activate);
		receptionDateItem.setDisabled(!activate);
		installationDateItem.setDisabled(!activate);
		purchaseInvoiceDateItem.setDisabled(!activate);
		purchaseOrderDateItem.setDisabled(!activate);
		contabilizationDateItem.setDisabled(!activate);
		lastDepreciationDate.setDisabled(!activate);
		realWarrantyBeginDate.setDisabled(!activate);
		intWarrantyBeginDate.setDisabled(!activate);
		purchaseInvoiceDateItem.setDisabled(!activate);
		purchaseOrderDateItem.setDisabled(!activate);
		acceptationDateItem.setDisabled(!activate);
	}

	@Override
	public void undo() {
		if (originalEntity == null)
			clear();
		else
			this.set(originalEntity);
		hasUnCommittedChanges = false;
	}

	@Override
	public void update(final GHAAsyncCallback<Eia> callback) {
		if (!hasUnCommittedChanges)
			return;
		final Eia eia = extract();
		if (eia != null) {
			EIAModel.update(eia, new GHAAsyncCallback<Eia>() {
				@Override
				public void onSuccess(Eia result) {
					EIAForm.this.originalEntity = result;
					hasUnCommittedChanges = false;
					notifyEia(result);

					if (callback != null)
						callback.onSuccess(result);
				}
			});
		}
	}
}
