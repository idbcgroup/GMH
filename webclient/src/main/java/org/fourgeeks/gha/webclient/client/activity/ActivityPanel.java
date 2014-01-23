package org.fourgeeks.gha.webclient.client.activity;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.TabStatus;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.SearchListener;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAHeaderOption;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAPanel;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAPanelHeader;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityAddForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityResultSet;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionProducer;

import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * 
 * @author caparicio
 * 
 */
public class ActivityPanel extends GHAPanel implements
		MaintenanceActivitySelectionListener,
		MaintenanceActivitySelectionProducer {

	private static final String TITLE = "Actividades de Mantenimiento";
	private ActivityTopForm topForm;
	private ActivityInternalTabset internalTabSet;
	private final List<MaintenanceActivitySelectionListener> listeners = new LinkedList<MaintenanceActivitySelectionListener>();
	private GHAHeaderOption searchOption;
	private GHAHeaderOption addOption;
	private MaintenanceActivityResultSet resultSet;
	private MaintenanceActivityAddForm addForm;

	/**
	 * @param token
	 */
	public ActivityPanel() {
		super();
		header = new GHAPanelHeader(this, TITLE);
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

		resultSet = new MaintenanceActivityResultSet(ResultSetContainerType.TAB);
		resultSet.setVisible(false);
		addHideableListener(resultSet);
		addClosableListener(resultSet);
		resultSet.addMaintenanceActivitySelectionListener(this);

		topForm = new ActivityTopForm(resultSet, this);
		addHideableListener(topForm);
		addClosableListener(topForm);
		addMaintenanceActivitySelectionListener(topForm);
		topForm.addSearchListener(new SearchListener() {

			@Override
			public void onSearch() {
				currentStatus = TabStatus.SEARCH_RESULTS;
			}
		});

		internalTabSet = new ActivityInternalTabset(this);
		addHideableListener(internalTabSet);
		addClosableListener(internalTabSet);
		addMaintenanceActivitySelectionListener(internalTabSet);

		addForm = new MaintenanceActivityAddForm("Nueva actividad");
		addHideableListener(addForm);
		addClosableListener(addForm);
		addForm.addMaintenanceActivitySelectionListener(this);
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

	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener activitySelectionListener) {
		listeners.add(activitySelectionListener);
	}

	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener activitySelectionListener) {
		listeners.remove(activitySelectionListener);
	}

	@Override
	public void notifyMaintenanceActivity(MaintenanceActivity activity) {
		for (MaintenanceActivitySelectionListener listener : listeners)
			listener.select(activity);
	}

	@Override
	public void select(MaintenanceActivity activity) {
		notifyMaintenanceActivity(activity);
		header.unMarkAllButtons();
		currentStatus = TabStatus.ENTITY_SELECTED;
	}

}