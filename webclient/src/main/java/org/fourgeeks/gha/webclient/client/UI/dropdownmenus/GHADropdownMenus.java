package org.fourgeeks.gha.webclient.client.UI.dropdownmenus;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class GHADropdownMenus {

	/**
	 * @param user
	 * @return
	 */
//	public static VLayout userMenu(Bpu user) {
//		final VLayout user_menu = new VLayout();
//
//		user_menu.setAnimateFadeTime(300);
//		user_menu.setPosition(Positioning.ABSOLUTE);
//		user_menu.setTop(70);
//		user_menu.setSize("280px", "*");
//		user_menu.setBackgroundColor("#FFFFFF");
//		user_menu.setBorder("1px solid #E0E0E0");
//		user_menu.setVisible(false);
//		
//		Label mailText;
//		if(user.getCitizen().getPrimaryEmail() != null){
//			mailText = new Label("Correo electrónico: " + user.getCitizen().getPrimaryEmail());
//		}else{
//			mailText = new Label("Correo electrónico: No posee.");
//		}
//		mailText.setHeight("20px");
//		mailText.setWidth100();
//		mailText.setStyleName("title-label");
//		
//		IButton logoutButton = new IButton("Cerrar Sesión", new ClickHandler() {
//			@Override
//			public void onClick(ClickEvent event) {
//				History.newItem("login");
//				user_menu.animateHide(AnimationEffect.FADE);
//				//GHASessionData.logout();
//			}
//		});
//
//		VLayout userdataLayout = new VLayout();
//		userdataLayout.setStyleName("sides-padding");
//
//		userdataLayout.addMembers(mailText, logoutButton);
//
//		user_menu
//				.addMembers(GHAUiHelper.verticalGraySeparatorLabel("25px", user
//						.getCitizen().getFirstName()
//						+ " "
//						+ user.getCitizen().getFirstLastName()), userdataLayout);
//
//		return user_menu;
//	}

	/**
	 * @return
	 */
	public static VLayout notificationsMenu() {
		VLayout notificationsMenu = new VLayout();
		notificationsMenu.setPosition(Positioning.ABSOLUTE);
		notificationsMenu.setTop(70);
		notificationsMenu.setSize("280px", "*");
		notificationsMenu.setBackgroundColor("#FFFFFF");
		notificationsMenu.setBorder("1px solid #E0E0E0");
		notificationsMenu.setVisible(false);

		HLayout topUserLayout = new HLayout();
		topUserLayout.setSize("100%", "25px");
		topUserLayout.setBackgroundColor("#666666");

		notificationsMenu.addMember(GHAUiHelper.verticalGraySeparator("25px"));

		return notificationsMenu;
	}
}
