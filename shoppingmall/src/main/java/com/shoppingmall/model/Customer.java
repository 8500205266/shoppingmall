package com.shoppingmall.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name="CUSTOMERTABLE")
@NoArgsConstructor
@AllArgsConstructor
@Data
/**
 * it has customerId and customerName variables
 */
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;
    private String customerName;
}
