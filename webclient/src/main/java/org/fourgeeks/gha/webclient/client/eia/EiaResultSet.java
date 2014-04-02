package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class EiaResultSet extends GHAResultSet<Eia> implements
		EiaSelectionProducer {
	private List<EIASelectionListener> listeners;
	private final EIAGrid grid;
	private final ResultSetContainerType containerType;

	{
		listeners = new ArrayList<EIASelectionListener>();
	}

	/**
	 * @param container
	 * 
	 */
	public EiaResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;
		setHeight(GHAUiHelper.getResultSetHeight(containerType));

		grid = new EIAGrid() {
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

		HLayout gridPanel = new HLayout();
		VLayout sideBar;

		GHACheckButton checkButton = new GHACheckButton(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				notifySelectedEia();
			}
		});

		if (containerType == ResultSetContainerType.TAB) {
			sideBar = GHAUiHelper.createBar(checkButton,
					GHAUiHelper.verticalGraySeparator("2px"),
					new GHADeleteButton(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							delete();
						}
					}));
		} else {
			sideBar = GHAUiHelper.createBar(checkButton);
			// setHeight(getHeight() - 42);
		}

		gridPanel.addMembers(grid, sideBar);
		addMember(gridPanel);
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
		grid.setData(new EIARecord[] {});
		showResultsSize(null, true);
	}

	private void delete() {
		if (grid.getSelectedRecord() == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}

		if (grid.getSelectedRecords().length > 1) {
			GHAErrorMessageProcessor.confirm("eias-delete-confirm",
					new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								List<Eia> entities = grid.getSelectedEntities();
								EIAModel.delete(entities,
										new GHAAsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												grid.removeSelectedData();
												refreshResultsSize(grid
														.getRecords().length);
												GHAErrorMessageProcessor
														.alert("eias-delete-success");
											}
										});
							}
						}
					});
		} else {
			GHAErrorMessageProcessor.confirm("eia-delete-confirm",
					new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								List<Eia> entities = grid.getSelectedEntities();
								EIAModel.delete(entities,
										new GHAAsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												grid.removeSelectedData();
												refreshResultsSize(grid
														.getRecords().length);
												GHAErrorMessageProcessor
														.alert("eia-delete-success");
											}
										});
							}
						}
					});
		}
	}

	@Override
	public void notifyEia(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	private void notifySelectedEia() {
		GHAGridRecord<Eia> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}
		notifyEia(((EIARecord) selectedRecord).toEntity());
		hide();
		grid.removeSelectedData();
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
	public void setRecords(List<Eia> records, boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyEia(records.get(0));
			hide();
			return;
		}
		showResultsSize(records, false);
		ListGridRecord[] array = EIAUtil.toGridRecords(records).toArray(
				new EIARecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);
	}

}
