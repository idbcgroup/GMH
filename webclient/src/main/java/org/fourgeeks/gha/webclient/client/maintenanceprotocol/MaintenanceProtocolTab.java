package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.tabs.GHATab;

/**
 * @author alacret
 * 
 */
public class MaintenanceProtocolTab extends GHATab implements
		MaintenanceProtocolSelectionListener,
		MaintenanceProtocolSelectionProducer {

	public static final String ID = "mprot";
	private static final String TITLE = "Protocolos De Mant.";
	private MaintenanceProtocolTopSection topSection;
	private MaintenanceProtocolInternalTabset internalTabset;
	private List<MaintenanceProtocolSelectionListener> listeners;

	private MaintenanceProtocol protocol;

	{
		listeners = new LinkedList<MaintenanceProtocolSelectionListener>();
	}

	/**
	 * @param token
	 */
	public MaintenanceProtocolTab(String token) {
		super(token);
		// getHeader().setTitle(TITLE);
		topSection = new MaintenanceProtocolTopSection(this);
		internalTabset = new MaintenanceProtocolInternalTabset(this);

		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabset);
		addMember(verticalPanel);

	}

	@Override
	protected void onDraw() {
		if (protocol == null)
			topSection.search();
	}

	@Override
	public String getId() {
		return ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.
	 * MaintenanceProtocolSelectionProducer
	 * #addMaintenanceProtocolSelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceprotocol.MaintenanceProtocolSelectionListener)
	 */
	@Override
	public void addMaintenanceProtocolSelectionListener(
			MaintenanceProtocolSelectionListener maintenanceProtocolSelectionListener) {
		listeners.add(maintenanceProtocolSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.
	 * MaintenanceProtocolSelectionProducer
	 * #removeMaintenanceProtocolSelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceprotocol.MaintenanceProtocolSelectionListener)
	 */
	@Override
	public void removeMaintenanceProtocolSelectionListener(
			MaintenanceProtocolSelectionListener maintenanceProtocolSelectionListener) {
		listeners.remove(maintenanceProtocolSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.
	 * MaintenanceProtocolSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		for (MaintenanceProtocolSelectionListener listener : listeners)
			listener.select(maintenanceProtocol);
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}
}