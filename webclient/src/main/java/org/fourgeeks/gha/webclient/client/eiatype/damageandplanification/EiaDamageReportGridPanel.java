package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.exceptions.UnavailableToCloseException;
import org.fourgeeks.gha.webclient.client.UI.icons.GHASearchButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideableListener;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.eia.EIAGrid;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eia.EIARecord;
import org.fourgeeks.gha.webclient.client.eia.EIASelectionListener;
import org.fourgeeks.gha.webclient.client.eia.EIAUtil;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EIADamageReportAddForm;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionListener;
import org.fourgeeks.gha.webclient.client.eiadamagereport.EiaDamageReportSelectionProducer;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeSelectionListener;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author naramirez
 * 
 */
public class EiaDamageReportGridPanel extends VLayout implements
		EIATypeSelectionListener, EiaDamageReportSelectionProducer,
		EiaDamageReportSelectionListener, HideableListener, ClosableListener {

	private EIAGrid grid;
	private EIADamageAndPlanificationSearchForm searchForm;
	private EiaType eiaType;
	private EIADamageReportAddForm addForm;

	{
		grid = new EIAGrid();
		searchForm = new EIADamageAndPlanificationSearchForm(
				GHAStrings.get("search-eia"));
		addForm = new EIADamageReportAddForm();
		// addForm.addEiaDamageReportSelectionListener(this);

		searchForm.addEiaSelectionListener(new EIASelectionListener() {
			@Override
			public void select(Eia eia) {
				searchForm.clean();
				addForm.select(eia);
				addForm.open();
			}
		});

		addEiaDamageReportSelectionListener(new EiaDamageReportSelectionListener() {
			@Override
			public void select(EiaDamageReport eiaDamageReport) {
				loadData();
			}
		});
	}

	/**
	 * 
	 */
	public EiaDamageReportGridPanel() {
		super();
		setWidth100();

		VLayout sideButtons = GHAUiHelper.createBar(new GHASearchButton(
				new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						search();
					}
				}));

		HLayout mainPanel = new HLayout();
		mainPanel.addMembers(grid, sideButtons);

		addMembers(new GHALabel(GHAStrings.get("eiaDamageReport")), mainPanel);
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
		EIAModel.findDamagedAndInMaintenance(eiaType,
				new GHAAsyncCallback<List<Eia>>() {
					@Override
					public void onSuccess(List<Eia> result) {
						List<EIARecord> gridRecords = EIAUtil
								.toGridRecords(result);
						EIARecord[] array = gridRecords
								.toArray(new EIARecord[] {});
						grid.setData(array);
					}
				});
	}

	private void search() {
		ListGridRecord records[] = grid.getRecords();
		List<Eia> blackList = new ArrayList<Eia>();

		for (int i = 0; i < records.length; ++i) {
			EIARecord eiaRecord = (EIARecord) records[i];
			blackList.add(eiaRecord.toEntity());
		}

		searchForm.filterBy(blackList);
		searchForm.open();

	}

	@Override
	public void select(EiaType eiaType) {
		this.eiaType = eiaType;
		searchForm.select(eiaType);
		loadData();
	}

	@Override
	public void addEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		addForm.addEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);

	}

	@Override
	public void removeEiaDamageReportSelectionListener(
			EiaDamageReportSelectionListener eiaDamageReportSelectionListener) {
		addForm.removeEiaDamageReportSelectionListener(eiaDamageReportSelectionListener);

	}

	@Override
	public void notifyEiaDamageReport(EiaDamageReport eiaDamageReport) {
	}

	@Override
	public void select(EiaDamageReport eiaDamageReport) {
		loadData();
	}
}
