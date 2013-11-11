package org.fourgeeks.gha.webclient.client.maintenanceactivity.information;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivityTab;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class MaintenanceActivityInformationFormPanel extends GHAVerticalLayout
		implements ClosableListener, HideableListener,
		MaintenanceActivitySelectionListener,
		MaintenanceActivitySelectionProducer {

	private MaintenanceActivityForm maintenanceActivityForm;
	private List<MaintenanceActivitySelectionListener> listeners;

	private MaintenanceActivity originalMaintenanceActivity;

	{
		maintenanceActivityForm = new MaintenanceActivityForm();
		listeners = new LinkedList<MaintenanceActivitySelectionListener>();
		this.originalMaintenanceActivity = null;
	}

	public MaintenanceActivityInformationFormPanel(MaintenanceActivityTab tab) {
		activateForm(false);
		tab.addClosableListener(this);

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
		gridPanel.addMembers(maintenanceActivityForm, new LayoutSpacer(),
				sideButtons);

		addMember(gridPanel);

		// register as maintenanceActivitySelectionListener with the form
		maintenanceActivityForm.addMaintenanceActivitySelectionListener(this);
	}

	public void activateForm(boolean activate) {
		maintenanceActivityForm.activateForm(activate);
	}

	@Override
	public void close() {

	}

	@Override
	public void hide() {

	}

	private void save() {
		maintenanceActivityForm.update();
	}

	protected void undo() {
		select(this.originalMaintenanceActivity);
		// save();
	}

	public void setMaintenanceActivity(MaintenanceActivity maintenanceActivity) {
		this.originalMaintenanceActivity = maintenanceActivity;
		maintenanceActivityForm.setMaintenanceActivity(maintenanceActivity);
		maintenanceActivityForm.activateForm(true);
	}

	// Producer/Consumer stuff
	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		listeners.add(maintenanceActivitySelectionListener);
	}

	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		listeners.remove(maintenanceActivitySelectionListener);
	}

	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		for (MaintenanceActivitySelectionListener listener : listeners)
			listener.select(maintenanceActivity);
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}
}
