package org.fourgeeks.gha.webclient.client.UI;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.Brand;
import org.fourgeeks.gha.domain.gmh.Manufacturer;
import org.fourgeeks.gha.webclient.client.brand.BrandModel;
import org.fourgeeks.gha.webclient.client.manufacturer.ManufacturerModel;

import com.google.gwt.user.client.Timer;

public enum GHACache {
	INSTANCE; // Single value

	/*
	 * The cache expiration time
	 */
	private static final int CACHE_TIME = 50000;
	private List<Brand> brands;
	private List<Manufacturer> manufacturers;

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
	}

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