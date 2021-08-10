package com.shoppingmall.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="OFFERSTABLE")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Offers
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer offerId;
    private String offerType;
    private Integer discountValue;
}
