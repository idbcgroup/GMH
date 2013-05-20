package org.fourgeeks.gha.webclient.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Gha implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final GWTUserServiceAsync service = GWT.create(GWTUserService.class);
		service.isLogged(new AsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				History.newItem("login");
			}
		
			@Override
			public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
				
			}
		});
		  
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				// TODO Auto-generated method stub
				String historyToken = event.getValue();
				//com.google.gwt.user.client.Window.alert("Result: "+historyToken.substring(historyToken.indexOf("=")+1));
			
				// Parse the history token
		        if (historyToken.substring(historyToken.indexOf("=")+1).equals("false")) {
					showLoginUI(false);
				} else {
					showLoginUI(true);
				}
			}
		});
	}
	
	public void showLoginUI(boolean logged){
		/*
		VLayout layout = new VLayout();
		
		if(!logged){
			DynamicForm loginForm = new DynamicForm();
			
			Img logo = new Img();
			Label title = new Label();
			title.setContents("<h3>Iniciar Sesion</h3>");
			Label ubicacion = new Label();
			ubicacion.setTitle("Ubicado en:");
			
			TextItem username = new TextItem("username", "Nombre De Usuario");
			PasswordItem password = new PasswordItem("password", "Contraseña");
			SubmitItem submitButton = new SubmitItem("submitButton","Iniciar Sesion");
			CheckboxItem rememberData = new CheckboxItem("rememberData", "Recordar mis Datos");
			LinkItem forgotData = new LinkItem("¿Olvido su Contraseña?");
			
			loginForm.setFields(username, password, submitButton, rememberData,forgotData);
			layout.addMember(logo);
			layout.addMember(title);
			layout.addMember(ubicacion);
			layout.addMember(loginForm);
			
		}else{
			layout.clear();
			Label loggedText = new Label();
			loggedText.setContents("<b>Ya existe un usuario loggeado en el sistema.</b>");
			
			layout.addMember(loggedText);
		}
		
		RootPanel.get("main-content").add(layout);	
		layout.draw();*/
		if(!logged){
			Window.alert("No esta loggeado.");
			HTML content = new HTML("<div class='login-panel'><div class='logo login-logo'></div><h1 class='login-titulo'>Iniciar Sesion</h1><div class='smallfont full'>Ubicado en:<span id='ubicacion'>Sotano enfermeria</span></div><form class='centered'><input type='text' value='Nombre de usuario'><br/><input type='password' value='Contraseña'> <br/><input type='submit' value='Iniciar Sesion' class='GHAButton'><div class='smallfont'><input type='checkbox'>Recordar mis datos</div><br/><a href='' class='smallfont'>¿Olvidaste tu contraseña?</a></form></div>");
			RootPanel.get("main-content").add(content);
		}else{
			Window.alert("esta loggeado.");
		}
	}

}
