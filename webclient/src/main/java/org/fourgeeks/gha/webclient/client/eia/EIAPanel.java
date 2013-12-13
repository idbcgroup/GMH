package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.TabStatus;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToHideException;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.SearchListener;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAPanel;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAPanelHeader;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAHeaderOption;

import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret, emiliot
 * 
 */
public class EIAPanel extends GHAPanel implements EIASelectionListener,
		EiaSelectionProducer {

	private final EIAAddForm addForm;
	private final EIAInternalTabset internalTabSet;
	private final List<EIASelectionListener> listeners = new ArrayList<EIASelectionListener>();
	private final EIATopForm topForm;
	private final EiaResultSet resultSet;
	private final GHAHeaderOption searchOption;
	private final GHAHeaderOption addOption;

	/**
	 * @param token
	 * 
	 */
	public EIAPanel() {
		header = new GHAPanelHeader(this, GHAStrings.get("equipments"));
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

		resultSet = new EiaResultSet(ResultSetContainerType.TAB);
		resultSet.setVisible(false);
		addHideableListener(resultSet);
		addClosableListener(resultSet);
		resultSet.addEiaSelectionListener(this);

		topForm = new EIATopForm(resultSet, this);
		addHideableListener(topForm);
		addClosableListener(topForm);
		addEiaSelectionListener(topForm);
		topForm.addSearchListener(new SearchListener() {

			@Override
			public void onSearch() {
				currentStatus = TabStatus.SEARCH_RESULTS;
			}
		});

		internalTabSet = new EIAInternalTabset(this);
		addHideableListener(internalTabSet);
		addClosableListener(internalTabSet);
		addEiaSelectionListener(internalTabSet);

		addForm = new EIAAddForm(GHAStrings.get("new-eia"));
		addHideableListener(addForm);
		addClosableListener(addForm);
		addForm.addEiaSelectionListener(this);
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

	/**
	 * 
	 */
	protected void add() {
		if (internalTabSet.isVisible()) {
			if (internalTabSet.canBeHidden(HideCloseAction.ASK))
				internalTabSet.hide();
			else
				return;
		}
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
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.add(eiaSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer#notifyEia
	 * (org.fourgeeks.gha.domain.gmh.Eia)
	 */
	@Override
	public void notifyEia(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.remove(eiaSelectionListener);
	}

	/**
	 * 
	 */
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
	public void select(Eia eia) {
		notifyEia(eia);
		header.unMarkAllButtons();
		currentStatus = TabStatus.ENTITY_SELECTED;
	}

	@Override
	public void show() {
		super.show();
		topForm.setVisibility(Visibility.VISIBLE);
		if (currentStatus.equals(TabStatus.ADD)
				|| (currentStatus.equals(TabStatus.SEARCH))
				|| (currentStatus.equals(TabStatus.INIT)))
			return;
		if (currentStatus.equals(TabStatus.ENTITY_SELECTED))
			internalTabSet.show();
		else
			resultSet.show();
	}

}