package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.List;

import org.fourgeeks.gha.domain.enu.MaintenanceActivitySubTypeEnum;
import org.fourgeeks.gha.domain.enu.MaintenanceActivityTypeEnum;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenanceActivitySubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAMaintenanceActivityTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityModel;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityResultSet;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityTopForm extends
		GHATopForm<MaintenanceActivityResultSet, MaintenanceActivity> implements
		MaintenanceActivitySelectionListener {

	private GHATextItem nameItem, descriptionItem;
	private GHAMaintenanceActivityTypeSelectItem typeSelectItem;
	private GHAMaintenanceActivitySubTypeSelectItem subTypeSelectItem;

	protected MaintenanceActivity selectedActivity;
	private GHADynamicForm form;

	{
		form = new GHADynamicForm(3, FormType.NORMAL_FORM);

		nameItem = new GHATextItem(GHAStrings.get("name"));
		nameItem.setLength(100);
		descriptionItem = new GHATextItem(GHAStrings.get("description"));
		descriptionItem.setColSpan(3);
		typeSelectItem = new GHAMaintenanceActivityTypeSelectItem();
		subTypeSelectItem = new GHAMaintenanceActivitySubTypeSelectItem();
	}

	/**
	 * @param resultSet
	 * @param tab
	 */
	public MaintenanceActivityTopForm(MaintenanceActivityResultSet resultSet,
			MaintenanceActivityPanel tab) {
		super(resultSet, tab);

		form.setItems(nameItem, typeSelectItem, subTypeSelectItem,
				descriptionItem);

		nameItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionItem.addKeyUpHandler(searchKeyUpHandler);

		form.setAutoFocus(true);
		nameItem.setSelectOnFocus(true);

		addMembers(form, new LayoutSpacer(), sideButtons);
	}

	@Override
	public void activate() {
		toggleForm(true);
		super.activate();
	}

	private void toggleForm(boolean active) {
		nameItem.setDisabled(!active);
		descriptionItem.setDisabled(!active);
		activated = active;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void clear() {
		nameItem.clearValue();
		descriptionItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(false);
		sideButtons.removeMembers(searchButton, cleanButton, deleteButton);
	}

	@Override
	protected void delete() {
		GHAAlertManager.confirm(GHAStrings.get("maitenance-plan"),
				GHAStrings.get("maintenance-plan-delete-confirm"),
				new BooleanCallback() {
					@Override
					public void execute(Boolean value) {
						if (value) {
							MaintenanceActivityModel.delete(
									selectedActivity.getId(),
									new GHAAsyncCallback<Void>() {
										@Override
										public void onSuccess(Void result) {
											containerTab.search();
											clear();
											GHAAlertManager
													.alert("maintenance-delete-success");
										}
									});
						}
					}
				});
	}

	@Override
	public void search() {
		MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
		if (nameItem.getValue() != null)
			maintenanceActivity.setName(nameItem.getValueAsString());
		if (descriptionItem.getValue() != null)
			maintenanceActivity.setDescription(descriptionItem
					.getValueAsString());
		if (subTypeSelectItem.getValue() != null)
			maintenanceActivity.setSubType(MaintenanceActivitySubTypeEnum
					.valueOf(subTypeSelectItem.getValueAsString()));
		if (typeSelectItem.getValue() != null)
			maintenanceActivity.setType(MaintenanceActivityTypeEnum
					.valueOf(typeSelectItem.getValueAsString()));

		search(maintenanceActivity);
	}

	@Override
	public void search(MaintenanceActivity maintenanceActivity) {
		MaintenanceActivityModel.find(maintenanceActivity,
				new GHAAsyncCallback<List<MaintenanceActivity>>() {
					@Override
					public void onSuccess(List<MaintenanceActivity> result) {
						resultSet.setRecords(result, true);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		selectedActivity = maintenanceActivity;
		nameItem.setValue(maintenanceActivity.getName());
		nameItem.setLength(100);
		descriptionItem.setValue(maintenanceActivity.getDescription());
		descriptionItem.setColSpan(3);
		typeSelectItem.setValue(maintenanceActivity.getType());
		subTypeSelectItem.setValue(maintenanceActivity.getSubType());

		deactivate();
		sideButtons.addMember(deleteButton, 0);
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();
	}

}