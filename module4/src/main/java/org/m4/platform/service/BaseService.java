package org.m4.platform.service;

import java.util.Map;

import org.m4.platform.domainmodel.DomainModel;

public interface BaseService {
	public <T, V> DomainModel makeWSCall(Map<T, V> param) throws Exception;
}
