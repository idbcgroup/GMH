package org.fourgeeks.gha.webclient.client.eiatype.materialcategory;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialCategory;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.materialcategory.MaterialCategoryAddForm;
import org.fourgeeks.gha.webclient.client.materialcategory.MaterialCategorySearchForm;
import org.fourgeeks.gha.webclient.client.materialcategory.MaterialCategorySelectionListener;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class EIATypeMaterialCategoryGridPanel extends GHAFormLayout
implements EIATypeSelectionListener, HideableListener, ClosableListener {

	private EiaTypeMaterialCategoryGrid grid;
	private MaterialCategorySearchForm searchForm;
	private EiaType eiaType;
	private MaterialCategoryAddForm addForm;

	{
		grid = new EiaTypeMaterialCategoryGrid();
		grid.getAmountGridField().addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaTypeMaterialCategory entity = ((EIATypeMaterialCategoryRecord) event
						.getRecord()).toEntity();
				entity.setAmount((Integer) event.getNewValue());
				EIATypeMaterialCategoryModel.update(entity,
						new GHAAsyncCallback<EiaTypeMaterialCategory>() {

					@Override
					public void onSuccess(EiaTypeMaterialCategory result) {
					}
				});
			}
		});

		MaterialCategorySelectionListener materialSelectionListener = new MaterialCategorySelectionListener() {

			@Override
			public void select(MaterialCategory materialCategory) {
				// clean the search form
				EIATypeMaterialCategoryGridPanel.this.searchForm.clean();

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
						delete();
					}
				}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(new GHATopTitleLabel(GHAStrings.get("materials")), mainPanel);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public void close() {
		hide();
		searchForm.close();
		addForm.close();
	}

	/**
	 * 
	 */
	private void delete() {
		final EiaTypeMaterialCategory eiaTypeMaterialCategory = grid
				.getSelectedEntity();

		if (eiaTypeMaterialCategory == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}

		GHAErrorMessageProcessor.confirm("eiatype-material-category-delete-confirm",
				new BooleanCallback() {

			@Override
			public void execute(Boolean value) {
				if (value) {
					EIATypeMaterialCategoryModel.delete(
							eiaTypeMaterialCategory.getId(),
							new GHAAsyncCallback<Void>() {

								@Override
								public void onSuccess(Void result) {
									grid.removeSelectedData();
								}
							});
				}
			}
		});
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

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		loadData();
	}
}