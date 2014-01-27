package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.ResultSetContainerType;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHACheckButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
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
public class UserResultSet extends GHAResultSet<SSOUser> implements
		UserSelectionProducer, ResizeHandler, HideableListener,
		ClosableListener {
	private List<UserSelectionListener> listeners = new ArrayList<UserSelectionListener>();
	private UserGrid grid;
	private ResultSetContainerType containerType;

	/**
	 * @param container 
	 * 
	 */
	public UserResultSet(ResultSetContainerType container) {
		super(GHAStrings.get("search-results"));
		this.containerType = container;
		
		grid = new UserGrid(){
			@Override
			public void onResize(ResizeEvent event) {
				super.onResize(event);
				grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
			}
		};
		grid.addCellDoubleClickHandler(new CellDoubleClickHandler() {

			@Override
			public void onCellDoubleClick(CellDoubleClickEvent event) {
				notifySelectedUser();
			}
		});
		grid.setHeight(GHAUiHelper.getResultSetGridSize(containerType));
		
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
		HLayout gridPanel = new HLayout();
		VLayout sideBar;

		GHACheckButton checkButton = new GHACheckButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				notifySelectedUser();
			}
		});

		if (containerType == ResultSetContainerType.TAB) {
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
		} else {
			sideBar = GHAUiHelper.createBar(checkButton);
//			setHeight(getHeight() - 42);
		}
		gridPanel.addMembers(grid, sideBar);

		// gridPanel.addMembers(grid, GHAUiHelper.createBar(new GHACheckButton(
		// new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// notifySelectedUser();
		// }
		// }), GHAUiHelper.verticalGraySeparator("2px"),
		// new GHADeleteButton(new ClickHandler() {
		//
		// @Override
		// public void onClick(ClickEvent event) {
		// delete();
		// }
		// })));
		//
		// if (containerType == ResultSetContainerType.SEARCH_FORM) {
		// setHeight(getHeight() - 35);
		// }
		addMember(gridPanel);
	}

	protected void delete() {
		if (grid.getSelectedRecord() == null) {
			GHAAlertManager.alert("record-not-selected");
			return;
		}

		String msj = grid.getSelectedRecords().length > 1 ? GHAStrings
				.get("users-delete-confirm") : GHAStrings
				.get("user-delete-confirm");
		GHAAlertManager.confirm(GHAStrings.get("user"), msj,
				new BooleanCallback() {

					@Override
					public void execute(Boolean value) {
						if (value) {
							List<SSOUser> entities = grid.getSelectedEntities();
							UserModel.delete(entities,
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
			GHAAlertManager.alert("record-not-selected");
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
		setHeight(GHAUiHelper.getResultSetHeight(containerType));
	}

}
