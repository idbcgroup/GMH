package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
@NamedQueries(value = { @NamedQuery(name = "Function.getAll", query = "SELECT e from Function e order by e.name") })
public class Function extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	@ManyToOne
	private View view;

	/**
	 * 
	 */
	public Function() {
	}

	/**
	 * @param view
	 * @param name
	 * @param code
	 */
	public Function(View view, String name, String code) {
		this.view = view;
		this.name = name;
		this.code = code;
	}

	/**
	 * @return the name of the function
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

}