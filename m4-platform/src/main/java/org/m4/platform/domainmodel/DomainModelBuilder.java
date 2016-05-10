package org.m4.platform.domainmodel;

import java.util.List;

import org.m4.platform.datamodel.DataModel;

public interface DomainModelBuilder {
	public DomainModel build(DomainModel domainModel, List<DataModel> dataModels);
}
