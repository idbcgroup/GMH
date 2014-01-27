package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHANewMessageDialog extends GHADialog {

	/**
	 * @param ghaMessage
	 */
	public GHANewMessageDialog(GHAMessage ghaMessage){
		//TODO: decodificador por el GHAMessage
		super(DialogType.NEW_MESSAGE,false,false);
	}

	/**
	 * @param ghaMessage
	 * @param buttons 
	 */
	public GHANewMessageDialog(GHAMessage ghaMessage,Button... buttons){
		//TODO: decodificador por el GHAMessage
		super(DialogType.NEW_MESSAGE,false,false,buttons);
	}

	/**
	 * @param message
	 */
	public GHANewMessageDialog(String message) {
		super(DialogType.NEW_MESSAGE,false,false);
		setMessage(message);
	}

	/**
	 * @param message
	 * @param buttons 
	 */
	public GHANewMessageDialog(String message, Button... buttons) {
		super(DialogType.NEW_MESSAGE,false,false,buttons);
		setMessage(message);
	}

	/**
	 * @param title 
	 * @param message
	 */
	public GHANewMessageDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title 
	 * @param message
	 * @param buttons 
	 */
	public GHANewMessageDialog(String title, String message, Button... buttons) {
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
