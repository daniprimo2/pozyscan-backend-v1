package com.gerenciador.frota.aplicacao.gerenciador.infra.service;

import com.gerenciador.frota.aplicacao.Util.Utils.UtilPaginacao;
import com.gerenciador.frota.aplicacao.autenticacao.model.RetornoServicoBase;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FornecedorFiltroRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.request.FornecedorRequest;
import com.gerenciador.frota.aplicacao.gerenciador.dto.response.SelectFornecedoresResponse;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.ContatoRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.EmailRepotitory;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.FornecedorRepository;
import com.gerenciador.frota.aplicacao.gerenciador.infra.repository.TelefoneRepository;
import com.gerenciador.frota.aplicacao.gerenciador.model.Contato;
import com.gerenciador.frota.aplicacao.gerenciador.model.Fornecedor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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

    public PageImpl<?> buscarFiltroFornecedor(FornecedorFiltroRequest fornecedorFiltroRequest,
                                           Pageable pageable) {
        List<Fornecedor> fornecedor = this.getFornecedors(fornecedorFiltroRequest);
        return UtilPaginacao.obterPaginacao(fornecedor, pageable);
    }

    private List<Fornecedor> getFornecedors(FornecedorFiltroRequest fornecedorFiltroRequest) {
        List<Fornecedor> listaDeFornecedores = new ArrayList<>();
        if (!fornecedorFiltroRequest.getIdFornecedor().isEmpty()) {
            log.info("[INFO] - Filtro pelo id do fornecedor.");
            listaDeFornecedores.add(fornecedorRepository.findById(Long.valueOf(fornecedorFiltroRequest.getIdFornecedor()))
                    .orElseThrow(() -> new RuntimeException("Filial não encontrado.")));
        } else {
            log.info("[INFO] - Filtro pelas demais informações do fornecedor.");
            listaDeFornecedores.addAll(fornecedorRepository.findAllByCnpjAndNome(
                    fornecedorFiltroRequest.getNomeFornecedor(),
                    fornecedorFiltroRequest.getCnpjFornecedor()));
        }

        return listaDeFornecedores;
    }

    public RetornoServicoBase deletarFornecedorPorId (Long id) {
        log.info("[START] - Deletar por filial");
        var fornecedor = fornecedorRepository.findById(id).get();
        fornecedorRepository.deletarFornecedor(id);
        log.info("[END] - Fornecedor excluido");
        return RetornoServicoBase.positivo("Fornecedor " + fornecedor.getNome() + " foi deletado.");
    }

    public Fornecedor buscarFornecedorPorId(Long fornecedorId) {
        return fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new RuntimeException("Fornecedor não encontrado"));
    }


    public List<Fornecedor> buscarTodosFornecedores() {
        return fornecedorRepository.findAll();
    }
}
