package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.icons.buttons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiasPlanificationAddForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author eguerere
 * 
 */
public class ResultSetEiaListPlanification extends
		GHAResultSet<EiaPlanificationEntity> implements EiaSelectionProducer {

	private List<EIASelectionListener> listeners;
	private final EiaListPlanificationGrid grid;
	private final ResultSetContainerType containerType;
	private final EiasPlanificationAddForm eiasPlanificationAddForm;

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
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedEia();
			}
		});
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));

		VLayout sideButtons = GHAUiHelper.createBar(new GHANewButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {

						if (grid.getRecords().length > 0) {

							List<EiaPlanificationEntity> eiaList = grid
									.getEntities();

							eiasPlanificationAddForm.select(eiaList);
							eiasPlanificationAddForm.open();

						} else {
							Window.alert("Deben Existir Registros");
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

	// private void delete() {
	// if (grid.getSelectedRecord() == null) {
	// GHAErrorMessageProcessor.alert("record-not-selected");
	// return;
	// }
	//
	// if (grid.getSelectedRecords().length > 1) {
	// GHAErrorMessageProcessor.confirm("eias-delete-confirm",
	// new BooleanCallback() {
	//
	// @Override
	// public void execute(Boolean value) {
	// if (value) {
	// List<Eia> entities = grid.getSelectedEntities();
	// EIAModel.delete(entities,
	// new GHAAsyncCallback<Void>() {
	//
	// @Override
	// public void onSuccess(Void result) {
	// grid.removeSelectedData();
	// refreshResultsSize(grid
	// .getRecords().length);
	// GHAErrorMessageProcessor
	// .alert("eias-delete-success");
	// }
	// });
	// }
	// }
	// });
	// } else {
	// GHAErrorMessageProcessor.confirm("eia-delete-confirm",
	// new BooleanCallback() {
	//
	// @Override
	// public void execute(Boolean value) {
	// if (value) {
	// List<Eia> entities = grid.getSelectedEntities();
	// EIAModel.delete(entities,
	// new GHAAsyncCallback<Void>() {
	//
	// @Override
	// public void onSuccess(Void result) {
	// grid.removeSelectedData();
	// refreshResultsSize(grid
	// .getRecords().length);
	// GHAErrorMessageProcessor
	// .alert("eia-delete-success");
	// }
	// });
	// }
	// }
	// });
	// }
	// }

	@Override
	public void notifyEia(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	private void notifySelectedEia() {
		// // GHAGridRecord<EiaListMaintenanceRecord> selectedRecord =
		// // grid.getSelectedRecord();
		// if (selectedRecord == null) {
		// GHAErrorMessageProcessor.alert("record-not-selected");
		// return;
		// }
		// // notifyEia(((EiaListMaintenanceRecord) selectedRecord).toEntity());
		// hide();
		// grid.removeSelectedData();
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

}
