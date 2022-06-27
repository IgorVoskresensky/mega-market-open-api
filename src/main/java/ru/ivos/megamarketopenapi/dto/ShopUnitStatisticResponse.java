package ru.ivos.megamarketopenapi.dto;

import lombok.*;

/**
 * @author iVos 21.06.2022
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShopUnitStatisticResponse {

    private ShopUnitStatisticUnit[] shopUnitStatisticUnits;
}
