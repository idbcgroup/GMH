package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHASuccessDialog extends GHADialog {

	/**
	 * @param ghaMessage
	 */
	public GHASuccessDialog(GHAMessage ghaMessage){
		//TODO: decodificador por el GHAMessage
		super(DialogType.SUCCESS,false,false);
	}

	/**
	 * @param ghaMessage
	 * @param buttons 
	 */
	public GHASuccessDialog(GHAMessage ghaMessage,Button... buttons){
		//TODO: decodificador por el GHAMessage
		super(DialogType.SUCCESS,false,false,buttons);
	}

	/**
	 * @param message
	 */
	public GHASuccessDialog(String message) {
		super(DialogType.SUCCESS,false,false);
		setMessage(message);
	}

	/**
	 * @param message
	 * @param buttons 
	 */
	public GHASuccessDialog(String message, Button... buttons) {
		super(DialogType.SUCCESS,false,false,buttons);
		setMessage(message);
	}

	/**
	 * @param title 
	 * @param message
	 */
	public GHASuccessDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title 
	 * @param message
	 * @param buttons 
	 */
	public GHASuccessDialog(String title, String message, Button... buttons) {
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
