
package com.yunbao.m4.soap.consumer.dao;

import java.text.SimpleDateFormat;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.yunbao.m4.platform.util.LogUtil;
import com.yunbao.m4.soap.consumer.weather.Forecast;
import com.yunbao.m4.soap.consumer.weather.ForecastReturn;
import com.yunbao.m4.soap.consumer.weather.GetCityForecastByZIP;
import com.yunbao.m4.soap.consumer.weather.GetCityForecastByZIPResponse;
import com.yunbao.m4.soap.consumer.weather.Temp;

public class WeatherClient extends WebServiceGatewaySupport {

	private static final org.apache.log4j.Logger log = LogUtil.getLogger(WeatherClient.class);

	public GetCityForecastByZIPResponse getCityForecastByZip(String zipCode) {

		GetCityForecastByZIP request = new GetCityForecastByZIP();
		request.setZIP(zipCode);

		log.debug("Requesting forecast for " + zipCode);

		GetCityForecastByZIPResponse response = (GetCityForecastByZIPResponse) getWebServiceTemplate()
				.marshalSendAndReceive(
						"http://wsf.cdyne.com/WeatherWS/Weather.asmx",
						request,
						new SoapActionCallback("http://ws.cdyne.com/WeatherWS/GetCityForecastByZIP"));

		return response;
	}

	public void printResponse(GetCityForecastByZIPResponse response) {

		ForecastReturn forecastReturn = response.getGetCityForecastByZIPResult();

		if (forecastReturn.isSuccess()) {
			log.info("Forecast for " + forecastReturn.getCity() + ", " + forecastReturn.getState());

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			for (Forecast forecast : forecastReturn.getForecastResult().getForecast()) {

				Temp temperature = forecast.getTemperatures();

				log.info(String.format("%s %s %s°-%s°", format.format(forecast.getDate().toGregorianCalendar().getTime()),
						forecast.getDesciption(), temperature.getMorningLow(), temperature.getDaytimeHigh()));
				log.info("");
			}
		} else {
			log.info("No forecast received");
		}
	}

}
