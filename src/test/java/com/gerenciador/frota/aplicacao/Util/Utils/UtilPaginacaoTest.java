package com.gerenciador.frota.aplicacao.Util.Utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilPaginacaoTest {

    private List<String> lista;

    @BeforeEach
    void setUp() {
        lista = Arrays.asList("item1", "item2", "item3", "item4", "item5", "item6", "item7", "item8", "item9");
    }

    // Testando o método obterPaginacao com pageSize e pageNumber
    @Test
    void testObterPaginacao_comTamanhoPaginaENumeroPagina() {
        List<?> resultado = UtilPaginacao.obterPaginacao(lista, 3, 1);
        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals("item1", resultado.get(0));
        assertEquals("item2", resultado.get(1));
        assertEquals("item3", resultado.get(2));
    }

    @Test
    @Disabled("verificar por que nao esta testando")
    void testObterPaginacao_comNumeroPaginaMaiorQueTamanhoDaLista() {
        List<?> resultado = UtilPaginacao.obterPaginacao(lista, 3, 4);
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("item10", resultado.get(0));  // Se tiver 10 itens ou mais.
    }

    @Test
    void testObterPaginacao_comPaginaZero() {
        List<?> resultado = UtilPaginacao.obterPaginacao(lista, 3, 0);
        assertNotNull(resultado);
        assertEquals(3, resultado.size());  // Pagina 1, mas o código trata o caso de pageNumber = 0 como página 1.
        assertEquals("item1", resultado.get(0));
    }

    @Test
    void testObterPaginacao_comPaginaNegativa() {
        List<?> resultado = UtilPaginacao.obterPaginacao(lista, 3, -1);
        assertNotNull(resultado);
        assertEquals(0, resultado.size());  // Caso de erro com página negativa, retorna lista vazia.
    }

    @Test
    void testObterPaginacao_comListaVazia() {
        List<?> resultado = UtilPaginacao.obterPaginacao(Arrays.asList(), 3, 1);
        assertNotNull(resultado);
        assertEquals(0, resultado.size());  // Lista vazia deve retornar uma lista vazia.
    }

    @Test
    void testObterPaginacao_comPaginaMaiorQueLista() {
        List<?> resultado = UtilPaginacao.obterPaginacao(lista, 3, 5);
        assertNotNull(resultado);
        assertEquals(0, resultado.size());  // Se a página não existir, deve retornar lista vazia.
    }

    // Testando o método obterPaginacao com Pageable
    @Test
    @Disabled("verificar por que nao esta testando")
    void testObterPaginacao_comPageable() {
        Pageable pageable = PageRequest.of(0, 3);  // Página 1 (base 0)
        PageImpl<?> resultado = UtilPaginacao.obterPaginacao(lista, pageable);

        assertNotNull(resultado);
        assertEquals(3, resultado.getContent().size());  // Deve retornar 3 itens
        assertEquals("item1", resultado.getContent().get(0));
        assertEquals("item2", resultado.getContent().get(1));
        assertEquals("item3", resultado.getContent().get(2));
        assertEquals(9, resultado.getTotalElements());  // Total de 9 elementos na lista original
        assertEquals(3, resultado.getTotalPages());  // 9 elementos com página de 3 itens cada = 3 páginas
    }

    @Test
    @Disabled("verificar por que nao esta testando")
    void testObterPaginacao_comPageableUltimaPagina() {
        Pageable pageable = PageRequest.of(2, 3);  // Página 3 (base 0)
        PageImpl<?> resultado = UtilPaginacao.obterPaginacao(lista, pageable);

        assertNotNull(resultado);
        assertEquals(3, resultado.getContent().size());  // Última página tem 3 itens
        assertEquals("item7", resultado.getContent().get(0));
        assertEquals("item8", resultado.getContent().get(1));
        assertEquals("item9", resultado.getContent().get(2));
        assertEquals(9, resultado.getTotalElements());
        assertEquals(3, resultado.getTotalPages());  // Total de 3 páginas
    }

    @Test
    void testObterPaginacao_comPageableComPaginaMaiorQueTotalPaginas() {
        Pageable pageable = PageRequest.of(10, 3);  // Página 11 (base 0), fora do alcance
        PageImpl<?> resultado = UtilPaginacao.obterPaginacao(lista, pageable);

        assertNotNull(resultado);
        assertEquals(0, resultado.getContent().size());  // Não existe página 11
        assertEquals(9, resultado.getTotalElements());
        assertEquals(3, resultado.getTotalPages());  // Total de 3 páginas
    }
}