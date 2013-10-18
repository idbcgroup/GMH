package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

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
	private static final String TITLE = "Equipos";
	private EIATopSection topSection;
	private EIAInternalTabset internalTabset;
	private List<EIASelectionListener> selectionListeners;
	private Eia eia;
	{
		selectionListeners = new ArrayList<EIASelectionListener>();
	}

	/**
	 * @param token
	 * 
	 */
	public EIATab(String token) {
		super(token);
		// header.setTitle(TITLE);
		//
		// topSection = new EIATopSection(this);
		// internalTabset = new EIAInternalTabset(this);
		//
		// verticalPanel.addMember(topSection);
		// verticalPanel.addMember(GHAUiHelper
		// .verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		// verticalPanel.addMember(internalTabset);
		addMember(verticalPanel);
	}

	@Override
	protected void onDraw() {
		if (eia == null)
			topSection.search();
	}

	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void select(Eia eia) {
		for (EIASelectionListener listener : selectionListeners)
			listener.select(eia);
	}

	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		selectionListeners.add(eiaSelectionListener);
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		selectionListeners.remove(eiaSelectionListener);
	}

}