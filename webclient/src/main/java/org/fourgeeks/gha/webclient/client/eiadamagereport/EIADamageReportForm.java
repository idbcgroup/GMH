package org.fourgeeks.gha.webclient.client.eiadamagereport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.fourgeeks.gha.domain.enu.EiaDamagePriorityEnum;
import org.fourgeeks.gha.domain.enu.EiaDamageStatusEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHASessionData;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextAreaItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATitletextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABpuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAFacilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAObuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.damageandplanification.EIADamageAndPlanificationUtil;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.LogicalDate;
import com.smartgwt.client.util.LogicalTime;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

/**
 * @author naramirez
 * 
 */
public class EIADamageReportForm extends GHAForm<EiaDamageReport> implements
		EIASelectionListener, EiaDamageReportSelectionProducer {
	private GHATextItem codeTextItem, serialTextItem, fixedAssetIdTextItem,
			facilityCodeTextItem;
	private GHAWorkingAreaSelectItem workingAreaSelectItem;
	private GHAFacilitySelectItem facilitySelectItem;
	private GHARoleSelectItem baseRoleSelectItem;
	private GHAObuSelectItem obuSelectItem;

	private GHATitletextItem report_TitleItem, Eia_TitleItem;
	// datos del reporte
	private GHADateItem damageDateItem;
	// private GHATimeItem damageTimeItem;
	private GHATextItem damageTimeItem;
	private GHATextAreaItem damageMotiveTextAreaItem;
	private GHABpuSelectItem userWhoRegistedSelectItem,
			userWhoReportedSelectItem;
	private GHASelectItem damageStatusSelectItem, equipmentCondSelectItem,
			damagePrioritySelectItem;

	private final GHADynamicForm reportForm;

	private List<EiaDamageReportSelectionListener> listeners;
	private Eia eia;

	{ // Global
		// sectionForm = new GHASectionForm(GHAStrings.get("reports"));
		listeners = new ArrayList<EiaDamageReportSelectionListener>();

		// report data Form Items
		Eia_TitleItem = new GHATitletextItem("Datos del Equipo:", 3);
		// report data Form Items
		report_TitleItem = new GHATitletextItem(
				"Datos del Reporte de Equipo Dañado:", 3);
		damageDateItem = new GHADateItem("Fecha del daño o falla",
				changedHandler);
		damageDateItem.setValidators(damageDateItem.getValidator());

		damageTimeItem = new GHATextItem("Hora del daño o falla", false,
				changedHandler); // new GHATimeItem("Hora del daño o falla",
		// changedHandler);
		// damageTimeItem.setKeyPressFilter("[0-2][0-9]:[0-5][0-9]");
		// damageTimeItem.setMask("");
		// ("[0-2][0-9]:[0-5][0-9]");

		RegExpValidator textValidator = new RegExpValidator();
		textValidator.setErrorMessage("La fecha introducida es Erronea");
		// textValidator.setExpression("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		textValidator.setExpression("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		damageTimeItem.setValidators(textValidator);
		// damageTimeItem.setValidateOnChange(true);
		damageTimeItem.setShowErrorIcon(true);

		damageStatusSelectItem = new GHASelectItem("Estado", true,
				changedHandler);
		equipmentCondSelectItem = new GHASelectItem("Condición del equipo",
				true, changedHandler);
		damagePrioritySelectItem = new GHASelectItem("Prioridad", true,
				changedHandler);
		userWhoReportedSelectItem = new GHABpuSelectItem(
				"Usuario que lo reportó", true, changedHandler);
		userWhoRegistedSelectItem = new GHABpuSelectItem(
				"Usuario que lo registró", true, changedHandler);
		damageMotiveTextAreaItem = new GHATextAreaItem(
				"Motivo del daño o falla", changedHandler);
		damageMotiveTextAreaItem.setColSpan(2);

		// Information Form Items

		codeTextItem = new GHATextItem("Código", false);
		codeTextItem.setLength(20);
		serialTextItem = new GHATextItem("Serial", false);
		serialTextItem.setLength(20);
		fixedAssetIdTextItem = new GHATextItem("Id Activo Fijo", false);
		fixedAssetIdTextItem.setLength(19);
		obuSelectItem = new GHAObuSelectItem();
		obuSelectItem.setDisabled(true);
		baseRoleSelectItem = new GHARoleSelectItem();
		baseRoleSelectItem.setDisabled(true);

		// workingAreaSelectItem = new GHAWorkingAreaSelectItem("Nombre");
		workingAreaSelectItem = new GHAWorkingAreaSelectItem("Area de Trabajo");
		workingAreaSelectItem.setDisabled(true);

		facilityCodeTextItem = new GHATextItem("Institución", false);
		// facilitySelectItem = new GHAFacilitySelectItem("Nombre");
		facilitySelectItem = new GHAFacilitySelectItem("Instalación");
		facilitySelectItem.setDisabled(true);
	}

	/**
	 * Constructor
	 */
	public EIADamageReportForm() {
		super();
		GHAUiHelper.addGHAResizeHandler(this);

		// infoBasicaForm = getInfoBasicaForm();
		// garantiasForm = getAdquisicionForm();
		// ubicacionForm = getUbicacionForm();
		reportForm = getReportForm();

		addMember(reportForm);
		// Fillers

		fillReportDataSelects();

		// sectionForm.openFirst();
	}

	@Override
	public void activate() {
		toggleForm(true);
	}

	@Override
	public void addEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		listeners.add(eiaDamageReportSelectionListener);
	}

	@Override
	public void clear() {
		super.clear();
		// clean text fields

		codeTextItem.clearValue();
		serialTextItem.clearValue();
		serialTextItem.validate();
		fixedAssetIdTextItem.clearValue();
		facilityCodeTextItem.clearValue();
		// clean select fields
		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
		workingAreaSelectItem.clearValue();
		facilitySelectItem.clearValue();

		// report data items
		damageDateItem.clearValue();
		damageTimeItem.clearValue();
		damageStatusSelectItem.clearValue();
		equipmentCondSelectItem.clearValue();
		damagePrioritySelectItem.clearValue();
		userWhoReportedSelectItem.clearValue();
		userWhoRegistedSelectItem.clearValue();
		damageMotiveTextAreaItem.clearValue();

		reportForm.clearErrors(true);

	}

	@Override
	public void deactivate() {
		toggleForm(false);
	}

	private EiaDamageReport extract() {
		// if (!hasUnCommittedChanges)
		// return null;

		final EiaDamageReport eiaDamageReport = new EiaDamageReport();

		eia.setState(EiaStateEnum.DAMAGED);
		eiaDamageReport.setEia(eia);

		eiaDamageReport.setDamageMotive(damageMotiveTextAreaItem
				.getValueAsString());

		if (damageStatusSelectItem.getValueAsString() != null)
			eiaDamageReport.setDamageStatus(EiaDamageStatusEnum
					.valueOf(damageStatusSelectItem.getValueAsString()));

		if (equipmentCondSelectItem.getValueAsString() != null)
			eiaDamageReport.setEiaCondition(EiaStateEnum
					.valueOf(equipmentCondSelectItem.getValueAsString()));

		if (damagePrioritySelectItem.getValueAsString() != null)
			eiaDamageReport.setPriority(EiaDamagePriorityEnum
					.valueOf(damagePrioritySelectItem.getValueAsString()));

		if (userWhoRegistedSelectItem.getValueAsString() != null) {
			final Bpu userWhoRegistered = new Bpu();
			userWhoRegistered.setId(Long.valueOf(userWhoRegistedSelectItem
					.getValueAsString()));
			eiaDamageReport.setUserWhoRegistered(userWhoRegistered);
		}

		if (userWhoReportedSelectItem.getValueAsString() != null) {
			final Bpu userWhoReported = new Bpu();
			userWhoReported.setId(Long.valueOf(userWhoReportedSelectItem
					.getValueAsString()));
			eiaDamageReport.setUserWhoReported(userWhoReported);
		}

		LogicalTime time = null;

		if (damageTimeItem.validate()) {
			// time = damageTimeItem.getValueAsLogicalTime();
		}

		final LogicalDate date = damageDateItem.getValueAsLogicalDate();

		final Timestamp timestamp = EIADamageAndPlanificationUtil.getTimestamp(
				date, time);

		eiaDamageReport.setDateTimestamp(timestamp);

		// VALIDANDO LOS DATOS
		Set<ConstraintViolation<EiaDamageReport>> violations = null;
		violations = validator.validate(eiaDamageReport);

		if (reportForm.validate() && violations.isEmpty())
			return eiaDamageReport;
		else {
			final List<String> violationsList = new ArrayList<String>();
			for (final ConstraintViolation<EiaDamageReport> violation : violations)
				violationsList.add(violation.getMessage());
			// GHAAlertManager.alert(violationsList);

			String validation_message[] = { "damageStatus-not-null",
					"eia-condition-not-null", "userWhoReported-not-null",
					"userWhoRegistered-not-null", "damagePriority-not-null" };

			for (int i = 0; i < validation_message.length; i++) {
				if (violationsList.contains(validation_message[i])) {
					GHAErrorMessageProcessor.alert(validation_message[i]);
					return null;
				}
			}
			// GHAErrorMessageProcessor.alert(violationsList.get(0));
		}
		return null;
	}

	private void fillReportDataSelects() {
		damageStatusSelectItem.setValueMap(EiaDamageStatusEnum.toValueMap());
		equipmentCondSelectItem.setValueMap(EiaStateEnum.toValueMapCondEia());
		damagePrioritySelectItem
				.setValueMap(EiaDamagePriorityEnum.toValueMap());
		damagePrioritySelectItem.setValue(EiaDamagePriorityEnum.NORMAL.name());
	}

	/**
	 * @return
	 */
	private GHADynamicForm getReportForm() {
		final GHADynamicForm reportForm = new GHADynamicForm(4,
				FormType.NORMAL_FORM);
		//
		//
		reportForm.setItems(

		Eia_TitleItem, new GHASpacerItem(), codeTextItem, serialTextItem,
				fixedAssetIdTextItem, new GHASpacerItem(), obuSelectItem,
				baseRoleSelectItem, facilitySelectItem, new GHASpacerItem(),
				workingAreaSelectItem, facilityCodeTextItem,
				new GHASpacerItem(), report_TitleItem, new GHASpacerItem(),
				damageStatusSelectItem, equipmentCondSelectItem,
				new GHASpacerItem(), new GHASpacerItem(), damageDateItem,
				damageTimeItem, userWhoReportedSelectItem, new GHASpacerItem(),
				userWhoRegistedSelectItem, damagePrioritySelectItem,
				new GHASpacerItem(), new GHASpacerItem(),
				damageMotiveTextAreaItem, new GHASpacerItem());

		return reportForm;
	}

	@Override
	public void hide() {
		super.hide();
		reportForm.hide();
		// sectionForm.hide();
	}

	@Override
	public void notifyEiaDamageReport(EiaDamageReport eiaDamageReport) {
		for (final EiaDamageReportSelectionListener listener : listeners)
			listener.select(eiaDamageReport);
	}

	@Override
	public void onResize(ResizeEvent arg0) {
		// infoBasicaForm.resize();
		// garantiasForm.resize();
		// ubicacionForm.resize();
		reportForm.resize();
	}

	/**
	 * Open the first section (view) of the sectionForm
	 */
	public void openFirstSeItemction() {
		// sectionForm.openFirst();
	}

	@Override
	public void removeEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		listeners.remove(eiaDamageReportSelectionListener);
	}

	@Override
	public void save(final GHAAsyncCallback<EiaDamageReport> callback) {
		final EiaDamageReport eiaDamageReport = extract();
		if (eiaDamageReport != null) {
			EiaDamageReportModel.save(eiaDamageReport,
					new GHAAsyncCallback<EiaDamageReport>() {
						@Override
						public void onSuccess(EiaDamageReport result) {
							hasUnCommittedChanges = false;
							notifyEiaDamageReport(result);
							clear();
							if (callback != null) {
								callback.onSuccess(result);
							}

						}
					});
		}
	}

	@Override
	public void select(Eia eia) {
		this.eia = eia;

		if (eia.getObu().getBpi().getInstitution().getName() != null) {
			facilityCodeTextItem.setValue(eia.getObu().getBpi()
					.getInstitution().getName());
			facilityCodeTextItem.setDisabled(true);
		}

		Bpu loggedUser = GHASessionData.getLoggedUser();
		userWhoRegistedSelectItem.setValue(loggedUser.getId());
		// basic information
		codeTextItem.setValue(eia.getCode());
		serialTextItem.setValue(eia.getSerialNumber());
		fixedAssetIdTextItem.setValue(eia.getFixedAssetIdentifier());

		obuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();

		if (eia.getObu() != null) {
			obuSelectItem.setValue(eia.getObu().getId());
		}
		if (eia.getResponsibleRole() != null) {
			baseRoleSelectItem.setValue(eia.getResponsibleRole().getId());
		}

		workingAreaSelectItem.clearValue();
		facilitySelectItem.clearValue();

		if (eia.getFacility() != null) {
			facilitySelectItem.setValue(eia.getFacility().getId());
			// facilityCodeTextItem.setValue(eia.getFacility().getId());
		}

	}

	@Override
	public void set(EiaDamageReport entity) {
	}

	@Override
	public void show() {
		reportForm.show();
		super.show();
	}

	private void toggleForm(boolean activate) {
		damageDateItem.setDisabled(!activate);
		damageTimeItem.setDisabled(!activate);
		damageStatusSelectItem.setDisabled(!activate);
		equipmentCondSelectItem.setDisabled(!activate);
		damagePrioritySelectItem.setDisabled(!activate);
		userWhoReportedSelectItem.setDisabled(!activate);
		userWhoRegistedSelectItem.setDisabled(!activate);
		damageMotiveTextAreaItem.setDisabled(!activate);
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
	public void update(GHAAsyncCallback<EiaDamageReport> callback) {
	}
}
