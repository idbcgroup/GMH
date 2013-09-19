package org.fourgeeks.gha.webclient.client.user;

import java.util.List;

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.mix.Citizen;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
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
public class UserSearchForm extends GHASlideInWindow implements UserSelectionProducer{

	private UserGrid grid;
	private GHATextItem usernameItem, firstNameItem, secondNameItem,
			firstLastNameItem, secondLastNameItem, emailItem, alterEmailItem,
			idItem;
	private GHASelectItem genderSelectItem;
	private UserAddForm addForm;

	{
		usernameItem = new GHATextItem("Usuario",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		firstNameItem = new GHATextItem("Primer nombre",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondNameItem = new GHATextItem("Segundo nombre",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		firstLastNameItem = new GHATextItem("Primer apellido",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		secondLastNameItem = new GHATextItem("Segundo apellido",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		emailItem = new GHATextItem("Correo",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		alterEmailItem = new GHATextItem("Correo alternativo",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		idItem = new GHATextItem("No. Identificiación",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		genderSelectItem = new GHASelectItem("Género",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE);
		grid = new UserGrid();
		addForm = new UserAddForm();
	}

	/**
	 * 
	 */
	public UserSearchForm() {
		super(1);
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight() + "px");

		Label title = new Label("<h3>Búsqueda de Usuarios</h3>");
		title.setWidth(400);
		title.setHeight("35px");
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
		formLayout.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
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
						// selectEia(((EIARecord) grid.getSelectedRecord())
						// .toEntity());
						hide();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						addForm.open();
						addForm.show();
					}
				}));

		gridLayout.addMembers(grid, sideGridButtons);

		addMember(gridLayout);

		fillExtras();
	}

	private void fillExtras() {
		genderSelectItem.setValueMap(GenderTypeEnum.toValueMap());
	}

	/**
	 * 
	 * @param eiaSelectionListener
	 */
	public void addEIASelectionListener(
			EIASelectionListener eiaSelectionListener) {
		// listeners.add(eiaSelectionListener);
	}

	private void selectEia(Eia eia) {
		// for (EIASelectionListener listener : listeners)
		// listener.select(eia);
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
				ListGridRecord[] array = UserUtil.toGridRecords(ssoUsers).toArray(new UserRecord[]{});
				grid.setData(array);
				//TODO: si hay un registro que coincide con el ssoU seleccionarlo.
			}
		});
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
		setHeight(GHAUiHelper.getTabHeight() + "px");
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#addUserSelectionListener(org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void addUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.user.UserSelectionProducer#removeUserSelectionListener(org.fourgeeks.gha.webclient.client.user.UserSelectionListener)
	 */
	@Override
	public void removeUserSelectionListener(
			UserSelectionListener userSelectionListener) {
		// TODO Auto-generated method stub
		
	}

}
