package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserSearchForm extends GHASlideInWindow implements
		UserSelectionProducer, UserSelectionListener {

	private UserGrid grid;
	private List<UserSelectionListener> listeners;
	private UserTopForm userTopForm;

	{
		grid = new UserGrid();
		listeners = new ArrayList<UserSelectionListener>();
	}

	/**
* 
*/
	public UserSearchForm() {
		super();
		userTopForm = new UserTopForm(new UserResultSet());
		setTop(GHAUiHelper.getTopSpace());
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");

		GHALabel title = new GHALabel("Búsqueda de Usuarios");
		addMember(title);

		// Event Handlers
		ClickHandler searchClickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		};

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				searchClickHandler), new GHAImgButton(
				"../resources/icons/clean.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						grid.setData(new ListGridRecord[0]);
					}
				}), new GHAImgButton("../resources/icons/cancel.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						hide();
					}
				}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		formLayout.addMembers(new LayoutSpacer(), sideButtons);

		addMembers(title, formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"));

		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						selectUser();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"));

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);

	}

	private void search() {
		userTopForm.search();
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");
	}

	@Override
	public void notifyUser(SSOUser ssoUser) {
		for (UserSelectionListener listener : listeners)
			listener.select(ssoUser);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * addUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * removeUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.user.UserSelectionListener#select(
	 * org.fourgeeks.gha.domain.ess.SSOUser)
	 */
	@Override
	public void select(SSOUser ssoUser) {
		UserRecord gridRecord = UserUtil.toGridRecord(ssoUser);
		ListGridRecord[] array = { gridRecord };
		grid.setData(array);
		grid.selectRecord(gridRecord);
	}

	/**
	 * 
	 */
	private void selectUser() {
		GHAGridRecord<SSOUser> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.oldAlert(GHAStrings.get("record-not-selected"));
			return;
		}
		notifyUser(((UserRecord) selectedRecord).toEntity());
		hide();
	}

	@Override
	public boolean canBeClosen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeHidden() {
		// TODO Auto-generated method stub
		return false;
	}

}