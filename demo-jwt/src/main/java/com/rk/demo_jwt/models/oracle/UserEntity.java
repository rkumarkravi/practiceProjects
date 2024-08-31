package com.rk.demo_jwt.models.oracle;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "DEMO_JWT_USER")
@Builder
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID",referencedColumnName = "id")
    private List<PostEntity> postEntity;

    @Column(updatable = false,nullable = false)
    private LocalDateTime localDateTime;

    @PrePersist
    protected void preInit(){
        localDateTime =LocalDateTime.now();
    }
}
