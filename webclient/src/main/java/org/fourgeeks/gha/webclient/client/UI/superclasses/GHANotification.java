package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.List;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.interfaces.GHAClosable;
import org.fourgeeks.gha.webclient.client.message.GWTMessageService;
import org.fourgeeks.gha.webclient.client.message.GWTMessageServiceAsync;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * @author alacret
 * 
 */
public class GHANotification {
	private static final GWTMessageServiceAsync messageService = GWT
			.create(GWTMessageService.class);

	/*
	 * public GHANotification() { setPosition(Positioning.ABSOLUTE);
	 * setBottom(Window.getClientHeight()-20); setSize("280px", "*");
	 * setBackgroundColor("#FFFFFF"); setBorder("1px solid #E0E0E0");
	 * setVisibility(Visibility.HIDDEN);
	 * 
	 * HLayout topUserLayout = new HLayout(); topUserLayout.setSize("100%",
	 * "25px"); topUserLayout.setBackgroundColor("#666666");
	 * 
	 * addMember(GHAUiHelper.verticalGraySeparator("25px")); }
	 */

	/**
	 * @param message
	 */
	@Deprecated
	public static void oldAlert(String message) {
		SC.say(GHAStrings.get("information"), message);
	}

	/**
	 * Alert a message using SC
	 * 
	 * @param message
	 */
	public static void info(String message) {
		alert(message);
	}

	/**
	 * @param title
	 * @param message
	 * @param callback
	 */
	public static void confirm(String title, String message,
			BooleanCallback callback) {
		SC.ask(title, message, callback);
	}

	/**
	 * this method receives a key to find and show the message from database
	 * 
	 * @param key
	 */
	public static void alert(String key) {
		messageService.find(key, new GHAAsyncCallback<GHAMessage>() {

			@Override
			public void onSuccess(GHAMessage result) {
				SC.say(result.getText());
			}
		});

	}

	/**
	 * this method receives a list of keys to find the messages and then show
	 * them to the user
	 * 
	 * @param keys
	 */
	public static void alert(List<String> keys) {
		if (keys.isEmpty()) {
			alert("form-errors");
			return;
		}
		messageService.find(keys, new GHAAsyncCallback<List<GHAMessage>>() {

			@Override
			public void onSuccess(List<GHAMessage> result) {
				StringBuilder builder = new StringBuilder();
				for (GHAMessage msg : result) {
					builder.append(msg.getText()).append("<br>");
				}
				SC.say(builder.toString());
			}
		});
	}

	/**
	 * @param ghaMessage
	 */
	public static void alert(GHAMessage ghaMessage) {
		SC.say(ghaMessage.getText());
	}

	private static class ModalInfoNotification extends VLayout implements
			ResizeHandler, GHAClosable {

		private int width = 300;
		private HTML backDiv = new HTML();

		public ModalInfoNotification(String title, String errorMessage) {
			super();
			GHAUiHelper.addGHAResizeHandler(this);

			setWidth(width);
			setHeight("*");
			setLeft((Window.getClientWidth() / 2) - (width / 2));
			setTop(140);
			setPosition(Positioning.ABSOLUTE);
			setBackgroundColor("#E0E0E0");
			setBorder("1px solid #E0E0E0");
			setDefaultLayoutAlign(Alignment.CENTER);
			setMembersMargin(10);
			setVisible(false);
			setAnimateTime(60);

			setShadowDepth(4);
			setShowShadow(true);
			setZIndex(444444);

			backDiv.setWidth("100%");
			backDiv.setHeight("100%");
			backDiv.setStyleName("backDivDim");
			backDiv.setVisible(false);
			// TITLE LAYOUT
			HLayout titleLayout = GHAUiHelper.verticalGraySeparatorLabel(
					"40px", "Informacion");

			// LABEL LAYOUT
			GHALabel errorText = new GHALabel(errorMessage);
			errorText.setStyleName("text-label");

			VLayout userdataLayout = new VLayout();
			userdataLayout.setHeight("*");
			userdataLayout.setWidth100();
			userdataLayout
					.setStyleName("sides-padding padding-top padding-bot");
			userdataLayout.setBackgroundColor("#E0E0E0");
			userdataLayout.setAlign(Alignment.CENTER);
			userdataLayout.setDefaultLayoutAlign(Alignment.CENTER);
			userdataLayout.setMembersMargin(10);

			GHAButton acceptButton = new GHAButton("Aceptar",
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							close();
						}
					});

			userdataLayout.addMembers(errorText, acceptButton);

			addMembers(titleLayout, userdataLayout);

			show();
		}

		public ModalInfoNotification(int membersMargin) {
			super(membersMargin);
			// TODO Auto-generated constructor stub
		}

		public ModalInfoNotification(JavaScriptObject jsObj) {
			super(jsObj);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onResize(ResizeEvent event) {
			setLeft((Window.getClientWidth() / 2) - (width / 2));
		}

		@Override
		public void close() {
			// TODO Auto-generated method stub
			RootPanel.get("notificationsBackDiv").removeStyleName("dim");
			int windowZIndex = getZIndex();
			RootPanel.get("notificationsBackDiv").getElement().getStyle().setZIndex(-80000);

			animateHide(AnimationEffect.FADE);
			backDiv.setVisible(false);
		}

		@Override
		public void show() {
			// super.show();
			RootPanel.get("notificationsBackDiv").addStyleName("dim");
			int windowZIndex = getZIndex();
			RootPanel.get("notificationsBackDiv").getElement().getStyle().setZIndex(windowZIndex - 1);
			
			animateShow(AnimationEffect.FADE);
			setVisible(true);
			bringToFront();
		}

		@Override
		public boolean canBeClosen() {
			// TODO Auto-generated method stub
			return false;
		}
	}

}
