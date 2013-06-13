package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.BpiInstitutionRelationTypeEnum;
import org.fourgeeks.gha.domain.enu.BpiOriginEnum;
import org.fourgeeks.gha.domain.enu.BpiRiskEnum;
import org.fourgeeks.gha.domain.enu.BpiTypeEnum;

@Entity
public class Bpi extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "institutionFk")
	private Institution institution;

//	@ManyToMany(mappedBy = "bpis")
//	private Collection<Bpu> bpus;
	
//	@OneToMany(mappedBy = "bpi")
//	private Collection<BuildingLocation> buildingLocations;
	
//	@OneToMany(mappedBy = "bpi")
//	private Collection<Waio> waios;
	
//	@OneToMany(mappedBy = "bpi")
//	private Collection<Obu> obus;
	
//	@OneToMany(mappedBy = "bpi")
//	private Collection<ServiceProvider> serviceProviders;
	
	/**
	 * This represents the children collection of this bpi
	 */
//	@OneToMany(mappedBy = "parentBpi")
//	private Collection<BpiChild> bpiChildren;
	
	/**
	 * This represents the link relation to my parent (if any),
	 * semantically it says who is my bpiChild to refer to my bpi parent
	 */
//	@OneToOne(mappedBy = "bpi")
//	private BpiChild bpiChild;
	
	/**Attributes*/
	
	private String bpiName; /** Nombre de la institución length =255 */
	private String bpiLogoNameRoute; /** Ruta y Nombre del Logo de la institución length =255 */
	
	@Enumerated(EnumType.STRING)
	private BpiTypeEnum bpiType; /** Tipo de Institución length =20 */
	
	private String bpiDescription; /** Información descriptiva de la institución (Opcional) length =255 */
	
	@Enumerated(EnumType.STRING)
	private BpiInstitutionRelationTypeEnum bpiInstitutionRelationship; /** Relacion entre las Instituciones length =20 */
	
	@Enumerated(EnumType.STRING)
	private BpiOriginEnum bpiOrigin; /** Origen de la Institución length =20 */
	private String healthCode; /** Código asignado a la Institución a niivel Nacional length =255 */
	private String taxCode; /** Código FISCAL asignado a la Institución length =255 */
	private String legalEntityId; /** UEID length =16 */
	private String bpiSector; /** Sector Público o Privado length =20 */
	private String bpiProductiveSector; /** Sector Productivo length =255 */
	private String bpiPrincipalProduct; /** Producto Primario o principal length =255 */
	private Short bpiParametDefaultValues; /** Utilizar para este BPI los valores por defecto que estan en la tabla GomPARAMET_DEFINITION length =1 */
	
	@Enumerated(EnumType.STRING)
	private BpiRiskEnum bpiRisk; /** Riesgo Institución length =60 */
	private Long institutionId; /** <FKEY> Id de la institución length =19 */


}
