
package com.yunbao.m4.soap.producer;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ClassUtils;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.xml.transform.StringResult;

@WebIntegrationTest
public class CountryTests {

	private Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

	@Value("${local.server.port}")
	private int port = 8080;

	@Before
	public void init() throws Exception {
		marshaller.setPackagesToScan(ClassUtils.getPackageName(GetCountryRequest.class));
		marshaller.afterPropertiesSet();
	}

	@Test
	public void testSendAndReceive() {
		GetCountryRequest request = new GetCountryRequest();
		request.setName("Spain");
		WebServiceTemplate wst = new WebServiceTemplate(marshaller);

		Object response = wst.marshalSendAndReceive("http://localhost:8080/soap-producer/ws/", request);
		assertNotNull(response);

		StringResult result = new StringResult();
		marshaller.marshal(response, result);
		System.out.println(result.toString());
		assertNotNull(result.toString());
	}

}
