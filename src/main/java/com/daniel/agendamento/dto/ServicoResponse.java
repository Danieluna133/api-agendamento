package com.daniel.agendamento.dto;

import com.daniel.agendamento.model.Servico;
import java.math.BigDecimal;

public record ServicoResponse(
        Long id,
        String nome,
        Integer duracaoMinutos,
        BigDecimal preco
) {
    public static ServicoResponse de(Servico servico) {
        return new ServicoResponse(
                servico.getId(),
                servico.getNome(),
                servico.getDuracaoMinutos(),
                servico.getPreco()
        );
    }
}