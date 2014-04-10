package org.fourgeeks.gha.webclient.client.eiatype.component;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.imageitems.buttons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.labels.GHATopTitleLabel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSearchForm;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

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
public class EIATypeComponentGridPanel extends GHAFormLayout implements
		EIATypeSelectionListener, HideableListener, ClosableListener {

	private EIATypeComponentGrid grid;
	private EiaType eiaType;
	private EIATypeSearchForm searchForm;

	{
		grid = new EIATypeComponentGrid();
		grid.getRequiredField().addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaTypeComponent eiaTypeComponent = ((EIATypeComponentRecord) event
						.getRecord()).toEntity();
				eiaTypeComponent.setComponentRequired((Boolean) event
						.getNewValue());
				EIATypeComponentModel.update(eiaTypeComponent,
						new GHAAsyncCallback<EiaTypeComponent>() {

							@Override
							public void onSuccess(EiaTypeComponent result) {
							}
						});
			}
		});
		grid.getReplaceableField().addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaTypeComponent eiaTypeComponent = ((EIATypeComponentRecord) event
						.getRecord()).toEntity();
				eiaTypeComponent.setComponentReplaceable((Boolean) event
						.getNewValue());
				EIATypeComponentModel.update(eiaTypeComponent,
						new GHAAsyncCallback<EiaTypeComponent>() {

							@Override
							public void onSuccess(EiaTypeComponent result) {
							}
						});
			}
		});
		grid.getAmountField().addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaTypeComponent eiaTypeComponent = ((EIATypeComponentRecord) event
						.getRecord()).toEntity();
				eiaTypeComponent.setAmount((Integer) event.getNewValue());
				EIATypeComponentModel.update(eiaTypeComponent,
						new GHAAsyncCallback<EiaTypeComponent>() {

							@Override
							public void onSuccess(EiaTypeComponent result) {
							}
						});
			}
		});

		searchForm = new EIATypeSearchForm(
				GHAStrings.get("search-component-eiatype"));
		searchForm.addEiaTypeSelectionListener(new EIATypeSelectionListener() {

			@Override
			public void select(EiaType eiaType) {
				// clean the search form
				EIATypeComponentGridPanel.this.searchForm.clean();

				final EiaTypeComponent eiaTypeComponent = new EiaTypeComponent();
				eiaTypeComponent
						.setParentEiaType(EIATypeComponentGridPanel.this.eiaType);
				eiaTypeComponent.setEiaType(eiaType);
				eiaTypeComponent.setComponentReplaceable(false);
				eiaTypeComponent.setComponentRequired(false);
				EIATypeComponentModel.save(eiaTypeComponent,
						new GHAAsyncCallback<EiaTypeComponent>() {

							@Override
							public void onSuccess(EiaTypeComponent result) {
								GHAErrorMessageProcessor
										.alert("eiatype-component-save-success");
								loadData();
							}
						});
			}
		});
	}

	public EIATypeComponentGridPanel() {
		super();
		GHATopTitleLabel title = new GHATopTitleLabel("Componentes");
		// //////Botones laterales
		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}), new GHADeleteButton(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				delete();
			}
		}));
		final HLayout gridContainer = new HLayout();
		gridContainer.addMembers(grid, sideButtons);

		addMembers(title, gridContainer);
	}

	@Override
	public boolean canBeClosen(HideCloseAction hideAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public void close() {
		searchForm.close();
	}

	private void delete() {
		if (grid.getSelectedRecord() == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}

		if (grid.getSelectedRecords().length > 1) {
			GHAErrorMessageProcessor.confirm(
					"eiatype-components-delete-confirm", new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								List<EiaTypeComponent> entities = grid
										.getSelectedEntities();
								EIATypeComponentModel.delete(entities,
										new GHAAsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												GHAErrorMessageProcessor
														.alert("eiatype-components-delete-success");
												loadData();
											}
										});
							}
						}
					});
		} else {
			GHAErrorMessageProcessor.confirm(
					"eiatype-component-delete-confirm", new BooleanCallback() {

						@Override
						public void execute(Boolean value) {
							if (value) {
								EiaTypeComponent entity = grid
										.getSelectedEntity();
								EIATypeComponentModel.delete(entity.getId(),
										new GHAAsyncCallback<Void>() {

											@Override
											public void onSuccess(Void result) {
												GHAErrorMessageProcessor
														.alert("eiatype-component-delete-success");
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
		searchForm.hide();
	}

	private void loadData() {
		EIATypeComponentModel.findByParentEiaType(eiaType,
				new GHAAsyncCallback<List<EiaTypeComponent>>() {

					@Override
					public void onSuccess(
							List<EiaTypeComponent> eiaTypeComponents) {
						ListGridRecord[] array = EIATypeComponentUtil
								.toGridRecords(eiaTypeComponents).toArray(
										new EIATypeComponentRecord[] {});
						grid.setData(array);
					}

				});
	}

	private void search() {
		ListGridRecord records[] = grid.getRecords();
		List<EiaType> blackList = new ArrayList<EiaType>();
		blackList.add(EIATypeComponentGridPanel.this.eiaType);

		for (int i = 0; i < records.length; i++) {
			blackList.add(((EIATypeComponentRecord) records[i]).toEntity()
					.getEiaType());
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
