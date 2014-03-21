package org.fourgeeks.gha.webclient.client.eiatype.utility;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
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
import org.fourgeeks.gha.webclient.client.eiatype.materialbrand.EIATypeMateriaBrandRecord;
import org.fourgeeks.gha.webclient.client.eiatype.materialbrand.EIATypeMaterialBrandModel;
import org.fourgeeks.gha.webclient.client.eiatype.materialbrand.EIATypeMaterialUtil;
import org.fourgeeks.gha.webclient.client.eiatype.materialbrand.EiaTypeMaterialBrandGrid;
import org.fourgeeks.gha.webclient.client.materialbrand.MaterialBrandSelectionListener;
import org.fourgeeks.gha.webclient.client.utility.UtilityAddForm;
import org.fourgeeks.gha.webclient.client.utility.UtilitySearchForm;

import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellSavedEvent;
import com.smartgwt.client.widgets.grid.events.CellSavedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret, emiliot
 * 
 */
public class EIATypeUtilityGridPanel extends GHAFormLayout implements
		EIATypeSelectionListener, HideableListener, ClosableListener {

	private EiaTypeMaterialBrandGrid grid;
	private UtilitySearchForm searchForm;
	private UtilityAddForm addForm;
	private EiaType eiaType;
	private final MaterialTypeEnum defaultType = MaterialTypeEnum.UTILITARIO;
	private final MaterialBrandSelectionListener materialBrandSelectionListener = new MaterialBrandSelectionListener() {

		@Override
		public void select(MaterialBrand materialBrand) {
			EIATypeUtilityGridPanel.this.searchForm.clean();

			final EiaTypeMaterialBrand entity = new EiaTypeMaterialBrand();
			entity.setEiaType(EIATypeUtilityGridPanel.this.eiaType);
			entity.setMaterialBrand(materialBrand);

			EIATypeMaterialBrandModel.save(entity,
					new GHAAsyncCallback<EiaTypeMaterialBrand>() {

						@Override
						public void onSuccess(EiaTypeMaterialBrand arg0) {
							loadData();
						}
					});
		}
	};

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
		addForm = new UtilityAddForm(GHAStrings.get("new-utility-service"));
		addForm.addMaterialBrandSelectionListener(materialBrandSelectionListener);
		searchForm = new UtilitySearchForm(
				GHAStrings.get("search-utility-material"));
		searchForm
				.addMaterialBrandSelectionListener(materialBrandSelectionListener);

	}

	/**
	 * 
	 */
	public EIATypeUtilityGridPanel() {
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

		addMembers(new GHALabel(GHAStrings.get("utility-services")), mainPanel);
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
		if (grid.getSelectedRecord() == null) {
			GHAAlertManager.alert("record-not-selected");
			return;
		}

		if (grid.getSelectedRecords().length > 1) {
			GHAAlertManager.confirm("eiatype-utilities-delete-confirm",
					new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								List<EiaTypeMaterialBrand> entities = grid
										.getSelectedEntities();
								EIATypeMaterialBrandModel.delete(entities,
										new GHAAsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												GHAAlertManager
														.alert("eiatype-utilities-delete-success");
												loadData();
											}
										});
							}
						}
					});
		} else {
			GHAAlertManager.confirm("eiatype-utility-delete-confirm",
					new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								EiaTypeMaterialBrand entity = grid
										.getSelectedEntity();
								EIATypeMaterialBrandModel.delete(
										entity.getId(),
										new GHAAsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												GHAAlertManager
														.alert("eiatype-utility-delete-success");
												loadData();
											}
										});
							}
						}
					});
		}
	}

	@Override
	public void hide() {
		if (searchForm.isVisible())
			searchForm.hide();
		if (addForm.isVisible())
			addForm.hide();
	}

	private void loadData() {
		EIATypeMaterialBrandModel.find(eiaType, defaultType,
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

	/**
	 * 
	 */
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
