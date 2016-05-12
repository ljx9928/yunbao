package com.yunbao.m4.platform.dao;

import java.util.Map;
import java.util.concurrent.Callable;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.yunbao.m4.platform.util.LogUtil;

public class AbstractDAO<T> extends RestTemplate implements Callable<T> {

	public Map params;
	public String url;
	public String responseType;
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

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}

	public T call() throws Exception {
		LogUtil.debug(AbstractDAO.class, "Calling AbstractDAO to get " + responseType + "...");
		return (T) super.getForObject(url, Class.forName(responseType), params);
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

}
