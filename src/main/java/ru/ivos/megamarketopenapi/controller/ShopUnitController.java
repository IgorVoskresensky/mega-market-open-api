package ru.ivos.megamarketopenapi.controller;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ivos.megamarketopenapi.dto.ShopUnit;
import ru.ivos.megamarketopenapi.dto.ShopUnitImportRequest;
import ru.ivos.megamarketopenapi.dto.ShopUnitStatisticResponse;
import ru.ivos.megamarketopenapi.exceptions.ErrorDetail;
import ru.ivos.megamarketopenapi.exceptions.ResourceNotFoundException;
import ru.ivos.megamarketopenapi.service.ShopUnitService;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern;
import java.text.DateFormat;
import java.time.format.DateTimeParseException;

/**
 * @author iVos 21.06.2022
 */

@RestController
public class ShopUnitController {

    @Autowired
    private ShopUnitService shopUnitService;

    @GetMapping("/nodes/{id}")
    public ShopUnit getOne(@PathVariable String id){
        return shopUnitService.getOne(id);
    }

    @GetMapping("/sales")
    public ShopUnitStatisticResponse getUnitsUpdatedStatistic(@RequestParam String date){
        return shopUnitService.getUnitsUpdatedStatistic(date);
    }

    @PostMapping(value = "/imports", headers = "Content-Type=application/json")
    public ResponseEntity<HttpStatus> importRequest (@RequestBody ShopUnitImportRequest shopUnitImportRequest){
        shopUnitService.shopUnitSave(shopUnitImportRequest);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler({ConstraintViolationException.class, IllegalArgumentException.class, DateTimeParseException.class})
    public ResponseEntity<?> handleException(Exception e) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.BAD_REQUEST.value());
        errorDetail.setMessage("Validation Failed");
        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable @NotNull
                           @Pattern(regexp="^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$")
                                   String id){
        shopUnitService.shopUnitDelete(id);
        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleException(ResourceNotFoundException e) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setCode(HttpStatus.NOT_FOUND.value());
        errorDetail.setMessage("Item not found");
        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

}
