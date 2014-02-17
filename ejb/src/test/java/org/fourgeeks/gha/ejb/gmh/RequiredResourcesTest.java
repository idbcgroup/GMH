package org.fourgeeks.gha.ejb.gmh;


/**
 * 
 * @author caparicio
 * 
 */
//@RunWith(Arquillian.class)
//public class RequiredResourcesTest {
//	/**
//	 * @return the deployment descriptor
//	 */
//	@Deployment
//	public static Archive<?> createDeployment() {
//		return ShrinkWrap
//				.create(WebArchive.class, "test.war")
//				.addClass(AbstractEntity.class)
//				.addClass(AbstractCodeEntity.class)
//				.addClass(AppForm.class)
//				.addClass(AppFormViewFunction.class)
//				.addClass(AppFormViewFunctionBpu.class)
//				.addClass(Bpi.class)
//				.addClass(BpiOriginEnum.class)
//				.addClass(BpiRiskEnum.class)
//				.addClass(BpiInstitutionRelationTypeEnum.class)
//				.addClass(BpiTypeEnum.class)
//				.addClass(Brand.class)
//				.addClass(BuildingLocation.class)
//				.addClass(Bpu.class)
//				.addClass(Citizen.class)
//				.addClass(CurrencyTypeEnum.class)
//				.addClass(DepreciationMethodEnum.class)
//				.addClass(DocumentTypeEnum.class)
//				.addClass(Eia.class)
//				.addClass(EiaStateEnum.class)
//				.addClass(EiaDamageReport.class)
//				.addClass(EiaMaintenancePlanification.class)
//				.addClass(EiaDamageStatusEnum.class)
//				.addClass(EiaMobilityEnum.class)
//				.addClass(EiaTypeEnum.class)
//				.addClass(EiaSubTypeEnum.class)
//				.addClass(EiaTypeCategory.class)
//				.addClass(EiaType.class)
//				.addClass(EiaTypeComponent.class)
//				.addClass(EiaTypeMaintenancePlan.class)
//				.addClass(EiaTypeComponentService.class)
//				.addClass(EiaTypeComponentServiceLocal.class)
//				.addClass(EiaTypeComponentServiceRemote.class)
//				.addClass(EiaTypeService.class)
//				.addClass(EiaTypeServiceRemote.class)
//				.addClass(EiaDamagePriorityEnum.class)
//				.addClass(EiaService.class)
//				.addClass(ExternalProvider.class)
//				.addClass(ExternalProviderService.class)
//				.addClass(ExternalProviderServiceRemote.class)
//				.addClass(EiaServiceTest.class)
//				.addClass(EiaServiceRemote.class)
//				.addClass(EiaPreventiveMaintenance.class)
//				.addClass(ExternalProvider.class)
//				.addClass(Facility.class)
//				.addClass(FacilityCategory.class)
//				.addClass(Function.class)
//				.addClass(GenderTypeEnum.class)
//				.addClass(GHAEJBException.class)
//				.addClass(GHAMessage.class)
//				.addClass(GHAMessageId.class)
//				.addClass(GHAEJBExceptionService.class)
//				.addClass(HasKey.class)
//				.addClass(Institution.class)
//				.addClass(InstitutionService.class)
//				.addClass(InstitutionServiceRemote.class)
//				.addClass(Job.class)
//				.addClass(JobCategory.class)
//				.addClass(JobPosition.class)
//				.addClass(LegalEntity.class)
//				.addClass(LegalEntityService.class)
//				.addClass(LegalEntityServiceRemote.class)
//				.addClass(LocationLevelEnum.class)
//				.addClass(LocationType.class)
//				.addClass(LanguageEnum.class)
//				.addClass(MaintenancePlan.class)
//				.addClass(MaintenancePlanStatus.class)
//				.addClass(MaintenancePlanCancelationOption.class)
//				.addClass(MaintenancePlanState.class)
//				.addClass(MaintenancePlanType.class)
//				.addClass(EiaMaintenanceState.class)
//				.addClass(MaintenancePlanificationType.class)
//				.addClass(MaintenanceProtocols.class)
//				.addClass(ActivityState.class)
//				.addClass(ActivityCategoryEnum.class)
//				.addClass(Manufacturer.class)
//				.addClass(ActivitySubCategoryEnum.class)
//				.addClass(Activity.class)
//				.addClass(RequiredResources.class)
//				.addClass(Module.class)
//				.addClass(Obu.class)
//				.addClass(ObuService.class)
//				.addClass(ObuServiceRemote.class)
//				.addClass(ProviderResourceTypeEnum.class)
//				.addClass(ProviderServicesEnum.class)
//				.addClass(ProviderPreferenceEnum.class)
//				.addClass(ProviderQualEnum.class)
//				.addClass(ProviderTypeEnum.class)
//				.addClass(ProviderRepresentEnum.class)
//				.addClass(Role.class)
//				.addClass(RoleService.class)
//				.addClass(RoleServiceRemote.class)
//				.addClass(RuntimeParameters.class)
//				.addClass(ServiceAndResource.class)
//				.addClass(ServiceResourceCategory.class)
//				.addClass(TimePeriodEnum.class)
//				.addClass(View.class)
//				.addClass(WorkingArea.class)
//				.addClass(WarrantySinceEnum.class)
//				.addClass(GHAMessageType.class)
//				.addClass(Activity.class)
//				.addClass(Bsp.class)
//				.addClass(ServiceAndResourceType.class)
//				.addClass(SubProtocolAndChecklist.class)
//				.addClass(MaintenanceActivity.class)
//				.addClass(MaintenanceActivityService.class)
//				.addClass(MaintenanceActivityServiceRemote.class)
//				.addClass(SubProtocolAndCheklistService.class)
//				.addClass(SubProtocolAndCheklistServiceRemote.class)
//				.addClass(SubProtocolAndCheklistServiceLocal.class)
//				.addClass(UILog.class)
//				.addClass(UILogService.class)
//				.addClass(UILogServiceLocal.class)
//				.addClass(UILogServiceRemote.class)
//				.addClass(SSOUser.class)
//				.addClass(SSOUserService.class)
//				.addClass(SSOUserServiceRemote.class)
//				.addClass(GHALog.class)
//				.addClass(EiaCorrectiveMaintenance.class)
//				.addClass(EiaMaintenance.class)
//				.addClass(UserLogonStatusEnum.class)
//				.addClass(MaintenanceCancelationCause.class)
//				.addClass(MaintenancePlanificationState.class)
//				.addAsResource("test-persistence.xml",
//						"META-INF/persistence.xml")
//				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//	}
//
//	@EJB(lookup = "java:global/test/SubProtocolAndCheklistService!"
//			+ "org.fourgeeks.gha.ejb.gmh.RequiredResourcesServiceRemote")
//	private RequiredResourcesServiceRemote serviceRemote;
//
//	private RequiredResources requiredR;
//	private Material material;
//	private EiaType eiaType;
//	private Activity activity;
//
//	/** */
//	@Before
//	public void set() {
//		activity = new Activity();
//		activity.setId(1);
//
//		eiaType = new EiaType();
//		
//	}
//
//	/** */
//	@After
//	public void unset() {
//
//	}
//
//	/** */
//	@Test
//	public void test() {
//		final String sep = "\n---------------------------------------\n";
//
//		System.out.println("TESTING SUBPROTOCOL AND CHECKLIST SERVICE\n");
//
//		System.out.println(sep + "getAllTest" + sep);
//		getAllTest();
//		System.out.println(sep + "getAllTest2" + sep);
//		getAllTest2();
//		System.out.println(sep + "findByParentActivityTest" + sep);
//		findByParentActivityTest();
//		System.out.println(sep + "findByIdTest" + sep);
//		findByIdTest();
//		System.out.println(sep + "saveTest" + sep);
//		saveTest();
//		System.out.println(sep + "updateTest" + sep);
//		updateTest();
//		System.out.println(sep + "deleteTest" + sep);
//		deleteTest();
//		System.out.println(sep + "deleteListTest" + sep);
//		deleteListTest();
//	}
//
//	private void findByParentActivityTest() {
//		try {
//			Activity activity = new Activity();
//			activity.setId(7);
//
//			final List<SubProtocolAndChecklist> result = serviceRemote
//					.findByParentActivity(activity);
//
//			Assert.assertEquals(3, result.size());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private void findByIdTest() {
//		try {
//			final SubProtocolAndChecklist result = serviceRemote.find(1);
//			Assert.assertNotNull(result);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void getAllTest() {
//		try {
//			final List<SubProtocolAndChecklist> result = serviceRemote.getAll();
//			Assert.assertEquals(3, result.size());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void getAllTest2() {
//		final int itemsExpected = 3;
//		try {
//			final List<SubProtocolAndChecklist> result = serviceRemote.getAll(
//					0, 3);
//			Assert.assertEquals(itemsExpected, result.size());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void saveTest() {
//		try {
//			subProtocol = serviceRemote.save(subProtocol);
//			Assert.assertNotNull(subProtocol);
//		} catch (GHAEJBException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void updateTest() {
//		final int ordinalExpected = 5;
//		try {
//			subProtocol.setOrdinal(ordinalExpected);
//			SubProtocolAndChecklist result = serviceRemote.update(subProtocol);
//			final int ordinalResult = result.getOrdinal();
//
//			Assert.assertEquals(ordinalExpected, ordinalResult);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void deleteTest() {
//		final int itemsExpected = 3;
//		try {
//			serviceRemote.delete(subProtocol.getId());
//
//			Assert.assertEquals(itemsExpected, serviceRemote.getAll().size());
//		} catch (GHAEJBException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private void deleteListTest() {
//		final int itemsExpected = 2;
//
//		Activity activity2 = new Activity();
//		activity2.setId(2);
//
//		Activity parentActivity2 = new Activity();
//		parentActivity2.setId(6);
//
//		SubProtocolAndChecklist subProtocol2 = new SubProtocolAndChecklist();
//		subProtocol2.setActivity(activity2);
//		subProtocol2.setParentActivity(parentActivity2);
//		subProtocol2.setOrdinal(5);
//
//		List<SubProtocolAndChecklist> list = new ArrayList<SubProtocolAndChecklist>();
//		list.add(subProtocol);
//		list.add(subProtocol2);
//		try {
//			serviceRemote.delete(list);
//			Assert.assertEquals(itemsExpected, serviceRemote.getAll().size());
//		} catch (GHAEJBException e) {
//			e.printStackTrace();
//		}
//	}
// }