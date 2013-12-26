package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAPeriodOfTimeSelectItem;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACancelButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACleanButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHADynamicForm.FormType;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class MaintenancePlanSearchForm extends GHASearchForm<MaintenancePlan>
		implements MaintenancePlanSelectionListener,
		MaintenancePlanSelectionProducer {

	private final GHADynamicForm form;
	private GHATextItem nameItem, descriptionItem, frequencyItem;

	private GHAPeriodOfTimeSelectItem periodOfTimeSelectItem;
	private final MaintenancePlanResultSet resultSet;

	{
		form = new GHADynamicForm(3, FormType.NORMAL_FORM);

		nameItem = new GHATextItem(GHAStrings.get("name"));
		nameItem.setLength(100);
		frequencyItem = new GHATextItem(GHAStrings.get("frequency"));
		periodOfTimeSelectItem = new GHAPeriodOfTimeSelectItem();
		periodOfTimeSelectItem.setDefaultValue((String) null);
		descriptionItem = new GHATextItem(GHAStrings.get("description"));
		descriptionItem.setColSpan(3);

		nameItem.addKeyUpHandler(searchKeyUpHandler);
		frequencyItem.addKeyUpHandler(searchKeyUpHandler);
		periodOfTimeSelectItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionItem.addKeyUpHandler(searchKeyUpHandler);

		resultSet = new MaintenancePlanResultSet(
				ResultSetContainerType.SEARCH_FORM);
		resultSet
				.addMaintenancePlanSelectionListener(new MaintenancePlanSelectionListener() {

					@Override
					public void select(MaintenancePlan maintenancePlan) {
						hide();
					}
				});
	}

	/**
	 * Create a search form to select a MaintenancePlan
	 * 
	 * @param title
	 *            the title of the search form
	 */
	public MaintenancePlanSearchForm(String title) {
		super(title);

		form.setItems(nameItem, frequencyItem, periodOfTimeSelectItem,
				descriptionItem);

		nameItem.addKeyUpHandler(searchKeyUpHandler);
		frequencyItem.addKeyUpHandler(searchKeyUpHandler);
		periodOfTimeSelectItem.addKeyUpHandler(searchKeyUpHandler);

		form.setAutoFocus(true);
		nameItem.setSelectOnFocus(true);

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				searchClickHandler), new GHACleanButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clean();
			}
		}), new GHACancelButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"), resultSet);
	}

	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		resultSet
				.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);

	}

	/**
	 * clean the form item and the data of the grid
	 */
	public void clean() {
		form.clearValues();
		resultSet.clean();
	}

	/**
	 * @param maintenancePlan
	 */
	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
	}

	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		form.resize();
	}

	@Override
	public void open() {
		resultSet.setVisible(true);
		super.open();
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		resultSet
				.removeMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void search() {
		MaintenancePlan maintenancePlan = new MaintenancePlan();
		if (nameItem.getValue() != null)
			maintenancePlan.setName(nameItem.getValueAsString());
		if (descriptionItem.getValue() != null)
			maintenancePlan.setDescription(descriptionItem.getValueAsString());
		if (frequencyItem.getValue() != null)
			maintenancePlan.setFrequency(Integer.parseInt(frequencyItem
					.getValueAsString()));
		if (periodOfTimeSelectItem.getValue() != null)
			maintenancePlan.setPot(TimePeriodEnum
					.valueOf(periodOfTimeSelectItem.getValueAsString()));
		search(maintenancePlan);
	}

	private void search(final MaintenancePlan maintenancePlan) {
		MaintenancePlanModel.find(maintenancePlan,
				new GHAAsyncCallback<List<MaintenancePlan>>() {
					@Override
					public void onSuccess(List<MaintenancePlan> result) {
						List<MaintenancePlan> newList = null;
						if (blackList != null) {
							List<AbstractEntity> tmpList = GHAUtil
									.binarySearchFilterEntity(result, blackList);
							List<MaintenancePlan> newTmpList = new ArrayList<MaintenancePlan>();
							for (AbstractEntity entity : tmpList)
								newTmpList.add((MaintenancePlan) entity);
							newList = newTmpList;
						} else
							newList = result;

						resultSet.setRecords(newList, false);
					}
				});
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		search(maintenancePlan);
	}
}
