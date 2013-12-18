package org.fourgeeks.gha.webclient.client.eia;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.google.gwt.event.logical.shared.ResizeHandler;
import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ClickHandler;
import com.smartgwt.client.widgets.menu.events.MenuItemClickEvent;

/**
 * @author alacret, emiliot
 * 
 */
public class EIAGrid extends GhaGrid<Eia> implements ResizeHandler {
	private MenuItem[] headerMenuItems;

	/**
	 * 
	 */
	public EIAGrid() {
		setEmptyMessage("No existen Equipos para mostrar");

		GHAGridField idGridField = new GHAGridField("id", "No");
		GHAGridField serialGridField = new GHAGridField("serialNumber",
				"Serial");
		GHAGridField faiGridField = new GHAGridField("fai", "Id Activo Fijo");
		GHAGridField locationGridField = new GHAGridField("location",
				"Ubicación");
		GHAGridField bpiGridField = new GHAGridField("bpi", "Institución");
		GHAGridField statusGridField = new GHAGridField("state", "Estado");

		// los que van ocultos por defecto
		GHAGridField obuGridField = new GHAGridField("obu", "Dept. Responsable", true);
		GHAGridField roleGridField = new GHAGridField("role", "Rol Responsable", true);
		GHAGridField typeFiled = new GHAGridField("type", "Tipo de equipo", true);
		GHAGridField brandFiled = new GHAGridField("brand", "Marca", true);
		GHAGridField modelFiled = new GHAGridField("model", "Modelo", true);

		setFields(idGridField, serialGridField, faiGridField,
				locationGridField, bpiGridField, statusGridField, obuGridField,
				roleGridField, typeFiled, brandFiled, modelFiled);

		headerMenuItems = generateHeaderMenuItems(idGridField, serialGridField,
				faiGridField, locationGridField, bpiGridField, statusGridField,
				obuGridField, roleGridField, typeFiled, brandFiled, modelFiled);

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
					EIAGrid.this.showField(field.getName());
				else
					EIAGrid.this.hideField(field.getName());
			}
		});
		return menuItem;
	}
}
