package org.fourgeeks.gha.domain.mix;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.AddressRelationTypeEnum;
import org.fourgeeks.gha.domain.enu.AddressTypeEnum;
import org.fourgeeks.gha.domain.enu.AvenueTypeEnum;
import org.fourgeeks.gha.domain.enu.PropertyTypeEnum;
import org.fourgeeks.gha.domain.enu.TelephoneTypeEnum;

@Entity
public class LegalEntityAddress extends AbstractEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "legalEntityFk")
	private LegalEntity legalEntity;
	
	/**Attributes*/
	private String state; /** Estado de la Dirección length =255 */
	private String country; /** País de la Dirección length =255 */
	
	@Enumerated(EnumType.STRING)
	private PropertyTypeEnum propertyType; /** Tipo de Propiedad length =60 Enu*/
	
	private String propertyName; /** length =255 */
	
	@Enumerated(EnumType.STRING)
	private TelephoneTypeEnum telephoneType; /** Tipo de Teléfono length =60 Enu*/
	
	private String countryCode; /** Código Telefónico del País length =10 */
	private String areaCode; /** Código de área Telefónico length =10 */
	private String telephoneNumber; /** Número de Teléfono length =16 */
	
	@Enumerated(EnumType.STRING)
	private AddressRelationTypeEnum addressRelationType; /** Tipo de Relación de Dirección length =60 Enu*/
	
	@Enumerated(EnumType.STRING)
	private AddressTypeEnum addressType; /** Tipo de Dirección length =60 Enu*/
	
	private String city; /** Ciudad de la Dirección length =255 Enu*/
	
	private String municipality; /** Municipío de la Dirección length =255 */
	private String parish; /** Parroquia de la Dirección length =255 */
	private String residentialZone; /** Zona Residencial length =255 */
	private String residentialZoneName; /** Nombre Zona Residencial length =255 */
	
	@Enumerated(EnumType.STRING)
	private AvenueTypeEnum avenueType; /** Tipo Avenida length =60 Enu*/
	
	private String avenueName; /** Nombre de la Avenida length =255 */
	private String floor; /** Piso de la Dirección length =255 */
	private String propertyNumber; /** Número de la propiedad de la Dirección length =255 */
	private Long zipCod; /** Codigo Postal de la Dirección length =10 */
	private String referencePlace; /** Sitio de Referencia length =255 */

	/**
	 * 
	 */
	public LegalEntityAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
}
