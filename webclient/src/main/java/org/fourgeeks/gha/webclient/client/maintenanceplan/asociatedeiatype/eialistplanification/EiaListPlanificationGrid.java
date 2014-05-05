package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification;

import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * @author eguerere
 * 
 */

public class EiaListPlanificationGrid extends GhaGrid<EiaPlanificationEntity> {
	private final MenuItem[] headerMenuItems;

	EiaListPlanificationGrid() {
		setEmptyMessage("No existen Equipos para mostrar");
		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField serialGridField = new GHAGridField("serialNumber",
				"Serial");
		GHAGridField faiGridField = new GHAGridField("fai", "Id Activo Fijo");
		GHAGridField statusGridField = new GHAGridField("state", "Estado");
		GHAGridField cargoField = new GHAGridField("role", "Cargo responsable");
		GHAGridField proveedorField = new GHAGridField("provider",
				"Proveedor del servicio");
		GHAGridField statusPlanification = new GHAGridField(
				"statusPlanification", "Estado Planificación");
		statusPlanification.setType(ListGridFieldType.BOOLEAN);
		statusPlanification.setCanEdit(true);

		setFields(idGridField, serialGridField, faiGridField, statusGridField,
				cargoField, proveedorField, statusPlanification);

		headerMenuItems = generateHeaderMenuItems(idGridField, serialGridField,
				faiGridField, statusGridField, cargoField, proveedorField,
				statusPlanification);

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
					EiaListPlanificationGrid.this.showField(field.getName());
				else
					EiaListPlanificationGrid.this.hideField(field.getName());
			}
		});
		return menuItem;
	}
}
