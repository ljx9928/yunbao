package com.yunbao.m4.soap.consumer.dao;

import com.yunbao.m4.soap.consumer.weather.GetCityForecastByZIPResponse;

public class WeatherDAO {

	public void execute(String[] args){
		String zipCode = "94304";

		if (args.length > 0) {
			zipCode = args[0];
		}
		
		WeatherClient ws= new WeatherClient();
		GetCityForecastByZIPResponse response = ws.getCityForecastByZip(zipCode);
	}
}
