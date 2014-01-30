package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.ActivityCategoryEnum;
import org.fourgeeks.gha.domain.enu.ActivitySubCategoryEnum;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.GHAUtil;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivityCategorySelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.selectitems.GHAActivitySubCategorySelectItem;
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
 * @author alacret, naramirez
 * 
 */
public class MaintenanceActivitySearchForm extends
		GHASearchForm<MaintenanceActivity> implements
		MaintenanceActivitySelectionListener,
		MaintenanceActivitySelectionProducer {

	private GHATextItem nameItem, descriptionItem;
	private GHAActivityCategorySelectItem typeSelectItem;
	private GHAActivitySubCategorySelectItem subTypeSelectItem;

	private final GHADynamicForm form;
	private final MaintenanceActivityResultSet resultSet;

	{
		form = new GHADynamicForm(3, FormType.NORMAL_FORM);

		nameItem = new GHATextItem(GHAStrings.get("name"));
		nameItem.setLength(100);
		descriptionItem = new GHATextItem(GHAStrings.get("description"));
		descriptionItem.setColSpan(3);
		typeSelectItem = new GHAActivityCategorySelectItem();
		subTypeSelectItem = new GHAActivitySubCategorySelectItem();

		resultSet = new MaintenanceActivityResultSet(
				ResultSetContainerType.SEARCH_FORM);
		resultSet
				.addMaintenanceActivitySelectionListener(new MaintenanceActivitySelectionListener() {
					@Override
					public void select(MaintenanceActivity maintenanceActivity) {
						blackList = null;
						hide();
					}
				});
	}

	/**
	 * 
	 * @param title
	 * @param isSubProtocol
	 */
	public MaintenanceActivitySearchForm(String title, boolean isSubProtocol) {
		this(title);
	}

	/**
	 * Create a search form to select a MaintenancePlan
	 * 
	 * @param title
	 *            the title of the search form
	 */
	public MaintenanceActivitySearchForm(String title) {
		super(title);

		form.setItems(nameItem, typeSelectItem, subTypeSelectItem,
				descriptionItem);

		nameItem.addKeyUpHandler(searchKeyUpHandler);
		descriptionItem.addKeyUpHandler(searchKeyUpHandler);

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				searchClickHandler), new GHACleanButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clean();
			}
		}), new GHACancelButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				clean();
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionProducer
	 * #addMaintenanceActivitySelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener selectionListener) {
		resultSet.addMaintenanceActivitySelectionListener(selectionListener);
	}

	/**
	 * clean the form item and the data of the grid
	 */
	public void clean() {
		form.clearValues();
		resultSet.clean();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionProducer
	 * #notifyMaintenanceActivity(org.fourgeeks
	 * .gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public void notifyMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm#onResize
	 * (com.google.gwt.event.logical.shared.ResizeEvent)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionProducer
	 * #removeMaintenanceActivitySelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener selectionListener) {
		resultSet.removeMaintenanceActivitySelectionListener(selectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm#search()
	 */
	@Override
	public void search() {
		MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
		if (nameItem.getValue() != null)
			maintenanceActivity.setName(nameItem.getValueAsString());
		if (descriptionItem.getValue() != null)
			maintenanceActivity.setDescription(descriptionItem
					.getValueAsString());
		if (subTypeSelectItem.getValue() != null)
			maintenanceActivity.setSubCategory(ActivitySubCategoryEnum
					.valueOf(subTypeSelectItem.getValueAsString()));
		if (typeSelectItem.getValue() != null)
			maintenanceActivity.setCategory(ActivityCategoryEnum
					.valueOf(typeSelectItem.getValueAsString()));

		search(maintenanceActivity);
	}

	/**
	 * Search a list of {@link MaintenanceActivity} entities that match with the
	 * values of the entity argument
	 * 
	 * @param activity
	 *            the entity with the vaules
	 */
	private void search(final MaintenanceActivity activity) {
		MaintenanceActivityModel.find(activity,
				new GHAAsyncCallback<List<MaintenanceActivity>>() {
					@Override
					public void onSuccess(List<MaintenanceActivity> result) {
						List<MaintenanceActivity> newList = null;
						if (blackList != null) {
							List<AbstractEntity> tmpList = GHAUtil
									.binarySearchFilterEntity(result, blackList);
							List<MaintenanceActivity> newTmpList = new ArrayList<MaintenanceActivity>();
							for (AbstractEntity entity : tmpList)
								newTmpList.add((MaintenanceActivity) entity);
							newList = newTmpList;
						} else
							newList = result;

						resultSet.setRecords(newList, false);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		search(maintenanceActivity);
	}
}
