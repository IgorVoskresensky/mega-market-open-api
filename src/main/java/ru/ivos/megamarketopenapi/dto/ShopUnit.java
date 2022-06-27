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
@EqualsAndHashCode
public class ShopUnit {

    private String id;
    private String name;
    private String date;
    private String parentId;
    private ShopUnitType type;
    private Integer price;
    private ShopUnit[] children;

}
