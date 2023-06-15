package com.eduardo.app.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {
    @Id
    @Column(name = "ci", unique = true)
    private String ci;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private String password;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<CuentaEntity> cuentas;
}
