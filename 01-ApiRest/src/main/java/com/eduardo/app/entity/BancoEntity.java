package com.eduardo.app.entity;

import lombok.*;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "banco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BancoEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @OneToMany(mappedBy = "banco", fetch = FetchType.LAZY)
    private List<CuentaEntity> cuentas;

}
