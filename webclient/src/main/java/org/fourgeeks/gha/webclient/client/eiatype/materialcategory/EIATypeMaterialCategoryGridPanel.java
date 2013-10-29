package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.materialcategory.MaterialCategoryAddForm;
import org.fourgeeks.gha.webclient.client.materialcategory.MaterialCategorySearchForm;
import org.fourgeeks.gha.webclient.client.materialcategory.MaterialCategorySelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIATypeMaterialCategoryGridPanel extends VLayout implements
		EIATypeSelectionListener, GHAClosable, GHAHideable {

	private EiaTypeMaterialCategoryGrid grid = new EiaTypeMaterialCategoryGrid();
	private MaterialCategorySearchForm searchForm;
	private EiaType eiaType;
	private MaterialCategoryAddForm addForm;

	{
		MaterialCategorySelectionListener materialSelectionListener = new MaterialCategorySelectionListener() {

			@Override
			public void select(MaterialCategory materialCategory) {
				final EiaTypeMaterialCategory eiaTypeMaterialCategory = new EiaTypeMaterialCategory();
				eiaTypeMaterialCategory
						.setEiaType(EIATypeMaterialCategoryGridPanel.this.eiaType);
				eiaTypeMaterialCategory.setMaterialCategory(materialCategory);
				EIATypeMaterialCategoryModel.save(eiaTypeMaterialCategory,
						new GHAAsyncCallback<EiaTypeMaterialCategory>() {

							@Override
							public void onSuccess(EiaTypeMaterialCategory result) {
								loadData();
							}

						});
			}
		};
		addForm = new MaterialCategoryAddForm();
		addForm.addMaterialSelectionListener(materialSelectionListener);
		searchForm = new MaterialCategorySearchForm(
				GHAStrings.get("search-material"));

		searchForm.addMaterialSelectionListener(materialSelectionListener);
	}

	public EIATypeMaterialCategoryGridPanel() {
		setWidth100();
		setBackgroundColor(GHAUiHelper.DEFAULT_BACKGROUND_COLOR);
		setStyleName("sides-padding padding-top");

		GHALabel title = new GHALabel(GHAStrings.get("materials"));

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						searchForm.open();
					}
				}), new GHANewButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addForm.open();
			}
		}), new GHADeleteButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				GHAGridRecord<EiaTypeMaterialCategory> selectedRecord = grid
						.getSelectedRecord();
				EiaTypeMaterialCategory eiaTypeMaterialCategory = selectedRecord
						.toEntity();
				EIATypeMaterialCategoryModel.delete(
						eiaTypeMaterialCategory.getId(),
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
		EIATypeMaterialCategoryModel.find(eiaType,
				new GHAAsyncCallback<List<EiaTypeMaterialCategory>>() {

					@Override
					public void onSuccess(
							List<EiaTypeMaterialCategory> eiaTypeMaterial) {
						ListGridRecord[] array = EIATypeMaterialCategoryUtil
								.toGridRecords(eiaTypeMaterial).toArray(
										new EIATypeMaterialCategoryRecord[] {});
						grid.setData(array);
					}
				});
	}

	@Override
	public void close() {
		hide();
		searchForm.close();
		addForm.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.smartgwt.client.widgets.Canvas#hide()
	 */
	@Override
	public void hide() {
		if (searchForm.isVisible())
			searchForm.hide();
		if (addForm.isVisible())
			addForm.hide();
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	@Override
	public boolean canBeClosen() {
		return true;
	}
}