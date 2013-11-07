package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author emiliot
 * 
 */
public class EiaResultSet extends GHAResultSet<Eia> implements
		EiaSelectionProducer {
	private List<EIASelectionListener> listeners;
	private EIAGrid grid;

	{
		listeners = new ArrayList<EIASelectionListener>();
		grid = new EIAGrid();
	}

	/**
	 * 
	 */
	public EiaResultSet() {
		super(GHAStrings.get("search-results"));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid, GHAUiHelper.createBar(new GHACheckButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectedEia();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"),
				new GHADeleteButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO delete an selected eia
					}
				})));
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
		showResultsSize(null);
	}

	private void delete() {
		// TODO: delete selected records on the grid
	}

	@Override
	public void notifyEia(Eia eia) {
		for (EIASelectionListener listener : listeners)
			listener.select(eia);
	}

	private void notifySelectedEia() {
		GHAGridRecord<Eia> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.alert(GHAStrings.get("record-not-selected"));
			return;
		}
		notifyEia(((EIARecord) selectedRecord).toEntity());
		hide();
		// TODO: VALIDAR grid.removeSelectedData();
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
		showResultsSize(records);
		ListGridRecord[] array = EIAUtil.toGridRecords(records).toArray(
				new EIARecord[] {});
		grid.setData(array);
		if (!isVisible())
			this.animateShow(AnimationEffect.FADE);
	}

}
