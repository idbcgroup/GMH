package org.fourgeeks.gha.ejb.helpers;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeCategory;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.Institution;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.ess.auth.RoleServiceRemote;
import org.fourgeeks.gha.ejb.gar.BspServiceRemote;
import org.fourgeeks.gha.ejb.gar.FacilityServiceRemote;
import org.fourgeeks.gha.ejb.gar.ObuServiceRemote;
import org.fourgeeks.gha.ejb.glm.ExternalProviderServiceRemote;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.ejb.gmh.BuildingLocationServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeCategoryServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote;
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;
import org.fourgeeks.gha.ejb.gom.CCDIServiceLocal;
import org.fourgeeks.gha.ejb.mix.BpiServiceRemote;
import org.fourgeeks.gha.ejb.mix.InstitutionServiceRemote;
import org.fourgeeks.gha.ejb.mix.LegalEntityServiceRemote;

/**
 * @author alacret
 * 
 */
public class EiaHelper {

	private CCDIServiceLocal ccdiServiceLocal;
	private ManufacturerServiceRemote manufacturerServiceRemote;
	private EiaTypeCategoryServiceRemote eiaTypeCategoryServiceRemote;
	private BrandServiceRemote brandServiceRemote;
	private EiaTypeServiceRemote eiaTypeServiceRemote;
	private RoleServiceRemote roleServiceRemote;
	private LegalEntityServiceRemote legalEntityServiceRemote;
	private BpiServiceRemote bpiServiceRemote;
	private InstitutionServiceRemote institutionServiceRemote;
	private ObuServiceRemote obuServiceRemote;
	private BspServiceRemote bspServiceRemote;
	private ExternalProviderServiceRemote externalProviderServiceRemote;
	private FacilityServiceRemote facilityServiceRemote;
	private BuildingLocationServiceRemote buildingLocationServiceRemote;
	private EiaServiceRemote eiaServiceRemote;

	private Eia savedEia;
	private ExternalProvider savedExternalProvider;
	private Facility savedFacility;
	private BuildingLocation savedBuildingLocation;
	private Bsp savedBsp;
	private Obu savedObu;
	private Bpi savedBpi;
	private Institution savedInstitution;
	private LegalEntity savedLegalEntity;
	private Role savedRole;
	private EiaType savedEiatype;
	private EiaTypeCategory savedEiatypeCategory;
	private Brand savedBrand;
	private Manufacturer savedManufacturer;
	private CCDIDefinition savedCCDIDefinition;

	/**
	 * @param ccdiServiceLocal
	 * @param manufacturerServiceRemote
	 * @param eiaTypeCategoryServiceRemote
	 * @param brandServiceRemote
	 * @param eiaTypeServiceRemote
	 * @param roleServiceRemote
	 * @param legalEntityServiceRemote
	 * @param bpiServiceRemote
	 * @param institutionServiceRemote
	 * @param obuServiceRemote
	 * @param bspServiceRemote
	 * @param externalProviderServiceRemote
	 * @param facilityServiceRemote
	 * @param buildingLocationServiceRemote
	 * @param eiaServiceRemote
	 */
	public EiaHelper(final CCDIServiceLocal ccdiServiceLocal,
			final ManufacturerServiceRemote manufacturerServiceRemote,
			final EiaTypeCategoryServiceRemote eiaTypeCategoryServiceRemote,
			final BrandServiceRemote brandServiceRemote,
			final EiaTypeServiceRemote eiaTypeServiceRemote,
			final RoleServiceRemote roleServiceRemote,
			final LegalEntityServiceRemote legalEntityServiceRemote,
			final BpiServiceRemote bpiServiceRemote,
			final InstitutionServiceRemote institutionServiceRemote,
			final ObuServiceRemote obuServiceRemote,
			final BspServiceRemote bspServiceRemote,
			final ExternalProviderServiceRemote externalProviderServiceRemote,
			final FacilityServiceRemote facilityServiceRemote,
			final BuildingLocationServiceRemote buildingLocationServiceRemote,
			final EiaServiceRemote eiaServiceRemote) {

		this.ccdiServiceLocal = ccdiServiceLocal;
		this.manufacturerServiceRemote = manufacturerServiceRemote;
		this.eiaTypeCategoryServiceRemote = eiaTypeCategoryServiceRemote;
		this.brandServiceRemote = brandServiceRemote;
		this.eiaTypeServiceRemote = eiaTypeServiceRemote;
		this.roleServiceRemote = roleServiceRemote;
		this.legalEntityServiceRemote = legalEntityServiceRemote;
		this.bpiServiceRemote = bpiServiceRemote;
		this.institutionServiceRemote = institutionServiceRemote;
		this.obuServiceRemote = obuServiceRemote;
		this.bspServiceRemote = bspServiceRemote;
		this.externalProviderServiceRemote = externalProviderServiceRemote;
		this.facilityServiceRemote = facilityServiceRemote;
		this.buildingLocationServiceRemote = buildingLocationServiceRemote;
		this.eiaServiceRemote = eiaServiceRemote;
	}

	/**
	 * @return a newly create eia
	 */
	public Eia createEia() {
		try {
			// creating a ccdi definition
			CCDIDefinition definition = new CCDIDefinition("CCDIDEFTEST");
			definition.setLevels(1);
			definition.setLength(10);
			savedCCDIDefinition = ccdiServiceLocal
					.createCCDIDefinition(definition);

			// creating a ccdi level denition
			CCDILevelDefinition ccdiLevelDefinition = new CCDILevelDefinition();
			ccdiLevelDefinition.setDefinition(savedCCDIDefinition);
			ccdiLevelDefinition.setLevel(0);
			ccdiLevelDefinition.setLength(2);
			ccdiLevelDefinition.setValueType(CCDIValueTypeEnum.FIXED);
			CCDILevelDefinition savedCCDILevelDefinition = ccdiServiceLocal
					.createCCDILevelDefinition(savedCCDIDefinition,
							ccdiLevelDefinition);

			// creating a ccdi level value
			CCDILevelValue ccdiLevelValue = new CCDILevelValue(
					savedCCDILevelDefinition, null, "TESTLEVELVALUE",
					"TESTLEVELVALUE", 1, "XX", CCDIValueStatusEnum.ACTIVE);
			ccdiLevelValue.setNextElement(1);
			CCDILevelValue savedCCDILevelValue = ccdiServiceLocal
					.createCCDILevelValue(savedCCDILevelDefinition, null,
							ccdiLevelValue);

			savedManufacturer = manufacturerServiceRemote
					.save(new Manufacturer("TESTMAN"));

			// Creating a Brand
			Brand b = new Brand("TESTBRAND");
			b.setManufacturer(savedManufacturer);
			savedBrand = brandServiceRemote.save(b);

			// Creating an Eiatye CAtegory
			final EiaTypeCategory category = new EiaTypeCategory();
			category.setName("TESTLEVELVALUE");
			category.setCode(savedCCDILevelValue.getCode());
			savedEiatypeCategory = eiaTypeCategoryServiceRemote.save(category);

			// CREating an eiatype
			final EiaType eiaType = new EiaType();
			eiaType.setBrand(savedBrand);
			eiaType.setName("TESTEIATYPE");
			eiaType.setMobility(EiaMobilityEnum.FIXED);
			eiaType.setEiaTypeCategory(savedEiatypeCategory);
			eiaType.setCode("EIATYPETESTCODE");
			eiaType.setSubtype(EiaSubTypeEnum.DIAGNOSE);
			eiaType.setModel("TESTMODEL");
			savedEiatype = eiaTypeServiceRemote.save(eiaType);

			savedRole = roleServiceRemote.save(new Role("Test Role 2"));

			savedLegalEntity = legalEntityServiceRemote.save(new LegalEntity(
					"J-0001"));

			// creating an Institution
			Institution institution = new Institution();
			institution.setName("TestInstitution");
			institution.setLegalEntity(savedLegalEntity);
			savedInstitution = institutionServiceRemote.save(institution);

			// Creating a BPI
			Bpi bpi = new Bpi();
			bpi.setInstitution(savedInstitution);
			savedBpi = bpiServiceRemote.save(bpi);

			// creating a OBU
			Obu obu = new Obu();
			obu.setName("TESTOVU");
			obu.setCode("TestOBU");
			obu.setBpi(savedBpi);
			savedObu = obuServiceRemote.save(obu);

			// creating a BSP
			final Bsp bsp = new Bsp();
			bsp.setObu(savedObu);
			savedBsp = bspServiceRemote.save(bsp);

			// creating a buildingLocation
			final BuildingLocation buildingLocation = new BuildingLocation(
					savedBpi, "Building 00", LocationLevelEnum.BUILDING,
					"Building Location Name ");
			savedBuildingLocation = buildingLocationServiceRemote
					.save(buildingLocation);

			// creating a Facility
			final Facility facility = new Facility();
			facility.setName("TESTFACILITY");
			facility.setBuildingLocation(savedBuildingLocation);
			savedFacility = facilityServiceRemote.save(facility);

			// creating an externalProvider
			ExternalProvider eP = new ExternalProvider();
			eP.setInstitution(savedInstitution);
			savedExternalProvider = externalProviderServiceRemote.save(eP);

			// creating an Eia
			final Eia eia = new Eia(savedRole, savedEiatype, savedObu,
					EiaStateEnum.ACQUIRED, "GHAEQ-00", savedBsp, "S9023423");
			eia.setCode("eia-00");
			eia.setFacility(savedFacility);
			eia.setProvider(savedExternalProvider);

			savedEia = eiaServiceRemote.save(eia);

		} catch (GHAEJBException e) {
			removeEia();
			Assert.fail("error setting: " + e.getMessage());
		}
		return savedEia;
	}

	/**
	 * 
	 */
	public void removeEia() {
		try {
			eiaServiceRemote.delete(savedEia.getId());
		} catch (Exception e) {
		}
		try {
			externalProviderServiceRemote.delete(savedExternalProvider.getId());
		} catch (Exception e) {
		}
		try {
			facilityServiceRemote.delete(savedFacility.getId());
		} catch (Exception e) {
		}
		try {
			buildingLocationServiceRemote.delete(savedBuildingLocation
					.getCode());
		} catch (Exception e) {
		}
		try {
			bspServiceRemote.delete(savedBsp.getId());
		} catch (Exception e) {
		}
		try {
			obuServiceRemote.delete(savedObu.getId());
		} catch (Exception e) {
		}
		try {
			bpiServiceRemote.delete(savedBpi.getId());
		} catch (Exception e) {
		}
		try {
			institutionServiceRemote.delete(savedInstitution.getId());
		} catch (Exception e) {
		}
		try {
			legalEntityServiceRemote.delete(savedLegalEntity.getId());
		} catch (Exception e) {
		}
		try {
			roleServiceRemote.delete(savedRole.getId());
		} catch (Exception e) {
		}
		try {
			eiaTypeServiceRemote.delete(savedEiatype.getCode());
		} catch (Exception e) {
		}
		try {
			eiaTypeCategoryServiceRemote.delete(savedEiatypeCategory);
		} catch (Exception e) {
		}
		try {
			brandServiceRemote.delete(savedBrand.getId());
		} catch (Exception e) {
		}
		try {
			manufacturerServiceRemote.delete(savedManufacturer.getId());
		} catch (Exception e) {
		}
		try {
			ccdiServiceLocal.deleteByCode(savedCCDIDefinition.getCode());
		} catch (Exception e) {
		}
	}

}
