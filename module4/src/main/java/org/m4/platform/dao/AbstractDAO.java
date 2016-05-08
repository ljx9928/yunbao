package org.m4.platform.dao;

import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

public class AbstractDAO<T> extends RestTemplate implements Callable<T> {

	public Map params;
	public String url;
	// ResponseExtractor responseExtractor;

	@Override
	public <T> T execute(String url, HttpMethod method, RequestCallback requestCallback,
			ResponseExtractor<T> responseExtractor, Map<String, ?> urlVariables) throws RestClientException {
		return super.execute(url, method, requestCallback, responseExtractor, urlVariables);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public T call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}

}
