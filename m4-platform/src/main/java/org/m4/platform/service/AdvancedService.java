package org.m4.platform.service;

import java.util.List;
import java.util.Map;

import org.m4.platform.domainmodel.DomainModel;

public interface AdvancedService {
	public   List<DomainModel> processBaseServices(Map<?, ?>  param) throws Exception ;
	
	public   void setParam(Map<?, ?> param) ;
}
