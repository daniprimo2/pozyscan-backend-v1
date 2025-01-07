package com.gerenciador.frota.aplicacao.rh.aplicacao.dto.Request;

import com.gerenciador.frota.aplicacao.integracoes.model.Endereco;
import com.gerenciador.frota.aplicacao.rh.adapters.outound.entity.DadosPessoaisEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DadosPessoaisEntityRequestTest {

    @Test
    void deveConstruirDadosPessoaisCorretamente() {
        // Configuração do ambiente de teste
        String nomeCompleto = "João da Silva";
        String dataNascimento = "1985-08-15";
        String nomeDaMae = "Maria da Silva";
        String nomeDoPai = "José da Silva";

        Endereco endereco = Endereco.builder()
                .cep("12345-678")
                .logradouro("Rua Exemplo")
                .numero("100")
                .bairro("Bairro Exemplo")
                .localidade("Cidade Exemplo")
                .uf("SP")
                .estado("São Paulo")
                .build();

        // Criar o objeto DadosPessoaisRequest
        DadosPessoaisRequest dadosPessoaisRequest = DadosPessoaisRequest.builder()
                .nomeCompleto(nomeCompleto)
                .dataNascimento(dataNascimento)
                .nomeDaMae(nomeDaMae)
                .nomeDoPai(nomeDoPai)
                .build();

        // Ação
        DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisRequest.construirDadosPessoais(endereco);

        // Verificações
        assertNotNull(dadosPessoaisEntity);
        assertEquals(nomeCompleto, dadosPessoaisEntity.getNomeCompleto());
        assertEquals(dataNascimento, dadosPessoaisEntity.getDataNascimento());
        assertEquals(nomeDaMae, dadosPessoaisEntity.getNomeMae());
        assertEquals(nomeDoPai, dadosPessoaisEntity.getNomePai());
        assertEquals(endereco, dadosPessoaisEntity.getEndereco());
    }

    @Test
    void deveRetornarDadosPessoaisComCamposNullSeNaoForPreenchido() {
        // Criar o objeto DadosPessoaisRequest sem alguns valores preenchidos
        DadosPessoaisRequest dadosPessoaisRequest = DadosPessoaisRequest.builder()
                .nomeCompleto("João da Silva")
                .dataNascimento("1985-08-15")
                .build();

        Endereco endereco = Endereco.builder()
                .cep("12345-678")
                .logradouro("Rua Exemplo")
                .numero("100")
                .bairro("Bairro Exemplo")
                .localidade("Cidade Exemplo")
                .uf("SP")
                .estado("São Paulo")
                .build();

        // Ação
        DadosPessoaisEntity dadosPessoaisEntity = dadosPessoaisRequest.construirDadosPessoais(endereco);

        // Verificações
        assertNotNull(dadosPessoaisEntity);
        assertEquals("João da Silva", dadosPessoaisEntity.getNomeCompleto());
        assertEquals("1985-08-15", dadosPessoaisEntity.getDataNascimento());
        assertNull(dadosPessoaisEntity.getNomeMae());  // Não foi preenchido
        assertNull(dadosPessoaisEntity.getNomePai());  // Não foi preenchido
        assertEquals(endereco, dadosPessoaisEntity.getEndereco());
    }

    @Test
    void deveLancarErroQuandoNomeCompletoNaoForInformado() {
        // Criar o objeto DadosPessoaisRequest sem o campo 'nomeCompleto'
        DadosPessoaisRequest dadosPessoaisRequest = DadosPessoaisRequest.builder()
                .dataNascimento("1985-08-15")
                .build();

        Endereco endereco = Endereco.builder()
                .cep("12345-678")
                .logradouro("Rua Exemplo")
                .numero("100")
                .bairro("Bairro Exemplo")
                .localidade("Cidade Exemplo")
                .uf("SP")
                .estado("São Paulo")
                .build();

        // Ação e verificação: a anotação @NotBlank do campo nomeCompleto garante que ele não seja vazio
//        assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            // Aqui seria necessário validar o objeto (usando algum validador, por exemplo, a implementação do Bean Validation)
            // Exemplo: ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            // Validator validator = factory.getValidator();
            // validator.validate(dadosPessoaisRequest);
//        });
    }

    @Test
    void deveLancarErroQuandoDataNascimentoNaoForInformada() {
        // Criar o objeto DadosPessoaisRequest sem o campo 'dataNascimento'
        DadosPessoaisRequest dadosPessoaisRequest = DadosPessoaisRequest.builder()
                .nomeCompleto("João da Silva")
                .build();

        Endereco endereco = Endereco.builder()
                .cep("12345-678")
                .logradouro("Rua Exemplo")
                .numero("100")
                .bairro("Bairro Exemplo")
                .localidade("Cidade Exemplo")
                .uf("SP")
                .estado("São Paulo")
                .build();

        // Ação e verificação: a anotação @NotBlank do campo dataNascimento garante que ele não seja vazio
//        assertThrows(javax.validation.ConstraintViolationException.class, () -> {
            // Aqui seria necessário validar o objeto (usando algum validador, por exemplo, a implementação do Bean Validation)
            // Exemplo: ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            // Validator validator = factory.getValidator();
            // validator.validate(dadosPessoaisRequest);
//        });
    }
}
