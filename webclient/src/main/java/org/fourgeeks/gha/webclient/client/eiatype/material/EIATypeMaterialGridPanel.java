package org.fourgeeks.gha.webclient.client.eiatype.material;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaterial;
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
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.material.MaterialAddFormSub;
import org.fourgeeks.gha.webclient.client.material.MaterialSearchFormSub;
import org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener;

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
public class EIATypeMaterialGridPanel extends GHAVerticalLayout implements
		EIATypeSelectionListener, HideableListener, ClosableListener {

	private EiaTypeMaterialGrid grid;
	private MaterialSearchFormSub searchForm;
	private EiaType eiaType;
	private MaterialAddFormSub addForm;

	{
		grid = new EiaTypeMaterialGrid();
		grid.getAmountGridField().addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaTypeMaterial entity = ((EIATypeMaterialRecord) event
						.getRecord()).toEntity();
				entity.setAmount((Integer) event.getNewValue());
				EIATypeMaterialModel.update(entity,
						new GHAAsyncCallback<EiaTypeMaterial>() {

							@Override
							public void onSuccess(EiaTypeMaterial result) {
							}
						});
			}
		});

		MaterialSelectionListener materialSelectionListener = new MaterialSelectionListener() {

			@Override
			public void select(Material material) {
				// clean the search form
				EIATypeMaterialGridPanel.this.searchForm.clean();

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
		};
		addForm = new MaterialAddFormSub(GHAStrings.get("new-material"));
		addForm.addMaterialSelectionListener(materialSelectionListener);
		searchForm = new MaterialSearchFormSub(
				GHAStrings.get("search-material"));

		searchForm.addMaterialSelectionListener(materialSelectionListener);
	}

	public EIATypeMaterialGridPanel() {
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
		final EiaTypeMaterial eiaTypeMaterial = grid.getSelectedEntity();

		if (eiaTypeMaterial == null) {
			GHAAlertManager.alert("record-not-selected");
			return;
		}

		GHAAlertManager.confirm(GHAStrings.get("materials-category"),
				GHAStrings.get("eiatype-material-delete-confirm"),
				new BooleanCallback() {

					@Override
					public void execute(Boolean value) {
						if (value) {
							EIATypeMaterialModel.delete(
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
		EIATypeMaterialModel.find(eiaType,
				new GHAAsyncCallback<List<EiaTypeMaterial>>() {

					@Override
					public void onSuccess(List<EiaTypeMaterial> list) {
						List<EIATypeMaterialRecord> gridRecords = EIATypeMaterialUtil
								.toGridRecords(list);
						ListGridRecord[] array = gridRecords
								.toArray(new EIATypeMaterialRecord[] {});
						grid.setData(array);
					}
				});
	}

	private void search() {
		ListGridRecord[] records = grid.getRecords();
		List<Material> blackList = null;
		if (records.length != 0) {
			blackList = new ArrayList<Material>();
			for (int i = 0; i < records.length; i++)
				blackList.add(((EIATypeMaterialRecord) records[i]).toEntity()
						.getMaterial());
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
