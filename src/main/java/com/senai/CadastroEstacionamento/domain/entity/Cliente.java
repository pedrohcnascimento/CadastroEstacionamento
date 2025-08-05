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
@DiscriminatorValue("CLIENTE")
public class Cliente extends Usuario{
    @OneToMany(mappedBy = "cliente")
    private List<Carro> carrosPossuidos;
}
