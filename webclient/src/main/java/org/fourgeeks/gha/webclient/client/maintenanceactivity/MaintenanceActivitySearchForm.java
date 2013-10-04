package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
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

public class MaintenanceActivitySearchForm extends GHASlideInWindow implements
		MaintenanceActivitySelectionListener,
		MaintenanceActivitySelectionProducer {

	private List<MaintenanceActivitySelectionListener> listeners;
	private MaintenanceActivityGrid grid;
	private GHATextItem codeItem, nameItem, descriptionItem;

	private MaintenanceActivityAddForm addForm;

	{
		listeners = new LinkedList<MaintenanceActivitySelectionListener>();
		nameItem = new GHATextItem("Nombre");
		descriptionItem = new GHATextItem("Descripción", 420);
		descriptionItem.setColSpan(4);

		grid = new MaintenanceActivityGrid();

		addForm = new MaintenanceActivityAddForm();
	}

	public MaintenanceActivitySearchForm() {
		super(1);
		setTop(110);
		setHeight(GHAUiHelper.getTabHeight() + "px");

		Label title = new Label(
				"<h3>Busqueda de Actividades de Mantenimiento</h3>");
		title.setWidth(400);
		title.setHeight("35px");
		addMember(title);

		final DynamicForm form = new DynamicForm();
		form.setTitleOrientation(TitleOrientation.TOP);
		form.setNumCols(4);

		form.setItems(nameItem, new GHASpacerItem(2), descriptionItem);

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
		descriptionItem.addKeyUpHandler(searchKeyUpHandler);

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
		formLayout.setHeight(GHAUiHelper.INNER_TOP_SECTION_HEIGHT + "px");
		formLayout.setDefaultLayoutAlign(VerticalAlignment.CENTER);
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
						notifyMaintenanceActivity(((MaintenanceActivityGridRecord) grid
								.getSelectedRecord()).toEntity());
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

		// register as listener to the addform producer
		addForm.addMaintenanceActivitySelectionListener(this);
	}

	private void search() {
		MaintenanceActivity maintenanceActivity = new MaintenanceActivity();
		if (nameItem.getValue() != null)
			maintenanceActivity.setName(nameItem.getValueAsString());
		if (descriptionItem.getValue() != null)
			maintenanceActivity.setDescription(descriptionItem
					.getValueAsString());
		search(maintenanceActivity);
	}

	private void search(final MaintenanceActivity activity) {
		MaintenanceActivityModel.find(activity,
				new GHAAsyncCallback<List<MaintenanceActivity>>() {

					@Override
					public void onSuccess(List<MaintenanceActivity> result) {
						ListGridRecord array[] = MaintenanceActivityUtil
								.toGridRecords(result).toArray(
										new MaintenanceActivityGridRecord[] {});
						grid.setData(array);

						// TODO: seleccionar un elemento si coincide exactamente
					}
				});
	}

	@Override
	public void close() {
		destroy();
		addForm.destroy();
	}

	@Override
	public void hide() {
		super.hide();
		addForm.hide();
	}

	@Override
	public void onResize(ResizeEvent event) {
		setHeight(GHAUiHelper.getTabHeight() + "px");
	}

	// Producer/Consumer stuff
	private void notifyMaintenanceActivity(
			MaintenanceActivity maintenanceActivity) {
		for (MaintenanceActivitySelectionListener listener : listeners) {
			listener.select(maintenanceActivity);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionProducer
	 * #addMaintenanceActivitySelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void addMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		listeners.add(maintenanceActivitySelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionProducer
	 * #removeMaintenanceActivitySelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceactivity.MaintenanceActivitySelectionListener)
	 */
	@Override
	public void removeMaintenanceActivitySelectionListener(
			MaintenanceActivitySelectionListener maintenanceActivitySelectionListener) {
		listeners.remove(maintenanceActivitySelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceactivity.
	 * MaintenanceActivitySelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenanceActivity)
	 */
	@Override
	public void select(MaintenanceActivity maintenanceActivity) {
		MaintenanceActivityGridRecord gridRecord = MaintenanceActivityUtil
				.toGridRecord(maintenanceActivity);
		ListGridRecord array[] = { gridRecord };
		grid.setData(array);
		grid.selectRecord(gridRecord);
	}

}
