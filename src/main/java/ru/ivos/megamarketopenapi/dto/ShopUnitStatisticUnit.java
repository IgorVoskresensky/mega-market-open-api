package ru.ivos.megamarketopenapi.dto;

import lombok.*;
import ru.ivos.megamarketopenapi.model.ShopUnitType;

import java.time.LocalDateTime;

/**
 * @author iVos 21.06.2022
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShopUnitStatisticUnit {

    private String id;
    private String name;
    private String parentId;
    private ShopUnitType type;
    private Integer price;
    private String date;
}
