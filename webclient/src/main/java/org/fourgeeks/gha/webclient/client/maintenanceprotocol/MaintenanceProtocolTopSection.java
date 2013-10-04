package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
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

public class MaintenanceProtocolTopSection extends HLayout implements
		MaintenanceProtocolSelectionListener, GHAClosable, ResizeHandler {

	private final MaintenanceProtocolTab maintenanceProtocolTab;
	private MaintenanceProtocolSearchForm maintenanceProtocolSearchForm;
	private GHATextItem nameItem, descriptionItem;

	{
		maintenanceProtocolSearchForm = new MaintenanceProtocolSearchForm();

		nameItem = new GHATextItem("Nombre", false);
		descriptionItem = new GHATextItem("Descripcion", 420, false);
		descriptionItem.setColSpan(4);

	}

	public MaintenanceProtocolTopSection(MaintenanceProtocolTab tab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);

		tab.addGHAClosableHandler(this);
		maintenanceProtocolTab = tab;

		// register tab as search listener, and topsection as listener
		maintenanceProtocolSearchForm
				.addMaintenanceProtocolSelectionListener(tab);
		tab.addMaintenanceProtocolSelectionListener(this);
		tab.addGHAHideableHandler(maintenanceProtocolSearchForm);
		tab.addGHAClosableHandler(maintenanceProtocolSearchForm);

		setStyleName("sides-padding padding-top");// Esto es VUDU!
		setWidth100();
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		setDefaultLayoutAlign(VerticalAlignment.CENTER);
		setBackgroundColor("#EAEAEA");

		DynamicForm form = new DynamicForm();
		// form.setWidth("100px");
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);
		form.setItems(nameItem, new GHASpacerItem(2), descriptionItem);

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
								GHATabSet.closeTab(maintenanceProtocolTab);
							}
						}));

		addMembers(form, new LayoutSpacer(), sideButtons);

	}

	public void search() {
		maintenanceProtocolSearchForm.open();
	}

	@Override
	public void close() {
		maintenanceProtocolSearchForm.close();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.
	 * MaintenanceProtocolSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		nameItem.setValue(maintenanceProtocol.getName());
		descriptionItem.setValue(maintenanceProtocol.getDescription());
	}
}