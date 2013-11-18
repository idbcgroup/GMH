package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

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
		EiaTypeSelectionProducer {
	private List<EIATypeSelectionListener> listeners;
	private EIATypeGrid grid;

	{
		listeners = new ArrayList<EIATypeSelectionListener>();
		grid = new EIATypeGrid();
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedEiaType();
			}
		});
	}

	/**
	 * 
	 */
	public EiaTypeResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		HLayout gridPanel = new HLayout();
		VLayout sideBar;

		GHACheckButton checkButton = new GHACheckButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				notifySelectedEiaType();
			}
		});

		if (container == ResultSetContainerType.TAB) {
			VLayout separator = GHAUiHelper.verticalGraySeparator("2px");
			GHADeleteButton deleteButton = new GHADeleteButton(
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							delete();
						}
					});

			sideBar = GHAUiHelper.createBar(checkButton, separator,
					deleteButton);
		} else
			sideBar = GHAUiHelper.createBar(checkButton);

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
			GHANotification.alert("record-not-selected");
			return;
		}

		String msj = grid.getSelectedRecords().length > 1 ? GHAStrings
				.get("eiatypes-delete-confirm") : GHAStrings
				.get("eiatype-delete-confirm");

		GHANotification.confirm(GHAStrings.get("eiatype"), msj,
				new BooleanCallback() {

					@Override
					public void execute(Boolean value) {
						if (value) {
							List<EiaType> entities = grid.getSelectedEntities();
							EIATypeModel.delete(entities,
									new GHAAsyncCallback<Void>() {

										@Override
										public void onSuccess(Void result) {
											grid.removeSelectedData();
										}
									});
						}
					}
				});
	}

	@Override
	public void notifyEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
	}

	/**
	 * notify selected eiaType from the grid
	 */
	private void notifySelectedEiaType() {
		GHAGridRecord<EiaType> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.alert("record-not-selected");
			return;
		}
		notifyEiaType(selectedRecord.toEntity());
		hide();
		grid.removeSelectedData();
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
		ListGridRecord[] array = EIATypeUtil.toGridRecords(records).toArray(
				new EIATypeRecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);

	}

}
