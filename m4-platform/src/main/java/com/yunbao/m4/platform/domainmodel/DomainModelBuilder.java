package com.yunbao.m4.platform.domainmodel;

import java.util.List;

import com.yunbao.m4.platform.datamodel.DataModel;

public interface DomainModelBuilder {
	public DomainModel build(DomainModel domainModel, List<DataModel> dataModels);
}
