package com.rk.demo_jwt.models.oracle;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "oracle_test_entity")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OracleTestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
}
