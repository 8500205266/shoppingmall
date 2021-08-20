package com.shoppingmall.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
/*
 * it has customerId and customerName variables
 */
public class CustomerDto
{
    private Integer customerId;
    private String customerName;
}
