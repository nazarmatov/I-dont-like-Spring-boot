package com.example.kaganat.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "characters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    @Id
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "age")
    private Integer age;
    @Column(name = "force_power")
    private Double forcePower;
    @Column(name = "side")
    private String side;

}
