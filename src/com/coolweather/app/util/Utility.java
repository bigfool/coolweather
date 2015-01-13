package com.coolweather.app.util;

import com.coolweather.app.model.City;
import com.coolweather.app.model.CoolWeatherDB;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;
import android.text.TextUtils;

public class Utility {
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, 
			String response) {
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String pString : allProvinces) {
					String[] array = pString.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					coolWeatherDB.savedProvince(province);
				}
				return true;
			}
 		}
		return false;
	}

	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, 
			String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCitiesStrings = response.split(",");
			if (allCitiesStrings != null && allCitiesStrings.length > 0) {
				for (String cString : allCitiesStrings) {
					String[] arrayStrings = cString.split("\\|");
					City city = new City();
					city.setCityCode(arrayStrings[0]);
					city.setCityName(arrayStrings[1]);
					city.setProvinceId(provinceId);
					coolWeatherDB.savedCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, 
			String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCountiesStrings = response.split(",");
			if (allCountiesStrings != null && allCountiesStrings.length > 0) {
				for (String cString : allCountiesStrings) {
					String[] arrayStrings = cString.split("\\|");
					County county = new County();
					county.setCountyCode(arrayStrings[0]);
					county.setCountyName(arrayStrings[1]);
					county.setCityId(cityId);
					coolWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
}
