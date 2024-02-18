package com.vaibhav.orderservice.external.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {

    private long productId;

    private String productName;

    private long price;

    private long quantity;

}
