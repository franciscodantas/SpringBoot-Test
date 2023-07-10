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
@Table(name = "TB_CLIENTE", uniqueConstraints = {@UniqueConstraint(columnNames = "Cli_nome")})
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue( strategy =  GenerationType.AUTO)
    @Column (name = "client_id")
    private UUID id;

    @Column (name = "Cli_end", nullable = false)
    private String endereco;

    @Column (name = "Cli_nome", nullable = false)
    private String nome;

}
