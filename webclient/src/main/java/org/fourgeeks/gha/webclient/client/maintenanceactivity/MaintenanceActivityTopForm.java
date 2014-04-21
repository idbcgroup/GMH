package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.List;

import org.fourgeeks.gha.domain.Activity;
import org.fourgeeks.gha.domain.ActivityType;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivitySubTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivityTypeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATopForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol.SubprotocolAndChecklistModel;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolModel;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
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
	private GHAActivityTypeSelectItem typeSelectItem;
	private GHAActivitySubTypeSelectItem subTypeSelectItem;

	protected MaintenanceActivity selectedActivity;
	private GHADynamicForm form;

	{
		form = new GHADynamicForm(3, FormType.NORMAL_FORM);

		nameItem = new GHATextItem(GHAStrings.get("name"));
		nameItem.setLength(100);
		descriptionItem = new GHATextItem(GHAStrings.get("description"));
		descriptionItem.setColSpan(3);
		typeSelectItem = new GHAActivityTypeSelectItem();
		subTypeSelectItem = new GHAActivitySubTypeSelectItem();

		typeSelectItem.addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				subTypeSelectItem.clearValue();
				subTypeSelectItem.fill(typeSelectItem.getValueAsActivityType());

				if (typeSelectItem.getValueAsString() == null)
					subTypeSelectItem.setDisabled(true);
				else
					subTypeSelectItem.setDisabled(false);
			}
		});
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

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void clear() {
		nameItem.clearValue();
		descriptionItem.clearValue();
		typeSelectItem.clearValue();
		subTypeSelectItem.clearValue();
	}

	@Override
	public void deactivate() {
		toggleForm(false);
		sideButtons.removeMembers(searchButton, cleanButton, deleteButton);
	}

	@Override
	protected void delete() {

		MaintenanceProtocolModel.findByMantenanceActivity(selectedActivity,
				new GHAAsyncCallback<List<MaintenanceProtocol>>() {
					@Override
					public void onSuccess(List<MaintenanceProtocol> result) {

						if (result.isEmpty()) {
							SubprotocolAndChecklistModel.findByParentActivity(
									selectedActivity.getActivity(),
									new GHAAsyncCallback<List<SubProtocolAndChecklist>>() {
										@Override
										public void onSuccess(
												List<SubProtocolAndChecklist> result) {

											if (result.isEmpty()) {

												GHAErrorMessageProcessor
														.confirm(
																"maintenance-activity-delete-confirm",
																new BooleanCallback() {
																	@Override
																	public void execute(
																			Boolean value) {
																		if (value) {
																			MaintenanceActivityModel
																					.delete(selectedActivity
																							.getId(),
																							new GHAAsyncCallback<Void>() {
																								@Override
																								public void onSuccess(
																										Void result) {
																									containerTab
																											.search();
																									clear();
																									GHAErrorMessageProcessor
																											.alert("maintenance-activity-delete-success");
																								}
																							});
																		}
																	}
																});
											} else {
												GHAErrorMessageProcessor
														.alert("maintenance-activity-protocolo-associated");
											}

										}
									});

						} else {

							GHAErrorMessageProcessor
									.alert("maintenance-activity-plan-associated");
						}

					}
				});

	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();
	}

	@Override
	public void search() {
		final MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
		final Activity activity = new Activity();

		if (nameItem.getValue() != null)
			activity.setName(nameItem.getValueAsString());
		if (descriptionItem.getValue() != null)
			activity.setDescription(descriptionItem.getValueAsString());

		if (typeSelectItem.getValue() != null) {
			Long value = Long.valueOf(typeSelectItem.getValueAsString());
			activity.setType(new ActivityType(value));
		}
		if (subTypeSelectItem.getValue() != null) {
			Long value = Long.valueOf(subTypeSelectItem.getValueAsString());
			activity.setSubType(new ActivityType(value));
		}

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
		final Activity activity = selectedActivity.getActivity();

		nameItem.setValue(activity.getName());
		descriptionItem.setValue(activity.getDescription());

		ActivityType type = activity.getType();
		if (type != null) {
			typeSelectItem.setValue(type.getId());
			subTypeSelectItem.fill(type);
		} else
			typeSelectItem.clearValue();

		ActivityType subType = activity.getSubType();
		if (subType != null)
			subTypeSelectItem.setValue(subType.getId());
		else
			subTypeSelectItem.clearValue();

		deactivate();
		sideButtons.addMember(deleteButton, 0);
	}

	private void toggleForm(boolean active) {
		nameItem.setDisabled(!active);
		descriptionItem.setDisabled(!active);
		typeSelectItem.setDisabled(!active);
		subTypeSelectItem.setDisabled(!active);
		activated = active;
	}

}