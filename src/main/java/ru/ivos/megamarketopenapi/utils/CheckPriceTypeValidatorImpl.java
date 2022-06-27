package ru.ivos.megamarketopenapi.utils;

import ru.ivos.megamarketopenapi.model.ShopUnitModel;
import ru.ivos.megamarketopenapi.model.ShopUnitType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author iVos 22.06.2022
 */

public class CheckPriceTypeValidatorImpl implements ConstraintValidator<CheckPriceTypeValidator, ShopUnitModel> {

    @Override
    public boolean isValid(ShopUnitModel shopUnitModel, ConstraintValidatorContext constraintValidatorContext) {
        if(shopUnitModel.getType() == ShopUnitType.CATEGORY && shopUnitModel.getPrice() != null){
            return false;
        } else if (shopUnitModel.getType() == ShopUnitType.OFFER && shopUnitModel.getPrice() == null){
            return false;}
        else return true;
    }
}
