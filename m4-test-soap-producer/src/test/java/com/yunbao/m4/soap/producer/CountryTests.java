/*
 * Copyright 2014-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
