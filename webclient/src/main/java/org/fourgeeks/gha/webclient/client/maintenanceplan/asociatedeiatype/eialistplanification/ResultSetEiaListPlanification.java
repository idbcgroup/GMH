package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.icons.buttons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener;
import org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiasPlanificationAddForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author eguerere
 * 
 */
public class ResultSetEiaListPlanification extends
		GHAResultSet<EiaPlanificationEntity> implements EiaSelectionProducer,
		MaintenancePlanSelectionProducer {

	private List<EIASelectionListener> listeners;
	private final EiaListPlanificationGrid grid;
	private final ResultSetContainerType containerType;
	private final EiasPlanificationAddForm eiasPlanificationAddForm;
	private EiaTypeMaintenancePlan eiaTypeMaintenancePlan;
	private ResultSetEiaListPlanification instance = this;

	{
		listeners = new ArrayList<EIASelectionListener>();
	}

	/**
	 * @param container
	 * 
	 */
	public ResultSetEiaListPlanification(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;
		setHeight(GHAUiHelper.getResultSetHeight(containerType));

		eiasPlanificationAddForm = new EiasPlanificationAddForm();

		grid = new EiaListPlanificationGrid() {
			@Override
			public void onResize(ResizeEvent event) {
				super.onResize(event);
				grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
			}
		};
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));

		VLayout sideButtons = GHAUiHelper.createBar(new GHANewButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {

						if (grid.getRecords().length > 0) {

							List<EiaPlanificationEntity> eiaList = grid
									.getEntities();
							eiasPlanificationAddForm.select(eiaList);
							eiasPlanificationAddForm
									.select(eiaTypeMaintenancePlan);
							eiasPlanificationAddForm.setResultset(instance);
							eiasPlanificationAddForm.open();

						} else {
							GHAErrorMessageProcessor.alert("exists-eia-grid");
						}
					}
				}));

		HLayout mainLayout = new HLayout();
		mainLayout.addMembers(grid, sideButtons);
		addMember(mainLayout);
	}

	/**
	 * @return the eiasPlanificationAddForm
	 */
	public EiasPlanificationAddForm getEiasPlanificationAddForm() {
		return eiasPlanificationAddForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer#
	 * addEiaSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eia.EIASelectionListener)
	 */
	@Override
	public void addEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.add(eiaSelectionListener);
	}

	@Override
	public void clean() {
		grid.setData(new EiaListMaintenanceRecord[] {});
		showResultsSize(null, true);
	}

	@Override
	public void notifyEia(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
	}

	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.remove(eiaSelectionListener);
	}

	@Override
	public void setRecords(List<EiaPlanificationEntity> records,
			boolean notifyIfOnlyOneResult) {
		showResultsSize(records, false);
		ListGridRecord[] array = EiaPlanificationEntityUtil.toGridRecords(
				records).toArray(new EiaListMaintenanceRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);
	}

	public EiaTypeMaintenancePlan getEiaTypeMaintenancePlan() {
		return eiaTypeMaintenancePlan;
	}

	public void setEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) {
		this.eiaTypeMaintenancePlan = eiaTypeMaintenancePlan;
	}

	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		eiasPlanificationAddForm
				.addMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		eiasPlanificationAddForm
				.removeMaintenancePlanSelectionListener(maintenancePlanSelectionListener);
	}

	@Override
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
	}
}
