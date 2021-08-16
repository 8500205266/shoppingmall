package com.shoppingmall.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="ITEMTABLE")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Items
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    private Integer unitPrice;
    private Integer offerId;
}
