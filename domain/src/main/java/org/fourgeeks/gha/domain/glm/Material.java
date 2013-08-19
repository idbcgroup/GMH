/**
 * 
 */
package org.fourgeeks.gha.domain.glm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author alacret
 * 
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Material.getAll", query = "SELECT m FROM Material m ORDER BY m.name"),
		@NamedQuery(name = "Material.getByType", query = "SELECT m FROM Material m WHERE m.type = :materialTypeId ORDER BY m.name"),
		@NamedQuery(name = "Material.findByName", query = "SELECT m FROM Material m WHERE LOWER(m.name) LIKE :name ORDER BY m.id") })
public class Material extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "El tipo de material no puede ser nulo")
	@Column(nullable = false)
	private MaterialTypeEnum type;

	// private BigDecimal length;
	// /** length length =7dec =3 */
	private String description;
	/** description length =500 */
	// private String local_name;
	// /** local_name length =255 */
	// private String status;
	// /** status length =60 */
	@ManyToOne
	@JoinColumn(name = "providerFk")
	private ExternalProvider externalProvider;
	private String name;
	/** vendor_name length =255 */
	// private BigDecimal width;
	// /** width length =7dec =3 */
	private String code;
	/** material_code length =60 */
	private String extCode;
	/** ext_material_code length =30 */
	// private BigDecimal last_cost;
	// /** last_cost length =10dec =5 */
	// private BigDecimal last_std_cost;
	// /** last_std_cost length =10dec =5 */
	// private String valoration_method;
	// /** valoration_method length =60 */
	// private String tax_category;
	// /** tax_category length =60 */
	// private BigDecimal service_level;
	// /** service_level length =10dec =5 */
	// private String dangerous_material;
	// /** dangerous_material length =60 */
	// private String purchase_unit;
	// /** purchase_unit length =60 */
	// private String sales_unit;
	// /** sales_unit length =60 */
	// private String stk_unit;
	// /** stk_unit length =60 */
	// private float lot_handler;
	// /** lot_handler length =30 */
	// private String package_size;
	// /** package_size length =60 */
	// private BigDecimal volume;
	// /** volume length =7dec =3 */
	// private String equivalent_code;
	// /** equivalent_code length =255 */
	// private String material_property;
	// /** material_property length =60 */
	// private int expiration_control;
	// /** expiration_control length =8 */
	// private String description_detail;
	// /** description_detail length =255 */
	// private String description_content;
	// /** description_content length =255 */
	// private String base_material;
	// /** base_material length =60 */
	private String model;

	/** material_model length =255 */
	// private String technical_name;
	// /** technical_name length =255 */
	// private String search_name1;
	// /** search_name1 length =255 */
	// private String search_name2;
	// /** search_name2 length =255 */
	// private String search_name3;
	// /** search_name3 length =255 */
	// private String english_name;
	// /** english_name length =255 */
	// private String composition_name;
	// /** composition_name length =255 */
	// private String composition_type;
	// /** composition_type length =60 */
	// private String storage_condition;
	// /** storage_condition length =60 */
	// private String manipulation_features;
	// /** manipulation_features length =60 */
	// private String manipulation_unit;
	// /** manipulation_unit length =60 */
	// private String material_color;
	// /** material_color length =60 */
	// private String material_style;
	// /** material_style length =255 */
	// private BigDecimal high;
	// /** high length =7dec =3 */
	// private String dimensions_unit;
	// /** dimensions_unit length =60 */
	// private BigDecimal gross_weight;
	// /** gross_weight length =7dec =3 */
	// private BigDecimal net_weight;
	// /** net_weight length =7dec =3 */
	// private String weight_unit;
	// /** weight_unit length =60 */
	// private String volume_unit;
	// /** volume_unit length =60 */
	// private BigDecimal density;
	// /** density length =7dec =3 */
	// private String density_unit;
	// /** density_unit length =60 */
	// private String consign_material;
	// /** consign_material length =60 */
	// private short category_fk;
	// /** category_fk length = */
	// private Timestamp created_date;
	// /** created_date length =22 */
	// private Timestamp last_updated_date;
	// /** last_updated_date length =22 */
	// private String usage;
	// /** usage length =2000 */
	// private String kit_type;
	// /** kit_type length =60 */
	// private String instructions;
	// /** instructions length =2000 */
	// private BigDecimal purchase_unit_qty;
	// /** purchase_unit_qty length =9dec =3 */
	// private String vendor_code;
	// /** vendor_code length =255 */
	// private short fixed_price;
	// /** fixed_price length =4 */
	// private BigDecimal average_cost;
	// /** average_cost length =10dec =5 */
	// private BigDecimal suggested_price;
	// /** suggested_price length =10dec =5 */
	// private String tax_handling;
	// /** tax_handling length =60 */
	// private Timestamp average_cost_date;
	// /** average_cost_date length =22 */
	// private Timestamp suggested_price_date;
	// /** suggested_price_date length =22 */
	// private String price_code;
	// /** price_code length =30 */
	// private String currency;
	// /** currency length =60 */
	// private String gazette_number;
	// /** gazette_number length =30 */
	// private Timestamp gazette_date;
	// /** gazette_date length =22 */
	// private String last_update_user;
	// /** last_update_user length =255 */
	// private String allows_unidose;
	// /** allows_unidose length =60 */
	// private String material_element;
	// /** material_element length =60 */
	// private String short_description;
	// /** short_description length =40 */
	// private short multiple_use;
	// /** multiple_use length =4 */
	// private String billing_service_code;
	// /** billing_service_code length =30 */
	// private String billing_service_description;
	// /** billing_service_description length =255 */
	// private String previous_material_code;
	// /** previous_material_code length =30 */
	// private String multiple_use_method;
	// /** multiple_use_method length =60 */
	// private String billing_type;
	// /** billing_type length =60 */
	// private String expense_type;
	// /** expense_type length =60 */
	// private BigDecimal manufacturer_usage_factor;
	// /** manufacturer_usage_factor length =7dec =3 */
	// private String expense_service_code;
	// /** expense_service_code length =30 */
	// private String expense_service_description;
	// /** expense_service_description length =255 */
	// private String consolidated;
	// /** consolidated length =60 */
	// private long consolidate_material_id;
	// /** consolidate_material_id length =19 */
	// private BigDecimal adjusted_usage_factor;
	// /** adjusted_usage_factor length =7dec =3 */
	// private short inmediate_replacement;
	// /** inmediate_replacement length =4 */
	// private short automatic_replacement;
	// /** automatic_replacement length =4 */
	// private String ext_material_code2;
	// /** ext_material_code2 length =30 */
	// private String ext_material_code3;
	// /** EXT_MATERIAL_CODE3 length =30 */

	/**
	 * 
	 */
	public Material() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param code
	 * @param name
	 * @param type
	 */
	public Material(String code, String name, MaterialTypeEnum type) {
		this.code = code;
		this.name = name;
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public MaterialTypeEnum getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(MaterialTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the provider
	 */
	public ExternalProvider getExternalProvider() {
		return externalProvider;
	}

	/**
	 * @param externalProvider
	 */
	public void setExternalProvider(ExternalProvider externalProvider) {
		this.externalProvider = externalProvider;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the extCode
	 */
	public String getExtCode() {
		return extCode;
	}

	/**
	 * @param extCode
	 *            the extCode to set
	 */
	public void setExtCode(String extCode) {
		this.extCode = extCode;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

}
