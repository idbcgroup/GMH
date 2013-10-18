package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * @author emiliot
 * 
 */
public class EiaTypeResultSet extends GHASlideInWindow implements
		EiaTypeSelectionProducer {
	private List<EIATypeSelectionListener> listeners;
	private EIATypeGrid eiaTypeGrid;

	{
		listeners = new ArrayList<EIATypeSelectionListener>();
		eiaTypeGrid = new EIATypeGrid();
	}

	/**
	 * 
	 */
	public EiaTypeResultSet() {
		super();

	}

	/**
	 * notify selected eiaType from the grid
	 */
	private void selectEiaType() {
		GHAGridRecord<EiaType> selectedRecord = eiaTypeGrid.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.alert(GHAStrings.get("record-not-selected"));
			return;
		}
		notifyEiaType(((EIATypeRecord) selectedRecord).toEntity());
		hide();
	}

	/**
	 * this method set the list of records inside the grid
	 * 
	 * @param records
	 */
	public void setRecords(List<EiaType> records) {
		ListGridRecord[] array = EIATypeUtil.toGridRecords(records).toArray(
				new EIATypeRecord[] {});
		eiaTypeGrid.setData(array);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.google.gwt.event.logical.shared.ResizeHandler#onResize(com.google
	 * .gwt.event.logical.shared.ResizeEvent)
	 */
	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");
	}

	private void notifyEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
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

}
