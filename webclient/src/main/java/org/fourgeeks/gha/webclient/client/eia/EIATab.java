package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATab extends GHATab implements EIASelectionListener,
		EiaSelectionProducer {

	/**
	 * The ID of the Tab in the app managers
	 */
	public static final String ID = "eia";
	private static final String TITLE = GHAStrings.get("equipments");
	private List<EIASelectionListener> listeners = new ArrayList<EIASelectionListener>();
	private EIATopForm topForm;
	private EIAAddForm addForm;
	private EIAInternalTabset internalTabset;
	private EiaResultSet resultSet;

	/**
	 * @param token
	 * 
	 */
	public EIATab(String token) {
		super(token);
		header = new GHATabHeader(this, TITLE);
		header.addSearchOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		});
		header.addAddOption(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				add();
			}
		});

		resultSet = new EiaResultSet();
		addGHAHideableHandler(resultSet);
		addGHAClosableHandler(resultSet);
		resultSet.addEiaSelectionListener(this);

		topForm = new EIATopForm(resultSet);
		topForm.activate();
		addGHAHideableHandler(topForm);
		addGHAClosableHandler(topForm);
		addEiaSelectionListener(topForm);

		internalTabset = new EIAInternalTabset(this);
		addGHAHideableHandler(internalTabset);
		addGHAClosableHandler(internalTabset);
		addEiaSelectionListener(internalTabset);

		addForm = new EIAAddForm();
		addGHAHideableHandler(addForm);
		addGHAClosableHandler(addForm);
		addForm.addEiaSelectionListener(this);

		verticalPanel.addMember(topForm);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabset);
		verticalPanel.addMember(resultSet);
		addMember(verticalPanel);
	}

	/**
	 * 
	 */
	protected void add() {
		if (addForm.isVisible()) {
			return;
		}
		if (internalTabset.isVisible()) {
			// TODO
		}
		if (topForm.isActivated())
			topForm.deactivate();
		if (resultSet.isVisible())
			resultSet.hide();
		addForm.open();
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.add(eiaSelectionListener);
	}

	@Override
	public String getId() {
		return ID;
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
	protected void search() {
		if (topForm.isActivated()) {
			return;
		}
		if (internalTabset.isVisible()) {
			if (internalTabset.canBeHidden())
				internalTabset.hide();
			else
				return;
		}
		if (addForm.isVisible()) {
			addForm.hide();
		}
		if (resultSet.isVisible()) {
			resultSet.hide();
		}
		topForm.activate();
		// GHANotification.info(GHAStrings.get("")); //TODO: Mensaje de
		// informacion para indicar que se ha actividado el modo de busqueda
	}

	@Override
	public void select(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

}