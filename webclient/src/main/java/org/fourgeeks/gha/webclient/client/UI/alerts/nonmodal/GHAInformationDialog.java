package org.fourgeeks.gha.webclient.client.UI.alerts.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessage;
import org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog;

import com.smartgwt.client.widgets.Button;

/**
 * @author jfuentes
 *
 */
public class GHAInformationDialog extends GHADialog {

	/**
	 * @param ghaMessage
	 */
	public GHAInformationDialog(GHAMessage ghaMessage){
		//TODO: decodificador por el GHAMessage
		super(DialogType.INFORMATION,false,false);
	}

	/**
	 * @param ghaMessage
	 * @param buttons 
	 */
	public GHAInformationDialog(GHAMessage ghaMessage,Button... buttons){
		//TODO: decodificador por el GHAMessage
		super(DialogType.INFORMATION,false,false,buttons);
	}

	/**
	 * @param message
	 */
	public GHAInformationDialog(String message) {
		super(DialogType.INFORMATION,false,false);
		setMessage(message);
	}

	/**
	 * @param message
	 * @param buttons 
	 */
	public GHAInformationDialog(String message, Button... buttons) {
		super(DialogType.INFORMATION,false,false,buttons);
		setMessage(message);
	}

	/**
	 * @param title 
	 * @param message
	 */
	public GHAInformationDialog(String title, String message) {
		this(message);
		setTitle(title);
	}

	/**
	 * @param title 
	 * @param message
	 * @param buttons 
	 */
	public GHAInformationDialog(String title, String message, Button... buttons) {
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
