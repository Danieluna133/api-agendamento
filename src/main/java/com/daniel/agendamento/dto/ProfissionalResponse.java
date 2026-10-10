package com.daniel.agendamento.dto;

import com.daniel.agendamento.model.Profissional;
import com.daniel.agendamento.model.Servico;
import java.util.Comparator;
import java.util.List;

public record ProfissionalResponse(
        Long id,
        String nome,
        Boolean ativo,
        List<ServicoResponse> servicos
) {
    public static ProfissionalResponse de(Profissional profissional) {
        List<ServicoResponse> servicos = profissional.getServicos().stream()
                .sorted(Comparator.comparing(Servico::getId))
                .map(ServicoResponse::de)
                .toList();
        return new ProfissionalResponse(
                profissional.getId(),
                profissional.getNome(),
                profissional.getAtivo(),
                servicos
        );
    }
}