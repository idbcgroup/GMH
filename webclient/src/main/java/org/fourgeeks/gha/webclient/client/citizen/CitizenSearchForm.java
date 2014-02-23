/**
 * 
 */
package org.fourgeeks.gha.webclient.client.citizen;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

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
public class CitizenSearchForm extends GHASearchForm<Citizen> implements
CitizenSelectionProducer {

	private CitizenGrid grid;
	private List<CitizenSelectionListener> listeners;
	// private CitizenTopForm userTopForm;

	{
		grid = new CitizenGrid();
		listeners = new ArrayList<CitizenSelectionListener>();
	}

	/**
	 * @param title
	 * 
	 */
	public CitizenSearchForm(String title) {
		super(title);
		// userTopForm = new UserTopForm(new UserResultSet());

		// Event Handlers
		final ClickHandler searchClickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		};

		final VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
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

		final HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		formLayout.addMembers(new LayoutSpacer(), sideButtons);

		addMembers(formLayout,
				GHAUiHelper
				.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
						+ "px"));

		final HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		final VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						selectUser();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"));

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * addUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void addCitizenSelectionListener(
			CitizenSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);
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
	public void notifyCitizen(Citizen citizen) {
		for (final CitizenSelectionListener listener : listeners)
			listener.onCitizenSelect(citizen);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#
	 * removeUserSelectionListener
	 * (org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void removeCitizenSelectionListener(
			CitizenSelectionListener userSelectionListener) {
		listeners.add(userSelectionListener);
	}

	@Override
	public void search() {
		// userTopForm.search();
	}

	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * org.fourgeeks.gha.webclient.client.user.UserSelectionListener#select(
	// * org.fourgeeks.gha.domain.ess.SSOUser)
	// */
	// @Override
	// public void select(Citizen citizen) {
	// final CitizenRecord gridRecord = CitizenUtil.toGridRecord(citizen);
	// final ListGridRecord[] array = { gridRecord };
	// grid.setData(array);
	// grid.selectRecord(gridRecord);
	// }

	/**
	 * 
	 */
	private void selectUser() {
		final GHAGridRecord<Citizen> selectedRecord = grid.getSelectedRecord();
		if (selectedRecord == null) {
			GHAAlertManager.alert("record-not-selected");
			return;
		}
		notifyCitizen(((CitizenRecord) selectedRecord).toEntity());
		hide();
	}

}
