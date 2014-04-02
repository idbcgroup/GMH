package org.fourgeeks.gha.webclient.client.UI.pmewindows.nonmodal;

import org.fourgeeks.gha.domain.msg.GHAMessageType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.pmewindows.GHADialog;

/**
 * @author jfuentes
 * 
 */
public class GHAAdvanceDialog extends GHADialog {
	/**
	 * @param type
	 * @param message
	 * @param time
	 */
	public GHAAdvanceDialog(GHAMessageType type, String message, int time) {
		super(type, time);
		setMessage(message);
		initTypeView();
	}

	/**
	 * @param type
	 * @param title
	 * @param message
	 * @param time
	 */
	public GHAAdvanceDialog(GHAMessageType type, String title, String message,
			int time) {
		this(type, message, time);
		setTitle(title);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fourgeeks.gha.webclient.client.UI.alerts.GHADialog#initTypeParams()
	 */
	@Override
	protected void initTypeView() {
		setTitle(GHAStrings.get("processsing"));
		setBorder("1px solid #AAC475");
		setBackgroundColor("#AAC475");
		setBodyColor("#D4E1BA");
		setIcon("../resources/icons/msgIT/process.png");
	}
}
