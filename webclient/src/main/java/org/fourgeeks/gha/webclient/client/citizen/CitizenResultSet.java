package org.fourgeeks.gha.webclient.client.citizen;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
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
 * @author alacret, emiliot
 * 
 */
public class CitizenResultSet extends GHAResultSet<Citizen> implements
		CitizenSelectionProducer, ResizeHandler, HideableListener,
		ClosableListener {
	private final List<CitizenSelectionListener> listeners = new ArrayList<CitizenSelectionListener>();
	private final CitizenGrid grid;
	private final ResultSetContainerType containerType;

	/**
	 * @param container
	 * 
	 */
	public CitizenResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;

		grid = new CitizenGrid() {
			@Override
			public void onResize(ResizeEvent event) {
				super.onResize(event);
				grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
			}
		};
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedCitizen();
			}
		});
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));

		setHeight(GHAUiHelper.getResultSetHeight(containerType));
		final HLayout gridPanel = new HLayout();
		VLayout sideBar;

		final GHACheckButton checkButton = new GHACheckButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectedCitizen();
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

	@Override
	public void addCitizenSelectionListener(
			CitizenSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);

	}

	@Override
	public void clean() {
		grid.setData(new CitizenRecord[] {});
		showResultsSize(null, true);
	}

	protected void delete() {
		if (grid.getSelectedRecord() == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}

		if (grid.getSelectedRecords().length > 1) {
			GHAErrorMessageProcessor.confirm("citizens-delete-confirm",
					new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								final List<Citizen> entities = grid
										.getSelectedEntities();
								CitizenModel.delete(entities,
										new GHAAsyncCallback<Void>() {

											@Override
											public void onSuccess(Void arg0) {
												grid.removeSelectedData();
												refreshResultsSize(grid
														.getRecords().length);
											}

										});
							}
						}
					});
		} else {
			GHAErrorMessageProcessor.confirm("citizen-delete-confirm",
					new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								final List<Citizen> entities = grid
										.getSelectedEntities();
								CitizenModel.delete(entities,
										new GHAAsyncCallback<Void>() {

											@Override
											public void onSuccess(Void arg0) {
												grid.removeSelectedData();
												refreshResultsSize(grid
														.getRecords().length);
											}

										});
							}
						}
					});
		}
	}

	/**
	 * notify selected citizen from the grid
	 */
	private void notifySelectedCitizen() {
		final GHAGridRecord<Citizen> selectedRecord = grid.getSelectedRecord();

		if (selectedRecord == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}
		notifyCitizen(selectedRecord.toEntity());
		hide();
	}

	@Override
	public void notifyCitizen(Citizen citizen) {
		for (final CitizenSelectionListener listener : listeners)
			listener.onCitizenSelect(citizen);
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
	}

	@Override
	public void removeCitizenSelectionListener(CitizenSelectionListener listener) {
		listeners.remove(listener);

	}

	@Override
	public void setRecords(List<Citizen> records, boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyCitizen(records.get(0));
			this.hide();
			return;
		}

		showResultsSize(records, false);
		final ListGridRecord[] array = CitizenUtil.toGridRecords(records)
				.toArray(new CitizenRecord[] {});
		grid.setData(array);
		// setAnimateAcceleration(AnimationAcceleration.NONE);
		this.animateShow(AnimationEffect.FADE);
	}

}
