package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAWarningDialog extends GHADialog {

	/**
	 * @param ghaMessage
	 */
	public GHAWarningDialog(GHAMessage ghaMessage){
		//TODO: decodificador por el GHAMessage
		super(DialogType.WARNING,false,false);
	}

	/**
	 * @param ghaMessage
	 * @param buttons 
	 */
	public GHAWarningDialog(GHAMessage ghaMessage,Button... buttons){
		//TODO: decodificador por el GHAMessage
		super(DialogType.WARNING,false,false,buttons);
	}

	/**
	 * @param message
	 */
	public GHAWarningDialog(String message) {
		super(DialogType.WARNING,false,false);
		setMessage(message);
	}

	/**
	 * @param message
	 * @param buttons 
	 */
	public GHAWarningDialog(String message, Button... buttons) {
		super(DialogType.WARNING,false,false,buttons);
		setMessage(message);
	}

	/**
	 * @param title 
	 * @param message
	 */
	public GHAWarningDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title 
	 * @param message
	 * @param buttons 
	 */
	public GHAWarningDialog(String title, String message, Button... buttons) {
		this(message,buttons);
		setTitle(title);
	}

	/* (non-Javadoc)
	 * @see org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeParams() {
		// TODO Auto-generated method stub

	}
}
