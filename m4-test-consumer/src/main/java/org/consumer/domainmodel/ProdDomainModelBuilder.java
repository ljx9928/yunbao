package org.consumer.domainmodel;

import java.util.List;

import org.consumer.datamodel.CarDataModel;
import org.consumer.datamodel.DeskDataModel;
import org.consumer.datamodel.HouseDataModel;
import org.consumer.datamodel.RoomDataModel;
import org.consumer.datamodel.ShopDataModel;
import org.consumer.datamodel.TruckDataModel;
import org.m4.platform.datamodel.DataModel;
import org.m4.platform.domainmodel.DomainModel;
import org.m4.platform.domainmodel.DomainModelBuilder;

import com.m4.domain.model.CollectionModel;

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
