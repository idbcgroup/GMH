package org.fourgeeks.gha.webclient.client.UI.alerts;

import com.google.gwt.user.client.Timer;
import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHANonModalDialog extends GHADialog {
	
	private boolean isTimed;
	private final int DEFAULT_WAITING_TIME = 5000;
	private Timer waiter = new Timer() {	
		@Override
		public void run() {
			close();
		}
	};
	
	/**
	 * @param type 
	 * @param timed 
	 */
	public GHANonModalDialog(DialogType type, boolean timed) {
		super(false);
		this.isTimed = timed;
		this.dialogType = type;
		setDialogIcon();
		if(isTimed)
			waiter.schedule(DEFAULT_WAITING_TIME);
	}
	
	/**
	 * @param type 
	 * @param timed 
	 * @param buttons 
	 */
	public GHANonModalDialog(DialogType type, boolean timed, Button... buttons) {
		super(false);
		this.isTimed = timed;
		this.dialogType = type;
		setDialogIcon();
		if(isTimed)
			waiter.schedule(DEFAULT_WAITING_TIME);
		
		setButtons(buttons);
	}

	/**
	 * @param type 
	 * @param timed 
	 * @param message
	 */
	public GHANonModalDialog(DialogType type, boolean timed, String message) {
		this(type, timed);
		setMessage(message);
		show();
	}

	/**
	 * @param type 
	 * @param timed 
	 * @param message
	 * @param buttons 
	 */
	public GHANonModalDialog(DialogType type, boolean timed, String message, Button... buttons) {
		this(type, timed, buttons);
		setMessage(message);
		show();
	}

	/**
	 * @param type 
	 * @param timed 
	 * @param title
	 * @param message
	 */
	public GHANonModalDialog(DialogType type, boolean timed, String title, String message) {
		this(type, timed, message);
		setTitle(title);
		show();
	}

	/**
	 * @param type 
	 * @param timed 
	 * @param title
	 * @param message
	 * @param buttons 
	 */
	public GHANonModalDialog(DialogType type, boolean timed, String title, String message, Button... buttons) {
		this(type, timed, message, buttons);
		setTitle(title);
		show();
	}
}
