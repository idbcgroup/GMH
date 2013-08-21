package org.fourgeeks.gha.webclient.client.eiatype.utility;

import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.material.MaterialGrid;
import org.fourgeeks.gha.webclient.client.material.MaterialModel;
import org.fourgeeks.gha.webclient.client.material.MaterialRecord;
import org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener;
import org.fourgeeks.gha.webclient.client.material.MaterialUtil;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIATypeUtilityGridPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable, GHAHideable {

	private MaterialGrid grid = new MaterialGrid();
	private UtilitySearchForm utilitySearchForm;
	private EiaType eiaType;
	
	
	{
		utilitySearchForm = new UtilitySearchForm();
		utilitySearchForm.addMaterialSelectionListener(new MaterialSelectionListener(){

			@Override
			public void select(Material material) {
				// TODO Auto-generated method stub
				EiaTypeUtility eiaTypeUtility = new EiaTypeUtility();
				eiaTypeUtility.setEiaType(EIATypeUtilityGridPanel.this.eiaType);
				eiaTypeUtility.setMaterial(material);
				
				EIATypeUtilityModel.save(eiaTypeUtility, new GHAAsyncCallback<EiaTypeUtility>(){

					@Override
					public void onSuccess(EiaTypeUtility result) {
						// TODO Auto-generated method stub
						loadData();
					}});
			}} );

	}
	
	/**
	 * 
	 */
	public EIATypeUtilityGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding top-padding");// Esto es VUDU!

		Label title = new Label("<h3>Servicios utilitarios</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");

		// //////Botones laterales

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						new UtilitySearchForm().open();
						utilitySearchForm.open();
					}
				}), 
				new GHAImgButton("../resources/icons/edit.png"),
				new GHAImgButton("../resources/icons/delete.png", new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						Material material =  grid.getSelectedRecord().toEntity();
						EIATypeUtilityModel.delete(material.getId(), new GHAAsyncCallback<Void>(){

							@Override
							public void onSuccess(Void result) {
								// TODO Auto-generated method stub
								loadData();
							}});

						
					}
				}),
				new GHAImgButton("../resources/icons/set.png",
						new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								// TODO Auto-generated method stub
								// EIARecord selectedRecord = (EIARecord)
								// eiaTypeGrid.getSelectedRecord();
								// History.newItem("eia/" +
								// selectedRecord.getCode());
							}
						}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(title, mainPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}
	
	private void loadData() {
		MaterialModel.getAllUtilities(new GHAAsyncCallback<List<Material>>() {

			@Override
			public void onSuccess(List<Material> materials) {
				// TODO Auto-generated method stub
				ListGridRecord[] array = MaterialUtil.toGridRecords(materials)
						.toArray(new MaterialRecord[] {});
				grid.setData(array);
			}
		});
	}


}
