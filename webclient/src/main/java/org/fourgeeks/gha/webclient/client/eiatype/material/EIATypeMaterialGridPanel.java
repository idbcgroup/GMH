package org.fourgeeks.gha.webclient.client.eiatype.material;

import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.material.MaterialSearchForm;
import org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener;

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
public class EIATypeMaterialGridPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable, GHAHideable {

	private EiaTypeMaterialGrid grid = new EiaTypeMaterialGrid();
	private MaterialSearchForm materialSearchForm;
	private EiaType eiaType;

	{
		materialSearchForm = new MaterialSearchForm();
		materialSearchForm
				.addMaterialSelectionListener(new MaterialSelectionListener() {

					@Override
					public void select(Material material) {
						final EiaTypeMaterial eiaTypeMaterial = new EiaTypeMaterial();
						eiaTypeMaterial
								.setEiaType(EIATypeMaterialGridPanel.this.eiaType);
						eiaTypeMaterial.setMaterial(material);
						EIATypeMaterialModel.save(eiaTypeMaterial,
								new GHAAsyncCallback<EiaTypeMaterial>() {

									@Override
									public void onSuccess(EiaTypeMaterial result) {
										loadData();
									}

								});
					}
				});
	}

	public EIATypeMaterialGridPanel() {
		setWidth100();
		setBackgroundColor("#E0E0E0");
		setStyleName("sides-padding padding-top");// Esto es VUDU!

		Label title = new Label("<h3>Materiales</h3>");
		title.setHeight(35);
		title.setWidth100();
		title.setStyleName("title-label");

		// //////Botones laterales

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						materialSearchForm.open();
					}
				}), new GHAImgButton("../resources/icons/delete.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						EiaTypeMaterial eiaTypeMaterial = ((EIATypeMaterialRecord) grid
								.getSelectedRecord()).toEntity();
						EIATypeMaterialModel.delete(eiaTypeMaterial.getId(),
								new GHAAsyncCallback<Void>() {

									@Override
									public void onSuccess(Void result) {
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
		this.eiaType = eiaType;
		loadData();
	}

	private void loadData() {
		EIATypeMaterialModel.find(eiaType,
				new GHAAsyncCallback<List<EiaTypeMaterial>>() {

					@Override
					public void onSuccess(List<EiaTypeMaterial> eiaTypeMaterial) {
						ListGridRecord[] array = EIATypeMaterialUtil
								.toGridRecords(eiaTypeMaterial).toArray(
										new EIATypeMaterialRecord[] {});
						grid.setData(array);
					}
				});
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}