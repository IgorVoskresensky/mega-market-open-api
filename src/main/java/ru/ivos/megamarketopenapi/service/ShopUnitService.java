package ru.ivos.megamarketopenapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ivos.megamarketopenapi.dto.*;
import ru.ivos.megamarketopenapi.exceptions.ResourceNotFoundException;
import ru.ivos.megamarketopenapi.model.ShopUnitModel;
import ru.ivos.megamarketopenapi.repository.ShopUnitRepository;
import ru.ivos.megamarketopenapi.utils.Utils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author iVos 22.06.2022
 */

@Service
public class ShopUnitService {

    @Autowired
    ShopUnitRepository shopUnitRepository;

    public void shopUnitSave(ShopUnitImportRequest shopUnitImportRequest) {

        for (int i = 0; i < shopUnitImportRequest.getItems().length; i++) {
            ShopUnitImport shopUnitImport = shopUnitImportRequest.getItems()[i];
            ShopUnitModel shopUnitModel = shopUnitRepository.findById(shopUnitImport.getId()).orElse(null);
            if(shopUnitModel == null){
                shopUnitModel = Utils.shopUnitModelCreate(shopUnitImport,
                        shopUnitImportRequest.getUpdateDate());
                shopUnitRepository.save(shopUnitModel);
            }
            else {
                if(shopUnitModel.getType() != shopUnitImport.getType()){
                    throw new IllegalArgumentException("");
                }
                shopUnitModel.setId(shopUnitModel.getId());
                shopUnitModel.setName(shopUnitModel.getName());
                shopUnitModel.setDate(shopUnitModel.getDate());
                shopUnitModel.setParentId(shopUnitModel.getParentId());
                shopUnitModel.setPrice(shopUnitModel.getPrice());
            }
            shopUnitRepository.save(shopUnitModel);
        }
    }

    public void shopUnitDelete(String id) {
        boolean isValid = id.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        if (!isValid) {
            throw new IllegalArgumentException("");
        }
        ShopUnitModel shopUnitModel = shopUnitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));

        List<ShopUnitModel> children = shopUnitRepository.findAllByParentId(id);
        shopUnitRepository.deleteAll(children);

        shopUnitRepository.delete(shopUnitModel);
    }

    public ShopUnit getOne(String id) {
        boolean isValid = id.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$");
        if (!isValid) {
            throw new IllegalArgumentException("");
        }
        ShopUnitModel shopUnitModel = shopUnitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(""));

        ShopUnit shopUnit = Utils.shopUnitCreate(shopUnitModel);
        List<ShopUnitModel> children = shopUnitRepository.findAllByParentId(id);
        ShopUnit[] shopUnitChildren = new ShopUnit[children.size()];
        int i = 0;
        for(ShopUnitModel child : children){
            ShopUnit dtoChild = Utils.shopUnitCreate(child);
            shopUnitChildren[i] = dtoChild;
            i++;
        }
        shopUnit.setChildren(shopUnitChildren);
        return shopUnit;
    }

    public ShopUnitStatisticResponse getUnitsUpdatedStatistic(String dateEnd){

        LocalDateTime dateEndUnit = LocalDateTime.parse(dateEnd, Utils.formatter);
        LocalDateTime dateStartUnit = dateEndUnit.minusHours(24);

        List<ShopUnitModel> stat = shopUnitRepository.findAllByDateBetween(dateStartUnit, dateEndUnit);
        ShopUnitStatisticUnit[] units = new ShopUnitStatisticUnit[stat.size()];
        for(int i = 0; i < stat.size(); i++){
            ShopUnitStatisticUnit s = Utils.shopUnitStatisticUnitCreate(stat.get(i));
            units[i] = s;
        }
        ShopUnitStatisticResponse shopUnitStatisticResponse = new ShopUnitStatisticResponse();
        shopUnitStatisticResponse.setShopUnitStatisticUnits(units);
        return shopUnitStatisticResponse;
    }

}
