package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAProgressDialog extends GHADialog {

	/**
	 * @param ghaMessage
	 */
	public GHAProgressDialog(GHAMessage ghaMessage){
		//TODO: decodificador por el GHAMessage
		super(DialogType.PROCESSING,false,true);
	}

	/**
	 * @param ghaMessage
	 * @param buttons 
	 */
	public GHAProgressDialog(GHAMessage ghaMessage,Button... buttons){
		//TODO: decodificador por el GHAMessage
		super(DialogType.PROCESSING,false,true,buttons);
	}

	/**
	 * @param message
	 */
	public GHAProgressDialog(String message) {
		super(DialogType.PROCESSING,false,true);
		setMessage(message);
	}

	/**
	 * @param message
	 * @param buttons 
	 */
	public GHAProgressDialog(String message, Button... buttons) {
		super(DialogType.PROCESSING,false,true,buttons);
		setMessage(message);
	}

	/**
	 * @param title 
	 * @param message
	 */
	public GHAProgressDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title 
	 * @param message
	 * @param buttons 
	 */
	public GHAProgressDialog(String title, String message, Button... buttons) {
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
