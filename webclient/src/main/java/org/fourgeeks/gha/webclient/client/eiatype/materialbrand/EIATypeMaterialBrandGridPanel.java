package org.fourgeeks.gha.webclient.client.eiatype.materialbrand;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterialBrand;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHAAlertManager;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.materialbrand.MaterialBrandAddFormSub;
import org.fourgeeks.gha.webclient.client.materialbrand.MaterialBrandSearchFormSub;
import org.fourgeeks.gha.webclient.client.materialbrand.MaterialBrandSelectionListener;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author emiliot
 * 
 */
public class EIATypeMaterialBrandGridPanel extends GHAFormLayout implements
		EIATypeSelectionListener, HideableListener, ClosableListener {

	private EiaTypeMaterialBrandGrid grid;
	private MaterialBrandSearchFormSub searchForm;
	private EiaType eiaType;
	private MaterialBrandAddFormSub addForm;

	{
		grid = new EiaTypeMaterialBrandGrid();
		grid.getAmountGridField().addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaTypeMaterialBrand entity = ((EIATypeMateriaBrandRecord) event
						.getRecord()).toEntity();
				entity.setAmount((Integer) event.getNewValue());
				EIATypeMaterialBrandModel.update(entity,
						new GHAAsyncCallback<EiaTypeMaterialBrand>() {

							@Override
							public void onSuccess(EiaTypeMaterialBrand result) {
							}
						});
			}
		});

		MaterialBrandSelectionListener materialBrandSelectionListener = new MaterialBrandSelectionListener() {

			@Override
			public void select(MaterialBrand materialBrand) {
				// clean the search form
				EIATypeMaterialBrandGridPanel.this.searchForm.clean();
				final EiaTypeMaterialBrand entity = new EiaTypeMaterialBrand();
				entity.setEiaType(EIATypeMaterialBrandGridPanel.this.eiaType);
				entity.setMaterialBrand(materialBrand);
				EIATypeMaterialBrandModel.save(entity,
						new GHAAsyncCallback<EiaTypeMaterialBrand>() {

							@Override
							public void onSuccess(EiaTypeMaterialBrand result) {
								loadData();
							}

						});
			}
		};
		addForm = new MaterialBrandAddFormSub(GHAStrings.get("new-material"));
		addForm.addMaterialBrandSelectionListener(materialBrandSelectionListener);
		searchForm = new MaterialBrandSearchFormSub(
				GHAStrings.get("search-material"));

		searchForm
				.addMaterialBrandSelectionListener(materialBrandSelectionListener);
	}

	public EIATypeMaterialBrandGridPanel() {
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

		addMembers(new GHALabel(GHAStrings.get("materials")), mainPanel);
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
		final EiaTypeMaterialBrand eiaTypeMaterial = grid.getSelectedEntity();

		if (eiaTypeMaterial == null) {
			GHAAlertManager.alert("record-not-selected");
			return;
		}

		GHAAlertManager.confirm("eiatype-material-delete-confirm",
				new BooleanCallback() {

					@Override
					public void execute(Boolean value) {
						if (value) {
							EIATypeMaterialBrandModel.delete(
									eiaTypeMaterial.getId(),
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
		EIATypeMaterialBrandModel.find(eiaType,
				new GHAAsyncCallback<List<EiaTypeMaterialBrand>>() {

					@Override
					public void onSuccess(List<EiaTypeMaterialBrand> list) {
						List<EIATypeMateriaBrandRecord> gridRecords = EIATypeMaterialUtil
								.toGridRecords(list);
						ListGridRecord[] array = gridRecords
								.toArray(new EIATypeMateriaBrandRecord[] {});
						grid.setData(array);
					}
				});
	}

	private void search() {
		ListGridRecord[] records = grid.getRecords();
		List<MaterialBrand> blackList = null;
		if (records.length != 0) {
			blackList = new ArrayList<MaterialBrand>();
			for (int i = 0; i < records.length; i++)
				blackList.add(((EIATypeMateriaBrandRecord) records[i])
						.toEntity().getMaterialBrand());
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