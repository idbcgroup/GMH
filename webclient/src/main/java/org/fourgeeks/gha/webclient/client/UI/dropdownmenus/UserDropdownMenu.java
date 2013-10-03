package org.fourgeeks.gha.webclient.client.UI.dropdownmenus;

import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAHideable;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserDropdownMenu extends VLayout implements GHAHideable, ResizeHandler{

	int posX, posY;
	
	public UserDropdownMenu(Bpu user) {
		GHAUiHelper.addGHAResizeHandler(this);
		posX = (Window.getClientWidth() * 70) /100;
		posY = (Window.getClientHeight() * 10) /100;
				
		setAnimateFadeTime(300);
		setPosition(Positioning.ABSOLUTE);
		setTop(70);
		setSize("280px", "*");
		setBackgroundColor("#FFFFFF");
		setBorder("1px solid #E0E0E0");
		setVisible(false);
		
		Label mailText;
		if(user.getCitizen().getPrimaryEmail() != null){
			mailText = new Label("Correo electrónico: " + user.getCitizen().getPrimaryEmail());
		}else{
			mailText = new Label("Correo electrónico: No posee.");
		}
		mailText.setHeight("20px");
		mailText.setWidth100();
		mailText.setStyleName("title-label");
		
		IButton logoutButton = new IButton("Cerrar Sesión", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				History.newItem("login");
				animateHide(AnimationEffect.FADE);
				//GHASessionData.logout();
			}
		});

		VLayout userdataLayout = new VLayout();
		userdataLayout.setStyleName("sides-padding");

		userdataLayout.addMembers(mailText, logoutButton);

		addMembers(GHAUiHelper.verticalGraySeparatorLabel("25px", 
						user.getCitizen().getFirstName()
						+ " "
						+ user.getCitizen().getFirstLastName()), 
						userdataLayout);
	}
	
	public void show(int x, int y){
		animateShow(AnimationEffect.FADE);
		setLeft(posX);
		setTop(posY);
		setVisible(true);
		focus();
		Window.alert("Focus:"+containsFocus());
	}
	
	@Override
	public void hide(){
		animateHide(AnimationEffect.FADE);
		setVisible(false);
		setLeft(0);
		setTop(0);
	}

	@Override
	public void onResize(ResizeEvent event) {
		posX = (Window.getClientWidth() * 70) /100;
	}
}
