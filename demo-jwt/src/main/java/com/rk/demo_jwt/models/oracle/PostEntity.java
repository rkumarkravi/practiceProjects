package com.rk.demo_jwt.models.oracle;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DEMO_JWT_POST")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", nullable = false)
    private Long post_id;
    private String postType;
    private String postDesc;
    @Column(updatable = false,nullable = false)
    private LocalDateTime localDateTime;

    @PrePersist
    protected void preInit(){
        localDateTime =LocalDateTime.now();
    }
}
