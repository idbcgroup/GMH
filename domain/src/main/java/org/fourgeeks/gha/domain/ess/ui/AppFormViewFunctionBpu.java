package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.gar.Bpu;

/**
 * @author alacret
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "appFormFk",
		"viewFk", "functionFk", "bpuFk" }))
@NamedQueries(value = { @NamedQuery(name = "AppFormViewFunctionBpu.findByBpu", query = "SELECT e from AppFormViewFunctionBpu e WHERE e.bpu = :bpu order by e.function") })
public class AppFormViewFunctionBpu extends AbstractCodeEntity {

	/**
	 * @param bpu
	 * @param appForm
	 * @param view
	 * @param function
	 */
	public AppFormViewFunctionBpu(Bpu bpu, AppForm appForm, View view,
			Function function) {
		super();
		setCode(appForm.getCode() + view.getCode() + function.getCode()
				+ bpu.getId());
		this.bpu = bpu;
		this.appForm = appForm;
		this.view = view;
		this.function = function;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull(message = "bpu-not-null")
	@ManyToOne
	@JoinColumn(name = "bpuFk", nullable = false)
	private Bpu bpu;
	@NotNull(message = "app-not-null")
	@ManyToOne
	@JoinColumn(name = "appFormFk", nullable = false)
	private AppForm appForm;
	@NotNull(message = "view-not-null")
	@ManyToOne
	@JoinColumn(name = "viewFk", nullable = false)
	private View view;
	@NotNull(message = "function-not-null")
	@ManyToOne
	@JoinColumn(name = "functionFk", nullable = false, columnDefinition = "varchar(255) REFERENCES function(code) ON UPDATE CASCADE ON DELETE CASCADE")
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
