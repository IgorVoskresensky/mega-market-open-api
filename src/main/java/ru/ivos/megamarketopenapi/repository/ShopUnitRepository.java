package ru.ivos.megamarketopenapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ivos.megamarketopenapi.model.ShopUnitModel;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author iVos 21.06.2022
 */

@Repository
@Transactional
public interface ShopUnitRepository extends JpaRepository<ShopUnitModel, Long> {

    Optional<ShopUnitModel> findById(String id);

    List<ShopUnitModel> findAllByParentId(String id);

    List<ShopUnitModel> findAllByDate(String date);

    List<ShopUnitModel> findAllByDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd);
}