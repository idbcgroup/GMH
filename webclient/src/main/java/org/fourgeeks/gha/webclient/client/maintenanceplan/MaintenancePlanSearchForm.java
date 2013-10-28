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
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

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
public class MaintenancePlanSearchForm extends GHASlideInWindow implements
		MaintenancePlanSelectionListener, MaintenancePlanSelectionProducer {

	private MaintenancePlanGrid grid;
	private GHATextItem nameItem, descriptionItem, frequencyItem;
	private GHASelectItem periodOfTimeSelectItem;

	private MaintenancePlanAddForm addForm;
	private List<MaintenancePlanSelectionListener> listeners;

	{
		listeners = new LinkedList<MaintenancePlanSelectionListener>();
		nameItem = new GHATextItem("Nombre");
		nameItem.setLength(100);
		frequencyItem = new GHATextItem("Frecuencia");
		periodOfTimeSelectItem = new GHASelectItem("Periodo de Tiempo");
		descriptionItem = new GHATextItem("Descripción", 420);
		descriptionItem.setColSpan(4);

		grid = new MaintenancePlanGrid();

		addForm = new MaintenancePlanAddForm();
	}

	/**
	 * 
	 */
	public MaintenancePlanSearchForm() {
		super();
		setTop(GHAUiHelper.getTopSpace());
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");

		GHALabel title = new GHALabel("Busqueda de Planes de Mantenimiento");
		addMember(title);

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);

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
		formLayout.setHeight(GHAUiHelper.DEFAULT_TOP_SECTION_HEIGHT + "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(title, formLayout,
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

	private void search() {
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

	@Override
	public boolean canBeClosen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canBeHidden() {
		// TODO Auto-generated method stub
		return false;
	}
}
