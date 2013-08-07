package org.fourgeeks.gha.webclient.client.eiatype.spare;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeSpare;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EIATypeSpareGridPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable, GHAHideable{

	private EIATypeSpareGrid eiaTypeSpareGrid = new EIATypeSpareGrid();
	private EIATypeSearchForm eiaTypeSearchForm;
	private EiaType eiaType;
	
	{
		eiaTypeSearchForm = new EIATypeSearchForm();
		eiaTypeSearchForm.AddEIATypeSelectionListener(new EIATypeSelectionListener() {
			
			@Override
			public void select(EiaType eiaType) {
				final EiaTypeSpare eiaTypeSpare = new EiaTypeSpare();
				eiaTypeSpare.setEiaType(EIATypeSpareGridPanel.this.eiaType);
				eiaTypeSpare.setSpare(eiaType);
				EIATypeSpareModel.save(eiaTypeSpare, new GHAAsyncCallback<EiaTypeSpare>() {

					@Override
					public void onSuccess(EiaTypeSpare result) {
						loadData();
					}
				});
			}
		});
	}
	
	public EIATypeSpareGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!
		
		
		Label title = new Label("<h3>Repuestos</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");
		
// //////Botones laterales
		
	    VLayout sideButtons = GHAUiHelper.createBar(
	    		new GHAImgButton("../resources/icons/new.png", new ClickHandler() {
	    			
					@Override
					public void onClick(ClickEvent event) {
						eiaTypeSearchForm.open();
					}
				}),
	    		new GHAImgButton("../resources/icons/delete.png", new ClickHandler() {
	    			
					@Override
					public void onClick(ClickEvent event) {
						EiaTypeSpare eiaTypeSpare = ((EIATypeSpareRecord) eiaTypeSpareGrid.getSelectedRecord()).toEntity();
						EIATypeSpareModel.delete(eiaTypeSpare.getId(), new GHAAsyncCallback<Void>() {

							@Override
							public void onSuccess(Void result) {
								loadData();
							}
						});
					}
				})
	    );
	    
	    HLayout mainPanel = new HLayout();
		mainPanel.addMembers(eiaTypeSpareGrid, sideButtons);
		
		addMembers(title, mainPanel);
	}

	private void loadData() {
		EIATypeSpareModel.find(eiaType, new GHAAsyncCallback<List<EiaTypeSpare>>() {

			@Override
			public void onSuccess(List<EiaTypeSpare> eiaTypeSpares) {
				ListGridRecord[] array = EIATypeSpareUtil.toGridRecords(eiaTypeSpares)
						.toArray(new EIATypeSpareRecord[] {});
				eiaTypeSpareGrid.setData(array);
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
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

}
