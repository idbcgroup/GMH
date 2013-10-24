package org.fourgeeks.gha.webclient.client.eia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeRecord;

import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.drawing.events.ResizedEvent;
import com.smartgwt.client.widgets.drawing.events.ResizedHandler;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class EiaResultSet extends VLayout implements EiaSelectionProducer,
		ResizedHandler, GHAHideable, GHAClosable {
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
		super();
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setVisible(false);
		addMember(new GHALabel(GHAStrings.get("search-results")));
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid, GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectedEia();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/delete.png", new ClickHandler() {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable#canBeClosen
	 * ()
	 */
	@Override
	public boolean canBeClosen() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable#canBeHidden
	 * ()
	 */
	@Override
	public boolean canBeHidden() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable#close()
	 */
	@Override
	public void close() throws UnavailableToCloseException {
		destroy();
	}

	private void notifyEia(Eia eia) {
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
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.smartgwt.client.widgets.drawing.events.ResizedHandler#onResized(com
	 * .smartgwt.client.widgets.drawing.events.ResizedEvent)
	 */
	@Override
	public void onResized(ResizedEvent event) {
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.eia.EiaSelectionProducer#
	 * removeEiaSelectionListener
	 * (org.fourgeeks.gha.webclient.client.eia.EIASelectionListener)
	 */
	@Override
	public void removeEiaSelectionListener(
			EIASelectionListener eiaSelectionListener) {
		listeners.remove(eiaSelectionListener);
	}

	public void setRecords(List<Eia> records) {
		// if only one record is on the list, notify the element and return
		if (records.size() == 1) {
			notifyEia(records.get(0));
			this.hide();
			return;
		}

		ListGridRecord[] array = EIAUtil.toGridRecords(records).toArray(
				new EIATypeRecord[] {});
		grid.setData(array);
		// setAnimateAcceleration(AnimationAcceleration.NONE);
		this.animateShow(AnimationEffect.FADE);
	}

}
