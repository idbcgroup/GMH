/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;
import org.fourgeeks.gha.webclient.client.maintenanceprotocol.MaintenanceProtocolModel;

import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.events.DropCompleteEvent;
import com.smartgwt.client.widgets.events.DropCompleteHandler;
import com.smartgwt.client.widgets.events.KeyPressEvent;
import com.smartgwt.client.widgets.events.KeyPressHandler;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * @author emiliot
 * 
 */
public class MaintenanceProtocolGrid extends GhaGrid<MaintenanceProtocol> {
	private final MenuItem[] headerMenuItems;
	private final GHAAsyncCallback<Void> callback = new GHAAsyncCallback<Void>() {
		@Override
		public void onSuccess(Void result) {
		}
	};

	/** */
	public MaintenanceProtocolGrid() {

		final GHAGridField ordinalGridField = new GHAGridField("ordinal",
				"Secuencia");
		final GHAGridField typeGridField = new GHAGridField("type", "Tipo");
		final GHAGridField codeGridField = new GHAGridField("code", "Código");
		final GHAGridField isSubProtocolGridField = new GHAGridField(
				"subprotocol", "Sub-protocolo");
		final GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		final GHAGridField descGridField = new GHAGridField("desc",
				"Descripción");

		// campos ocultos

		final GHAGridField timeGridField = new GHAGridField("time", "Duración",
				true);
		final GHAGridField periodOfTimeGridField = new GHAGridField("pot",
				"Periodo de Tiempo", true);
		final GHAGridField costGridField = new GHAGridField("cost", "Costo",
				true);
		final GHAGridField currencyGridField = new GHAGridField("currency",
				"Moneda", true);

		isSubProtocolGridField.setType(ListGridFieldType.BOOLEAN);
		isSubProtocolGridField.setCanEdit(false);

		setEmptyMessage("No existen actividades o sub-protocolos para mostrar");

		setFields(ordinalGridField, typeGridField, codeGridField,
				isSubProtocolGridField, nameGridField, descGridField,
				timeGridField, periodOfTimeGridField, costGridField,
				currencyGridField);

		headerMenuItems = generateHeaderMenuItems(ordinalGridField,
				typeGridField, codeGridField, isSubProtocolGridField,
				nameGridField, descGridField, timeGridField,
				periodOfTimeGridField, costGridField, currencyGridField);

		setCanFocus(true);
		setCanHover(true);
		setShowHover(true);
		setShowHoverComponents(true);
		setCanSort(false);

		setCanReorderRecords(true);
		setCanAcceptDroppedRecords(true);
		setCanDragRecordsOut(true);
		setDragDataAction(DragDataAction.MOVE);
		addDropCompleteHandler(new DropCompleteHandler() {
			@Override
			public void onDropComplete(DropCompleteEvent event) {
				final List<MaintenanceProtocol> entities = getEntities();

				int size = getRecords().length;
				for (int i = 0, ordinal = 1; i < size; i++, ordinal++) {
					getRecord(i).setAttribute("ordinal", ordinal);
					entities.get(i).setOrdinal(ordinal);
				}

				MaintenanceProtocolModel.update(entities, callback);
			}
		});

		addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getKeyName().equals("Escape")) {
					deselectRecords(getSelectedRecords());
				}
			}
		});
	}

	@Override
	protected MenuItem[] getHeaderContextMenuItems(Integer fieldNum) {
		return headerMenuItems;
	}

	private MenuItem[] generateHeaderMenuItems(GHAGridField... fields) {
		MenuItem[] menuItems = new MenuItem[fields.length];

		for (int i = 0; i < fields.length; i++) {
			MenuItem menuItem = getShowFildMenuItem(fields[i]);
			menuItems[i] = menuItem;
		}

		return menuItems;
	}

	private MenuItem getShowFildMenuItem(final GHAGridField field) {
		final MenuItem menuItem = new MenuItem(field.getTitle());
		menuItem.setChecked(!field.getIsHidden());

		menuItem.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(MenuItemClickEvent event) {
				menuItem.setChecked(!menuItem.getChecked());
				if (menuItem.getChecked())
					MaintenanceProtocolGrid.this.showField(field.getName());
				else
					MaintenanceProtocolGrid.this.hideField(field.getName());
			}
		});
		return menuItem;
	}

}
