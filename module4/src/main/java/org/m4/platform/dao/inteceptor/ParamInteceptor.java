package org.m4.platform.dao.inteceptor;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class ParamInteceptor implements ClientHttpRequestInterceptor{

	public ClientHttpResponse intercept(HttpRequest paramHttpRequest, byte[] paramArrayOfByte,
			ClientHttpRequestExecution paramClientHttpRequestExecution) throws IOException {
		// TODO Auto-generated method stub
		
//		paramHttpRequest.g
//		paramHttpServletRequest.setAttribute("daoParams", param);
//		
		return null;
	}

}
