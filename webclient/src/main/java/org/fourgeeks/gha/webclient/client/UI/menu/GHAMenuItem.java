package org.fourgeeks.gha.webclient.client.UI.menu;

class GHAMenuItem {

	private String code;
	private String text;
	private String icon;
	private String token;

	public String getCode() {
		return code;
	}

	public String getIcon() {
		return icon;
	}

	public String getText() {
		return text;
	}

	public String getToken() {
		return token;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public void setIcon(final String icon) {
		this.icon = icon;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public void setToken(final String token) {
		this.token = token;
	}
}
