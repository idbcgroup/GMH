package org.fourgeeks.gha.webclient.client.UI;

import java.util.List;

import org.fourgeeks.gha.domain.ess.BaseRole;
import org.fourgeeks.gha.domain.gar.BuildingLocation;
import org.fourgeeks.gha.domain.gar.Obu;
import org.fourgeeks.gha.domain.glm.ExternalProvider;
import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.baserole.BaseRoleModel;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;
import org.fourgeeks.gha.webclient.client.buildinglocation.BuildingLocationModel;
import org.fourgeeks.gha.webclient.client.externalprovider.ExternalProviderModel;
import org.fourgeeks.gha.webclient.client.manufacturer.ManufacturerModel;
import org.fourgeeks.gha.webclient.client.obu.ObuModel;

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
	private List<BaseRole> roles;
	private List<ExternalProvider> externalProviders;

	{
		// Inititalization of the invalidation policy
		Timer t = new Timer() {
			@Override
			public void run() {
				invalidateCache();
			}
		};
		t.scheduleRepeating(CACHE_TIME);
	}

	protected void invalidateCache() {
		brands = null;
		manufacturers = null;
		buildingLocations = null;
		obus = null;
		roles = null;
		externalProviders = null;
	}

	/**
	 * @param callback
	 */
	public void getExternalProviders(
			GHAAsyncCallback<List<ExternalProvider>> callback) {
		// Avoiding synchronization problems
		List<ExternalProvider> localCopy = externalProviders;
		if (localCopy == null)
			getExternalProvidersFromServer(callback);
		else
			callback.onSuccess(localCopy);

	}

	private void getExternalProvidersFromServer(
			final GHAAsyncCallback<List<ExternalProvider>> callback) {
		ExternalProviderModel
				.getAll(new GHAAsyncCallback<List<ExternalProvider>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(List<ExternalProvider> result) {
						externalProviders = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getBaseRoles(GHAAsyncCallback<List<BaseRole>> callback) {
		// Avoiding synchronization problems
		List<BaseRole> localCopy = roles;
		if (localCopy == null)
			getBaseRolesFromServer(callback);
		else
			callback.onSuccess(localCopy);

	}

	private void getBaseRolesFromServer(
			final GHAAsyncCallback<List<BaseRole>> callback) {
		BaseRoleModel.getAll(new GHAAsyncCallback<List<BaseRole>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(List<BaseRole> result) {
						roles = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getObus(GHAAsyncCallback<List<Obu>> callback) {
		// Avoiding synchronization problems
		List<Obu> localCopy = obus;
		if (localCopy == null)
			getObusFromServer(callback);
		else
			callback.onSuccess(localCopy);

	}

	private void getObusFromServer(final GHAAsyncCallback<List<Obu>> callback) {
		ObuModel.getAll(new GHAAsyncCallback<List<Obu>>() { // AyncCallback
			// subclass

			@Override
			public void onSuccess(List<Obu> result) {
				obus = result;
				// Avoiding synchronization problems
				callback.onSuccess(result);
			}
		});
	}

	/**
	 * @param callback
	 */
	public void getBuildingLocations(
			GHAAsyncCallback<List<BuildingLocation>> callback) {
		// Avoiding synchronization problems
		List<BuildingLocation> localCopy = buildingLocations;
		if (localCopy == null)
			getBuildingLocationsFromServer(callback);
		else
			callback.onSuccess(localCopy);

	}

	private void getBuildingLocationsFromServer(
			final GHAAsyncCallback<List<BuildingLocation>> callback) {
		BuildingLocationModel
				.getAll(new GHAAsyncCallback<List<BuildingLocation>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(List<BuildingLocation> result) {
						buildingLocations = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getBrands(GHAAsyncCallback<List<Brand>> callback) {
		// Avoiding synchronization problems
		List<Brand> localBrands = brands;
		if (localBrands == null)
			getBrandsFromServer(callback);
		else {
			callback.onSuccess(localBrands);
		}
	}

	private void getBrandsFromServer(
			final GHAAsyncCallback<List<Brand>> callback) {
		BrandModel.getAll(new GHAAsyncCallback<List<Brand>>() { // AyncCallback
																// subclass

					@Override
					public void onSuccess(List<Brand> result) {
						brands = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}

	/**
	 * @param callback
	 */
	public void getManufacturesrs(GHAAsyncCallback<List<Manufacturer>> callback) {
		// Avoiding synchronization problems
		List<Manufacturer> localManufacturers = manufacturers;
		if (localManufacturers == null)
			getManufacturersFromServer(callback);
		else {
			callback.onSuccess(localManufacturers);
		}
	}

	private void getManufacturersFromServer(
			final GHAAsyncCallback<List<Manufacturer>> callback) {
		ManufacturerModel.getAll(new GHAAsyncCallback<List<Manufacturer>>() { // AyncCallback
					// subclass

					@Override
					public void onSuccess(List<Manufacturer> result) {
						manufacturers = result;
						// Avoiding synchronization problems
						callback.onSuccess(result);
					}
				});
	}
}