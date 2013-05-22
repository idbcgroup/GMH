package org.fourgeeks.gha.webclient.client;

import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.UIPlace;
import org.fourgeeks.gha.webclient.client.UI.UIPlacesFactory;
import org.fourgeeks.gha.webclient.client.login.GWTLoginService;
import org.fourgeeks.gha.webclient.client.login.GWTLoginServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */

public class Gha implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		History.addValueChangeHandler(new ValueChangeHandler<String>() {
			@Override
			public void onValueChange(ValueChangeEvent<String> event) {
				String historyToken = event.getValue();
				if (historyToken == null)
					return;

				UIPlace place = UIPlacesFactory.createPlace(historyToken);
				place.show();
			}
		});

		final GWTLoginServiceAsync service = GWT.create(GWTLoginService.class);
		service.isLogged(new GHAAsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				Window.alert("" + result);
				if (!result) {
					String token = History.getToken();
					Window.alert(token);
					if (token.equals("login"))
						History.fireCurrentHistoryState();
					else
						History.newItem("login");
				} else
					History.fireCurrentHistoryState();
			}
		});

	}

	public void showLoginUI(boolean logged) {
		/*
		 * VLayout layout = new VLayout();
		 * 
		 * if(!logged){ DynamicForm loginForm = new DynamicForm();
		 * 
		 * Img logo = new Img(); Label title = new Label();
		 * title.setContents("<h3>Iniciar Sesion</h3>"); Label ubicacion = new
		 * Label(); ubicacion.setTitle("Ubicado en:");
		 * 
		 * TextItem username = new TextItem("username", "Nombre De Usuario");
		 * PasswordItem password = new PasswordItem("password", "Contraseña");
		 * SubmitItem submitButton = new
		 * SubmitItem("submitButton","Iniciar Sesion"); CheckboxItem
		 * rememberData = new CheckboxItem("rememberData",
		 * "Recordar mis Datos"); LinkItem forgotData = new
		 * LinkItem("¿Olvido su Contraseña?");
		 * 
		 * loginForm.setFields(username, password, submitButton,
		 * rememberData,forgotData); layout.addMember(logo);
		 * layout.addMember(title); layout.addMember(ubicacion);
		 * layout.addMember(loginForm);
		 * 
		 * }else{ layout.clear(); Label loggedText = new Label();
		 * loggedText.setContents
		 * ("<b>Ya existe un usuario loggeado en el sistema.</b>");
		 * 
		 * layout.addMember(loggedText); }
		 * 
		 * RootPanel.get("main-content").add(layout); layout.draw();
		 */
		if (!logged) {
			Window.alert("No esta loggeado.");
		} else {
			Window.alert("esta loggeado.");
		}
	}

}
