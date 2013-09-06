package org.fourgeeks.gha.webclient.client.user;

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

public class UserTopSection extends HLayout
		implements GHAClosable, ResizeHandler {

	private final UserTab userTab;
	private UserSearchForm userSearchForm;
	private GHATextItem codeItem, nameItem, descriptionItem;
		
	{
		userSearchForm = new UserSearchForm();
		
		codeItem = new GHATextItem("Código", false);
		nameItem = new GHATextItem("Nombre", false);
		descriptionItem = new GHATextItem("Descripcion",420, false);
		descriptionItem.setColSpan(4);

	}

	public UserTopSection(UserTab tab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		
		tab.addGHAClosableHandler(this);
		userTab = tab;
		
		userTab.addGHAHideableHandler(new GHAHideable() {
			
			@Override
			public void hide() {
				userSearchForm.hide();
			}
		});
		userTab.addGHAClosableHandler(new GHAClosable() {
			
			@Override
			public void close() {
				userSearchForm.destroy();
			}
		});
		
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		setBackgroundColor("#EAEAEA");

		DynamicForm form = new DynamicForm();
		//form.setWidth("100px");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(codeItem,nameItem,new GHASpacerItem(2),
					  descriptionItem);
		
		VLayout sideButtons = GHAUiHelper.createBar(
				new GHAImgButton("../resources/icons/search.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						search();
					}
				}),
				new GHAImgButton("../resources/icons/clean.png"),
				new GHAImgButton("../resources/icons/cancel.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						GHATabSet.closeTab(userTab);
					}
				})
		);
		
		addMembers(form, new LayoutSpacer(), sideButtons);

	}

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
}