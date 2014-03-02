package org.fourgeeks.gha.dbadmin.client;

import gwtupload.client.IUploader;
import gwtupload.client.IUploader.OnFinishUploaderHandler;
import gwtupload.client.SingleUploader;

import java.util.ArrayList;

import org.fourgeeks.gha.dbadmin.shared.Record;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DBAdmin implements EntryPoint {

	VerticalPanel mainPanel = new VerticalPanel();
	HorizontalPanel header = new HorizontalPanel();
	HorizontalPanel uploadPanel = new HorizontalPanel();

	Label tableName = new Label();
	FlexTable mainTable = new FlexTable();
	VerticalPanel recordTable = new VerticalPanel();

	public TableServiceAsync service;

	public void build(ArrayList<Record> list) {

	}

	@Override
	public void onModuleLoad() {
		final SingleUploader upload = new SingleUploader();

		service = GWT.create(TableService.class);

		upload.setTitle("uploadFormElement");

		upload.addOnFinishUploadHandler(new OnFinishUploaderHandler() {

			@Override
			public void onFinish(IUploader uploader) {
				recordTable.clear();
				service.getColumns(new AsyncCallback<ArrayList<String>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Error: no se pudo cargar los nombres de las columnas");
					}

					@Override
					public void onSuccess(ArrayList<String> result) {
						// TODO Auto-generated method stub
						final HorizontalPanel p = new HorizontalPanel();
						for (final String c : result) {
							final TextBox tb = new TextBox();
							tb.setText(c);
							p.add(tb);
						}
						recordTable.add(p);
					}
				});

				service.getTable(new AsyncCallback<ArrayList<Record>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO error
					}

					@Override
					public void onSuccess(ArrayList<Record> result) {
						// build(result);
						for (final Record r : result) {
							final HorizontalPanel p = new HorizontalPanel();
							for (final String s : r.getList()) {
								final TextBox tb = new TextBox();
								tb.setText(s);
								p.add(tb);
							}
							final Button b = new Button();
							b.setText("SQL");
							b.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									Window.alert(r.getQuery());
								}
							});
							p.add(b);
							recordTable.add(p);
						}
					}
				});
			}
		});

		uploadPanel.add(upload);

		header.add(new Label("Tabla:"));
		header.add(tableName);

		mainPanel.add(uploadPanel);
		mainPanel.add(header);
		mainPanel.add(recordTable);

		RootPanel.get("root").add(mainPanel);
	}
}
