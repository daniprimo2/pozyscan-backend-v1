package com.gerenciador.frota.aplicacao.Util.Utils;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class UtilPaginacao {
    protected static List<?> obterPaginacao(List<?> lista, int pageSize, int pageNumber) {
        if (pageNumber == 0)
            pageNumber = 1;

        int startIndex = (pageNumber - 1) * pageSize;
        int emdIndex = Math.min(startIndex + pageSize, lista.size());

        if (emdIndex > lista.size()) {
            emdIndex = lista.size();
        }

        if (startIndex < 0 || startIndex >= lista.size() || emdIndex < 0) {
            return new ArrayList<>();
        }

        return lista.subList(startIndex, emdIndex);
    }


    public static PageImpl<?> obterPaginacao (List<?> lista, Pageable pageable) {

        List<?> listaPaginada = obterPaginacao(lista,
                pageable.getPageSize(),
                pageable.getPageNumber());

        Pageable pageable1Response = PageRequest.of(pageable.getPageNumber() - 1, pageable.getPageSize());
        return new PageImpl<>(listaPaginada, pageable1Response, lista.size());
    }


}
