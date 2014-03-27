/**
 * 
 */
package org.fourgeeks.gha.ejb.glm;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import junit.framework.Assert;

import org.fourgeeks.gha.domain.enu.CCDICodeTypeEnum;
import org.fourgeeks.gha.domain.enu.CCDIEndValueActionEnum;
import org.fourgeeks.gha.domain.enu.CCDIStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueStatusEnum;
import org.fourgeeks.gha.domain.enu.CCDIValueTypeEnum;
import org.fourgeeks.gha.domain.exceptions.GHAEJBException;
import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.glm.MaterialBrand;
import org.fourgeeks.gha.domain.glm.MaterialCategory;
import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.gom.CCDIDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelDefinition;
import org.fourgeeks.gha.domain.gom.CCDILevelValue;
import org.fourgeeks.gha.ejb.GHAArquillianBaseServiceTest;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;
import org.fourgeeks.gha.ejb.gom.CCDIServiceRemote;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author emiliot
 * 
 */

@RunWith(Arquillian.class)
public class MaterialBrandServiceTest extends GHAArquillianBaseServiceTest {

	@EJB(lookup = "java:global/test/MaterialBrandService!org.fourgeeks.gha.ejb.glm.MaterialBrandServiceRemote")
	MaterialBrandServiceRemote service;

	@EJB(lookup = "java:global/test/MaterialCategoryService")
	MaterialCategoryServiceRemote materialCategoryService;

	@EJB(lookup = "java:global/test/CCDIService!org.fourgeeks.gha.ejb.gom.CCDIServiceRemote")
	CCDIServiceRemote ccdiService;

	@EJB(lookup = "java:global/test/ManufacturerService")
	ManufacturerServiceRemote manufacturerService;

	@EJB(lookup = "java:global/test/BrandService")
	BrandServiceRemote brandService;

	@EJB(lookup = "java:global/test/MaterialService")
	MaterialServiceRemote materialService;

	Brand brand1 = new Brand();
	Brand brand2 = new Brand();

	Manufacturer manufacturer = new Manufacturer();

	List<Long> materialBrandIds = new ArrayList<>();

	/**
	 * 
	 */
	@Before
	public void set() {
		try {
			CCDIDefinition material = null;
			material = new CCDIDefinition("MATERIAL-test", "MATERIAL", 11, 5,
					CCDIStatusEnum.ACTIVE, null, CCDICodeTypeEnum.ALPHANUMERIC,
					false, "");

			material = ccdiService.createCCDIDefinition(material);

			CCDILevelDefinition materiales = new CCDILevelDefinition(material,
					0, "MATERIALES-test", 2, CCDIValueTypeEnum.FIXED, 0, 0, "",
					CCDIEndValueActionEnum.RESTART);
			CCDILevelDefinition type = new CCDILevelDefinition(material, 1,
					"TIPO-test", 1, CCDIValueTypeEnum.FIXED, 1, 1, "",
					CCDIEndValueActionEnum.RESTART);
			CCDILevelDefinition family = new CCDILevelDefinition(material, 2,
					"FAMILIA-test", 2, CCDIValueTypeEnum.VARIABLE, 1, 1, "",
					CCDIEndValueActionEnum.RESTART);
			CCDILevelDefinition subFamily = new CCDILevelDefinition(material,
					3, "SUB FAMILIA-test", 2, CCDIValueTypeEnum.VARIABLE, 1, 1,
					"", CCDIEndValueActionEnum.RESTART);
			CCDILevelDefinition element = new CCDILevelDefinition(material, 4,
					"ELEMENTOS-test", 4, CCDIValueTypeEnum.VARIABLE, 1, 1, "",
					CCDIEndValueActionEnum.RESTART);

			materiales = ccdiService.createCCDILevelDefinition(material,
					materiales);
			type = ccdiService.createCCDILevelDefinition(material, type);
			family = ccdiService.createCCDILevelDefinition(material, family);
			subFamily = ccdiService.createCCDILevelDefinition(material,
					subFamily);
			element = ccdiService.createCCDILevelDefinition(material, element);

			CCDILevelValue materialValue = new CCDILevelValue(null, null,
					"MATERIAL-test", null, 0, "T0", CCDIValueStatusEnum.ACTIVE);
			materialValue = ccdiService.createCCDILevelValue(materiales, null,
					materialValue);
			CCDILevelValue suppliesValue = new CCDILevelValue(null, null,
					"SUMINISTROS", null, 1, "1", CCDIValueStatusEnum.ACTIVE);
			CCDILevelValue pharmacsValue = new CCDILevelValue(null, null,
					"FARMACOS", null, 1, "4", CCDIValueStatusEnum.ACTIVE);
			suppliesValue = ccdiService.createCCDILevelValue(type,
					materialValue, suppliesValue);
			pharmacsValue = ccdiService.createCCDILevelValue(type,
					materialValue, pharmacsValue);
			CCDILevelValue needlesValue = new CCDILevelValue(null, null,
					"AGUJAS", null, 1, null, CCDIValueStatusEnum.ACTIVE);
			CCDILevelValue syringeValue = new CCDILevelValue(null, null,
					"INYECTADORAS", null, 1, null, CCDIValueStatusEnum.ACTIVE);
			CCDILevelValue antiBiotics = new CCDILevelValue(null, null,
					"ANTIBIOTICOS", null, 1, null, CCDIValueStatusEnum.ACTIVE);
			needlesValue = ccdiService.createCCDILevelValue(family,
					suppliesValue, needlesValue);
			syringeValue = ccdiService.createCCDILevelValue(family,
					suppliesValue, syringeValue);
			antiBiotics = ccdiService.createCCDILevelValue(family,
					pharmacsValue, antiBiotics);
			CCDILevelValue hypodermic = new CCDILevelValue(null, null,
					"HIPODERMICAS", null, 1, null, CCDIValueStatusEnum.ACTIVE);
			CCDILevelValue puncture = new CCDILevelValue(null, null, "PUNCION",
					null, 1, null, CCDIValueStatusEnum.ACTIVE);
			CCDILevelValue insuline = new CCDILevelValue(null, null,
					"INSULINA", null, 1, null, CCDIValueStatusEnum.ACTIVE);
			CCDILevelValue penicilline = new CCDILevelValue(null, null,
					"PENICILINA", null, 1, null, CCDIValueStatusEnum.ACTIVE);
			hypodermic = ccdiService.createCCDILevelValue(subFamily,
					needlesValue, hypodermic);
			puncture = ccdiService.createCCDILevelValue(subFamily,
					needlesValue, puncture);
			insuline = ccdiService.createCCDILevelValue(subFamily,
					syringeValue, insuline);
			penicilline = ccdiService.createCCDILevelValue(subFamily,
					antiBiotics, penicilline);

			MaterialCategory category1 = new MaterialCategory();
			category1.setName(hypodermic.getName());
			category1.setCode(hypodermic.getCode());
			materialCategoryService.save(category1);

			MaterialCategory category2 = new MaterialCategory();
			category2.setName(puncture.getName());
			category2.setCode(puncture.getCode());
			materialCategoryService.save(category2);

			manufacturer.setName("TEST-MANUFACTURER");
			manufacturer = manufacturerService.save(manufacturer);

			brand1.setManufacturer(manufacturer);
			brand1.setName("TEST-BRAND1");
			brand1 = brandService.save(brand1);

			brand2.setManufacturer(manufacturer);
			brand2.setName("TEST-BRAND2");
			brand2 = brandService.save(brand2);

		} catch (GHAEJBException e) {
			System.out.println("setting data for materialbrand test failed");
			Assert.fail(e.getCause().getMessage());
			unset();
		}
	}

	/**
	 * 
	 */
	@Test
	public void test() {
		System.out.println("Testing MaterialBrandService");

		try {
			brand1 = brandService.find(this.brand1.getId());
			brand2 = brandService.find(this.brand2.getId());
		} catch (GHAEJBException e) {
			System.out.println("failed getting brand");
			Assert.fail(e.getCause().getMessage());
		}

		MaterialCategory hypodermic = null;
		MaterialCategory puncture = null;
		try {
			hypodermic = materialCategoryService.find("T010101");
			puncture = materialCategoryService.find("T010102");
		} catch (GHAEJBException e) {
			System.out.println("failed getting categories");
			Assert.fail(e.getCause().getMessage());
		}

		Material agujaH1 = new Material();
		agujaH1.setDescription("descripcion de prueba");
		agujaH1.setExternalCode("external code test");
		agujaH1.setModel("model test");
		agujaH1.setName("TEST AGUJA H1");
		agujaH1.setType(MaterialTypeEnum.MATERIAL);
		agujaH1.setMaterialCategory(hypodermic);

		Material agujaH2 = new Material();
		agujaH2.setDescription("descripcion de prueba");
		agujaH2.setExternalCode("external code test");
		agujaH2.setModel("model test");
		agujaH2.setName("TEST AGUJA H1");
		agujaH2.setType(MaterialTypeEnum.MATERIAL);
		agujaH2.setMaterialCategory(hypodermic);

		Material agujaP1 = new Material();
		agujaP1.setDescription("descripcion de prueba");
		agujaP1.setExternalCode("external code test");
		agujaP1.setModel("model test");
		agujaP1.setName("TEST AGUJA H1");
		agujaP1.setType(MaterialTypeEnum.MATERIAL);
		agujaP1.setMaterialCategory(puncture);

		Material agujaP2 = new Material();
		agujaP2.setDescription("descripcion de prueba");
		agujaP2.setExternalCode("external code test");
		agujaP2.setModel("model test");
		agujaP2.setName("TEST AGUJA H1");
		agujaP2.setType(MaterialTypeEnum.MATERIAL);
		agujaP2.setMaterialCategory(puncture);

		MaterialBrand agujaH1B = new MaterialBrand();
		agujaH1B.setMaterial(agujaH1);
		agujaH1B.setBrand(brand1);
		agujaH1B.setAmount(10);

		MaterialBrand agujaH2B = new MaterialBrand();
		agujaH2B.setMaterial(agujaH2);
		agujaH2B.setBrand(brand1);
		agujaH2B.setAmount(5);

		MaterialBrand agujaP1B = new MaterialBrand();
		agujaP1B.setMaterial(agujaP1);
		agujaP1B.setBrand(brand2);
		agujaP1B.setAmount(7);

		MaterialBrand agujaP2B = new MaterialBrand();
		agujaP2B.setMaterial(agujaP2);
		agujaP2B.setBrand(brand2);
		agujaP2B.setAmount(10);

		System.out.println("1");

		try {
			MaterialBrand test1 = service.save(agujaH1B);
			Assert.assertNotNull(test1);
			Assert.assertNotNull(test1.getMaterial());
			Assert.assertNotNull(test1.getBrand());
			Assert.assertEquals(10, test1.getAmount());
			Assert.assertEquals("model test", test1.getMaterial().getModel());
			Assert.assertEquals("T0101010001", test1.getMaterial().getCode());
			Assert.assertEquals("TEST-BRAND1", test1.getBrand().getName());
			materialBrandIds.add(test1.getId());

			MaterialBrand test2 = service.save(agujaH2B);
			Assert.assertNotNull(test2);
			Assert.assertNotNull(test2.getMaterial());
			Assert.assertNotNull(test2.getBrand());
			Assert.assertEquals(5, test2.getAmount());
			Assert.assertEquals("descripcion de prueba", test2.getMaterial()
					.getDescription());
			Assert.assertEquals("T0101010002", test2.getMaterial().getCode());
			Assert.assertEquals("TEST-BRAND1", test2.getBrand().getName());
			materialBrandIds.add(test2.getId());

			System.out.println("2");

			MaterialBrand test3 = service.save(agujaP1B);
			Assert.assertNotNull(test3);
			Assert.assertNotNull(test3.getMaterial());
			Assert.assertNotNull(test3.getBrand());
			Assert.assertEquals(7, test3.getAmount());
			Assert.assertEquals("TEST AGUJA H1", test3.getMaterial().getName());
			Assert.assertEquals("T0101020001", test3.getMaterial().getCode());
			Assert.assertEquals("TEST-BRAND2", test3.getBrand().getName());
			materialBrandIds.add(test3.getId());

			System.out.println("4");

			MaterialBrand test4 = service.save(agujaP2B);
			Assert.assertNotNull(test4);
			Assert.assertNotNull(test4.getMaterial());
			Assert.assertNotNull(test4.getBrand());
			Assert.assertEquals(10, test4.getAmount());
			Assert.assertEquals("external code test", test4.getMaterial()
					.getExternalCode());
			Assert.assertEquals("T0101020002", test4.getMaterial().getCode());
			Assert.assertEquals("TEST-BRAND2", test4.getBrand().getName());
			materialBrandIds.add(test4.getId());

			System.out.println("Material Brand Service test complete!!");
		} catch (GHAEJBException e) {
			System.out.println("Error saving materialbrands");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		// testing services
		try {
			List<MaterialBrand> getAll = service.getAll();
			Assert.assertNotNull(getAll);
		} catch (GHAEJBException e) {
			System.out.println("Error getting all materialbrands");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		try {
			List<MaterialBrand> findByBrand = service.findByBrand(brand1);
			Assert.assertNotNull(findByBrand);
			for (MaterialBrand entity : findByBrand)
				Assert.assertEquals(brand1.getId(), entity.getBrand().getId());
		} catch (GHAEJBException e) {
			System.out.println("Error finding all materialbrands by brand");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		try {
			List<MaterialBrand> findByType = service
					.findByMaterialType(MaterialTypeEnum.MATERIAL);
			Assert.assertNotNull(findByType);
			for (MaterialBrand entity : findByType)
				Assert.assertEquals(MaterialTypeEnum.MATERIAL, entity
						.getMaterial().getType());
		} catch (GHAEJBException e) {
			System.out.println("Error finding all materialbrands by type");
			Assert.fail(e.getCause().getMessage());
			unset();
		}

		try {
			List<MaterialBrand> entities = service.find(agujaH1B);
			for (MaterialBrand entity : entities) {
				Assert.assertNotNull(entity);
				Assert.assertEquals(agujaH1B.getMaterial().getName(), entity
						.getMaterial().getName());
			}

		} catch (GHAEJBException e) {
			System.out
					.println("Error finding all materialbrands by materialbrand");
			Assert.fail(e.getCause().getMessage());
			unset();
		}
	}

	/**
	 * 
	 */
	@After
	public void unset() {
		try {
			System.out.println("Beginning Unset");

			for (Long id : materialBrandIds)
				service.delete(id);

			materialService.delete("T0101010001");
			materialService.delete("T0101010002");
			materialService.delete("T0101020001");
			materialService.delete("T0101020002");
			materialCategoryService.delete("T010101");
			materialCategoryService.delete("T010102");

			brandService.delete(this.brand1.getId());
			brandService.delete(this.brand2.getId());
			manufacturerService.delete(this.manufacturer.getId());

			ccdiService.deleteByCode("MATERIAL-test");
			System.out.println("Unset Done!");
		} catch (GHAEJBException e) {
			Assert.fail(e.getCause().getMessage());
		}
	}
}
