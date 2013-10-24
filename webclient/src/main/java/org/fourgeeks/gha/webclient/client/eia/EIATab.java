package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATabHeader;

/**
 * @author alacret
 * 
 */
public class EIATab extends GHATab implements EIASelectionListener,
		EiaSelectionProducer {

	/**
	 * The ID of the Tab in the app managers
	 */
	public static final String ID = "eia";
	private static final String TITLE = GHAStrings.get("equipos");
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

		resultSet = new EiaResultSet();
		addGHAHideableHandler(resultSet);
		addGHAClosableHandler(resultSet);
		resultSet.addEiaSelectionListener(this);

		topForm = new EIATopForm(resultSet);
		topForm.activate();
		addGHAHideableHandler(topForm);
		addGHAClosableHandler(topForm);

		//
		// topSection = new EIATopSection(this);
		// internalTabset = new EIAInternalTabset(this);
		//
		// verticalPanel.addMember(topSection);
		// verticalPanel.addMember(GHAUiHelper
		// .verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		// verticalPanel.addMember(internalTabset);
		// addMember(verticalPanel);
	}

	@Override
	protected void onDraw() {
		// if (eia == null)
		// topSection.search();
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void select(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.add(eiaSelectionListener);
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.remove(eiaSelectionListener);
	}

	@Override
	public boolean canBeClosen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeHidden() {
		// TODO Auto-generated method stub
		return false;
	}

}