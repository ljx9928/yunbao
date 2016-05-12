package com.yunbao.m4.platform.domainmodel;

import java.util.List;
import java.util.Map;

import com.yunbao.m4.platform.datamodel.DataModel;

public interface DomainModel {

	public void build(List<DataModel> dataModels);
	
	public Map<?, ?> getDataMap();
}
