package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.LayoutSpacer;
import com.smartgwt.client.widgets.layout.VLayout;

public class MaintenancePlanSearchForm extends GHASlideInWindow implements MaintenancePlanSelectionListener, MaintenancePlanSelectionProducer{

	private MaintenancePlanGrid grid;
	private GHATextItem nameItem, descriptionItem, frequencyItem;
	private GHASelectItem periodOfTimeSelectItem;
	
	private MaintenancePlanAddForm addForm;
	private List<MaintenancePlanSelectionListener> listeners;

	{
		listeners = new LinkedList<MaintenancePlanSelectionListener>();
		nameItem = new GHATextItem("Nombre");
		frequencyItem = new GHATextItem("Frecuencia");
		periodOfTimeSelectItem = new GHASelectItem("Periodo de Tiempo");
		descriptionItem = new GHATextItem("Descripción",420);
		descriptionItem.setColSpan(4);
		
		grid = new MaintenancePlanGrid();
		
		addForm = new MaintenancePlanAddForm();
	}

	public MaintenancePlanSearchForm() {
		super(1);
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight() + "px");

		Label title = new Label("<h3>Busqueda de Planes de Mantenimiento</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		addMember(title);
		
		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);

		form.setItems(nameItem,frequencyItem,periodOfTimeSelectItem,
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
				new GHAImgButton("../resources/icons/clean.png",new ClickHandler() {
					
					@Override
					public void onClick(ClickEvent event) {
						form.clearValues();
						grid.setData(new ListGridRecord[0]);
					}
				}),
				new GHAImgButton("../resources/icons/cancel.png",
						new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								hide();
							}
						}));

		HLayout formLayout = new HLayout();
		formLayout.setPadding(10);
		formLayout.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		formLayout.addMembers(form, new LayoutSpacer(), sideButtons);

		addMembers(title,
				   formLayout,
				   GHAUiHelper.verticalGraySeparator(GHAUiHelper.V_SEPARATOR_HEIGHT + "px"));

		HLayout gridLayout = new HLayout();
		gridLayout.setPadding(10);

		VLayout sideGridButtons = GHAUiHelper.createBar(new GHAImgButton(
				"../resources/icons/check.png", new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						hide();
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
		
		//register as listener to the addForm producer
		addForm.addMaintenancePlanSelectionListener(this);
	}

	private void fillExtras() {
	}

	private void search() {
	}

	private void search(final MaintenancePlan plan) {
	}

	@Override
	public void close() {
		destroy();
	}

	@Override
	public void hide() {
		super.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() + "px");
	}
	
	//Producer/Consumer Stuff
	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer#addMaintenancePlanSelectionListener(org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void addMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.add(maintenancePlanSelectionListener);
		
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionProducer#removeMaintenancePlanSelectionListener(org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener)
	 */
	@Override
	public void removeMaintenancePlanSelectionListener(
			MaintenancePlanSelectionListener maintenancePlanSelectionListener) {
		listeners.remove(maintenancePlanSelectionListener);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.maintenanceplan.MaintenancePlanSelectionListener#select(org.fourgeeks.gha.domain.gmh.MaintenancePlan)
	 */
	@Override
	public void select(MaintenancePlan maintenancePlan) {
		MaintenancePlanRecord gridRecord = MaintenancePlanUtil.toGridRecord(maintenancePlan);
		ListGridRecord array[] = {gridRecord};
		grid.setData(array);
		grid.selectRecord(gridRecord);
	}
}
