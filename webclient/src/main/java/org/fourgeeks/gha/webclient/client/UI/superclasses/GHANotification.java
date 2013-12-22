package org.fourgeeks.gha.webclient.client.UI.superclasses;

import java.util.List;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.icons.GHAImgButton;
import org.fourgeeks.gha.webclient.client.UI.interfaces.ClosableListener;
import org.fourgeeks.gha.webclient.client.UI.interfaces.HideCloseAction;
import org.fourgeeks.gha.webclient.client.message.GWTMessageService;
import org.fourgeeks.gha.webclient.client.message.GWTMessageServiceAsync;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.types.Positioning;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.ButtonClickEvent;
import com.smartgwt.client.widgets.events.ButtonClickHandler;
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

	/**
	 * 
	 */
	public static final ModalNotification modalNotification = new ModalNotification();

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
	 * @param title
	 * @param message
	 * @param buttonYesHandler
	 * @param buttonNoHandler
	 * @param buttonCancelHandler
	 *            the cancelbutton handler, or null if you just want the dialog
	 *            to disapear
	 */
	public static void askYesNoCancel(final String title, String message,
			final ClickHandler buttonYesHandler,
			final ClickHandler buttonNoHandler,
			final ClickHandler buttonCancelHandler) {
		final Dialog dialog = new Dialog();
		dialog.setMessage(message);
		dialog.setIcon("[SKIN]ask.png");
		Button buttonYes = new Button(GHAStrings.get("yes"));
		if (buttonYesHandler != null)
			buttonYes.addClickHandler(buttonYesHandler);
		Button buttonNo = new Button(GHAStrings.get("no"));
		if (buttonNoHandler != null)
			buttonNo.addClickHandler(buttonNoHandler);
		Button buttonCancel = new Button(GHAStrings.get("cancel"));
		if (buttonCancelHandler != null)
			buttonCancel.addClickHandler(buttonCancelHandler);
		dialog.setButtons(buttonYes, buttonNo, buttonCancel);
		dialog.addButtonClickHandler(new ButtonClickHandler() {

			@Override
			public void onButtonClick(ButtonClickEvent event) {
				dialog.hide();
				dialog.destroy();
			}
		});
		dialog.show();
	}

	/**
	 * @param message
	 */
	@Deprecated
	public static void oldAlert(String message) {
		SC.say(GHAStrings.get("information"), message);
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
				SC.say(GHAStrings.get("information"), result.getText());
			}
		});

	}

	/**
	 * this method receives a key to find and show the message from database
	 * 
	 * @param key
	 */
	public static void info(String key) {
		messageService.find(key, new GHAAsyncCallback<GHAMessage>() {

			@Override
			public void onSuccess(GHAMessage result) {
				SC.say(GHAStrings.get("information"), result.getText());
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
				SC.say(GHAStrings.get("information"), builder.toString());
			}
		});
	}

	/**
	 * @param ghaMessage
	 */
	public static void alert(GHAMessage ghaMessage) {
		SC.say(ghaMessage.getText());
	}

	/**
	 * @author alacret
	 * 
	 */
	public static class ModalNotification extends VLayout implements
			ResizeHandler, ClosableListener {

		private final int width = 700;
		private final RootPanel backDivPanel = RootPanel
				.get("notificationsBackDiv");
		private GHALabel titleText;
		private GHALabel errorText;
		{
			titleText = new GHALabel("Información");
			// titleText.setBackgroundColor("#CBCBCB");
			errorText = new GHALabel("default error text");
			errorText.setStyleName("text-label");
			errorText.setHeight("*");
		}

		/**
		 * 
		 */
		public ModalNotification() {
			super();
			GHAUiHelper.addGHAResizeHandler(this);

			setWidth(width);
			setHeight("*");
			setLeft((Window.getClientWidth() / 2) - (width / 2));
			// setTop(140);
			setPosition(Positioning.ABSOLUTE);
			setBackgroundColor("#E0E0E0");
			setBorder("1px solid #E0E0E0");
			setDefaultLayoutAlign(Alignment.CENTER);
			setMembersMargin(10);
			setVisible(false);
			setAnimateTime(400);

			setShadowDepth(4);
			setShowShadow(true);
			setZIndex(444444);

			VLayout textLayout = new VLayout();
			textLayout.setHeight("*");
			// textLayout.setWidth100();
			// textLayout.setStyleName("sides-padding padding-top padding-bot");
			// textLayout.setAlign(Alignment.CENTER);
			textLayout.setDefaultLayoutAlign(Alignment.CENTER);
			textLayout.setMembersMargin(10);
			textLayout.addMembers(titleText, errorText);

			GHAImgButton acceptButton = new GHAImgButton("Aceptar",
					new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							close();
						}
					});

			VLayout buttonsLayout = new VLayout();
			buttonsLayout.setHeight("*");
			buttonsLayout.setWidth("100px");
			buttonsLayout.setMembersMargin(10);
			buttonsLayout.setAlign(VerticalAlignment.BOTTOM);
			buttonsLayout.setDefaultLayoutAlign(Alignment.CENTER);
			buttonsLayout.addMembers(acceptButton);

			HLayout mainLayout = new HLayout();
			mainLayout.setHeight("*");
			mainLayout.setWidth100();
			mainLayout.setMembersMargin(10);
			mainLayout.setBackgroundColor("#E0E0E0");
			mainLayout.setStyleName("sides-padding padding-top padding-bot");

			mainLayout.addMembers(textLayout, buttonsLayout);

			addMembers(mainLayout, GHAUiHelper.verticalGraySeparatorImgBar(
					"../resources/icons/notifications/closerBar.png", 18, 12,
					12));
		}

		@Override
		public void onResize(ResizeEvent event) {
			setLeft((Window.getClientWidth() / 2) - (width / 2));
		}

		@Override
		public void close() {
			backDivPanel.removeStyleName("dim");
			backDivPanel.getElement().getStyle().setZIndex(-80000);

			animateHide(AnimationEffect.SLIDE);
		}

		@Override
		public void show() {
			animateShow(AnimationEffect.SLIDE);
			backDivPanel.addStyleName("dim");
			backDivPanel.getElement().getStyle().setZIndex(getZIndex() - 1);
		}

		/**
		 * @param title
		 * @param innerText
		 */
		public void show(String title, String innerText) {
			setText(title, innerText);
			show();
		}

		private void setText(String tit, String text) {
			titleText.setContents(tit);
			errorText.setContents(text);
		}

		@Override
		public boolean canBeClosen(HideCloseAction hideAction) {
			return true;
		}
	}
}
