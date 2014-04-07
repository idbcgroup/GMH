package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHADeleteButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHAErrorMessageProcessor;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAFormLayout;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIaComponentsSearchForm;

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
public class EIAComponentGridPanel extends GHAFormLayout implements
		ClosableListener, HideableListener, EIASelectionListener {

	private EIAComponentGrid grid;
	private Eia eia;
	private EIaComponentsSearchForm searchForm;

	{
		grid = new EIAComponentGrid();
		grid.getComponentobsField().addCellSavedHandler(new CellSavedHandler() {

			@Override
			public void onCellSaved(CellSavedEvent event) {
				EiaComponent eiaComponent = ((EIAComponentRecord) event
						.getRecord()).toEntity();
				eiaComponent.setComponentObs((String) event.getNewValue());
				EIAComponentModel.update(eiaComponent,
						new GHAAsyncCallback<EiaComponent>() {

							@Override
							public void onSuccess(EiaComponent result) {
							}
						});

			}
		});

		searchForm = new EIaComponentsSearchForm(
				GHAStrings.get("search-component-eia"));
		searchForm.addEiaSelectionListener(new EIASelectionListener() {

			@Override
			public void select(Eia eia) {
				// clean the searchForm
				EIAComponentGridPanel.this.searchForm.clean();

				final EiaComponent eiaComponent = new EiaComponent();
				eiaComponent.setParentEia(EIAComponentGridPanel.this.eia);
				eiaComponent.setEia(eia);
				eiaComponent.setComponentObs("");
				EIAComponentModel.save(eiaComponent,
						new GHAAsyncCallback<EiaComponent>() {

							@Override
							public void onSuccess(EiaComponent result) {
								GHAErrorMessageProcessor
										.alert("eiacomponent-save-success");
								loadData();
							}
						});

			}
		});
	}

	public EIAComponentGridPanel() {
		super();
		GHALabel title = new GHALabel("Componentes de Equipos");

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
		searchForm.close();

	}

	private void delete() {
		if (grid.getSelectedRecord() == null) {
			GHAErrorMessageProcessor.alert("record-not-selected");
			return;
		}

		if (grid.getSelectedRecords().length > 1) {
			GHAErrorMessageProcessor.confirm("eiaComponents-delete-confirm",
					new BooleanCallback() {
						@Override
						public void execute(Boolean value) {
							if (value) {
								List<EiaComponent> entities = grid
										.getSelectedEntities();

								EIAComponentModel.delete(entities,
										new GHAAsyncCallback<Void>() {
											@Override
											public void onSuccess(Void result) {
												GHAErrorMessageProcessor
														.alert("eiacomponents-delete-success");
												loadData();
											}
										});
							}
						}
					});
		} else {
			GHAErrorMessageProcessor.confirm("eiaComponent-delete-confirm",
					new BooleanCallback() {
						@Override
						public void execute(Boolean value) {
							if (value) {
								List<EiaComponent> entities = grid
										.getSelectedEntities();

								EIAComponentModel.delete(entities,
										new GHAAsyncCallback<Void>() {
											@Override
											public void onSuccess(Void result) {
												GHAErrorMessageProcessor
														.alert("eiacomponent-delete-success");
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
		EIAComponentModel.findByEiaId(eia,
				new GHAAsyncCallback<List<EiaComponent>>() {

					@Override
					public void onSuccess(List<EiaComponent> eiaComponents) {
						ListGridRecord[] array = EIAComponentUtil
								.toGridRecords(eiaComponents).toArray(
										new EIAComponentRecord[] {});
						grid.setData(array);
					}

				});

	}

	private void search() {
		ListGridRecord records[] = grid.getRecords();
		List<Eia> blackList = new ArrayList<Eia>();
		blackList.add(EIAComponentGridPanel.this.eia);

		for (int i = 0; i < records.length; ++i)
			blackList
					.add(((EIAComponentRecord) records[i]).toEntity().getEia());

		searchForm.filterBy(blackList);
		searchForm.open();
	}

	@Override
	public void select(Eia eia) {
		this.eia = eia;
		loadData();
		searchForm.setEiaType(eia.getEiaType());
	}
}
