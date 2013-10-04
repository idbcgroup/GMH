package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
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

public class MaintenanceActivityTopSection extends HLayout
implements MaintenanceActivitySelectionListener, GHAClosable, ResizeHandler {

	private final MaintenanceActivityTab maintenanceActivityTab;
	private MaintenanceActivitySearchForm maintenanceActivitySearchForm;
	private GHATextItem nameItem, descriptionItem;

	{
		maintenanceActivitySearchForm = new MaintenanceActivitySearchForm();

		nameItem = new GHATextItem("Nombre", false);
		descriptionItem = new GHATextItem("Descripcion",420, false);
		descriptionItem.setColSpan(4);

	}

	public MaintenanceActivityTopSection(MaintenanceActivityTab tab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);

		tab.addGHAClosableHandler(this);
		maintenanceActivityTab = tab;

		//register tab as search listener, and topsection as listener
		maintenanceActivitySearchForm.addMaintenanceActivitySelectionListener(tab);
		tab.addMaintenanceActivitySelectionListener(this);

		maintenanceActivityTab.addGHAHideableHandler(new GHAHideable() {

			@Override
			public void hide() {
				maintenanceActivitySearchForm.hide();
			}
		});
		maintenanceActivityTab.addGHAClosableHandler(new GHAClosable() {

			@Override
			public void close() {
				maintenanceActivitySearchForm.destroy();
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
		form.setItems(nameItem,new GHASpacerItem(2),
				descriptionItem);

		VLayout sideButtons = GHAUiHelper.createBar(
				new GHAImgButton("../resources/icons/search.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}),
				new GHAImgButton("../resources/icons/clean.png"),
				new GHAImgButton("../resources/icons/cancel.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						GHATabSet.closeTab(maintenanceActivityTab);
					}
				})
				);

		addMembers(form, new LayoutSpacer(), sideButtons);

	}

	@Override
	public void close() {
		maintenanceActivitySearchForm.close();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");		
	}

	public void search() {
		maintenanceActivitySearchForm.open();
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.MaintenanceActivitySelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		nameItem.setValue(maintenanceActivity.getName());
		descriptionItem.setValue(maintenanceActivity.getDescription());
	}

}