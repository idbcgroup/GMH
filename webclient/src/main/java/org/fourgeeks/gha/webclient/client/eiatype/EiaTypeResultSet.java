package org.fourgeeks.gha.webclient.client.eiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class EiaTypeResultSet extends VLayout implements
		EiaTypeSelectionProducer, ResizeHandler {
	private List<EIATypeSelectionListener> listeners;
	private EIATypeGrid grid;

	{
		listeners = new ArrayList<EIATypeSelectionListener>();
		grid = new EIATypeGrid();
	}

	/**
	 * 
	 */
	public EiaTypeResultSet() {
		super();
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setVisible(false);
		addMember(new GHALabel(GHAStrings.get("search-results")));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid, GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectedEiaType();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/delete.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO delete an selected eiatype
					}
				})));
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

	private void notifyEiaType(EiaType eiaType) {
		for (EIATypeSelectionListener listener : listeners)
			listener.select(eiaType);
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

	/**
	 * notify selected eiaType from the grid
	 */
	private void notifySelectedEiaType() {
		GHAGridRecord<EiaType> selectedRecord = grid.getSelectedRecord();
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
		// if only one record is on the list, notify the element and return
		if (records.size() == 1) {
			notifyEiaType(records.get(0));
			this.hide();
			return;
		}

		ListGridRecord[] array = EIATypeUtil.toGridRecords(records).toArray(
				new EIATypeRecord[] {});
		grid.setData(array);
		// setAnimateAcceleration(AnimationAcceleration.NONE);
		this.animateShow(AnimationEffect.FADE);
	}

}
