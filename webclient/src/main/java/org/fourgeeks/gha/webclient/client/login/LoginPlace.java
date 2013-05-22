package org.fourgeeks.gha.webclient.client.login;

import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.UIPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class LoginPlace implements UIPlace {

	public LoginPlace() {
	}

	@Override
	public void show() {
		Window.alert("login}");
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
		DOM.setEventListener(element, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				String username = RootPanel.get("username").getElement()
						.getAttribute("value");
				String password = RootPanel.get("password").getElement()
						.getAttribute("value");

				Window.alert(username);

				final GWTLoginServiceAsync service = GWT
						.create(GWTLoginService.class);
				service.login(username, password,
						new GHAAsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean result) {
								Window.alert("" + result);
							}
						});

			}
		});

	}
}
