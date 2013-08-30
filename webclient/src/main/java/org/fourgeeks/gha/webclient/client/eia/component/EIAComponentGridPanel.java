package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eia.EIASearchForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentGridPanel;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentModel;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentRecord;
import org.fourgeeks.gha.webclient.client.eiatype.component.EIATypeComponentUtil;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIAComponentGridPanel extends VLayout implements GHAClosable, GHAHideable, EIASelectionListener {

	private EIAComponentGrid eiaComponentGrid;
	private Eia eia;
	private EIASearchForm eIASearchForm;
	
	{
		eiaComponentGrid = new EIAComponentGrid();
		eiaComponentGrid.getComponentobsField().addCellSavedHandler(new CellSavedHandler() {
			
			@Override
			public void onCellSaved(CellSavedEvent event) {
				// TODO Auto-generated method stub
				Window.alert("onCellSaved 1");
				EiaComponent eiaComponent = ((EIAComponentRecord) event.getRecord()).toEntity();
				eiaComponent.setComponentObs((String) event.getNewValue());
				Window.alert("valor nuevo: "+ eiaComponent.getComponentObs());
				EIAComponentModel.update(eiaComponent,
						new GHAAsyncCallback<EiaComponent>() {

							@Override
							public void onSuccess(EiaComponent result) {
								Window.alert("onCellSaved 1--onSuccess");
							}
						});
				
			}
		});
		
		eIASearchForm = new EIASearchForm();
		eIASearchForm.addEiaSelectionListener(new EIASelectionListener() {
			
			@Override
			public void select(Eia eia) {
				// TODO Auto-generated method stub
				Window.alert("select EIASelectionListener 1");
				final EiaComponent eiaComponent = new EiaComponent();
				eiaComponent.setParentEia(EIAComponentGridPanel.this.eia);
				if(EIAComponentGridPanel.this.eia!=null)Window.alert("EIAComponentGridPanel.this.eia: "+EIAComponentGridPanel.this.eia.getCode());
				eiaComponent.setEia(eia);
				if(eia!=null)Window.alert("eia: "+eia.getCode());
				eiaComponent.setComponentObs("");
				Window.alert("select EIASelectionListener 2");
				EIAComponentModel.save(eiaComponent,
						new GHAAsyncCallback<EiaComponent>() {

							@Override
							public void onSuccess(EiaComponent result) {
								// TODO Auto-generated method stub
								Window.alert("onSuccess 1");
								loadData();
								Window.alert("onSuccess 2");
								
							}
						});

			}
		});
	}
	
	
	public EIAComponentGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!
		
		
		Label title = new Label("<h3>Componentes de EIA</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
		
		
		
// //////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(
	    		new GHAImgButton("../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
//						form.animateShow(AnimationEffect.FLY);
						eIASearchForm.open();
					}
				}),
	    		new GHAImgButton("../resources/icons/edit.png"),
	    		new GHAImgButton("../resources/icons/delete.png", new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						//EiaComponent eiaComponent = ((EIAComponentRecord) eiaComponentGrid.getSelectedRecord()).toEntity();
						EiaComponent eiaComponent = eiaComponentGrid.getSelectedEntity();
						EIAComponentModel.delete(eiaComponent.getId(),
								new GHAAsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										Window.alert("onSuccess delete 1");
										loadData();
										Window.alert("onSuccess delete 2");
									}
								});

					}
				}),
	    		new GHAImgButton("../resources/icons/set.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
//						EIARecord selectedRecord = (EIARecord) eiaTypeGrid.getSelectedRecord();
//						History.newItem("eia/" + selectedRecord.getCode());
					}
				}));
		
		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(eiaComponentGrid,sideButtons);
		
		addMembers(title, mainPanel);
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void select(Eia eia) {
		// TODO Auto-generated method stub
		if(eia != null)Window.alert("select EIAComponentGridPanel eia: "+eia.getCode());
		this.eia = eia;
		loadData();
	}

	private void loadData() {
		// TODO Auto-generated method stub
		EIAComponentModel.findByEiaId(eia,
				new GHAAsyncCallback<List<EiaComponent>>() {

					@Override
					public void onSuccess(List<EiaComponent> eiaComponents) {
						ListGridRecord[] array = EIAComponentUtil.toGridRecords(eiaComponents).toArray(
								new EIAComponentRecord[]{});
						eiaComponentGrid.setData(array);
					}

		});

	}
}

