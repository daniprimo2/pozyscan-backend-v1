package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FornecedorRequest;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.ContatoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.EmailRepotitory;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.FornecedorRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.TelefoneRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Contato;
import com.gerenciador.frota.aplicacao.gerenciador.model.Fornecedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private EmailRepotitory emailRepotitory;

    @Autowired
    private TelefoneRepository telefoneRepository;

    public RetornoServicoBase adicionarFornecedor(FornecedorRequest fornecedorRequest) {
        Fornecedor fornecedorSalvo = fornecedorRepository.save(fornecedorRequest.construirFornecedor(fornecedorRequest));
        System.out.println(fornecedorRequest.getContatos().size());
        if (!fornecedorRequest.getContatos().isEmpty()) {

            fornecedorRequest.converterDeRequestParaDominioContato(fornecedorSalvo).forEach((contato -> {
                Contato contatoSalvo = contatoRepository.save(Contato.builder().nome(contato.getNome()).fornecedor(contato.getFornecedor()).build());
                System.out.println(contatoSalvo);
                contato.getTelefones().forEach(telefone -> {
                    telefone.setContato(contatoSalvo);
                    telefoneRepository.save(telefone);
                });
                contato.getEmails().forEach(emailContato -> {
                    emailContato.setContato(contatoSalvo);
                    emailRepotitory.save(emailContato);
                });
            }));


        }


        return RetornoServicoBase.positivo("Fornecedor "+ fornecedorSalvo.getNome() + " foi salvo.");
    }

}
