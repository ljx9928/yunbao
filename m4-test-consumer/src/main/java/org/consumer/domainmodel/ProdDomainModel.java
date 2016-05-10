package org.consumer.domainmodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.m4.platform.datamodel.DataModel;
import org.m4.platform.domainmodel.DomainModel;
import org.m4.platform.domainmodel.DomainModelBuilder;

import com.m4.domain.model.CollectionModel;

public class ProdDomainModel implements DomainModel {
	final String prodDomainModel = "ProdDomainModel";
	private DomainModelBuilder domainModelBuilder;

	private CollectionModel collectModel;
	private Map<String, DomainModel> map = new HashMap<String, DomainModel>();

	@Override
	public void build(List<DataModel> dataModels) {
		map.put(prodDomainModel, domainModelBuilder.build(this, dataModels));
	}

	@Override
	public Map<String, DomainModel> getDataMap() {
		return map;
	}

	public CollectionModel getCollectModel() {
		return collectModel;
	}

	public void setCollectModel(CollectionModel collectModel) {
		this.collectModel = collectModel;
	}

	public DomainModelBuilder getDomainModelBuilder() {
		return domainModelBuilder;
	}

	public void setDomainModelBuilder(DomainModelBuilder domainModelBuilder) {
		this.domainModelBuilder = domainModelBuilder;
	}
}
