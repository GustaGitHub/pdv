package com.logistica.pdv.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class NewProductDTO {
    private BigDecimal Price;
    private String Description;
    private Long SellerID;

}
