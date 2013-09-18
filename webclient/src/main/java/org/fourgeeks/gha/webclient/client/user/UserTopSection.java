package org.fourgeeks.gha.webclient.client.user;

import org.fourgeeks.gha.domain.ess.SSOUser;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class UserTopSection extends HLayout implements GHAClosable,
		ResizeHandler, GHAHideable, UserSelectionListener {

	private UserSearchForm userSearchForm;
	private GHATextItem usernameItem, idItem, nameItem, lastNameItem,
			typeidSelectItem, blockedItem;

	{
		userSearchForm = new UserSearchForm();
		usernameItem = new GHATextItem("Usuario",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		blockedItem = new GHATextItem("Estado", false);
		nameItem = new GHATextItem("Primer Nombre",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		lastNameItem = new GHATextItem("Apellido",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		typeidSelectItem = new GHATextItem("Tipo ID",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
		idItem = new GHATextItem("No. Identificiación",
				GHAUiHelper.FOUR_COLUMN_FORMITEM_SIZE, false);
	}

	/**
	 * @param tab
	 */
	public UserTopSection(final UserTab tab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		tab.addGHAClosableHandler(this);
		tab.addUserSelectionListener(this);
		// tab.addGHAHideableHandler(this);
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		setBackgroundColor("#EAEAEA");
		DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(5);
		form.setItems(usernameItem, blockedItem, new GHASpacerItem(2),
				nameItem, lastNameItem, typeidSelectItem, idItem);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}), new GHAImgButton("../resources/icons/clean.png"),
				new GHAImgButton("../resources/icons/cancel.png",
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								GHATabSet.closeTab(tab);
							}
						}));

		addMembers(form, new LayoutSpacer(), sideButtons);

	}

	/**
	 * 
	 */
	public void search() {
		userSearchForm.open();
	}

	@Override
	public void close() {
		userSearchForm.close();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
	}

	@Override
	public void select(SSOUser ssoUser) {
		usernameItem.setValue(ssoUser.getUserName());
		blockedItem.setValue(ssoUser.getUserLogonStatus());
		nameItem.setValue(ssoUser.getBpu().getCitizen().getFirstName());
		lastNameItem.setValue(ssoUser.getBpu().getCitizen().getFirstLastName());
		typeidSelectItem.setValue(ssoUser.getBpu().getCitizen().getIdType());
		idItem.setValue(ssoUser.getBpu().getCitizen().getIdNumber());

	}
}