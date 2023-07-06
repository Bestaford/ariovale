package ru.bestaford.ariovale.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bestaford.ariovale.util.Sex;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profile_data")
public class ProfileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "money", nullable = false, precision = 19, scale = 2)
    private BigDecimal money;
}