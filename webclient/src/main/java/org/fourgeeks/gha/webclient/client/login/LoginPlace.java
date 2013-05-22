package org.fourgeeks.gha.webclient.client.login;

import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.UIPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class LoginPlace implements UIPlace {

	public LoginPlace() {
	}

	@Override
	public void show() {
		RootPanel.get("main-content").clear();
		//Window.alert("login}");
		StringBuilder html = new StringBuilder();
		html.append("<div class='login-panel'>");
		html.append("<div class='logo login-logo'></div>");
		html.append("<h1 class='login-titulo'>Iniciar Sesion</h1>");
		html.append("<div class='smallfont full'>Ubicado en:<span id='ubicacion'>Sotano enfermeria</span></div>");
		html.append("<form class='centered'>");
		html.append("<input id='username' type='text' placeholder='Nombre de usuario'><br/><input id='password' type='password' placeholder='Contraseña'> <br/>");
		html.append("<input id='login-button' type='button' value='Iniciar Sesion' class='GHAButton'>");
		html.append("<div class='smallfont'><input type='checkbox'>Recordar mis datos</div><br/><a href='' class='smallfont'>¿Olvidaste tu contraseña?</a></form></div>");
		HTML content = new HTML(html.toString());
		RootPanel.get("main-content").add(content);

		Element element = RootPanel.get("login-button").getElement();
		DOM.sinkEvents(element, Event.ONCLICK);
		DOM.setEventListener(element, new EventListener(){

			@Override
			public void onBrowserEvent(Event event) {
				InputElement userTextbox = (InputElement) Document.get().getElementById("username");
				InputElement passTextbox = (InputElement) Document.get().getElementById("password");
				String username = userTextbox.getValue();
				String password = passTextbox.getValue();
				
				Window.alert(username+"-"+password);

				final GWTLoginServiceAsync service = GWT
						.create(GWTLoginService.class);
				service.login(username, password,
						new GHAAsyncCallback<Boolean>() {
							@Override
							public void onSuccess(Boolean result) {
								Window.alert("Button Clicked. Result:" + result);
								if (!result) {
									String token = History.getToken();
									Window.alert("Token:" + token);
									if (token.equals("home"))
										History.fireCurrentHistoryState();
									else
										History.newItem("home");
								} else
									History.fireCurrentHistoryState();
							}
						});

			}
		});

	}
}
