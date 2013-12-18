/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * @author emiliot
 * 
 */
public class MaintenanceProtocolsGrid extends GhaGrid<MaintenanceProtocols> {
	private final MenuItem[] headerMenuItems;

	/** */
	public MaintenanceProtocolsGrid() {

		final GHAGridField ordinalGridField = new GHAGridField("ordinal",
				"Secuencia");
		final GHAGridField typeGridField = new GHAGridField("type", "Tipo");
		final GHAGridField codeGridField = new GHAGridField("code", "Código");
		final GHAGridField isSubProtocolGridField = new GHAGridField(
				"subprotocol", "Sub-protocolo");
		final GHAGridField nameGridField = new GHAGridField("name", "Nombre");
		final GHAGridField descGridField = new GHAGridField("desc",
				"Descripción");

		final GHAGridField materialsGridField = new GHAGridField("materials",
				"Materiales", true);
		final GHAGridField toolsGridField = new GHAGridField("tools",
				"Herramientas", true);
		final GHAGridField equipsGridField = new GHAGridField("equips",
				"Equipos", true);
		final GHAGridField timeGridField = new GHAGridField("time", "Duración",
				true);
		final GHAGridField periodOfTimeGridField = new GHAGridField("pot",
				"Periodo de Tiempo", true);
		final GHAGridField costGridField = new GHAGridField("cost", "Costo",
				true);
		final GHAGridField currencyGridField = new GHAGridField("currency",
				"Moneda", true);

		setEmptyMessage("No existen actividades o sub-protocolos para mostrar");

		setFields(ordinalGridField, typeGridField, codeGridField,
				isSubProtocolGridField, nameGridField, descGridField,
				materialsGridField, toolsGridField, equipsGridField,
				timeGridField, periodOfTimeGridField, costGridField,
				currencyGridField);

		headerMenuItems = generateHeaderMenuItems(ordinalGridField,
				typeGridField, codeGridField, isSubProtocolGridField,
				nameGridField, descGridField, materialsGridField,
				toolsGridField, equipsGridField, timeGridField,
				periodOfTimeGridField, costGridField, currencyGridField);

		setCanHover(true);
		setShowHover(true);
		setShowHoverComponents(true);
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
					MaintenanceProtocolsGrid.this.showField(field.getName());
				else
					MaintenanceProtocolsGrid.this.hideField(field.getName());
			}
		});
		return menuItem;
	}
}
