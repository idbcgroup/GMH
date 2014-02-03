/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridField;
import org.fourgeeks.gha.webclient.client.UI.grids.GhaGrid;

import com.smartgwt.client.types.DragDataAction;
import com.smartgwt.client.widgets.events.DropCompleteEvent;
import com.smartgwt.client.widgets.events.DropCompleteHandler;

/**
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolActivitiesGrid extends
		GhaGrid<SubProtocolAndChecklist> {
	private final GHAAsyncCallback<List<SubProtocolAndChecklist>> callback = new GHAAsyncCallback<List<SubProtocolAndChecklist>>() {
		@Override
		public void onSuccess(List<SubProtocolAndChecklist> result) {
		}
	};

	/**
	 * 
	 */
	public MaintenanceSubprotocolActivitiesGrid() {

		final GHAGridField ordinalGridField = new GHAGridField("ordinal",
				"Secuencia");
		final GHAGridField typeGridField = new GHAGridField("type", "Tipo");
		final GHAGridField codeGridField = new GHAGridField("code", "Código");
		final GHAGridField nameGridField = new GHAGridField("name", "Nombre");

		final GHAGridField timeGridField = new GHAGridField("time", "Duración");
		final GHAGridField periodOfTimeGridField = new GHAGridField("pot",
				"Periodo de Tiempo");
		final GHAGridField costGridField = new GHAGridField("cost", "Costo");
		final GHAGridField currencyGridField = new GHAGridField("currency",
				"Moneda");

		setEmptyMessage("No existen actividades para mostrar");

		setFields(ordinalGridField, typeGridField, codeGridField,
				nameGridField, timeGridField, periodOfTimeGridField,
				costGridField, currencyGridField);

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
				final List<SubProtocolAndChecklist> entities = getEntities();

				int size = getRecords().length;
				for (int i = 0, ordinal = 1; i < size; i++, ordinal++) {
					getRecord(i).setAttribute("ordinal", ordinal);
					entities.get(i).setOrdinal(ordinal);
				}

				SubprotocolAndChecklistModel.update(entities, callback);
			}
		});
	}
}