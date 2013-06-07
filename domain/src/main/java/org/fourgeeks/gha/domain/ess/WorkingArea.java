/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.gar.Waio;

/**
 * @author emiliot
 *
 */

@Entity
public class WorkingArea extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//TODO: pendiente por consulta
	@ManyToMany
	private Collection <RoleIt> itRoles;
	
	//TODO: Pendiente por consulta
	@ManyToMany
	private Collection <ViewFunction> viewFunctions;
	
	@ManyToOne
	@JoinColumn(name = "locationTypeFk")
	private LocationType locationType;
	
	@OneToMany(mappedBy = "workingArea")
	private Collection <Waio> waios;
}
