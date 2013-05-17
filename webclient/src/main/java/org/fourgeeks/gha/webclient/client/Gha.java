package org.fourgeeks.gha.webclient.client;

import org.fourgeeks.gha.domain.mix.LegalEntity;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gha implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
	/*	
		final GWTUserServiceAsync service = GWT.create(GWTUserService.class);
		service.test(new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				Window.alert("" + result);
				service.test2(new AsyncCallback<LegalEntity>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(LegalEntity result) {
						if (result != null)
							Window.alert("success");
						else
							Window.alert("error");

					}
				});
			}

			
			
			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}
		});
*/
		final DynamicForm loginForm = new DynamicForm();
		loginForm.setWidth("400");
		loginForm.setLayoutAlign(VerticalAlignment.CENTER);
		final TextItem username = new TextItem("username", "Nombre De Usuario");
		final PasswordItem password = new PasswordItem("password", "Contraseña");
		final SubmitItem submitButton = new	SubmitItem("submitButton","Iniciar Sesion");
		final CheckboxItem rememberData = new CheckboxItem("rememberData", "Recordar mis Datos");
		final LinkItem forgotData = new LinkItem("¿Olvido su Contraseña?");
		
		loginForm.setFields(username,password,submitButton,rememberData, forgotData);
		
		loginForm.draw();
		
	}

}
