package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHADeleteButton;
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
public class EiaTypeResultSet extends GHAResultSet<EiaType> implements
		EiaTypeSelectionProducer, EiaTypeListSelectionProducer {

	private List<EIATypeSelectionListener> listeners;
	private List<EiaTypeListSelectionListener> listenersList;
	private final EIATypeGrid grid;
	private final ResultSetContainerType containerType;

	{
		listeners = new ArrayList<EIATypeSelectionListener>();
		listenersList = new ArrayList<EiaTypeListSelectionListener>();
	}

	/**
	 * @param container
	 * 
	 */
	public EiaTypeResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;

		grid = new EIATypeGrid() {
			@Override
			public void onResize(ResizeEvent event) {
				super.onResize(event);
				grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
			}
		};
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedEiaType();
			}
		});
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));

		setHeight(GHAUiHelper.getResultSetHeight(containerType));
		final HLayout gridPanel = new HLayout();
		VLayout sideBar;

		final GHACheckButton checkButton = new GHACheckButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (grid.getSelectedRecords().length > 1)
					notifySelectedEiaTypes();
				else
					notifySelectedEiaType();
			}
		});

		if (containerType == ResultSetContainerType.TAB) {
			final VLayout separator = GHAUiHelper.verticalGraySeparator("2px");
			final GHADeleteButton deleteButton = new GHADeleteButton(
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							delete();
						}
					});

			sideBar = GHAUiHelper.createBar(checkButton, separator,
					deleteButton);
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
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EiaTypeSelectionProducer#
	 * addEiaTypeSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener)
	 */
	@Override
	public void addEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.add(eIATypeSelectionListener);
	}

	@Override
	public void clean() {
		grid.setData(new EIATypeRecord[] {});
		showResultsSize(null, true);
	}

	/**
	 * Elimina los eiaType seleccionados en el grid
	 */
	private void delete() {
		if (grid.getSelectedRecord() == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}

		if (grid.getSelectedRecords().length > 1) {
			GHAErrorMessageProcessor.confirm("eiatypes-delete-confirm",
					new BooleanCallback() {

				@Override
				public void execute(Boolean value) {
					if (value) {
						final List<EiaType> entities = grid
								.getSelectedEntities();
						EIATypeModel.delete(entities,
								new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								grid.removeSelectedData();
								refreshResultsSize(grid
										.getRecords().length);
								GHAErrorMessageProcessor
								.alert("eiatypes-delete-success");
							}
						});
					}
				}
			});
		} else {
			GHAErrorMessageProcessor.confirm("eiatype-delete-confirm",
					new BooleanCallback() {

				@Override
				public void execute(Boolean value) {
					if (value) {
						final List<EiaType> entities = grid
								.getSelectedEntities();
						EIATypeModel.delete(entities,
								new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								grid.removeSelectedData();
								refreshResultsSize(grid
										.getRecords().length);
								GHAErrorMessageProcessor
								.alert("eiatype-delete-success");
							}
						});
					}
				}
			});
		}
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
		for (final EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
	}

	/**
	 * notify selected eiaType from the grid
	 */
	private void notifySelectedEiaType() {
		final GHAGridRecord<EiaType> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}
		notifyEiaType(selectedRecord.toEntity());
		hide();
		grid.removeSelectedData();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eiatype.EiaTypeSelectionProducer#
	 * removeEiaTypeSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener)
	 */
	@Override
	public void removeEiaTypeSelectionListener(
			EIATypeSelectionListener eIATypeSelectionListener) {
		listeners.remove(eIATypeSelectionListener);

	}

	@Override
	public void setRecords(List<EiaType> records, boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyEiaType(records.get(0));
			hide();
			return;
		}
		showResultsSize(records, false);
		final ListGridRecord[] array = EIATypeUtil.toGridRecords(records).toArray(
				new EIATypeRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);

	}

	@Override
	public void addEiaTypeListSelectionListener(
			EiaTypeListSelectionListener eiaTypeListSelectionListener) {
		listenersList.add(eiaTypeListSelectionListener);
	}

	@Override
	public void removeEiaTypeListSelectionListener(
			EiaTypeListSelectionListener eiaTypeListSelectionListener) {
		listenersList.remove(eiaTypeListSelectionListener);
	}

	@Override
	public void notifyEiaTypeList(List<EiaType> eiaTypes) {
		for (EiaTypeListSelectionListener listener : listenersList)
			listener.select(eiaTypes);
	}

	private void notifySelectedEiaTypes() {
		notifyEiaTypeList(grid.getSelectedEntities());
		hide();
		grid.removeSelectedData();

	}
}
