package org.fourgeeks.gha.webclient.client.UI;

import org.fourgeeks.gha.webclient.client.UI.places.LoginPlace;
import org.fourgeeks.gha.webclient.client.UI.places.UIPlace;

public class UIPlacesFactory{
	/*
	private UIPlacesFactory() throws UnavailableException{
		throw new UnavailableException("This class does not supposed to be instantiated");
	}*/
	
	public static UIPlace createPlace(String token){
		if(token.equals("login"))
			return new LoginPlace();
		/*
		switch (token) {
			case "login":
		}*/
		throw new IllegalArgumentException("Unsuported token");
	}
}
