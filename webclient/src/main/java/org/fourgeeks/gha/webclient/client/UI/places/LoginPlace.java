package org.fourgeeks.gha.webclient.client.UI.places;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;



public class LoginPlace implements UIPlace{
	
	public LoginPlace() {
	}


	@Override
	public void show() {
		// TODO Auto-generated method stub
		HTML content = new HTML("<div class='login-panel'><div class='logo login-logo'></div><h1 class='login-titulo'>Iniciar Sesion</h1><div class='smallfont full'>Ubicado en:<span id='ubicacion'>Sotano enfermeria</span></div><form class='centered'><input type='text' value='Nombre de usuario'><br/><input type='password' value='Contraseña'> <br/><input type='submit' value='Iniciar Sesion' class='GHAButton'><div class='smallfont'><input type='checkbox'>Recordar mis datos</div><br/><a href='' class='smallfont'>¿Olvidaste tu contraseña?</a></form></div>");
		RootPanel.get("main-content").add(content);
	}
}
