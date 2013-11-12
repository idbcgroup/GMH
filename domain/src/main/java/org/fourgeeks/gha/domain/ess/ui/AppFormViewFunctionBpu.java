package org.fourgeeks.gha.domain.ess.ui;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Entity
@Table(name = "AppFormViewFunctionBpu")
@IdClass(AppFormViewFunctionBpuId.class)
@NamedQueries(value = { @NamedQuery(name = "AppFormViewFunctionBpu.findByBpu", query = "SELECT e from AppFormViewFunctionBpu e WHERE e.bpu = :bpu") })
public class AppFormViewFunctionBpu implements Serializable {

	/**
	 * @param bpu
	 * @param appForm
	 * @param view
	 * @param function
	 */
	public AppFormViewFunctionBpu(Bpu bpu, AppForm appForm, View view,
			Function function) {
		super();
		this.bpu = bpu;
		this.appForm = appForm;
		this.view = view;
		this.function = function;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull(message = "bpu-not-null")
	private Bpu bpu;
	@Id
	@NotNull(message = "app-not-null")
	private AppForm appForm;
	@Id
	@NotNull(message = "view-not-null")
	private View view;
	@Id
	@NotNull(message = "function-not-null")
	private Function function;

	/**
	 * 
	 */
	public AppFormViewFunctionBpu() {
	}

	/**
	 * @return the bpu
	 */
	public Bpu getBpu() {
		return bpu;
	}

	/**
	 * @param bpu
	 *            the bpu to set
	 */
	public void setBpu(Bpu bpu) {
		this.bpu = bpu;
	}

	/**
	 * @return the appForm
	 */
	public AppForm getAppForm() {
		return appForm;
	}

	/**
	 * @param appForm
	 *            the appForm to set
	 */
	public void setAppForm(AppForm appForm) {
		this.appForm = appForm;
	}

	/**
	 * @return the view
	 */
	public View getView() {
		return view;
	}

	/**
	 * @param view
	 *            the view to set
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * @return the function
	 */
	public Function getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            the function to set
	 */
	public void setFunction(Function function) {
		this.function = function;
	}
}
