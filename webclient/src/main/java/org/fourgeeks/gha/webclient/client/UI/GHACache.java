package org.fourgeeks.gha.webclient.client.UI;

import java.util.List;

import org.fourgeeks.gha.domain.ess.WorkingArea;
import org.fourgeeks.gha.domain.ess.auth.Role;
import org.fourgeeks.gha.domain.gar.Bpu;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Facility;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.Bsp;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.ServiceResourceCategory;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.domain.mix.Bpi;
import org.fourgeeks.gha.webclient.client.bpi.BpiModel;
import org.fourgeeks.gha.webclient.client.bpu.BpuModel;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;
import org.fourgeeks.gha.webclient.client.buildinglocation.BuildingLocationModel;
import org.fourgeeks.gha.webclient.client.eia.EIAModel;
import org.fourgeeks.gha.webclient.client.eiatype.EIATypeModel;
import org.fourgeeks.gha.webclient.client.eiatype.EiaTypeCategoryModel;
import org.fourgeeks.gha.webclient.client.externalprovider.ExternalProviderModel;
import org.fourgeeks.gha.webclient.client.facility.FacilityModel;
import org.fourgeeks.gha.webclient.client.manufacturer.ManufacturerModel;
import org.fourgeeks.gha.webclient.client.materialcategory.MaterialCategoryModel;
import org.fourgeeks.gha.webclient.client.obu.BspModel;
import org.fourgeeks.gha.webclient.client.obu.ObuModel;
import org.fourgeeks.gha.webclient.client.rolebase.RoleModel;
import org.fourgeeks.gha.webclient.client.workingarea.WorkingAreaModel;

import com.google.gwt.user.client.Timer;

/**
 * @author alacret
 * 
 */
public enum GHACache {
	/**
	 * 
	 */
	INSTANCE; // Single value

	/*
	 * The cache expiration time
	 */
	private static final int CACHE_TIME = 50000;
	private List<Brand> brands;
	private List<Manufacturer> manufacturers;
	private List<BuildingLocation> buildingLocations;
	private List<Obu> obus;
	private List<Role> roles;
	private List<ExternalProvider> externalProviders;
	private List<EiaType> eiaTypes;
	private List<Eia> eias;
	private List<WorkingArea> workingAreas;
	private List<Facility> facilities;
	private List<Bpi> bpis;
	private List<Bpu> bpus;
	private List<ServiceResourceCategory> serviceResourceCategories;
	private List<ServicesResourceCategory> servicesResourceCategories;
	private List<Bsp> bsps;

	{
		// Inititalization of the invalidation policy
		final Timer t = new Timer() {
			@Override
			public void run() {
				invalidateCache();
			}
		};
		t.scheduleRepeating(CACHE_TIME);
	}

	/**
	 * @param callback
	 */
	public void getBaseRoles(final GHAAsyncCallback<List<Role>> callback) {
		// Avoiding synchronization problems
		if (roles == null)
			getBaseRolesFromServer(callback);
		else
			callback.onSuccess(roles);

	}

	private void getBaseRolesFromServer(
			final GHAAsyncCallback<List<Role>> callback) {
		RoleModel.getAll(new GHAAsyncCallback<List<Role>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(final List<Role> result) {
						roles = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getBpis(final GHAAsyncCallback<List<Bpi>> callback,
			final boolean forceFromServer) {
		if (forceFromServer || bpis == null)
			getBpisFromServer(callback);
		else {
			callback.onSuccess(bpis);
		}
	}

	private void getBpisFromServer(final GHAAsyncCallback<List<Bpi>> callback) {
		BpiModel.getAll(new GHAAsyncCallback<List<Bpi>>() { // AsyncCallback
			// subclass

			@Override
			public void onSuccess(final List<Bpi> result) {
				bpis = result;
				// Avoiding synchronization problems
				callback.onSuccess(result);
			}
		});
	}

	/**
	 * @param callback
	 */
	public void getBpus(final GHAAsyncCallback<List<Bpu>> callback,
			final boolean forceFromServer) {
		if (forceFromServer || bpus == null)
			getBpusFromServer(callback);
		else {
			callback.onSuccess(bpus);
		}
	}

	private void getBpusFromServer(final GHAAsyncCallback<List<Bpu>> callback) {
		BpuModel.getAll(new GHAAsyncCallback<List<Bpu>>() {
			@Override
			public void onSuccess(final List<Bpu> result) {
				bpus = result;
				// Avoiding synchronization problems
				callback.onSuccess(result);
			}
		});
	}

	/**
	 * @param callback
	 * @param forceFromServer
	 */
	public void getBrands(final GHAAsyncCallback<List<Brand>> callback,
			final boolean forceFromServer) {
		if (forceFromServer || brands == null)
			getBrandsFromServer(callback);
		else
			callback.onSuccess(brands);
	}

	private void getBrandsFromServer(
			final GHAAsyncCallback<List<Brand>> callback) {
		BrandModel.getAll(new GHAAsyncCallback<List<Brand>>() { // AyncCallback
																// subclass

					@Override
					public void onSuccess(final List<Brand> result) {
						brands = result;
						callback.onSuccess(result);
					}
				});
	}

	public void getBsps(final GHAAsyncCallback<List<Bsp>> callback) {
		// Avoiding synchronization problems
		if (bsps == null)
			getBspsServer(callback);
		else
			callback.onSuccess(bsps);

	}

	private void getBspsServer(final GHAAsyncCallback<List<Bsp>> callback) {
		BspModel.getAll(new GHAAsyncCallback<List<Bsp>>() {

			@Override
			public void onSuccess(final List<Bsp> result) {
				bsps = result;
				callback.onSuccess(result);
			}
		});
	}

	/**
	 * @param callback
	 */
	public void getBuildingLocations(
			final GHAAsyncCallback<List<BuildingLocation>> callback) {
		// Avoiding synchronization problems
		if (buildingLocations == null)
			getBuildingLocationsFromServer(callback);
		else
			callback.onSuccess(buildingLocations);

	}

	private void getBuildingLocationsFromServer(
			final GHAAsyncCallback<List<BuildingLocation>> callback) {
		BuildingLocationModel
				.getAll(new GHAAsyncCallback<List<BuildingLocation>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(final List<BuildingLocation> result) {
						buildingLocations = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	public void getEias(final GHAAsyncCallback<List<Eia>> callback) {
		if (eias == null)
			getEiasFromServer(callback);
		else
			callback.onSuccess(eias);
	}

	private void getEiasFromServer(final GHAAsyncCallback<List<Eia>> callback) {
		EIAModel.getAll(new GHAAsyncCallback<List<Eia>>() {

			@Override
			public void onSuccess(final List<Eia> result) {
				eias = result;
				callback.onSuccess(eias);
			}
		});
	}

	/**
	 * get the list of eiatype categories
	 * 
	 * @param callback
	 */
	public void getEiaTypeCategories(
			final GHAAsyncCallback<List<ServiceResourceCategory>> callback) {
		if (serviceResourceCategories == null)
			getEiaTypeCategoriesFromServer(callback);
		else
			callback.onSuccess(serviceResourceCategories);
	}

	private void getEiaTypeCategoriesFromServer(
			final GHAAsyncCallback<List<ServiceResourceCategory>> callback) {
		EiaTypeCategoryModel
				.getAll(new GHAAsyncCallback<List<ServiceResourceCategory>>() {

					@Override
					public void onSuccess(final List<ServiceResourceCategory> result) {
						serviceResourceCategories = result;
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 * @param forceFromServer
	 */
	public void getEiaTypes(final GHAAsyncCallback<List<EiaType>> callback,
			final boolean forceFromServer) {
		if (forceFromServer || eiaTypes == null)
			getEiaTypesFromServer(callback);
		else
			callback.onSuccess(eiaTypes);
	}

	private void getEiaTypesFromServer(
			final GHAAsyncCallback<List<EiaType>> callback) {
		EIATypeModel.getAll(new GHAAsyncCallback<List<EiaType>>() {

			@Override
			public void onSuccess(final List<EiaType> result) {
				eiaTypes = result;
				callback.onSuccess(eiaTypes);
			}
		});
	}

	/**
	 * @param callback
	 */
	public void getExternalProviders(
			final GHAAsyncCallback<List<ExternalProvider>> callback) {
		// Avoiding synchronization problems
		if (externalProviders == null)
			getExternalProvidersFromServer(callback);
		else
			callback.onSuccess(externalProviders);

	}

	private void getExternalProvidersFromServer(
			final GHAAsyncCallback<List<ExternalProvider>> callback) {
		ExternalProviderModel
				.getAll(new GHAAsyncCallback<List<ExternalProvider>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(final List<ExternalProvider> result) {
						externalProviders = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	public void getFacilities(final GHAAsyncCallback<List<Facility>> callback) {
		if (facilities == null)
			getFacilitiesFromServer(callback);
		else
			callback.onSuccess(facilities);

	}

	/**
	 * @param callback
	 */

	private void getFacilitiesFromServer(
			final GHAAsyncCallback<List<Facility>> callback) {
		FacilityModel.getAll(new GHAAsyncCallback<List<Facility>>() {

			@Override
			public void onSuccess(final List<Facility> result) {
				facilities = result;
				callback.onSuccess(result);
			}
		});
	}

	private void getManufacturersFromServer(
			final GHAAsyncCallback<List<Manufacturer>> callback) {
		ManufacturerModel.getAll(new GHAAsyncCallback<List<Manufacturer>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(final List<Manufacturer> result) {
						manufacturers = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getManufacturesrs(
			final GHAAsyncCallback<List<Manufacturer>> callback,
			final boolean forceFromServer) {
		if (forceFromServer || manufacturers == null)
			getManufacturersFromServer(callback);
		else {
			callback.onSuccess(manufacturers);
		}
	}

	public void getMaterialCategories(
			final GHAAsyncCallback<List<ServicesResourceCategory>> callback) {
		if (servicesResourceCategories == null)
			getMaterialCategoriesFromServer(callback);
		else
			callback.onSuccess(servicesResourceCategories);
	}

	private void getMaterialCategoriesFromServer(
			final GHAAsyncCallback<List<ServicesResourceCategory>> callback) {
		MaterialCategoryModel
				.getAll(new GHAAsyncCallback<List<ServicesResourceCategory>>() {

					@Override
					public void onSuccess(final List<ServicesResourceCategory> result) {
						servicesResourceCategories = result;
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getObus(final GHAAsyncCallback<List<Obu>> callback) {
		// Avoiding synchronization problems
		if (obus == null)
			getObusFromServer(callback);
		else
			callback.onSuccess(obus);

	}

	private void getObusFromServer(final GHAAsyncCallback<List<Obu>> callback) {
		ObuModel.getAll(new GHAAsyncCallback<List<Obu>>() { // AyncCallback
			// subclass

			@Override
			public void onSuccess(final List<Obu> result) {
				obus = result;
				// Avoiding synchronization problems
				callback.onSuccess(result);
			}
		});
	}

	public void getWorkingAreas(
			final GHAAsyncCallback<List<WorkingArea>> callback) {
		if (workingAreas == null)
			getWorkingAreasFromServer(callback);
		else
			callback.onSuccess(workingAreas);

	}

	private void getWorkingAreasFromServer(
			final GHAAsyncCallback<List<WorkingArea>> callback) {
		WorkingAreaModel.getAll(new GHAAsyncCallback<List<WorkingArea>>() {

			@Override
			public void onSuccess(final List<WorkingArea> result) {
				workingAreas = result;
				callback.onSuccess(result);
			}
		});
	}

	private void invalidateCache() {
		brands = null;
		manufacturers = null;
		buildingLocations = null;
		obus = null;
		roles = null;
		externalProviders = null;
		eiaTypes = null;
		workingAreas = null;
		facilities = null;
		bpis = null;
		servicesResourceCategories = null;
		serviceResourceCategories = null;
	}

}