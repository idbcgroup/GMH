package org.fourgeeks.gha.webclient.client.eia.component;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eia.EIASearchForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;

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
public class EIAComponentGridPanel extends GHAVerticalLayout implements
		ClosableListener, HideableListener, EIASelectionListener {

	private EIAComponentGrid grid;
	private Eia eia;
	private EIASearchForm searchForm;

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

		searchForm = new EIASearchForm(GHAStrings.get("search-component"));
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
				}), new GHAImgButton("../resources/icons/delete.png",
				new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						EiaComponent eiaComponent = grid.getSelectedEntity();
						EIAComponentModel.delete(eiaComponent.getId(),
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
	public boolean canBeClosen() {
		return true;
	}

	@Override
	public boolean canBeHidden() {
		return true;
	}

	@Override
	public void close() {
		searchForm.close();

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
	}
}
