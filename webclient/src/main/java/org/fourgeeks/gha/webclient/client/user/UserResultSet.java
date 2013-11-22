package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAResultSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.CellDoubleClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @author alacret
 * 
 */
public class UserResultSet extends GHAResultSet<SSOUser> implements
		UserSelectionProducer, ResizeHandler, HideableListener,
		ClosableListener {
	private List<UserSelectionListener> listeners = new ArrayList<UserSelectionListener>();
	private UserGrid grid = new UserGrid();
	private ResultSetContainerType containerType;

	{
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedUser();
			}
		});
	}

	/**
	 * 
	 */
	public UserResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;
		HLayout gridPanel = new HLayout();
		gridPanel.addMembers(grid, GHAUiHelper.createBar(new GHACheckButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						notifySelectedUser();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"),
				new GHADeleteButton(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						delete();
					}
				})));
		
		if (containerType == ResultSetContainerType.SEARCH_FORM) {
			setHeight(getHeight() - 35);
		}
		addMember(gridPanel);
	}

	protected void delete() {
		// TODO Auto-generated method stub
	}

	@Override
	public void notifyUser(SSOUser user) {
		for (UserSelectionListener listener : listeners)
			listener.select(user);
	}

	/**
	 * notify selected eiaType from the grid
	 */
	private void notifySelectedUser() {
		GHAGridRecord<SSOUser> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.alert(GHAStrings.get("record-not-selected"));
			return;
		}
		notifyUser(selectedRecord.toEntity());
		hide();
	}

	@Override
	public void setRecords(List<SSOUser> records, boolean notifyIfOnlyOneResult) {
		// if only one record is on the list, notify the element and return
		if (notifyIfOnlyOneResult && records.size() == 1) {
			notifyUser(records.get(0));
			this.hide();
			return;
		}

		showResultsSize(records, false);
		ListGridRecord[] array = UserUtil.toGridRecords(records).toArray(
				new UserRecord[] {});
		grid.setData(array);
		// setAnimateAcceleration(AnimationAcceleration.NONE);
		this.animateShow(AnimationEffect.FADE);
	}

	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);

	}

	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.remove(userSelectionListener);

	}

	@Override
	public void clean() {
		grid.setData(new UserRecord[] {});
		showResultsSize(null, true);
	}
	
	@Override
	public void onResize(ResizeEvent event) {
		super.onResize(event);
		if (containerType == ResultSetContainerType.SEARCH_FORM) {
			setHeight(getHeight() - 35);
		}
	}

}
