package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.List;

import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.ActivitySubCategoryEnum;
import org.fourgeeks.gha.domain.gmh.Activity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHATopForm;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivityCategorySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivitySubCategorySelectItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;

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
	private GHAActivityCategorySelectItem categorySelectItem;
	private GHAActivitySubCategorySelectItem subCategorySelectItem;

	protected MaintenanceActivity selectedActivity;
	private GHADynamicForm form;

	{
		form = new GHADynamicForm(3, FormType.NORMAL_FORM);

		nameItem = new GHATextItem(GHAStrings.get("name"));
		nameItem.setLength(100);
		descriptionItem = new GHATextItem(GHAStrings.get("description"));
		descriptionItem.setColSpan(3);
		categorySelectItem = new GHAActivityCategorySelectItem();
		subCategorySelectItem = new GHAActivitySubCategorySelectItem();
	}

	/**
	 * @param resultSet
	 * @param tab
	 */
	public MaintenanceActivityTopForm(MaintenanceActivityResultSet resultSet,
			MaintenanceActivityPanel tab) {
		super(resultSet, tab);

		form.setItems(nameItem, categorySelectItem, subCategorySelectItem,
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
		categorySelectItem.setDisabled(!active);
		subCategorySelectItem.setDisabled(!active);
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
		categorySelectItem.clearValue();
		subCategorySelectItem.clearValue();
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
		Activity activity = new Activity();

		if (nameItem.getValue() != null)
			activity.setName(nameItem.getValueAsString());
		if (descriptionItem.getValue() != null)
			activity.setDescription(descriptionItem.getValueAsString());
		if (subCategorySelectItem.getValue() != null)
			activity.setSubCategory(ActivitySubCategoryEnum
					.valueOf(subCategorySelectItem.getValueAsString()));
		if (categorySelectItem.getValue() != null)
			activity.setCategory(ActivityCategoryEnum
					.valueOf(categorySelectItem.getValueAsString()));

		maintenanceActivity.setActivity(activity);

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
		Activity activity = selectedActivity.getActivity();

		nameItem.setValue(activity.getName());
		descriptionItem.setValue(activity.getDescription());
		categorySelectItem.setValue(activity.getCategory());
		subCategorySelectItem.setValue(activity.getSubCategory());

		deactivate();
		sideButtons.addMember(deleteButton, 0);
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();
	}

}