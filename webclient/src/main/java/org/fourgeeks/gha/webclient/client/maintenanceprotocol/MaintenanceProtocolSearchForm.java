package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.LinkedList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASpacerItem;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHALabel;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHANotification;
import org.fourgeeks.gha.webclient.client.UI.superclasses.GHASlideInWindow;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
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
public class MaintenanceProtocolSearchForm extends GHASlideInWindow implements
		MaintenanceProtocolSelectionListener,
		MaintenanceProtocolSelectionProducer {

	private MaintenanceProtocolGrid grid;
	private GHATextItem nameItem, descriptionItem;

	private MaintenanceProtocolAddForm addForm;
	private List<MaintenanceProtocolSelectionListener> listeners;

	{
		listeners = new LinkedList<MaintenanceProtocolSelectionListener>();
		nameItem = new GHATextItem("Nombre");
		nameItem.setLength(100);
		descriptionItem = new GHATextItem("Descripción", 420);
		descriptionItem.setColSpan(4);

		grid = new MaintenanceProtocolGrid();

		addForm = new MaintenanceProtocolAddForm();
	}

	/**
	 * 
	 */
	public MaintenanceProtocolSearchForm() {
		super();
		setTop(GHAUiHelper.getTopSpace());
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");

		GHALabel title = new GHALabel("Busqueda de Protocolos de Mantenimiento");
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
		formLayout.setHeight(GHAUiHelper.DEFAULT_INNER_TOP_SECTION_HEIGHT
				+ "px");
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
						selectMaintenanceProtocol();
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
		addForm.addMaintenanceProtocolSelectionListener(this);
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
		setHeight(GHAUiHelper.getTabHeight() - 4 + "px");
	}

	private void search() {
		MaintenanceProtocol maintenanceProtocol = new MaintenanceProtocol();
		if (nameItem.getValue() != null)
			maintenanceProtocol.setName(nameItem.getValueAsString());
		if (descriptionItem.getValue() != null)
			maintenanceProtocol.setDescription(descriptionItem
					.getValueAsString());
		search(maintenanceProtocol);
	}

	private void search(final MaintenanceProtocol protocol) {
		MaintenanceProtocolModel.find(protocol,
				new GHAAsyncCallback<List<MaintenanceProtocol>>() {

					@Override
					public void onSuccess(List<MaintenanceProtocol> result) {
						ListGridRecord array[] = MaintenanceProtocolUtil
								.toGridRecords(result).toArray(
										new MaintenanceProtocolGridRecord[] {});
						grid.setData(array);

						// TODO: seleccionar un elemento si coincide exactamente
						// con el de busqueda
					}
				});
	}

	// Producer/Consumer Stuff
	private void notifyMaintenanceProtocol(
			MaintenanceProtocol maintenanceProtocol) {
		for (MaintenanceProtocolSelectionListener listener : listeners)
			listener.select(maintenanceProtocol);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.
	 * MaintenanceProtocolSelectionProducer
	 * #addMaintenanceProtocolSelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceprotocol.MaintenanceProtocolSelectionListener)
	 */
	@Override
	public void addMaintenanceProtocolSelectionListener(
			MaintenanceProtocolSelectionListener maintenanceProtocolSelectionListener) {
		listeners.add(maintenanceProtocolSelectionListener);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.
	 * MaintenanceProtocolSelectionProducer
	 * #removeMaintenanceProtocolSelectionListener
	 * (org.fourgeeks.gha.webclient.client
	 * .maintenanceprotocol.MaintenanceProtocolSelectionListener)
	 */
	@Override
	public void removeMaintenanceProtocolSelectionListener(
			MaintenanceProtocolSelectionListener maintenanceProtocolSelectionListener) {
		listeners.remove(maintenanceProtocolSelectionListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fourgeeks.gha.webclient.client.maintenanceprotocol.
	 * MaintenanceProtocolSelectionListener
	 * #select(org.fourgeeks.gha.domain.gmh.MaintenanceProtocol)
	 */
	@Override
	public void select(MaintenanceProtocol maintenanceProtocol) {
		MaintenanceProtocolGridRecord gridRecord = MaintenanceProtocolUtil
				.toGridRecord(maintenanceProtocol);
		ListGridRecord array[] = { gridRecord };
		grid.setData(array);
		grid.selectRecord(gridRecord);
	}

	/**
	 * 
	 */
	private void selectMaintenanceProtocol() {
		GHAGridRecord<MaintenanceProtocol> selectedRecord = grid
				.getSelectedRecord();
		if (selectedRecord == null) {
			GHANotification.oldAlert(GHAStrings.get("record-not-selected"));
			return;
		}
		notifyMaintenanceProtocol(((MaintenanceProtocolGridRecord) selectedRecord)
				.toEntity());
		hide();
	}

}
