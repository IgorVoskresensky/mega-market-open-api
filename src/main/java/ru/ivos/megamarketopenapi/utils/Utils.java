package ru.ivos.megamarketopenapi.utils;

import ru.ivos.megamarketopenapi.dto.ShopUnit;
import ru.ivos.megamarketopenapi.dto.ShopUnitImport;
import ru.ivos.megamarketopenapi.dto.ShopUnitStatisticUnit;
import ru.ivos.megamarketopenapi.model.ShopUnitModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author iVos 21.06.2022
 */

public class Utils {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static ShopUnitModel shopUnitModelCreate(ShopUnitImport shopUnitImport, String date){
        ShopUnitModel shopUnitModel = new ShopUnitModel();
        shopUnitModel.setId(shopUnitImport.getId());
        shopUnitModel.setName(shopUnitImport.getName());
        shopUnitModel.setDate(LocalDateTime.parse(date, Utils.formatter));
        shopUnitModel.setParentId(shopUnitImport.getParentId());
        shopUnitModel.setType(shopUnitImport.getType());
        shopUnitModel.setPrice(shopUnitImport.getPrice());
        return shopUnitModel;
    }

    public static ShopUnit shopUnitCreate(ShopUnitModel shopUnitModel){
        ShopUnit shopUnit = new ShopUnit();
        shopUnit.setId(shopUnitModel.getId());
        shopUnit.setName(shopUnitModel.getName());
        shopUnit.setDate(shopUnitModel.getDate().toString());
        shopUnit.setParentId(shopUnitModel.getParentId());
        shopUnit.setType(shopUnitModel.getType());
        shopUnit.setPrice(shopUnitModel.getPrice());
        return shopUnit;
    }

    public static ShopUnitStatisticUnit shopUnitStatisticUnitCreate(ShopUnitModel shopUnitModel){
        ShopUnitStatisticUnit shopUnitStatisticUnit = new ShopUnitStatisticUnit();
        shopUnitStatisticUnit.setId(shopUnitModel.getId());
        shopUnitStatisticUnit.setName(shopUnitModel.getName());
        shopUnitStatisticUnit.setParentId(shopUnitModel.getParentId());
        shopUnitStatisticUnit.setType(shopUnitModel.getType());
        shopUnitStatisticUnit.setPrice(shopUnitModel.getPrice());
        shopUnitStatisticUnit.setDate(shopUnitModel.getDate().toString());
        return shopUnitStatisticUnit;
    }

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
}
