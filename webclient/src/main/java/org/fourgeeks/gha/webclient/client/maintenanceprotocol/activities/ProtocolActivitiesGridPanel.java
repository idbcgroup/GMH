package org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolSelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class ProtocolActivitiesGridPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener,
		MaintenanceProtocolSelectionListener,
		MaintenanceActivitySelectionListener {

	private MaintenanceActivityMaintenanceProtocolGrid grid;
	private MaintenanceProtocol maintenanceProtocol;
	private MaintenanceActivitySearchForm searchForm;

	{
		grid = new MaintenanceActivityMaintenanceProtocolGrid();
		searchForm = new MaintenanceActivitySearchForm();
	}

	public ProtocolActivitiesGridPanel() {
		super();
		GHALabel title = new GHALabel("Actividades del Protocolo");

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						searchForm.open();
					}
				}), /* new GHAImgButton("../resources/icons/edit.png"), */
				new GHAImgButton("../resources/icons/delete.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								MaintenanceActivityMaintenanceProtocol entity = grid
										.getSelectedEntity();
								MaintenanceActivityMaintenanceProtocolModel
										.delete(entity.getId(),
												new GHAAsyncCallback<Void>() {

													@Override
													public void onSuccess(
															Void result) {
														loadData();
													}
												});
							}
						}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);
		addMembers(title, mainPanel);

		// register as listener to the maintenanceactivity search form
		this.searchForm.addMaintenanceActivitySelectionListener(this);
	}

	@Override
	public void close() {
		searchForm.close();
	}

	@Override
	public void hide() {
		searchForm.hide();
	}

	private void loadData() {
		MaintenanceActivityMaintenanceProtocolModel
				.findByMaintenanceProtocol(
						this.maintenanceProtocol,
						new GHAAsyncCallback<List<MaintenanceActivityMaintenanceProtocol>>() {

							@Override
							public void onSuccess(
									List<MaintenanceActivityMaintenanceProtocol> result) {
								ListGridRecord array[] = MaintenanceActivityMaintenanceProtocolUtil
										.toGridRecords(result)
										.toArray(
												new MaintenanceActivityMaintenanceProtocolGridRecord[] {});
								grid.setData(array);
							}
						});
	}

	// consumer stuff
	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		final MaintenanceActivityMaintenanceProtocol entity = new MaintenanceActivityMaintenanceProtocol();
		entity.setActivity(maintenanceActivity);
		entity.setProtocol(this.maintenanceProtocol);
		MaintenanceActivityMaintenanceProtocolModel.save(entity,
				new GHAAsyncCallback<MaintenanceActivityMaintenanceProtocol>() {

					@Override
					public void onSuccess(
							MaintenanceActivityMaintenanceProtocol arg0) {
						loadData();
					}
				});
	}

	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		this.maintenanceProtocol = maintenanceProtocol;
		loadData();
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
