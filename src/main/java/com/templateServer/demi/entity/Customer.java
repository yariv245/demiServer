package com.templateServer.demi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native")
    @Column(name = "customer_id")
    private Long customerId;
    @Column
    private String name;
    @Column
    private String email;
    @Column(name = "mobile_number")
    private String mobileNumber;
}
