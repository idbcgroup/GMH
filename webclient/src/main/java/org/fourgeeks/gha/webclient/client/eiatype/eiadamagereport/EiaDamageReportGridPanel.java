package org.fourgeeks.gha.webclient.client.eiatype.eiadamagereport;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHANewButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAVerticalLayout;
import org.fourgeeks.gha.webclient.client.eia.EIASearchForm;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EIADamageReportAddForm;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportModel;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportUtil;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class EiaDamageReportGridPanel extends GHAVerticalLayout implements
		EIATypeSelectionListener, HideableListener, ClosableListener {

	private EiaDamageReportGrid grid;
	private EIASearchForm searchForm;
	private EiaType eiaType;
	private EIADamageReportAddForm addForm;

	{
		grid = new EiaDamageReportGrid();
		searchForm = new EIASearchForm(GHAStrings.get("search-eia"));
		addForm = new EIADamageReportAddForm();
		addForm.addEiaDamageReportSelectionListener(new EiaDamageReportSelectionListener() {
			@Override
			public void select(EiaDamageReport eiaDamageReport) {
				loadData();
			}
		});

		searchForm.addEiaSelectionListener(new EIASelectionListener() {
			@Override
			public void select(Eia eia) {
				addForm.select(eia);
				addForm.open();
			}
		});
	}

	public EiaDamageReportGridPanel() {
		super();
		VLayout sideButtons = GHAUiHelper.createBar(new GHANewButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						searchForm.open();
					}
				}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(new GHALabel(GHAStrings.get("materials")), mainPanel);
	}

	@Override
	public boolean canBeClosen(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public boolean canBeHidden(HideCloseAction closeAction) {
		return true;
	}

	@Override
	public void close() throws UnavailableToCloseException {
		hide();
		searchForm.close();
		addForm.close();
	}

	private void loadData() {
		EiaDamageReportModel.findByEiaType(eiaType,
				new GHAAsyncCallback<List<EiaDamageReport>>() {
					@Override
					public void onSuccess(List<EiaDamageReport> result) {
						List<EiaDamageReportRecord> gridRecords = EiaDamageReportUtil
								.toGridRecords(result);
						EiaDamageReportRecord[] array = gridRecords
								.toArray(new EiaDamageReportRecord[] {});
						grid.setData(array);
					}
				});
	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		loadData();
	}

}
