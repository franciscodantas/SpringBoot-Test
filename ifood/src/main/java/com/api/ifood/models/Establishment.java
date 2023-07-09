package com.api.ifood.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "TB_ESTABELECIMENTO", uniqueConstraints = {@UniqueConstraint(columnNames = "Est_nome")})
public class Establishment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy =  GenerationType.AUTO)
    private UUID id;

    @Column (name = "Est_end", nullable = false)
    private String endereco;

    @Column (name = "Est_nome", nullable = false)
    private String nome;
}
