package org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanAddForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSearchForm;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanGrid;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanRecord;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanUtil;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaintenanceGridPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener, EIATypeSelectionListener {

	private EiaTypeMaintenancePlanGrid grid;
	private EiaType eiaType;
	private MaintenancePlanSearchForm searchForm;
	private MaintenancePlanAddForm addForm;

	private MaintenancePlanSelectionListener maintenancePlanSelectionListener = new MaintenancePlanSelectionListener() {

		@Override
		public void select(MaintenancePlan maintenancePlan) {
			// TODO ADD BEHAVIOR WHEN A PLAN IS SELECTED
		}
	};

	{
		grid = new EiaTypeMaintenancePlanGrid();
		grid.setMaintenancePlanFields();
		searchForm = new MaintenancePlanSearchForm(
				GHAStrings.get("search-maintenance-plan"));
		searchForm
				.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
		addForm = new MaintenancePlanAddForm(
				GHAStrings.get("new-maintenance-plan"));
		addForm.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	/**
	 * 
	 */
	public EIATypeMaintenanceGridPanel() {
		super();
		GHALabel title = new GHALabel(GHAStrings.get("maintenance-plan"));
		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						Window.alert("search maintenance plan");

					}
				}), new GHANewButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("new maintenance plan");

			}
		}), new GHADeleteButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.alert("delete maintenance plan");

			}
		}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(title, mainPanel);
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
	public void close() {
		hide();
		searchForm.close();
		addForm.close();
	}

	@Override
	public void hide() {
		if (searchForm.isVisible())
			searchForm.hide();
		if (addForm.isVisible())
			addForm.hide();
	}

	private void loadData() {
		EiaTypeMaintenancePlanModel.findByEiaType(this.eiaType,
				new GHAAsyncCallback<List<EiaTypeMaintenancePlan>>() {

					@Override
					public void onSuccess(List<EiaTypeMaintenancePlan> result) {
						ListGridRecord array[] = EiaTypeMaintenancePlanUtil
								.toMaintenancePlanGridRecords(result).toArray(
										new EiaTypeMaintenancePlanRecord[] {});
						grid.setData(array);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener#select
	 * (org.fourgeeks.gha.domain.gmh.EiaType)
	 */
	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		loadData();
	}

}
