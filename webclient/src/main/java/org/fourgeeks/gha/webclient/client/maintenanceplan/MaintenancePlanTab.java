package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.TabStatus;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.SearchListener;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader.Option;

import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret
 * 
 */
public class MaintenancePlanTab extends GHATab implements
		MaintenancePlanSelectionListener, MaintenancePlanSelectionProducer {

	public static final String ID = "mplan";
	private static final String TITLE = GHAStrings.get("maintenance-plans");
	private MaintenancePlanTopForm topForm;
	private MaintenancePlanInternalTabset internalTabSet;
	private final List<MaintenancePlanSelectionListener> listeners = new LinkedList<MaintenancePlanSelectionListener>();
	private Option searchOption;
	private Option addOption;
	private MaintenancePlanResultSet resultSet;
	private MaintenancePlanAddForm addForm;

	/**
	 * @param token
	 */
	public MaintenancePlanTab() {
		super();
		header = new GHATabHeader(this, TITLE);
		searchOption = header.addSearchOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		addOption = header.addAddOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				add();
			}
		});

		resultSet = new MaintenancePlanResultSet(ResultSetContainerType.TAB);
		resultSet.setVisible(false);
		addHideableListener(resultSet);
		addClosableListener(resultSet);
		resultSet.addMaintenancePlanSelectionListener(this);

		topForm = new MaintenancePlanTopForm(resultSet, this);
		addHideableListener(topForm);
		addClosableListener(topForm);
		addMaintenancePlanSelectionListener(topForm);
		topForm.addSearchListener(new SearchListener() {

			@Override
			public void onSearch() {
				currentStatus = TabStatus.SEARCH_RESULTS;
			}
		});

		internalTabSet = new MaintenancePlanInternalTabset(this);
		addHideableListener(internalTabSet);
		addClosableListener(internalTabSet);
		addMaintenancePlanSelectionListener(internalTabSet);

		addForm = new MaintenancePlanAddForm(
				GHAStrings.get("new-maintenance-plan"));
		addHideableListener(addForm);
		addClosableListener(addForm);
		addForm.addMaintenancePlanSelectionListener(this);
		addForm.addHideableListener(new HideableListener() {

			@Override
			public void hide() throws UnavailableToHideException {
				if (TabStatus.ENTITY_SELECTED.equals(currentStatus))
					return;
				else
					search();
			}

			@Override
			public boolean canBeHidden(HideCloseAction closeAction) {
				return true;
			}
		});

		verticalPanel.addMember(topForm);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabSet);
		verticalPanel.addMember(resultSet);
		addMember(verticalPanel);
		search();
	}

	protected void add() {
		if (internalTabSet.isVisible())
			if (internalTabSet.canBeHidden(HideCloseAction.SAVE))
				internalTabSet.hide();
			else
				return;
		topForm.deactivate();
		topForm.clear();
		if (resultSet.isVisible())
			resultSet.hide();
		addForm.open();
		header.unMarkAllButtons();
		addOption.markSelected();
		currentStatus = TabStatus.ADD;
		// GHANotification.info(GHAStrings.get("")); //TODO: Mensaje de
		// informacion para indicar que se ha actividado el modo de busqueda
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.remove(maintenancePlanSelectionListener);
	}

	@Override
	public void select(MaintenancePlan maintenancePlan) {
		notifyMaintenancePlan(maintenancePlan);
		header.unMarkAllButtons();
		currentStatus = TabStatus.ENTITY_SELECTED;
	}

	@Override
	public void search() {
		if (currentStatus.equals(TabStatus.SEARCH))
			return;
		if (internalTabSet.isVisible())
			if (internalTabSet.canBeHidden(HideCloseAction.SAVE))
				internalTabSet.hide();
			else
				return;
		if (resultSet.isVisible())
			resultSet.hide();
		topForm.activate();
		header.unMarkAllButtons();
		searchOption.markSelected();
		currentStatus = TabStatus.SEARCH;
		// GHANotification.info(GHAStrings.get("")); //TODO: Mensaje de
		// informacion para indicar que se ha actividado el modo de busqueda
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
		for (MaintenancePlanSelectionListener listener : listeners)
			listener.select(maintenancePlan);
	}

	@Override
	public void show() {
		super.show();
		topForm.setVisibility(Visibility.VISIBLE);
		if (currentStatus.equals(TabStatus.ADD))
			return;
		if (currentStatus.equals(TabStatus.SEARCH))
			return;

		if (currentStatus.equals(TabStatus.ENTITY_SELECTED))
			internalTabSet.show();
		else
			resultSet.show();
	}

}