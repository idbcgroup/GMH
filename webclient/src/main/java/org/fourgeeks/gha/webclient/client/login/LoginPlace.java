package org.fourgeeks.gha.webclient.client.login;

import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAPlace;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.EventListener;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class LoginPlace implements GHAPlace {

	public LoginPlace() {
	}

	@Override
	public void show() {
		RootPanel.get("main-content").clear();
		RootPanel.get("user-info").clear();
		RootPanel.get("menu-bar").clear();

		StringBuilder html = new StringBuilder();
		html.append("<div class='login-panel'>");
		html.append("<div class='logo login-logo'></div>");
		html.append("<h1 class='login-titulo'>Iniciar Sesion</h1>");
		html.append("<div class='smallfont full'>Ubicado en:<span id='ubicacion'>Sotano enfermeria</span></div>");
		html.append("<form class='centered'>");
		html.append("<input class='round' id='username' type='text' placeholder='Nombre de usuario'><br/>");
		html.append("<input class='round' id='password' type='password' placeholder='Contraseña'> <br/>");
		html.append("<input id='login-button' type='button' value='Iniciar Sesion' class='GHAButton'>");
		html.append("<div class='smallfont'><input type='checkbox'>Recordar mis datos</div><br/><a href='' class='smallfont'>¿Olvidaste tu contraseña?</a></form></div>");
		HTML content = new HTML(html.toString());
		RootPanel.get("main-content").add(content);

		//for Events
		Element element = RootPanel.get("login-button").getElement();
		Element uTextbox = RootPanel.get("username").getElement();
		Element pTextbox = RootPanel.get("password").getElement();;
		

		final InputElement userTextbox = (InputElement) Document.get().getElementById("username");
		final InputElement passTextbox = (InputElement) Document.get().getElementById("password");
		userTextbox.focus();
		
		DOM.sinkEvents(element, Event.ONCLICK);
		DOM.setEventListener(element, new EventListener() {

			@Override
			public void onBrowserEvent(Event event) {
				String username = userTextbox.getValue();
				String password = passTextbox.getValue();

				// Window.alert(username+"-"+password);

				final GWTLoginServiceAsync service = GWT
						.create(GWTLoginService.class);
				service.login(username, password,
						new GHAAsyncCallback<Boolean>() {
							@Override
							public void onSuccess(Boolean result) {
								// Window.alert("Button Clicked. Result:" +
								// result);
								if (!result) {
									String token = History.getToken();
									// Window.alert("Token:" + token);
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
		
		DOM.sinkEvents(pTextbox, Event.ONKEYUP);
		DOM.setEventListener(pTextbox, new EventListener() {
			
			@Override
			public void onBrowserEvent(Event event) {
				// TODO Auto-generated method stub
				
				if(event.getKeyCode() == 13){
					String username = userTextbox.getValue();
					String password = passTextbox.getValue();
	
					// Window.alert(username+"-"+password);
	
					final GWTLoginServiceAsync service = GWT
							.create(GWTLoginService.class);
					service.login(username, password,
							new GHAAsyncCallback<Boolean>() {
								@Override
								public void onSuccess(Boolean result) {
									// Window.alert("Button Clicked. Result:" +
									// result);
									if (!result) {
										String token = History.getToken();
										// Window.alert("Token:" + token);
										if (token.equals("home"))
											History.fireCurrentHistoryState();
										else
											History.newItem("home");
									} else
										History.fireCurrentHistoryState();
								}
							});
				}
			}
		});		
	}
}