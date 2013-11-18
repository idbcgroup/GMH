package org.fourgeeks.gha.webclient.client.maintenanceplan.information;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanTab;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class MaintenancePlanInformationFormPanel extends GHAVerticalLayout
		implements MaintenancePlanSelectionListener,
		MaintenancePlanSelectionProducer, ClosableListener, HideableListener {
	private MaintenancePlanForm maintenancePlanForm;
	private List<MaintenancePlanSelectionListener> listeners;

	private MaintenancePlan originalMaintenancePlan;

	{
		maintenancePlanForm = new MaintenancePlanForm();
		listeners = new LinkedList<MaintenancePlanSelectionListener>();
		this.originalMaintenancePlan = null;
	}

	public MaintenancePlanInformationFormPanel(MaintenancePlanTab tab) {
		activateForm(false);
		tab.addClosableListener(this);

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
		gridPanel.addMembers(maintenancePlanForm, new LayoutSpacer(),
				sideButtons);

		addMember(gridPanel);

		// register as maintenanceplanselectedlistener with maintenanceplanform
		maintenancePlanForm.addMaintenancePlanSelectionListener(this);
	}

	public void activateForm(boolean activate) {
		maintenancePlanForm.activateForm(activate);
	}

	protected void undo() {
		select(this.originalMaintenancePlan);
		// save();
	}

	private void save() {
		maintenancePlanForm.update();
	}

	public void setMaintenancePlan(MaintenancePlan maintenancePlan) {
		this.originalMaintenancePlan = maintenancePlan;
		maintenancePlanForm.setMaintenancePlan(maintenancePlan);
		maintenancePlanForm.activateForm(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionProducer
	 * #addMaintenancePlanSelectionListener(org.fourgeeks
	 * .gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionProducer
	 * #removeMaintenancePlanSelectionListener(org
	 * .fourgeeks.gha.webclient.client
	 * .maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.remove(maintenancePlanSelectionListener);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		for (MaintenancePlanSelectionListener listener : listeners)
			listener.select(maintenancePlan);
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
		// TODO Auto-generated method stub

	}
}
