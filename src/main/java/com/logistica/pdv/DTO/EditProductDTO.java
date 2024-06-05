package com.logistica.pdv.DTO;

import com.logistica.pdv.entity.Seller;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EditProductDTO {
    private long Id;
    private String Description;
    private BigDecimal Price;
    private Long SellerID;
}
