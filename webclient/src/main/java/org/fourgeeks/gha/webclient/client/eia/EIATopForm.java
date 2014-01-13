package org.fourgeeks.gha.webclient.client.eia;

import java.util.Date;
import java.util.List;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.ess.Role;
import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHABpiSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAEiaStateSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAFacilitySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAObuSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHARoleSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAWorkingAreaSelectItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATopForm extends GHATopForm<EiaResultSet, Eia> implements
		EIASelectionListener {

	private GHATextItem serialNumber;
	private GHATextItem fixedAssetIdentifier;
	private GHATextItem warrantyState;
	private GHATextItem warrantyTimeLeft;
	private GHAEiaStateSelectItem stateSelectItem;
	private GHAObuSelectItem obuSelectItem;
	private GHABpiSelectItem bpiObuSelectItem;
	private GHARoleSelectItem baseRoleSelectItem;
	private GHAWorkingAreaSelectItem workingAreaLocationSelectItem;
	private GHAFacilitySelectItem facilityLocationSelectItem;
	private GHADynamicForm form;
	private int HOURS_DAY = 24;
	private int DAYS_WEEK = 7;
	private int MONTHS_SEMESTER = 6;
	private int MONTHS_YEAR = 12;

	private Eia selectedEia;

	{
		serialNumber = new GHATextItem(GHAStrings.get("serial"), false);
		fixedAssetIdentifier = new GHATextItem(
				GHAStrings.get("fixed-asset-identifier"), false);
		warrantyState = new GHATextItem(GHAStrings.get("warranty-state"), false);
		warrantyTimeLeft = new GHATextItem(GHAStrings.get("on-warranty"), false);
		warrantyTimeLeft.setHint(GHAStrings.get("month(s)"));

		stateSelectItem = new GHAEiaStateSelectItem();
		stateSelectItem.disable();

		workingAreaLocationSelectItem = new GHAWorkingAreaSelectItem();
		workingAreaLocationSelectItem.disable();

		facilityLocationSelectItem = new GHAFacilitySelectItem();
		facilityLocationSelectItem.disable();

		obuSelectItem = new GHAObuSelectItem();
		obuSelectItem.disable();

		bpiObuSelectItem = new GHABpiSelectItem();
		bpiObuSelectItem.disable();

		baseRoleSelectItem = new GHARoleSelectItem();
		baseRoleSelectItem.disable();

		form = new GHADynamicForm(5, FormType.NORMAL_FORM);
	}

	/**
	 * @param resultSet
	 * @param eiaTab
	 */
	public EIATopForm(EiaResultSet resultSet, EIAPanel eiaTab) {
		super(resultSet, eiaTab);

		form.setItems(serialNumber, fixedAssetIdentifier, stateSelectItem,
				bpiObuSelectItem, warrantyState, workingAreaLocationSelectItem,
				facilityLocationSelectItem, obuSelectItem, baseRoleSelectItem,
				warrantyTimeLeft);
		form.setAutoFocus(true);
		serialNumber.setSelectOnFocus(true);
		addMembers(form, new LayoutSpacer(), sideButtons);
	}

	private void toggleForm(boolean disabled) {
		serialNumber.setDisabled(disabled);
		fixedAssetIdentifier.setDisabled(disabled);
		stateSelectItem.setDisabled(disabled);
		workingAreaLocationSelectItem.setDisabled(disabled);
		facilityLocationSelectItem.setDisabled(disabled);
		obuSelectItem.setDisabled(disabled);
		bpiObuSelectItem.setDisabled(disabled);
		baseRoleSelectItem.setDisabled(disabled);
		activated = !disabled;
	}

	@Override
	public void activate() {
		toggleForm(false);
		super.activate();

	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void deactivate() {
		toggleForm(true);
		sideButtons.removeMembers(searchButton, cleanButton, deleteButton);
	}

	@Override
	protected void delete() {
		GHAAlertManager.confirm(GHAStrings.get("eia"),
				GHAStrings.get("eia-delete-success"), new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							EIAModel.delete(selectedEia.getId(),
									new GHAAsyncCallback<Boolean>() {
										@Override
										public void onSuccess(Boolean result) {
											containerTab.search();
											clear();
											GHAAlertManager
													.alert("eia-delete-success");
										}
									});
						}
					}
				});
	}

	/**
	 * 
	 */
	@Override
	public void search() {
		super.search();
		Obu obu = new Obu();
		Eia eia = new Eia();
		eia.setState(null);

		if (serialNumber.getValue() != null)
			eia.setSerialNumber(serialNumber.getValueAsString());
		if (fixedAssetIdentifier != null)
			eia.setFixedAssetIdentifier(fixedAssetIdentifier.getValueAsString());
		if (obuSelectItem.getValue() != null) {
			obu.setId(Long.valueOf(obuSelectItem.getValueAsString()));
			eia.setObu(obu);
		}
		if (bpiObuSelectItem.getValue() != null) {
			Bpi bpi = new Bpi(Long.valueOf(bpiObuSelectItem.getValueAsString()));
			obu.setBpi(bpi);
			eia.setObu(obu);
		}
		if (baseRoleSelectItem.getValue() != null) {
			Role baseRole = new Role();
			baseRole.setId(Integer.valueOf(baseRoleSelectItem
					.getValueAsString()));
			eia.setResponsibleRole(baseRole);
		}
		if (stateSelectItem.getValue() != null) {
			eia.setState(EiaStateEnum.valueOf(stateSelectItem
					.getValueAsString()));
		}
		if (facilityLocationSelectItem.getValue() != null) {
			eia.setWorkingArea(null);
			eia.setFacility(new Facility(Integer
					.valueOf(facilityLocationSelectItem.getValueAsString())));
		}
		if (workingAreaLocationSelectItem.getValue() != null) {
			eia.setFacility(null);
			eia.setWorkingArea(new WorkingArea(Integer
					.valueOf(workingAreaLocationSelectItem.getValueAsString())));
		}
		search(eia);
	}

	@Override
	public void search(Eia eia) {
		EIAModel.find(eia, new GHAAsyncCallback<List<Eia>>() {
			@Override
			public void onSuccess(List<Eia> result) {
				resultSet.setRecords(result, true);
			}
		});
	}

	@Override
	public void select(Eia eia) {
		super.search();
		this.selectedEia = eia;

		if (eia.getSerialNumber() != null)
			serialNumber.setValue(eia.getSerialNumber());
		if (eia.getFixedAssetIdentifier() != null)
			fixedAssetIdentifier.setValue(eia.getFixedAssetIdentifier());
		if (eia.getObu() != null) {
			Obu obu = eia.getObu();
			obuSelectItem.setValue(obu.getId());
			if (obu.getBpi() != null)
				bpiObuSelectItem.setValue(obu.getBpi().getId());
		}
		if (eia.getResponsibleRole() != null)
			baseRoleSelectItem.setValue(eia.getResponsibleRole().getId());
		if (eia.getState() != null)
			stateSelectItem.setValue(eia.getState().name());
		if (eia.getWorkingArea() != null) {
			workingAreaLocationSelectItem
					.setValue(eia.getWorkingArea().getId());
		}
		if (eia.getFacility() != null) {
			facilityLocationSelectItem.setValue(eia.getFacility().getId());
		}

		Integer time = getWarrantyTimeLeft();
		if (time == null) {
			warrantyState.setValue(GHAStrings.get("N/A"));
			warrantyTimeLeft.setValue(GHAStrings.get("N/A"));
		} else {
			if (time <= 0) {
				warrantyState.setValue(GHAStrings.get("eia-expired-warranty"));
				warrantyTimeLeft.setValue(0);
			} else {
				warrantyState.setValue(GHAStrings.get("eia-existing-warranty"));
				warrantyTimeLeft.setValue(time);
			}
		}
		// lock fields of the topform
		deactivate();
		sideButtons.addMember(deleteButton, 0);
	}

	@Override
	public void clear() {
		// first check if the topform is active for search
		if (!this.activated)
			return;

		serialNumber.clearValue();
		fixedAssetIdentifier.clearValue();
		stateSelectItem.clearValue();
		workingAreaLocationSelectItem.clearValue();
		facilityLocationSelectItem.clearValue();
		obuSelectItem.clearValue();
		bpiObuSelectItem.clearValue();
		baseRoleSelectItem.clearValue();
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();
	}

	/**
	 * Time left (in months) of the selected eia´s warranty
	 * 
	 * @return The time. <b>null</b> if some information to calculate the
	 *         remaining time is missing.
	 */
	private Integer getWarrantyTimeLeft() {

		if (selectedEia.getIntWarrantyBegin() == null
				|| selectedEia.getRealWarrantyBegin() == null)
			return null;

		Date intWB = (Date) selectedEia.getIntWarrantyBegin().clone();
		Date realWB = (Date) selectedEia.getRealWarrantyBegin().clone();
		Date warrantyBegin;
		TimePeriodEnum warrantyPot;
		int warrantyTime;

		if (intWB.equals(realWB)) {
			warrantyBegin = realWB;
			warrantyPot = selectedEia.getRealWarrantyPoT();
			warrantyTime = selectedEia.getRealWarrantyTime();
		} else {
			warrantyBegin = intWB;
			warrantyPot = selectedEia.getIntWarrantyPoT();
			warrantyTime = selectedEia.getIntWarrantyTime();
		}

		int months = 0;
		switch (warrantyPot) {
		case HOURS:
			int hoursToDays = (int) Math
					.ceil((double) warrantyTime / HOURS_DAY);
			months = getMonthsLeft(warrantyBegin, hoursToDays,
					TimePeriodEnum.DAYS);
			break;
		case DAYS:
			months = getMonthsLeft(warrantyBegin, warrantyTime,
					TimePeriodEnum.DAYS);
			break;
		case WEEKS:
			months = getMonthsLeft(warrantyBegin, DAYS_WEEK * warrantyTime,
					TimePeriodEnum.DAYS);
			break;
		case MONTHS:
			months = getMonthsLeft(warrantyBegin, warrantyTime,
					TimePeriodEnum.MONTHS);
			break;
		case SEMESTERS:
			months = getMonthsLeft(warrantyBegin, MONTHS_SEMESTER
					* warrantyTime, TimePeriodEnum.MONTHS);
			break;
		case YEARS:
			months = getMonthsLeft(warrantyBegin, MONTHS_YEAR * warrantyTime,
					TimePeriodEnum.MONTHS);
			break;
		}
		return new Integer(months);
	}

	private int getMonthsLeft(Date warranty, int warrantyTime,
			TimePeriodEnum time) {
		if (time == TimePeriodEnum.DAYS) {
			CalendarUtil.addDaysToDate(warranty, warrantyTime);
		} else if (time == TimePeriodEnum.MONTHS) {
			CalendarUtil.addMonthsToDate(warranty, warrantyTime);
		}
		Date now = new Date();

		int monthsLeft = (int) Math.ceil((double) (warranty.getTime() - now
				.getTime()) / (1000L * 60 * 60 * 24 * 30));
		return monthsLeft;
	}
}