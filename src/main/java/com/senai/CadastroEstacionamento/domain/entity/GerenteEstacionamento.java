package com.senai.CadastroEstacionamento.domain.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("GERENTEESTACIONAMENTO")
public class GerenteEstacionamento extends Usuario{

    @OneToMany(mappedBy = "gerenteEstacionamento")
    private List<Estacionamento> estacionamentos;
}
