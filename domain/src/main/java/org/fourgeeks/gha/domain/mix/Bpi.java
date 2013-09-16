package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.BpiInstitutionRelationTypeEnum;
import org.fourgeeks.gha.domain.enu.BpiOriginEnum;
import org.fourgeeks.gha.domain.enu.BpiRiskEnum;
import org.fourgeeks.gha.domain.enu.BpiTypeEnum;

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Bpi.getAll", query = "SELECT e from Bpi e order by e.name") })
public class Bpi extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "institutionFk", nullable = false)
	private Institution institution;

	/** Attributes */

	private String name;
	/** Nombre de la institución length =255 */
	private String bpiLogoNameRoute;
	/** Ruta y Nombre del Logo de la institución length =255 */

	private BpiTypeEnum type;
	/** Tipo de Institución length =20 */

	private String description;
	/** Información descriptiva de la institución (Opcional) length =255 */

	private BpiInstitutionRelationTypeEnum bpiInstitutionRelationship;
	/** Relacion entre las Instituciones length =20 */

	private BpiOriginEnum bpiOrigin;
	/** Origen de la Institución length =20 */
	private String healthCode;
	/** Código asignado a la Institución a niivel Nacional length =255 */
	private String taxCode;
	/** Código FISCAL asignado a la Institución length =255 */
	private String legalEntityId;
	/** UEID length =16 */
	private String bpiSector;
	/** Sector Público o Privado length =20 */
	private String bpiProductiveSector;
	/** Sector Productivo length =255 */
	private String bpiPrincipalProduct;
	/** Producto Primario o principal length =255 */
	private Short bpiParametDefaultValues;
	/**
	 * Utilizar para este BPI los valores por defecto que estan en la tabla
	 * GomPARAMET_DEFINITION length =1
	 */

	private BpiRiskEnum bpiRisk;
	/** Riesgo Institución length =60 */
	private Long institutionId;

	/** <FKEY> Id de la institución length =19 */
	/**
	 * @return the institution
	 */
	public Institution getInstitution() {
		return institution;
	}

	/**
	 * @param institution
	 *            the institution to set
	 */
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

}
