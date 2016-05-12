package com.yunbao.m4.consumer.domainmodel;

import java.util.List;

import com.yunbao.m4.consumer.datamodel.CarDataModel;
import com.yunbao.m4.consumer.datamodel.DeskDataModel;
import com.yunbao.m4.consumer.datamodel.HouseDataModel;
import com.yunbao.m4.consumer.datamodel.RoomDataModel;
import com.yunbao.m4.consumer.datamodel.ShopDataModel;
import com.yunbao.m4.consumer.datamodel.TruckDataModel;
import com.yunbao.m4.model.CollectionModel;
import com.yunbao.m4.platform.datamodel.DataModel;
import com.yunbao.m4.platform.domainmodel.DomainModel;
import com.yunbao.m4.platform.domainmodel.DomainModelBuilder;

public class ProdDomainModelBuilder implements DomainModelBuilder {

	@Override
	public DomainModel build(DomainModel domainModel, List<DataModel> dataModels) {
		CollectionModel collectModel = new CollectionModel();
		for (DataModel dm : dataModels) {
			if (dm instanceof ShopDataModel) {
				collectModel.setShop(((ShopDataModel) dm).getShop());
			} else if (dm instanceof CarDataModel) {
				collectModel.setCar(((CarDataModel) dm).getProd());
			} else if (dm instanceof DeskDataModel) {
				collectModel.setDesk(((DeskDataModel) dm).getDesk());
			} else if (dm instanceof HouseDataModel) {
				collectModel.setHouse(((HouseDataModel) dm).getHouse());
			} else if (dm instanceof RoomDataModel) {
				collectModel.setRoom(((RoomDataModel) dm).getRoom());
			} else if (dm instanceof TruckDataModel) {
				collectModel.setTruck(((TruckDataModel) dm).getTruck());
			}
		}
		((ProdDomainModel) domainModel).setCollectModel(collectModel);
		return domainModel;

	}

}
