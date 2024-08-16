package com.gerenciador.frota.aplicacao.gerenciador.model;

import com.gerenciador.frota.aplicacao.gerenciador.dto.response.SelectFornecedoresResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_fornecedor", schema = "sc_gerenciador")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fornecedor")
    private Long id;

    @Column(name = "nome_fornecedor")
    private String nome;

    @Column(name = "cnpj_fornecedor")
    private String cnpj;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_filial")
    private Filial filial;

    public SelectFornecedoresResponse tratarSelectsFornecedores() {
        return SelectFornecedoresResponse.builder()
                .idFornecedor(this.id)
                .nomeFornecedor(this.nome)
                .build();
    }
}
