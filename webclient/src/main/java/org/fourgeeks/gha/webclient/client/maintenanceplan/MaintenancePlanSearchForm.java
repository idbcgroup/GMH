package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASearchForm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class MaintenancePlanSearchForm extends GHASearchForm<MaintenancePlan> implements
		MaintenancePlanSelectionListener, MaintenancePlanSelectionProducer {

	private MaintenancePlanGrid grid;
	private GHATextItem nameItem, descriptionItem, frequencyItem;
	private GHASelectItem periodOfTimeSelectItem;

	private final DynamicForm form;
	private MaintenancePlanAddForm addForm;
	private List<MaintenancePlanSelectionListener> listeners;

	{
		form = new DynamicForm();
		
		listeners = new LinkedList<MaintenancePlanSelectionListener>();
		
		nameItem = new GHATextItem("Nombre");
		nameItem.setValue("PRUEBA!");
//		nameItem.setWidth("400px");
		nameItem.setLength(100);
		frequencyItem = new GHATextItem("Frecuencia");
//		frequencyItem.setWidth("*");
		periodOfTimeSelectItem = new GHASelectItem("Periodo de Tiempo");
//		periodOfTimeSelectItem.setWidth("*");
		
		descriptionItem = new GHATextItem("Descripción"/*, 420*/);
		descriptionItem.setColSpan(3);
//		descriptionItem.setWidth("*");
		
		
		grid = new MaintenancePlanGrid();

		addForm = new MaintenancePlanAddForm("Nuevo Plan de Mantenimiento");
	}

	/**
	 * 
	 */
	public MaintenancePlanSearchForm(String title) {
		super(title);
		
		form.setWidth(920);
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(3);
		form.setWrapItemTitles(false);
//		form.setClipItemTitles(true);
//		form.setFixedColWidths(true);
		form.setColWidths("200px","200px","200px");

//		nameItem.setWidth("400px");
		
		form.setItems(nameItem, frequencyItem, periodOfTimeSelectItem,
					  descriptionItem);

		// Event Handlers
		ClickHandler searchClickHandler = new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				search();
			}
		};
		KeyUpHandler searchKeyUpHandler = new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (event.getKeyName().equals("Enter")) {
					search();
				}
			}
		};
		nameItem.addKeyUpHandler(searchKeyUpHandler);
		frequencyItem.addKeyUpHandler(searchKeyUpHandler);
		periodOfTimeSelectItem.addKeyUpHandler(searchKeyUpHandler);

		VLayout sideButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/search.png", searchClickHandler),
				new GHAImgButton("../resources/icons/clean.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								form.clearValues();
								grid.setData(new ListGridRecord[0]);
							}
						}), new GHAImgButton("../resources/icons/cancel.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								hide();
							}
						}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(formLayout,
				GHAUiHelper
						.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT
								+ "px"));

		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						selectMaintenancePlan();
					}
				}), GHAUiHelper.verticalGraySeparator("2px"), new GHAImgButton(
				"../resources/icons/new.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						addForm.open();
					}
				}));

		gridLayout.addMembers(grid, sideGridButtons);
		addMember(gridLayout);
		fillExtras();

		// register as listener to the addForm producer
		addForm.addMaintenancePlanSelectionListener(this);
	}

	private void fillExtras() {
		periodOfTimeSelectItem.setValueMap(TimePeriodEnum.toValueMap());
	}

	public void search() {
		MaintenancePlan maintenancePlan = new MaintenancePlan();
		if (nameItem.getValue() != null)
			maintenancePlan.setName(nameItem.getValueAsString());
		if (descriptionItem.getValue() != null)
			maintenancePlan.setDescription(descriptionItem.getValueAsString());
		if (frequencyItem.getValue() != null)
			maintenancePlan.setFrequency(Integer.parseInt(frequencyItem
					.getValueAsString()));
		if (periodOfTimeSelectItem.getValue() != null)
			maintenancePlan.setPot(TimePeriodEnum
					.valueOf(periodOfTimeSelectItem.getValueAsString()));
		search(maintenancePlan);
	}

	private void search(final MaintenancePlan maintenancePlan) {
		MaintenancePlanModel.find(maintenancePlan,
				new GHAAsyncCallback<List<MaintenancePlan>>() {

					@Override
					public void onSuccess(List<MaintenancePlan> result) {
						ListGridRecord array[] = MaintenancePlanUtil
								.toGridRecords(result).toArray(
										new MaintenancePlanRecord[] {});
						grid.setData(array);

						// TODO: seleccionar un elemento si coincide exactamente
						// con el de busqueda
					}
				});
	}

	@Override
	public void close() {
		addForm.destroy();
		destroy();
	}

	@Override
	public void hide() {
		addForm.hide();
		super.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");
	}

	// Producer/Consumer Stuff
	/**
	 * @param maintenancePlan
	 */
	public void notifyMaintenancePlan(MaintenancePlan maintenancePlan) {
		for (MaintenancePlanSelectionListener listener : listeners)
			listener.select(maintenancePlan);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionProducer
	 * #addMaintenancePlanSelectionListener(org.fourgeeks
	 * .gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionProducer
	 * #removeMaintenancePlanSelectionListener(org
	 * .fourgeeks.gha.webclient.client
	 * .maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.remove(maintenancePlanSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.
	 * MaintenancePlanSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		MaintenancePlanRecord gridRecord = MaintenancePlanUtil
				.toGridRecord(maintenancePlan);
		ListGridRecord array[] = { gridRecord };
		grid.setData(array);
		grid.selectRecord(gridRecord);
	}

	/**
	 * 
	 */
	private void selectMaintenancePlan() {
		GHAGridRecord<MaintenancePlan> selectedRecord = grid
				.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.oldAlert(GHAStrings.get("record-not-selected"));
			return;
		}
		notifyMaintenancePlan(((MaintenancePlanRecord) selectedRecord)
				.toEntity());
		hide();
	}
}
