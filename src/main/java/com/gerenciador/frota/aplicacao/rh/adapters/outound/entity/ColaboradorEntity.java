package com.gerenciador.frota.aplicacao.rh.adapters.outound.entity;

import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarDocumentoCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarEmailCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.casoDeUso.GerenciarTelefoneCasoDeUso;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request.*;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.enums.StatusColaborador;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.ColaboradorResponseList;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.DocumentosResponse;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.EmailResponse;
import com.gerenciador.frota.aplicacao.rh.aplicacao.dto.response.TelefonesResponse;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_COLABORADOR", schema = "sc_recursos_humanos")
public class ColaboradorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_COLABORADOR")
    private Long id;
    @Column(name = "DESC_ATIVIDADE")
    private String descricaoAtividade;
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private StatusColaborador status;
    @Column(name = "DATA_CONTRATACAO")
    private String dataContratacao;
    @Column(name = "DATA_DEMISSAOE")
    private String dataDemissao;

    @ManyToOne
    @JoinColumn(name = "COD_DADOS_PESSOAIS")
    private DadosPessoaisEntity dadosPessoaisEntity;


    @ManyToOne
    @JoinColumn(name = "COD_CARGO", nullable = false)
    private CargoEntity cargoEntity;

    @OneToMany(mappedBy = "colaboradorEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TelefonesEntity> telefones = new ArrayList<>();


    @OneToMany(mappedBy = "colaboradorEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<EmailEmtity> emailEmtities = new ArrayList<>();

    @OneToMany(mappedBy = "colaboradorEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<DocumentosEntity> documentos = new ArrayList<>();


    public ColaboradorEntity fromColaborador(ColaboradorResponseList colaboradorResponseList) {
        this.id = colaboradorResponseList.getId();
        this.descricaoAtividade = colaboradorResponseList.getDescricaoAtividade();
        this.status = colaboradorResponseList.getStatus();
        this.dataContratacao = colaboradorResponseList.getDataContratacao();
        this.dataDemissao = colaboradorResponseList.getDataDemissao();
        this.dadosPessoaisEntity = colaboradorResponseList.getDadosPessoaisEntity();
        this.cargoEntity = colaboradorResponseList.getCargoEntity();

        List<TelefonesEntity> telefones = new ArrayList<>();
        for (TelefonesResponse telefone : colaboradorResponseList.getTelefones()) {
            TelefonesEntity telefonesEntity1 = new TelefonesEntity();
            telefones.add(telefonesEntity1.fromTelefone(telefone));
        }
        this.telefones = telefones;

        List<EmailEmtity> emailEmtities = new ArrayList<>();
        for (EmailResponse email : colaboradorResponseList.getEmailS()) {
            EmailEmtity emailEmtity1 = new EmailEmtity();
            emailEmtities.add(emailEmtity1.fromEmail(email));
        }
        this.emailEmtities = emailEmtities;

        List<DocumentosEntity> documentos = new ArrayList<>();
        for (DocumentosResponse documentosResponse : colaboradorResponseList.getDocumentos()) {
            DocumentosEntity documentosEntity1 = new DocumentosEntity();
            documentos.add(documentosEntity1.fromDocumento(documentosResponse));
        }
        this.documentos = documentos;

        return this;
    }

    public void atualizarDadosPessoais(DadosPessoaisRequest dadosPessoaisRequest) {
        this.dadosPessoaisEntity.setNomeCompleto(dadosPessoaisRequest.getNomeCompleto());
        this.dadosPessoaisEntity.setNomeMae(dadosPessoaisRequest.getNomeDaMae());
        this.dadosPessoaisEntity.setNomePai(dadosPessoaisRequest.getNomeDoPai());
        this.dadosPessoaisEntity.setDataNascimento(dadosPessoaisRequest.getDataNascimento());
    }


    public void atualizarDocumentos(List<DocumentosRequest> documentosRequestList, GerenciarDocumentoCasoDeUso gerenciarDocumentoCasoDeUso) {
        for (DocumentosRequest documentosRequest : documentosRequestList) {
            for (DocumentosEntity documento : this.documentos) {
                if (documento.getTipoDocumento().equals(documentosRequest.getTipoDocumento())){
                    gerenciarDocumentoCasoDeUso.atualizarDocumento(documento.getId(),
                            documentosRequest.cosntruirDocumento(this));
                } else {
                    DocumentosEntity novoDocumento = documentosRequest.cosntruirDocumento(this);
                    gerenciarDocumentoCasoDeUso.salvarDocumento(novoDocumento);
                }
            }
        }

    }

    public void atualizarTelefones(List<TelefonesRequest> telefones, GerenciarTelefoneCasoDeUso gerenciarTelefoneCasoDeUso) {
        for (TelefonesRequest telefonesRequest : telefones) {
            if (telefonesRequest.getTipoContato() == null){
                throw new RuntimeException("Deve se informadp o tipo de contato.");
            }
            for (TelefonesEntity telefonesEntityAtuais : this.telefones) {
                System.out.println(telefonesEntityAtuais);
                System.out.println("Numero: "+ telefonesRequest.getTelefone()  + " Tipo: " + telefonesRequest.getTipoContato().getDescricao());
                System.out.println("Id "+ telefonesEntityAtuais.getId() + "Numero: "+ telefonesEntityAtuais.getTelefone()  + " Tipo: " + telefonesEntityAtuais.getTipoContato().getDescricao());
                if (telefonesEntityAtuais.getTipoContato().getDescricao()
                        .equals(telefonesRequest.getTipoContato().getDescricao())){
                    gerenciarTelefoneCasoDeUso.atualizarTelefones(telefonesEntityAtuais.getId(), telefonesEntityAtuais
                            .atualizarTelefone(telefonesRequest));
                }
            }

        }

    }

    public void atualizarEmails(List<EmailRequest> emails, GerenciarEmailCasoDeUso gerenciarEmailCasoDeUso) {
        for (EmailRequest emailRequest : emails) {
            for (EmailEmtity emailEmtityAtual : this.emailEmtities) {
                emailEmtityAtual.setColaboradorEntity(this);
                if (emailEmtityAtual.getTipoContato().getDescricao()
                        .equals(emailRequest.getTipoContato().getDescricao())) {
                    gerenciarEmailCasoDeUso.atualizarEmail(emailEmtityAtual.getId(), emailEmtityAtual.atualizarEmail(emailRequest));
                }
            }
        }

    }
}
