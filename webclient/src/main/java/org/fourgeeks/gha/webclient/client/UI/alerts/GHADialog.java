package org.fourgeeks.gha.webclient.client.UI.alerts;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.AnimationCallback;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.events.MouseOutEvent;
import com.smartgwt.client.widgets.events.MouseOutHandler;
import com.smartgwt.client.widgets.events.MouseOverEvent;
import com.smartgwt.client.widgets.events.MouseOverHandler;
import com.smartgwt.client.widgets.layout.LayoutSpacer;

/**
 * @author jfuentes
 * 
 */
public abstract class GHADialog extends Dialog implements ResizeHandler {
	/**
	 * @author jfuentes
	 * 
	 */
	public enum DialogType {
		/**
		 * Type for a Say Dialog. Just messages, no buttons, can be timed or
		 * untimed.
		 */
		SAY,
		/**
		 * Type for a confirmation dialog. Gray, Modal, Untimed messages.
		 * Usually a question with 2 options: accept, cancel.
		 */
		CONFIRMATION,
		/**
		 * Type for an Ask yes/no dialog. Gray, Modal, Untimed messages. Usually
		 * a question with 3 options: yes, no, cancel.
		 */
		ASKYESNO,
		/**
		 * Type for a hard error dialog. Red, modal, untimed window that informs
		 * about a hard error. Has an accept and a copy button.
		 */
		ERROR_HARD,
		/**
		 * Type for a soft error dialog. Yellow, modal untimed message that
		 * informs about a soft error. Has an accept and a copy button.
		 */
		ERROR_SOFT,
		/**
		 * Type for a warning dialog. Blue, Non-modal, (optionally) timed
		 * message without buttons.
		 */
		WARNING,
		/**
		 * Type for an information dialog. Blue, Non-modal, (optionally) timed
		 * messages without buttons, for delivering info.
		 */
		INFORMATION,
		/**
		 * Type for a process failure dialog. Yellow, Non-modal, (optionally)
		 * timed, failure messages without buttons.
		 */
		FAILURE,
		/**
		 * Type for a process success dialog. Green, Non-modal, (optionally)
		 * timed, success messages without buttons.
		 */
		SUCCESS,
		/**
		 * Type for an operation-in-process dialog. Green, Non-modal,
		 * (optionally) timed messages without buttons, for giving feedback. can
		 * be minimized and can't be closed. the dialog closes when the
		 * operation finishes.
		 */
		PROCESSING,
		/**
		 * Type for an new_message dialog. Gray, Non-modal, (optionally) timed
		 * messages without buttons, for telling when a message arrives.
		 */
		NEW_MESSAGE;
	}

	private final int HEADER_HEIGHT = 20;
	private final int FOOTER_HEIGHT = 15;
	private final int RIGHT_MARGIN = 40;
	private final int BORDER_SEPARATION = 7;

	protected boolean hasButtons;
	protected DialogType dialogType;
	protected boolean isModal;
	protected boolean isTimed;

	private final int DEFAULT_WAITING_TIME = 4000;
	private final Timer waiter = new Timer() {
		@Override
		public void run() {
			close();
		}
	};
	protected final int DEFAULT_ANIMATION_TIME = 400;

	/**
	 * Creates a GHADialog without buttons, with the specifying parameters.
	 * 
	 * @param type
	 * @param timed
	 * @param modal
	 * @param hasCloseButton
	 * @param canMinimize
	 */
	public GHADialog(DialogType type, boolean hasCloseButton,
			boolean canMinimize) {
		super();
		init(false, type);

		// ---Init
		setWidth(GHAUiHelper.DEFAULT_NOTIFICATION_WIDTH);
		setHeaderStyle("dialogHeaderStyle");
		setStyleName("dialogStyle");
		setBodyStyle("dialogBodyStyle");
		setAutoSize(false);
		setAutoCenter(false);
		setAnimateTime(DEFAULT_ANIMATION_TIME);

		// ---Modal & Timing Conf.
		if (isModal) {
			setIsModal(true);
			setShowModalMask(true);
			setModalMaskOpacity(40);
		} else if (!hasButtons && dialogType != DialogType.PROCESSING) {
			if (isTimed) {
				waiter.schedule(DEFAULT_WAITING_TIME);
			}
			addMouseOverHandler(new MouseOverHandler() {
				@Override
				public void onMouseOver(MouseOverEvent event) {
					waiter.cancel();
				}
			});
			addMouseOutHandler(new MouseOutHandler() {
				@Override
				public void onMouseOut(MouseOutEvent event) {
					waiter.schedule(DEFAULT_WAITING_TIME - 1000);
				}
			});
		}
		// ---Header Controls
		setShowHeaderIcon(true);
		setHeaderIcon("../resources/icons/favicon.ico");
		setShowMinimizeButton(canMinimize);
		setShowCloseButton(hasCloseButton);
		setShowMaximizeButton(false);

		// ---Foooter controls
		setShowFooter(true);
		Label function = new Label("Función");
		function.setStyleName("windowFooterText");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		// Label date = new
		// Label(DateFormat.getDateInstance(DateFormat.TIMEZONE_FIELD).format(new
		// Date()));
		Label date = new Label(sdf.format(new Date()));
		date.setWidth(120);
		date.setStyleName("windowFooterText");
		setFooterControls(function, new LayoutSpacer(), date);
		setFooterHeight(FOOTER_HEIGHT);

		// ---Contents
		setTitle(GHAStrings.get("information"));
		setMessage("");

		// ---Handlers
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				close();
			}
		});
	}

	/**
	 * Creates a GHADialog with buttons, with the specifying parameters.
	 * 
	 * @param type
	 * @param timed
	 * @param modal
	 * @param hasCloseButton
	 * @param canMinimize
	 * @param buttons
	 */
	public GHADialog(DialogType type, boolean hasCloseButton,
			boolean canMinimize, Button... buttons) {
		super();
		init(true, type);

		// ---Init
		setWidth(GHAUiHelper.DEFAULT_NOTIFICATION_WIDTH);
		setHeaderStyle("dialogHeaderStyle");
		setStyleName("dialogStyle");
		setBodyStyle("dialogBodyStyle");
		setAutoSize(false);
		setAutoCenter(false);
		setAnimateTime(DEFAULT_ANIMATION_TIME);

		// ---Modal & Timing Conf.
		if (isModal) {
			setIsModal(true);
			setShowModalMask(true);
			setModalMaskOpacity(40);
		} else if (!hasButtons && dialogType != DialogType.PROCESSING) {
			if (isTimed) {
				waiter.schedule(DEFAULT_WAITING_TIME);
			}
			addMouseOverHandler(new MouseOverHandler() {
				@Override
				public void onMouseOver(MouseOverEvent event) {
					waiter.cancel();
				}
			});
			addMouseOutHandler(new MouseOutHandler() {
				@Override
				public void onMouseOut(MouseOutEvent event) {
					waiter.schedule(DEFAULT_WAITING_TIME - 1000);
				}
			});
		}
		// ---Header Controls
		setShowHeaderIcon(true);
		setHeaderIcon("../resources/icons/favicon.ico");
		setShowMinimizeButton(canMinimize);
		setShowCloseButton(hasCloseButton);
		setShowMaximizeButton(false);

		// ---Foooter controls
		setShowFooter(true);
		Label function = new Label("Función");
		function.setStyleName("windowFooterText");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		// Label date = new
		// Label(DateFormat.getDateInstance(DateFormat.TIMEZONE_FIELD).format(new
		// Date()));
		Label date = new Label(sdf.format(new Date()));
		date.setWidth(120);
		date.setStyleName("windowFooterText");
		setFooterControls(function, new LayoutSpacer(), date);
		setFooterHeight(FOOTER_HEIGHT);

		// ---Contents
		setTitle(GHAStrings.get("information"));
		setMessage("");

		// ---Buttons
		if (hasButtons) {
			setButtons(buttons);
		}
		// ---Handlers
		addCloseClickHandler(new CloseClickHandler() {
			@Override
			public void onCloseClick(CloseClickEvent event) {
				close();
			}
		});
	}

	/**
	 * Closes and destroy the window
	 */
	public void close() {
		animateRect(null, Window.getClientHeight(), null, null,
				new AnimationCallback() {
					@Override
					public void execute(boolean earlyFinish) {
						hide();
						destroy();
					}
				}, DEFAULT_ANIMATION_TIME);
	}

	private void init(boolean buttons, DialogType type) {
		GHAUiHelper.addGHAResizeHandler(this);
		hasButtons = buttons;
		dialogType = type;
		initTypeParameters();
	}

	/**
	 * Sets the dialog icon, depending of is DialogType
	 */
	public void initTypeParameters() {
		if (dialogType == DialogType.SAY) {
			// Gray
			isTimed = true;
			isModal = false;
			setBorder("1px solid #BCBCBC");
			setBackgroundColor("#BCBCBC");
			setBodyColor("#EFEFEF");
			setIcon("../resources/icons/msgIT/say.png");
		} else if (dialogType == DialogType.CONFIRMATION) {
			// Gray
			isTimed = false;
			isModal = true;
			setBorder("1px solid #BCBCBC");
			setBackgroundColor("#BCBCBC");
			setBodyColor("#FFFFFF");
			setIcon("../resources/icons/msgIT/say.png");
		} else if (dialogType == DialogType.ASKYESNO) {
			// Gray
			isTimed = false;
			isModal = true;
			setBorder("1px solid #BCBCBC");
			setBackgroundColor("#BCBCBC");
			setBodyColor("#EFEFEF");
			setIcon("../resources/icons/msgIT/ask.png");
		} else if (dialogType == DialogType.ERROR_HARD) {
			// Red
			isTimed = false;
			isModal = false;
			setBorder("1px solid #FC7A7E");
			setBackgroundColor("#FC7A7E");
			setBodyColor("#FCC2C3");
			setIcon("../resources/icons/msgIT/error.png");
		} else if (dialogType == DialogType.ERROR_SOFT) {
			// Yellow
			isTimed = false;
			isModal = false;
			setBorder("1px solid #FCD14A");
			setBackgroundColor("#FCD14A");
			setBodyColor("#FCE499");
			setIcon("../resources/icons/msgIT/error.png");
		} else if (dialogType == DialogType.WARNING) {
			// Blue
			isTimed = false;
			isModal = false;
			setBorder("1px solid #8CB1E0");
			setBackgroundColor("#8CB1E0");
			setBodyColor("#D9E3EF");
			setIcon("../resources/icons/msgIT/warn.png");
		} else if (dialogType == DialogType.INFORMATION) {
			// Blue
			isTimed = true;
			isModal = false;
			setBorder("1px solid #8CB1E0");
			setBackgroundColor("#8CB1E0");
			setBodyColor("#D9E3EF");
			setIcon("../resources/icons/msgIT/info.png");
		} else if (dialogType == DialogType.FAILURE) {
			// Yellow
			isTimed = false;
			isModal = false;
			setBorder("1px solid #FCD14A");
			setBackgroundColor("#FCD14A");
			setBodyColor("#FCE499");
			setIcon("../resources/icons/msgIT/warn.png");
		} else if (dialogType == DialogType.SUCCESS) {
			// Green
			isTimed = true;
			isModal = false;
			setBorder("1px solid #AAC475");
			setBackgroundColor("#AAC475");
			setBodyColor("#D4E1BA");
			setIcon("../resources/icons/msgIT/check.png");
		} else if (dialogType == DialogType.PROCESSING) {
			// Green
			isTimed = false;
			isModal = false;
			setBorder("1px solid #AAC475");
			setBackgroundColor("#AAC475");
			setBodyColor("#D4E1BA");
			setIcon("../resources/icons/msgIT/process.png");
		} else if (dialogType == DialogType.NEW_MESSAGE) {
			// Gray
			isTimed = true;
			isModal = false;
			setBorder("1px solid #BCBCBC");
			setBackgroundColor("#BCBCBC");
			setBodyColor("#EFEFEF");
			setIcon("../resources/icons/msgIT/newmsg.png");
		}

	}

	protected abstract void initTypeParams();

	@Override
	public void onResize(ResizeEvent event) {
		if (isVisible()) {
			if (hasButtons) {
				setLeft(Window.getClientWidth() - (getWidth() + RIGHT_MARGIN));
				setTop(Window.getClientHeight()
						- (getHeight() + BORDER_SEPARATION));
			} else {
				setLeft(Window.getClientWidth() - (getWidth() + RIGHT_MARGIN));
				setTop(Window.getClientHeight()
						- (getHeight() + BORDER_SEPARATION));
			}
		}
	}

	@Override
	public void show() {
		super.show();

		getMessageStack().setOverflow(Overflow.AUTO);
		setLeft(Window.getClientWidth() - (getWidth() + RIGHT_MARGIN));
		setTop(Window.getClientHeight());

		if (hasButtons) {
			// Window.alert("haz buttons!");
			setHeight(GHAUiHelper.DEFAULT_NOTIFICATION_BUTTONS_HEIGHT);
			setMaxHeight(GHAUiHelper.DEFAULT_NOTIFICATION_BUTTONS_HEIGHT);
			getBody().setHeight(
					GHAUiHelper.DEFAULT_NOTIFICATION_BUTTONS_HEIGHT
							- (HEADER_HEIGHT + BORDER_SEPARATION));
			animateRect(
					null,
					Window.getClientHeight()
							- (GHAUiHelper.DEFAULT_NOTIFICATION_BUTTONS_HEIGHT + BORDER_SEPARATION),
					null, null);
		} else {
			// Window.alert("doesnt haz buttons!");
			setHeight(GHAUiHelper.DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT);
			setMaxHeight(GHAUiHelper.DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT);
			getBody().setHeight(
					GHAUiHelper.DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT
							- (HEADER_HEIGHT + BORDER_SEPARATION));
			animateRect(
					null,
					Window.getClientHeight()
							- (GHAUiHelper.DEFAULT_NOTIFICATION_NOBUTTONS_HEIGHT + BORDER_SEPARATION),
					null, null);
		}
	}
}
