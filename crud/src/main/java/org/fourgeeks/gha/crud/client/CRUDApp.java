package org.fourgeeks.gha.crud.client;

import java.util.ArrayList;

import org.fourgeeks.gha.crud.shared.GHARecord;
import org.fourgeeks.gha.crud.shared.GHARecordTable;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CRUDApp implements EntryPoint {
	
	RootLayoutPanel root;
	
	DockLayoutPanel mainPanel;
	HorizontalPanel selectTablePanel;
	LayoutPanel tablePanel;
	DataGrid<GHARecord<String>> dataGrid;
	DataGrid<GHARecord<String>> popupGrid;
	
	GHARecordTable table;
	
	GHARecord<String> entry;
	
	Label selectTableLabel;
	TextBox selectTableBox;
	Button selectTableButton;
	Button entryButton;
	Button popupCloseButton;
	
	PopupPanel entryPopupPanel;
	
	//SELECT table_name FROM INFORMATION_SCHEMA.TABLES ORDER BY table_name ASC
	
	public DBServiceAsync service;
	
	public void editEntry(GHARecord<String> record) {
		entryPopupPanel.center();
		ArrayList<GHARecord<String>> list = new ArrayList<GHARecord<String>>();
		list.add(record);
		entry = record;
		popupGrid.setRowData(list);
	}
	
	public void build(GHARecordTable table) {
		for (int i = 0; i < table.getColumns().size(); i++) {
			final int index = i;
			Column<GHARecord<String>, String> textCol = new Column<GHARecord<String>, String>(new TextCell()){
	
				@Override
				public String getValue(GHARecord<String> object) {
					// TODO Auto-generated method stub
					return object.get(index);
				}};
			dataGrid.addColumn(textCol, table.getColumns().get(i));
			dataGrid.setColumnWidth(i, "100px");
		}
		
		Column<GHARecord<String>, String> buttonCol = new Column<GHARecord<String>, String>(new ButtonCell()){

			@Override
			public String getValue(GHARecord<String> object) {
				// TODO Auto-generated method stub
				return "Edit";
			}};
		buttonCol.setFieldUpdater(new FieldUpdater<GHARecord<String>, String>() {

			@Override
			public void update(int index, GHARecord<String> object, String value) {
				editEntry(object);
			}});
		dataGrid.addColumn(buttonCol, "Buttons");
		dataGrid.setColumnWidth(buttonCol, "80px");
		
		dataGrid.setRowData(table.getRecords());
		
		popupGrid = new DataGrid<GHARecord<String>>();
		
		for (int i = 0; i < table.getColumns().size(); i++) {
			final int index = i;
			Column<GHARecord<String>, String> textCol = new Column<GHARecord<String>, String>(new TextInputCell()){
	
				@Override
				public String getValue(GHARecord<String> object) {
					// TODO Auto-generated method stub
					return object.get(index);
				}};
			popupGrid.addColumn(textCol, table.getColumns().get(i));
			popupGrid.setColumnWidth(i, "200px");
			textCol.setFieldUpdater(new FieldUpdater<GHARecord<String>, String>() {

				@Override
				public void update(int index, GHARecord<String> object,
						String value) {
					object.set(index, value);
				}});
		}
		
		DockLayoutPanel popupLayoutPanel = new DockLayoutPanel(Unit.EM);
		
		popupCloseButton = new Button("Ok");
		
		popupCloseButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				entryPopupPanel.hide();
				//TODO update db
			}});
		popupLayoutPanel.addSouth(popupCloseButton, 3);
		popupLayoutPanel.add(popupGrid);
		entryPopupPanel.setWidget(popupLayoutPanel);
		
	}
	
	@Override
	public void onModuleLoad() {
		
		service = GWT.create(DBService.class);
		
		dataGrid = new DataGrid<GHARecord<String>>();
		//dataGrid.setMinimumTableWidth(140, Unit.EM);
		
		mainPanel = new DockLayoutPanel(Unit.EM);
		tablePanel = new LayoutPanel();
		
		selectTableLabel = new Label("Table name:");
		selectTableBox = new TextBox();
		selectTableButton = new Button("Load");
		selectTablePanel = new HorizontalPanel();
		
		entryPopupPanel = new PopupPanel(true);
		
		entryButton = new Button("New Entry");
		
		entryButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				GHARecord<String> record = new GHARecord<String>();
				for(int i = 0; i < table.getColumns().size(); i++)
					record.add("");
				editEntry(record);
			}});
		
		selectTableButton.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				service.getTable(selectTableBox.getText(), "schema",
						new AsyncCallback<GHARecordTable>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onSuccess(GHARecordTable result) {
								table = result;
								build(result);
							}});
			}});
		
		entryPopupPanel.setSize("900px", "200px");
		
		selectTablePanel.add(selectTableLabel);
		selectTablePanel.add(selectTableBox);
		selectTablePanel.add(selectTableButton);
		
		tablePanel.add(dataGrid);
		
		mainPanel.addNorth(selectTablePanel, 5);
		mainPanel.addSouth(entryButton, 3);
		mainPanel.add(tablePanel);
		
		root = RootLayoutPanel.get();
		
		root.add(mainPanel);
		
	}
}
