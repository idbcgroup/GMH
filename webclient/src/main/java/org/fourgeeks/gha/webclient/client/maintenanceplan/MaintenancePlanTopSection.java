package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHATabSet;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

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
		implements EIASelectionListener, GHAClosable, ResizeHandler {

	private final MaintenancePlanTab maintenancePlanTab;
	private List<EIASelectionListener> selectionListeners;
	private MaintenancePlanSearchForm maintenancePlanSearchForm;
	private GHATextItem codeItem, frequencyItem, periodOfTimeItem,  
	usesItem, descriptionItem;
		
	{
		maintenancePlanSearchForm = new MaintenancePlanSearchForm();
		
		codeItem = new GHATextItem("Código", false);
		frequencyItem = new GHATextItem("Frecuencia", false);
		periodOfTimeItem = new GHATextItem("Periodo de Tiempo", false);
		usesItem = new GHATextItem("Usos", false);
		
		descriptionItem = new GHATextItem("Descripcion",420, false);
		descriptionItem.setColSpan(4);

	}

	public MaintenancePlanTopSection(MaintenancePlanTab tab) {
		super();
		GHAUiHelper.addGHAResizeHandler(this);
		
		tab.addGHAClosableHandler(this);
		maintenancePlanTab = tab;
		maintenancePlanSearchForm.addEIASelectionListener(maintenancePlanTab);
		
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
		form.setItems(codeItem,frequencyItem,periodOfTimeItem, usesItem,
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



	public void AddEIATypeSelectionListener(EIATypeSelectionListener selecionListener) {
		//selectionListeners.add(selecionListener);
	}

	@Override
	public void close() {
		
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");		
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		//for (EIASelectionListener listener : selectionListeners)
		//	listener.select(eia);
	}
}