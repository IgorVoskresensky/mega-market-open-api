package ru.ivos.megamarketopenapi.model;

import lombok.*;
import ru.ivos.megamarketopenapi.utils.CheckPriceTypeValidator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author iVos 21.06.2022
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@CheckPriceTypeValidator
public class ShopUnitModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long shopUnitId;

    @Column(unique = true, nullable = false)
    private String id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(nullable = true)
    private String parentId;

    @Column(nullable = false)
    private ShopUnitType type;

    @Column(nullable = true)
    private Integer price;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "parent_id")
//    private List<ShopUnitModel> children;
}
