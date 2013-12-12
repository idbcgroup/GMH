package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.panels.GHAPanel;

import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class MaintenanceActivityTab extends GHAPanel implements
		MaintenanceActivitySelectionListener,
		MaintenanceActivitySelectionProducer {

	private final MaintenanceActivityTopSection topSection;
	private final MaintenanceActivityInternalTabset internalTabset;
	List<MaintenanceActivitySelectionListener> listeners;

	private MaintenanceActivity activity;

	{
		listeners = new LinkedList<MaintenanceActivitySelectionListener>();
	}

	/**
	 * @param token
	 * 
	 */
	public MaintenanceActivityTab(String token) {
		super();
		// getHeader().setTitle(TITLE);

		topSection = new MaintenanceActivityTopSection(this);
		internalTabset = new MaintenanceActivityInternalTabset(this);

		// Creacion de la tab de EIA
		VLayout verticalPanel = new VLayout();
		verticalPanel.setBackgroundColor("#E0E0E0");

		verticalPanel.addMember(topSection);
		verticalPanel.addMember(GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));
		verticalPanel.addMember(internalTabset);
		addMember(verticalPanel);

	}

	@Override
	protected void onDraw() {
		if (activity == null)
			topSection.search();
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
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		listeners.add(maintenanceActivitySelectionListener);
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
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		listeners.remove(maintenanceActivitySelectionListener);

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
		for (MaintenanceActivitySelectionListener listener : listeners)
			listener.select(maintenanceActivity);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void search() {
		// TODO Auto-generated method stub

	}

}