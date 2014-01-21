package org.fourgeeks.gha.webclient.client.UI.alerts;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;

/**
 * @author jfuentes
 *
 */
public class GHADialog extends Dialog implements ResizeHandler{
	/**
	 * @author jfuentes
	 *
	 */
	public enum DialogType {
		/**
		 * Type for a Say Dialog. Just messages, no buttons, can be timed or untimed.
		 */
		SAY, 
		/**
		 * Type for an information dialog. Non-modal messages with a button for confirmation (ok button).
		 */
		INFORMATION,
		/**
		 * Type for a confirmation dialog. Usually a question with 2 options: accept, cancel.
		 */
		CONFIRMATION,
		/**
		 * Type for an Ask yes/no dialog. Usually a question with 3 options: yes, no, cancel.
		 */
		ASKYESNO,
		/**
		 * Type for a warning dialog. Non-modal message without timer and one button: ok.
		 */
		WARNING,
		/**
		 * Type for a soft error dialog. Modal window that informs a normal error.
		 */
		ERROR_SOFT,
		/**
		 * Type for a hard error dialog. Modal window that crashes the application.
		 */
		ERROR_HARD;
	}


	protected boolean hasButtons;
	protected final int DEFAULT_ANIMATION_TIME = 400;
	protected DialogType dialogType;

	/**
	 * Creates a GHADialog.
	 * @param buttons 
	 */
	public GHADialog(boolean buttons) {
		super();
		hasButtons=buttons;
		setDialogIcon();
		GHAUiHelper.addGHAResizeHandler(this);

		setOverflow(Overflow.AUTO);
		setAutoCenter(false);
		setShowMinimizeButton(false);
		setShowMaximizeButton(false);
		setAnimateTime(DEFAULT_ANIMATION_TIME);

		setBorder("1px solid #666666");
		setBackgroundColor("#666666");
		setStyleName("dialogStyle");
		setBodyStyle("dialogBodyStyle");

		setTitle(GHAStrings.get("information"));
		setMessage("");

		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				close();
			}
		});		
	}

	/**
	 * Create a GHADialog specifying its message.
	 * @param hasButtons 
	 * @param message
	 */
	public GHADialog(boolean hasButtons, String message) {
		this(hasButtons);
		setMessage(message);
		show();
	}

	/**
	 * Create a GHADialog specifying the title and the message.
	 * @param hasButtons 
	 * @param title
	 * @param message
	 */
	public GHADialog(boolean hasButtons, String title, String message) {
		this(hasButtons,message);
		setTitle(title);
		show();
	}

	/**
	 * Closes and destroy the window
	 */
	public void close(){
		animateRect(null, Window.getClientHeight(), null, null, new AnimationCallback() {
			@Override
			public void execute(boolean earlyFinish) {
				hide();
				destroy();
			}
		},DEFAULT_ANIMATION_TIME);
	}

	@Override
	public void onResize(ResizeEvent event) {
		if(isVisible()){
			if(hasButtons){
				setLeft(Window.getClientWidth()-(getWidth()+50));
				setTop(Window.getClientHeight()-GHAUiHelper.DEFAULT_NOTIFICATION_BUTTONS_HEIGHT-7);
			}else{
				setLeft(Window.getClientWidth()-(getWidth()+50));
				setTop(Window.getClientHeight()-GHAUiHelper.DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT-7);
			}
		}
	}

	/**
	 * Sets the dialog icon, depending of is DialogType
	 */
	public void setDialogIcon() {
		if(dialogType == DialogType.SAY)
			setIcon("[SKIN]say.png");
		else if(dialogType == DialogType.INFORMATION)
			setIcon("[SKIN]notify.png");
		else if(dialogType == DialogType.CONFIRMATION)
			setIcon("[SKIN]confirm.png");
		else if(dialogType == DialogType.ASKYESNO)
			setIcon("[SKIN]ask.png");
		else if(dialogType == DialogType.WARNING)
			setIcon("[SKIN]warn.png");
		else if(dialogType == DialogType.ERROR_SOFT)
			setIcon("[SKIN]stop.png");
		else if(dialogType == DialogType.ERROR_HARD)
			setIcon("[SKIN]error.png");
	}

	@Override
	public void show() {
		super.show();

		getBody().setWidth(GHAUiHelper.DEFAULT_NOTIFICATION_WIDTH-20);
		setWidth(GHAUiHelper.DEFAULT_NOTIFICATION_WIDTH);
		setLeft(Window.getClientWidth()-(getWidth()+50));
		setTop(Window.getClientHeight());

		if(hasButtons){
			setHeight(GHAUiHelper.DEFAULT_NOTIFICATION_BUTTONS_HEIGHT);
			getBody().setHeight(GHAUiHelper.DEFAULT_NOTIFICATION_BUTTONS_HEIGHT-27);
			animateRect(null, Window.getClientHeight()-GHAUiHelper.DEFAULT_NOTIFICATION_BUTTONS_HEIGHT, null, null);
		}else{
			setHeight(GHAUiHelper.DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT);
			getBody().setHeight(GHAUiHelper.DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT-27);
			animateRect(null, Window.getClientHeight()-GHAUiHelper.DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT, null, null);
		}		
	}
}
