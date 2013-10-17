package org.fourgeeks.gha.webclient.client.user;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
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
	private GHATextItem usernameItem, firstNameItem, secondNameItem,
			firstLastNameItem, secondLastNameItem, emailItem, alterEmailItem,
			idItem;
	private GHASelectItem genderSelectItem;
	private UserAddForm addForm;
	private List<UserSelectionListener> listeners;

	{
		usernameItem = new GHATextItem("Usuario",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		usernameItem.setLength(20);
		usernameItem.setMask("AAAAAAAAAAAAAAAAAAAA");
		firstNameItem = new GHATextItem("Primer nombre",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		firstNameItem.setLength(20);
		firstNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");
		secondNameItem = new GHATextItem("Segundo nombre",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondNameItem.setLength(20);
		secondNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");
		firstLastNameItem = new GHATextItem("Primer apellido",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		firstLastNameItem.setLength(20);
		firstLastNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");
		secondLastNameItem = new GHATextItem("Segundo apellido",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondLastNameItem.setLength(20);
		secondLastNameItem.setMask(">A<AAAAAAAAAAAAAAAAAAA");
		emailItem = new GHATextItem("Correo",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		emailItem.setLength(254);
		alterEmailItem = new GHATextItem("Correo alternativo",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		alterEmailItem.setLength(254);
		idItem = new GHATextItem("No. Identificación",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		idItem.setLength(20);
		idItem.setMask("####################");
		genderSelectItem = new GHASelectItem("Género",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		grid = new UserGrid();
		addForm = new UserAddForm();
		listeners = new ArrayList<UserSelectionListener>();
	}

	/**
* 
*/
	public UserSearchForm() {
		super();
		setTop(GHAUiHelper.getTopSpace());
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");

		GHALabel title = new GHALabel("Búsqueda de Usuarios");
		addMember(title);

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);

		form.setItems(usernameItem, firstNameItem, secondNameItem,
				firstLastNameItem, secondLastNameItem, idItem, emailItem,
				alterEmailItem, genderSelectItem);

		// Event Handlers
		ClickHandler searchClickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		};
		KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getKeyName().equals("Enter")) {
					search();
				}
			}
		};
		usernameItem.addKeyUpHandler(searchKeyUpHandler);
		firstNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondNameItem.addKeyUpHandler(searchKeyUpHandler);
		firstLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		secondLastNameItem.addKeyUpHandler(searchKeyUpHandler);
		emailItem.addKeyUpHandler(searchKeyUpHandler);
		genderSelectItem.addKeyUpHandler(searchKeyUpHandler);
		alterEmailItem.addKeyUpHandler(searchKeyUpHandler);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", searchClickHandler),
				new GHAImgButton("../resources/icons/clean.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								form.clearValues();
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
		formLayout.setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

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
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						addForm.open();
						// addForm.show();
					}
				}));

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);

		fillExtras();
		// register as listener to the addform producer
		addForm.addUserSelectionListener(this);
	}

	private void fillExtras() {
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());
	}

	private void search() {
		SSOUser ssoUser = new SSOUser();
		if (usernameItem.getValue() != null)
			ssoUser.setUserName(usernameItem.getValueAsString());

		Citizen citizen = new Citizen();
		if (firstNameItem.getValue() != null)
			citizen.setFirstName(firstNameItem.getValueAsString());
		if (secondNameItem.getValue() != null)
			citizen.setSecondName(secondNameItem.getValueAsString());
		if (firstLastNameItem.getValue() != null)
			citizen.setFirstLastName(firstLastNameItem.getValueAsString());
		if (secondLastNameItem.getValue() != null)
			citizen.setSecondLastName(secondLastNameItem.getValueAsString());
		if (idItem.getValue() != null)
			citizen.setIdNumber(idItem.getValueAsString());
		if (genderSelectItem.getValue() != null)
			citizen.setGender(GenderTypeEnum.valueOf(genderSelectItem
					.getValueAsString()));
		if (emailItem.getValue() != null)
			citizen.setPrimaryEmail(emailItem.getValueAsString());
		if (alterEmailItem.getValue() != null)
			citizen.setAlternativeEmail(alterEmailItem.getValueAsString());

		Bpu bpu = new Bpu();
		bpu.setCitizen(citizen);
		ssoUser.setBpu(bpu);
		search(ssoUser);

	}

	private void search(final SSOUser ssoU) {
		UserModel.find(ssoU, new GHAAsyncCallback<List<SSOUser>>() {

			@Override
			public void onSuccess(List<SSOUser> ssoUsers) {
				ListGridRecord[] array = UserUtil.toGridRecords(ssoUsers)
						.toArray(new UserRecord[] {});
				grid.setData(array);
				// TODO: seleccionar un elemento si coincide con el usuario de
				// la busqueda
			}
		});
	}

	@Override
	public void close() {
		addForm.destroy();
		destroy();
	}

	@Override
	public void hide() {
		super.hide();
		addForm.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");
	}

	// Producer Consumer stuff
	protected void notifyUser(SSOUser ssoUser) {
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
			GHANotification.alert(GHAStrings.get("record-not-selected"));
			return;
		}
		notifyUser(((UserRecord) selectedRecord).toEntity());
		hide();
	}

}