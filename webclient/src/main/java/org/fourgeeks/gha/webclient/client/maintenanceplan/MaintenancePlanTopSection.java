package org.fourgeeks.gha.webclient.client.maintenanceplan;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
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

public class MaintenancePlanTopSection extends HLayout
		implements MaintenancePlanSelectionListener, GHAClosable, ResizeHandler {

	private final MaintenancePlanTab maintenancePlanTab;
	private MaintenancePlanSearchForm maintenancePlanSearchForm;
	private GHATextItem nameItem, frequencyItem, periodOfTimeItem, descriptionItem;
		
	{
		maintenancePlanSearchForm = new MaintenancePlanSearchForm();
		
		nameItem = new GHATextItem("Nombre", false);
		frequencyItem = new GHATextItem("Frecuencia", false);
		periodOfTimeItem = new GHATextItem("Periodo de Tiempo", false);
		
		descriptionItem = new GHATextItem("Descripcion",420, false);
		descriptionItem.setColSpan(4);

	}

	public MaintenancePlanTopSection(MaintenancePlanTab tab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		
		tab.addGHAClosableHandler(this);
		maintenancePlanTab = tab;
		maintenancePlanSearchForm.addMaintenancePlanSelectionListener(tab);
		maintenancePlanTab.addGHAHideableHandler(new GHAHideable() {
			
			@Override
			public void hide() {
				maintenancePlanSearchForm.hide();
			}
		});
		maintenancePlanTab.addGHAClosableHandler(new GHAClosable() {
			
			@Override
			public void close() {
				maintenancePlanSearchForm.destroy();
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
		form.setItems(nameItem, frequencyItem,periodOfTimeItem, 
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
						GHATabSet.closeTab(maintenancePlanTab);
					}
				})
		);
		
		addMembers(form, new LayoutSpacer(), sideButtons);

	}

	public void search() {
		maintenancePlanSearchForm.open();
	}

	@Override
	public void close() {
		maintenancePlanSearchForm.close();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		// TODO Auto-generated method stub
	}

}