package org.fourgeeks.gha.webclient.client.eiatype.utility;

import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener;

import com.google.gwt.user.client.rpc.AsyncCallback;
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

	// private MaterialGrid grid = new MaterialGrid();
	private EiaTypeUtilityGrid grid = new EiaTypeUtilityGrid();
	private UtilitySearchForm utilitySearchForm;
	private EiaType eiaType;

	{
		utilitySearchForm = new UtilitySearchForm();
		utilitySearchForm
				.addMaterialSelectionListener(new MaterialSelectionListener() {

					@Override
					public void select(Material material) {
						// TODO Auto-generated method stub
						EiaTypeUtility eiaTypeUtility = new EiaTypeUtility();
						eiaTypeUtility
								.setEiaType(EIATypeUtilityGridPanel.this.eiaType);
						eiaTypeUtility.setMaterial(material);
						EIATypeUtilityModel.save(eiaTypeUtility,
								new GHAAsyncCallback<EiaTypeUtility>() {

									@Override
									public void onSuccess(EiaTypeUtility result) {
										// TODO Auto-generated method stub
										loadData();
									}
								});
					}
				});

	}

	/**
	 * 
	 */
	public EIATypeUtilityGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!

		Label title = new Label("<h3>Servicios utilitarios</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");

		// //////Botones laterales

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						utilitySearchForm.open();
					}
				}), new GHAImgButton("../resources/icons/delete.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						// TODO Auto-generated method stub
						// EiaTypeUtility eiaTypeUtility =
						// ((EIATypeUtilityRecord)
						// grid.getSelectedRecord()).toEntity();
						EiaTypeUtility eiaTypeUtility = grid
								.getSelectedEntity();
						EIATypeUtilityModel.delete(eiaTypeUtility.getId(),
								new GHAAsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
										// TODO Auto-generated method stub
										loadData();
									}
								});

					}
				}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(title, mainPanel);
	}

	@Override
	public void select(EiaType eiaType) {
		// TODO Auto-generated method stub
		this.eiaType = eiaType;
	}

	@Override
	public void close() {
		utilitySearchForm.close();
	}

	@Override
	public void hide() {
		utilitySearchForm.hide();
	}

	private void loadData() {
		EIATypeUtilityModel.findByEiaType(eiaType,
				new AsyncCallback<List<EiaTypeUtility>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<EiaTypeUtility> result) {
						// TODO Auto-generated method stub
						ListGridRecord[] array = EIATypeUtilityUtil
								.toGridRecords(result).toArray(
										new EIATypeUtilityRecord[] {});
						grid.setData(array);

					}
				});
	}

}
