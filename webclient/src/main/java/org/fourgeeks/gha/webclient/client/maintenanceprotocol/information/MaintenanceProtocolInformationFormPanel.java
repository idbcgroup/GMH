package org.fourgeeks.gha.webclient.client.maintenanceprotocol.information;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolForm;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolTab;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class MaintenanceProtocolInformationFormPanel extends VLayout implements
		GHAClosable, GHAHideable, MaintenanceProtocolSelectionListener,
		MaintenanceProtocolSelectionProducer {
	private MaintenanceProtocolForm maintenanceProtocolForm;
	private List<MaintenanceProtocolSelectionListener> listeners;

	private MaintenanceProtocol originalMaintenanceProtocol;
	{
		maintenanceProtocolForm = new MaintenanceProtocolForm();
		listeners = new LinkedList<MaintenanceProtocolSelectionListener>();
		this.originalMaintenanceProtocol = null;
	}

	public MaintenanceProtocolInformationFormPanel(MaintenanceProtocolTab tab) {
		activateForm(false);
		tab.addGHAClosableHandler(this);

		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setAlign(Alignment.CENTER);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/save.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						save();
					}
				}), new GHAImgButton("../resources/icons/undo.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						undo();
					}
				}));

		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(maintenanceProtocolForm, new LayoutSpacer(),
				sideButtons);

		addMember(gridPanel);

		// register as maintenanceProtocolSelectionListener with the Form
		maintenanceProtocolForm.addMaintenanceProtocolSelectionListener(this);
	}

	public void activateForm(boolean activate) {
		maintenanceProtocolForm.activateForm(activate);
	}

	protected void undo() {
		select(this.originalMaintenanceProtocol);
		// save();
	}

	private void save() {
		maintenanceProtocolForm.update();
	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {

	}

	/**
	 * @param maintenanceProtocol
	 */
	public void setMaintenanceProtocol(MaintenanceProtocol maintenanceProtocol) {
		this.originalMaintenanceProtocol = maintenanceProtocol;
		maintenanceProtocolForm.setMaintenanceProtocol(maintenanceProtocol);
		maintenanceProtocolForm.activateForm(true);
	}

	// Producer/Consumer stuff
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
	public boolean canBeHidden() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeClosen() {
		// TODO Auto-generated method stub
		return false;
	}
}
