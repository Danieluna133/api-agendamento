package com.daniel.agendamento.service;

import com.daniel.agendamento.dto.ServicoRequest;
import com.daniel.agendamento.dto.ServicoResponse;
import com.daniel.agendamento.exception.RecursoNaoEncontradoException;
import com.daniel.agendamento.model.Servico;
import com.daniel.agendamento.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository repository;

    @Transactional
    public ServicoResponse criar(ServicoRequest dados) {
        Servico servico = new Servico();
        preencher(servico, dados);
        return ServicoResponse.de(repository.save(servico));
    }

    @Transactional(readOnly = true)
    public List<ServicoResponse> listar() {
        return repository.findAll().stream().map(ServicoResponse::de).toList();
    }

    @Transactional(readOnly = true)
    public ServicoResponse buscar(Long id) {
        return ServicoResponse.de(buscarEntidade(id));
    }

    @Transactional
    public ServicoResponse atualizar(Long id, ServicoRequest dados) {
        Servico servico = buscarEntidade(id);
        preencher(servico, dados);
        return ServicoResponse.de(repository.save(servico));
    }

    @Transactional
    public void excluir(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Servico buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Serviço não encontrado: " + id));
    }

    private void preencher(Servico servico, ServicoRequest dados) {
        servico.setNome(dados.nome());
        servico.setDuracaoMinutos(dados.duracaoMinutos());
        servico.setPreco(dados.preco());
    }
}