package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import java.util.ArrayList;
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
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
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
public class EIATypeMaterialCategoryGridPanel extends GHAVerticalLayout
		implements EIATypeSelectionListener, GHAHideable, GHAClosable {

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
		addForm = new MaterialCategoryAddForm(GHAStrings.get("new-material"));
		addForm.addMaterialSelectionListener(materialSelectionListener);
		searchForm = new MaterialCategorySearchForm(
				GHAStrings.get("search-material"));

		searchForm.addMaterialSelectionListener(materialSelectionListener);
	}

	/**
	 * 
	 */
	public EIATypeMaterialCategoryGridPanel() {
		super();
		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
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

		addMembers(new GHALabel(GHAStrings.get("materials")), mainPanel);
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
					public void onSuccess(List<EiaTypeMaterialCategory> list) {
						List<EIATypeMaterialCategoryRecord> gridRecords = EIATypeMaterialCategoryUtil
								.toGridRecords(list);
						ListGridRecord[] array = gridRecords
								.toArray(new EIATypeMaterialCategoryRecord[] {});
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

	private void search() {
		ListGridRecord[] records = grid.getRecords();
		List<MaterialCategory> blackList = null;
		if (records.length != 0) {
			blackList = new ArrayList<MaterialCategory>();
			for (int i = 0; i < records.length; i++)
				blackList.add(((EIATypeMaterialCategoryRecord) records[i])
						.toEntity().getMaterialCategory());
		}
		searchForm.filterBy(blackList);
		searchForm.open();
	}
}