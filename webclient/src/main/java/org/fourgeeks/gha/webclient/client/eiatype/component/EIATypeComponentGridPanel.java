package org.fourgeeks.gha.webclient.client.eiatype.component;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.BackgroundRepeat;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeComponentGridPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable {

	private EIATypeComponentGrid eiaTypeComponentGrid;
	private EiaType eiaType;
	private EIATypeSearchForm eiaTypeSearchForm;
	
	{
		eiaTypeComponentGrid = new EIATypeComponentGrid();
		eiaTypeComponentGrid.getRequiredField().addCellSavedHandler(new CellSavedHandler() {
			
			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaTypeComponent eiaTypeComponent = ((EIATypeComponentRecord) event.getRecord()).toEntity();
				eiaTypeComponent.setComponentRequired((Boolean) event.getNewValue());
				EIATypeComponentModel.update(eiaTypeComponent, new GHAAsyncCallback<EiaTypeComponent>() {

					@Override
					public void onSuccess(EiaTypeComponent result) {
					}
				});
			}
		});
		eiaTypeComponentGrid.getReplaceableField().addCellSavedHandler(new CellSavedHandler() {
			
			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaTypeComponent eiaTypeComponent = ((EIATypeComponentRecord) event.getRecord()).toEntity();
				eiaTypeComponent.setComponentReplaceable((Boolean) event.getNewValue());
				EIATypeComponentModel.update(eiaTypeComponent, new GHAAsyncCallback<EiaTypeComponent>() {

					@Override
					public void onSuccess(EiaTypeComponent result) {
					}
				});
			}
		});
		
		eiaTypeSearchForm = new EIATypeSearchForm();
		eiaTypeSearchForm.AddEIATypeSelectionListener(new EIATypeSelectionListener() {
			
			@Override
			public void select(EiaType eiaType) {
				final EiaTypeComponent eiaTypeComponent = new EiaTypeComponent();
				eiaTypeComponent.setParentEiaType(EIATypeComponentGridPanel.this.eiaType);
				eiaTypeComponent.setEiaType(eiaType);
				eiaTypeComponent.setComponentReplaceable(false);
				eiaTypeComponent.setComponentRequired(false);
				EIATypeComponentModel.save(eiaTypeComponent, new GHAAsyncCallback<EiaTypeComponent>() {

					@Override
					public void onSuccess(EiaTypeComponent result) {
						loadData();
					}
				});
			}
		});
	}
	
	public EIATypeComponentGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		
		Label title = new Label("<h3>Partes/Componentes de EIAType</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
		
// //////Botones laterales
		VLayout sideButtons = new VLayout();
		sideButtons.setWidth(30);
		sideButtons.setLayoutMargin(5);
		// botones1.setBackgroundImage("../resources/img/botonBox.jpg");
		sideButtons.setBackgroundColor("#E0E0E0");
		sideButtons.setBackgroundRepeat(BackgroundRepeat.REPEAT_Y);
		sideButtons.setMembersMargin(10);
		sideButtons.setDefaultLayoutAlign(Alignment.CENTER);

		ImgButton addButton = new ImgButton();
		// addButton.sinkEvents(Event.MOUSEEVENTS);
		addButton.setSrc("../resources/icons/new.png");
		addButton.setShowRollOver(false);
		addButton.setSize("20px", "20px");
		
		addButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				eiaTypeSearchForm.open();
			}
		});
		
		ImgButton deleteButton = new ImgButton();
		deleteButton.setSrc("../resources/icons/delete.png");
		deleteButton.setShowRollOver(false);
		deleteButton.setSize("20px", "20px");
		deleteButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				EiaTypeComponent eiaTypeComponent = ((EIATypeComponentRecord) eiaTypeComponentGrid.getSelectedRecord()).toEntity();
				EIATypeComponentModel.delete(eiaTypeComponent.getId(), new GHAAsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						loadData();
					}
				});
			}
		});

		sideButtons.addMembers(addButton, deleteButton);
		
		final HLayout gridContainer = new HLayout();
        gridContainer.addMembers(eiaTypeComponentGrid, sideButtons);
		
		addMembers(title, gridContainer);		
	}
	
	private void loadData() {
		EIATypeComponentModel.find(eiaType, new GHAAsyncCallback<List<EiaTypeComponent>>() {

			@Override
			public void onSuccess(List<EiaTypeComponent> eiaTypeComponents) {
				ListGridRecord[] array = EIATypeComponentUtil.toGridRecords(eiaTypeComponents)
						.toArray(new EIATypeComponentRecord[] {});
				eiaTypeComponentGrid.setData(array);
			}

		});
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		loadData();
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
