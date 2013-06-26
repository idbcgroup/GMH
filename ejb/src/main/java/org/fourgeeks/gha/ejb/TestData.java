package org.fourgeeks.gha.ejb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.domain.enu.LocationLevelEnum;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;
import org.fourgeeks.gha.domain.enu.WarrantySinceEnum;
import org.fourgeeks.gha.domain.enu.WarrantyStateEnum;
import org.fourgeeks.gha.domain.ess.SingleSignOnUser;
import org.fourgeeks.gha.domain.exceptions.EJBException;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaComponent;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeComponent;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.domain.mix.LegalEntity;
import org.fourgeeks.gha.ejb.gmh.BrandServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaComponentServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeComponentServiceRemote;
import org.fourgeeks.gha.ejb.gmh.EiaTypeServiceRemote;
import org.fourgeeks.gha.ejb.gmh.ManufacturerServiceRemote;

@Startup
@Singleton
public class TestData {

	@PersistenceContext
	EntityManager em;

	@EJB(name = "gmh.BrandSErvice")
	BrandServiceRemote bServiceRemote;

	@EJB(name = "gmh.ManufacturerService")
	ManufacturerServiceRemote mServiceRemote;

	@EJB(name = "gmh.EiaTypeService")
	EiaTypeServiceRemote eiaTypeServ;

	@EJB(name = "gmh.EiaService")
	EiaServiceRemote eiaServ;

	@EJB(name = "gmh.EiaTypeComponentService")
	EiaTypeComponentServiceRemote etcService;

	@EJB(name = "gmh.EiaComponentService")
	EiaComponentServiceRemote ecService;

	@Resource(mappedName = "java:/jdbc/gha")
	DataSource dataSource;

	@PostConstruct
	public void inicializar() {
		userTestData();
		createIndexs();
		eiaTypeTestData();
		brandTestData();
		manufacturerTestData();
	}

	/**
	 * 
	 */
	private void manufacturerTestData() {
		try {
			Manufacturer manufacturer = new Manufacturer();
			manufacturer.setName("TEST Manufacturer 1");
			manufacturer = mServiceRemote.save(manufacturer);
			
			Manufacturer manufacturer2 = new Manufacturer();
			manufacturer2.setName("TEST Manufacturer 2");
			mServiceRemote.save(manufacturer2);

			System.out.println("MANUFACTURER: testing find");
			Manufacturer search = new Manufacturer();
			search.setName("TEST");
			List<Manufacturer> findResult = mServiceRemote.find(search);
			for (Manufacturer man : findResult) {
				System.out.println("manufacturer id=" + Long.toString(man.getId())
						+ " name=" + man.getName());
			}

			// System.out.println("MANUFACTURER: testing delete");
			// mServiceRemote.delete(1L);

			System.out.println("MANUFACTURER: testing getAll");
			List<Manufacturer> getAllResult = mServiceRemote.getAll();
			for (Manufacturer man : getAllResult) {
				System.out.println("manufacturer id=" + Long.toString(man.getId())
						+ " name=" + man.getName());
			}
		} catch (EJBException e) {
			System.out.println("Test data failed for manufacturer");
			System.out.println(e.getCause().getMessage());
		}
	}

	/**
	 * 
	 */
	private void brandTestData() {
		try {
			Brand brand = new Brand();
			brand.setName("TEST Brand 1");
			brand = bServiceRemote.save(brand);
			
			Brand brand2 = new Brand();
			brand2.setName("TEST Brand 2");
			brand2 = bServiceRemote.save(brand2);

			System.out.println("BRAND: testing find");
			Brand search = new Brand();
			search.setName("TEST");
			List<Brand> findResult = bServiceRemote.find(search);
			for (Brand br : findResult) {
				System.out.println("brand id=" + Long.toString(br.getId())
						+ " name=" + br.getName());
			}

			// System.out.println("BRAND: testing delete");
			// bServiceRemote.delete(1L);

			System.out.println("BRAND: testing getAll");
			List<Brand> getAllResult = bServiceRemote.getAll();
			for (Brand br : getAllResult) {
				System.out.println("manufacturer id=" + Long.toString(br.getId())
						+ " name=" + br.getName());
			}
		} catch (EJBException e) {
			System.out.println("Test data failed for brand");
			System.out.println(e.getCause().getMessage());
		}

	}

	private void createIndexs() {

		System.out.println("Creating indexes...");
		Connection con = null;
		PreparedStatement ps;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("CREATE INDEX username_index ON singlesignonuser (username)");
			ps.execute();
			ps = con.prepareStatement("CREATE INDEX eiaType_index ON eiatype (type)");
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				return;
			}
		}
		System.out.println("...done creating indexes!");
	}

	private void eiaTypeTestData() {

		String query = "SELECT t from EiaType t WHERE id = 1 ";
		try {
			em.createQuery(query).getSingleResult();
		} catch (NoResultException e) {
			try {
				Manufacturer manufacturer = new Manufacturer();
				manufacturer.setName("ChinaFactory");
				manufacturer = mServiceRemote.save(manufacturer);

				Brand brand = new Brand();
				brand.setName("Epson");
				brand = bServiceRemote.save(brand);

				Brand brand2 = new Brand();
				brand2.setName("Hewlet Packard");
				brand2 = bServiceRemote.save(brand2);

				EiaType eiaType = new EiaType(brand, manufacturer,
						"Impresora Tinta", EiaMobilityEnum.FIXED,
						EiaTypeEnum.EQUIPMENT, EiaSubTypeEnum.IT_SYSTEM,
						"Stylus", "90001");
				eiaType = eiaTypeServ.save(eiaType);

				EiaType eiaType2 = new EiaType(brand2, manufacturer,
						"Impresora Laser", EiaMobilityEnum.FIXED,
						EiaTypeEnum.EQUIPMENT, EiaSubTypeEnum.IT_SYSTEM,
						"Deskjet", "90002");
				eiaType2 = eiaTypeServ.save(eiaType2);

				Bpi bpi = new Bpi();
				em.persist(bpi);
				em.flush();

				BuildingLocation buildingLocation = new BuildingLocation(bpi,
						"Building001", LocationLevelEnum.AREA_HALL, 2);
				em.persist(buildingLocation);
				em.flush();

				Facility facility = new Facility();
				facility.setBuildingLocation(buildingLocation);
				em.persist(facility);
				em.flush();

				Eia eia = new Eia(facility, eiaType,
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);
				eia.setCode("Stylus-001");
				eia.setSerialNumber("001");
				eia = eiaServ.save(eia);

				System.out.println("Testing EiaTypeComponent");
				EiaType eiaType3 = new EiaType(brand, manufacturer,
						"Cartucho Tricolor", EiaMobilityEnum.FIXED,
						EiaTypeEnum.PART, EiaSubTypeEnum.IT_SYSTEM, "EP60",
						"90003");
				eiaType3 = eiaTypeServ.save(eiaType3);

				EiaType eiaType4 = new EiaType(brand2, manufacturer,
						"Toner Laser", EiaMobilityEnum.FIXED, EiaTypeEnum.PART,
						EiaSubTypeEnum.IT_SYSTEM, "HP60", "90004");
				eiaType4 = eiaTypeServ.save(eiaType4);

				EiaType eiaType5 = new EiaType(brand, manufacturer,
						"Cartucho Negro", EiaMobilityEnum.FIXED,
						EiaTypeEnum.PART, EiaSubTypeEnum.IT_SYSTEM, "EPN60",
						"90005");
				eiaType5 = eiaTypeServ.save(eiaType5);

				System.out.println("TESTING: EiaType find service");
				eiaType = eiaTypeServ.find(1L);
				eiaType2 = eiaTypeServ.find(2L);
				eiaType3 = eiaTypeServ.find(3L);
				eiaType4 = eiaTypeServ.find(4L);
				eiaType5 = eiaTypeServ.find(5L);

				EiaTypeComponent eiaTypeComponent = new EiaTypeComponent(
						eiaType, eiaType3, true, true);
				EiaTypeComponent eiaTypeComponent2 = new EiaTypeComponent(
						eiaType2, eiaType4, true, true);
				EiaTypeComponent eiaTypeComponent3 = new EiaTypeComponent(
						eiaType, eiaType5, true, true);

				etcService.save(eiaTypeComponent);
				etcService.save(eiaTypeComponent2);
				etcService.save(eiaTypeComponent3);
				System.out.println("EiaTypeComponent Guardados");

				System.out.println("Testing EiaTypeComponents getAll/find");
				List<EiaTypeComponent> eiaTypeComponents = etcService.getAll();
				for (EiaTypeComponent next : eiaTypeComponents) {
					System.out.println("ID = " + Long.toString(next.getId()));
					System.out.println("Parent = "
							+ next.getParentEiaType().getName());
					System.out.println("EiaType = "
							+ next.getEiaType().getName());
				}

				System.out.println("Testing EiaTypeComponents findByEiaTypeId");
				eiaTypeComponents = etcService.findByEiaTypeId(1L);
				for (EiaTypeComponent next : eiaTypeComponents) {
					System.out.println("ID = " + Long.toString(next.getId()));
					System.out.println("Parent = "
							+ next.getParentEiaType().getName());
					System.out.println("EiaType = "
							+ next.getEiaType().getName());
				}

				System.out.println("TESTING: EIA components services");

				Eia eia2 = new Eia(facility, eiaType3,
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.YEARS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);

				Eia eia3 = new Eia(facility, eiaType5,
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.YEARS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);

				Eia eia4 = new Eia(facility, eiaType4,
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);

				Eia eia5 = new Eia(facility, eiaType2,
						WarrantySinceEnum.ACCEPTATION, TimePeriodEnum.DAYS,
						EiaStateEnum.TEST, WarrantyStateEnum.VALID);

				System.out.println("Testing part 1: eia services");
				eia2 = eiaServ.save(eia2);
				eia3 = eiaServ.save(eia3);
				eia4 = eiaServ.save(eia4);
				eia5 = eiaServ.save(eia5);

				eia = eiaServ.find(1L); // parent Impresora Epson
				eia2 = eiaServ.find(2L); // cartucho tricolor
				eia3 = eiaServ.find(3L); // cartucho negro
				eia4 = eiaServ.find(4L); // toner
				eia5 = eiaServ.find(5L); // parent Impresora Hp

				EiaComponent eiaComponent = new EiaComponent(eia, eia2,
						"Cartucho Tricolor Impresora Epson Stylus-001");
				EiaComponent eiaComponent2 = new EiaComponent(eia, eia3,
						"Cartucho Negro Impresora Epson Stylus-001");
				EiaComponent eiaComponent3 = new EiaComponent(eia5, eia4,
						"Toner de Tinta para la Impresora Laser HP60");

				System.out.println("Testing part 2: save eiacomponent");
				ecService.save(eiaComponent);
				ecService.save(eiaComponent2);
				ecService.save(eiaComponent3);

				System.out.println("Testing part 3: eiacomponent listing");
				List<EiaComponent> eiaComponents = ecService.getAll();
				for (EiaComponent next : eiaComponents) {
					System.out.println(next.getComponentObs());
				}

				System.out.println("Testing part 4: eiacomponent find by eia");
				List<EiaComponent> eiaComponents2 = ecService.findByEiaId(1L);
				for (EiaComponent next : eiaComponents2) {
					System.out.println(next.getComponentObs());
				}

				System.out.println("TESTING: update eiacomponent");
				EiaComponent eiaCompUpd = eiaComponents2.get(0);
				eiaCompUpd
						.setComponentObs("Update: Cartucho Tricolor Impresora Epson Stylus-001");
				ecService.update(eiaCompUpd);

			} catch (EJBException e1) {
				System.out.println("Failed to initialize test data");
				e1.printStackTrace();
			}
		}
	}

	private void userTestData() {
		try {
			String query = "SELECT t from LegalEntity t WHERE id = 1 ";
			try {
				em.createQuery(query).getSingleResult();
			} catch (NoResultException e) {
				LegalEntity legalEntity = new LegalEntity();
				em.persist(legalEntity);
				SingleSignOnUser signOnUser = new SingleSignOnUser();
				signOnUser.setLegalEntity(legalEntity);
				signOnUser.setPassword("admin");
				signOnUser.setUserName("admin");
				em.persist(signOnUser);

			}
		} catch (Exception e) {
			System.out.println("ERROR: No se puede cargar la data de prueba:"
					+ e.getMessage());
		}
	}
}