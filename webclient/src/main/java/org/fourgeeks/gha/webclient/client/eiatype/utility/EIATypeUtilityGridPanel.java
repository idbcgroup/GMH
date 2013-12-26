package org.fourgeeks.gha.webclient.client.eiatype.utility;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeUtility;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;
import org.fourgeeks.gha.webclient.client.material.MaterialSelectionListener;
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
public class EIATypeUtilityGridPanel extends GHAVerticalLayout implements
		EIATypeSelectionListener, HideableListener, ClosableListener {

	private EiaTypeUtilityGrid grid;
	private UtilitySearchForm searchForm;
	private UtilityAddForm addForm;
	private EiaType eiaType;
	private MaterialSelectionListener materialSelectionListener = new MaterialSelectionListener() {

		@Override
		public void select(Material material) {
			EIATypeUtilityGridPanel.this.searchForm.clean();

			EiaTypeUtility eiaTypeUtility = new EiaTypeUtility();
			eiaTypeUtility.setEiaType(EIATypeUtilityGridPanel.this.eiaType);
			eiaTypeUtility.setMaterial(material);
			EIATypeUtilityModel.save(eiaTypeUtility,
					new GHAAsyncCallback<EiaTypeUtility>() {

						@Override
						public void onSuccess(EiaTypeUtility result) {
							loadData();
						}
					});
		}
	};

	{
		grid = new EiaTypeUtilityGrid();
		grid.getAmountGridField().addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaTypeUtility entity = ((EIATypeUtilityRecord) event
						.getRecord()).toEntity();
				entity.setAmount((Integer) event.getNewValue());
				EIATypeUtilityModel.update(entity,
						new GHAAsyncCallback<EiaTypeUtility>() {

							@Override
							public void onSuccess(EiaTypeUtility result) {
							}
						});
			}
		});
		addForm = new UtilityAddForm(GHAStrings.get("new-utility-service"));
		addForm.addMaterialSelectionListener(materialSelectionListener);
		searchForm = new UtilitySearchForm(
				GHAStrings.get("search-utility-material"));
		searchForm.addMaterialSelectionListener(materialSelectionListener);

	}

	/**
	 * 
	 */
	public EIATypeUtilityGridPanel() {
		super();

		GHALabel title = new GHALabel("Servicios utilitarios");

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

		addMembers(title, mainPanel);
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
		final EiaTypeUtility eiaTypeUtility = grid.getSelectedEntity();

		if (eiaTypeUtility == null) {
			GHANotification.alert("record-not-selected");
			return;
		}

		GHANotification.confirm(GHAStrings.get("material-category"),
				GHAStrings.get("eiatype-utility-service-delete-confirm"),
				new BooleanCallback() {

					@Override
					public void execute(Boolean value) {
						if (value) {
							EIATypeUtilityModel.delete(eiaTypeUtility.getId(),
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

	@Override
	public void hide() {
		if (searchForm.isVisible())
			searchForm.hide();
		if (addForm.isVisible())
			addForm.hide();
	}

	private void loadData() {
		EIATypeUtilityModel.findByEiaType(eiaType,
				new GHAAsyncCallback<List<EiaTypeUtility>>() {

					@Override
					public void onSuccess(List<EiaTypeUtility> result) {
						ListGridRecord[] array = EIATypeUtilityUtil
								.toGridRecords(result).toArray(
										new EIATypeUtilityRecord[] {});
						grid.setData(array);

					}
				});
	}

	/**
	 * 
	 */
	private void search() {
		ListGridRecord[] records = grid.getRecords();
		List<Material> blackList = null;
		if (records.length != 0) {
			blackList = new ArrayList<Material>();
			for (int i = 0; i < records.length; i++)
				blackList.add(((EIATypeUtilityRecord) records[i]).toEntity()
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
