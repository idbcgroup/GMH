package org.fourgeeks.gha.ejb.language;

import java.util.List;

import javax.ejb.Remote;

import org.fourgeeks.gha.domain.msg.UiString;

/**
 * @author alacret
 * 
 */
@Remote
public interface LanguageServiceRemote {

	/**
	 * @return the language list of strings
	 */
	public List<UiString> getLanguageStringsList();

}
