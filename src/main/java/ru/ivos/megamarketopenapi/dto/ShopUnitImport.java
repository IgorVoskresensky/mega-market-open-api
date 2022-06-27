package ru.ivos.megamarketopenapi.dto;

import com.sun.istack.NotNull;
import lombok.*;
import ru.ivos.megamarketopenapi.model.ShopUnitType;
import ru.ivos.megamarketopenapi.utils.CheckPriceTypeValidator;

/**
 * @author iVos 21.06.2022
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShopUnitImport{

    private @NotNull String id;
    private @NotNull String name;
    private String parentId;
    private @NotNull ShopUnitType type;
    private Integer price;
}
