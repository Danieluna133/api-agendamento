package com.daniel.agendamento.service;

import com.daniel.agendamento.dto.ProfissionalRequest;
import com.daniel.agendamento.dto.ProfissionalResponse;
import com.daniel.agendamento.exception.RecursoNaoEncontradoException;
import com.daniel.agendamento.model.Profissional;
import com.daniel.agendamento.model.Servico;
import com.daniel.agendamento.repository.ProfissionalRepository;
import com.daniel.agendamento.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository repository;
    private final ServicoRepository servicoRepository;

    @Transactional
    public ProfissionalResponse criar(ProfissionalRequest dados) {
        Profissional profissional = new Profissional();
        profissional.setNome(dados.nome());
        profissional.setAtivo(dados.ativo() == null || dados.ativo());
        profissional.setServicos(buscarServicos(dados.servicoIds()));
        return ProfissionalResponse.de(repository.save(profissional));
    }

    @Transactional(readOnly = true)
    public List<ProfissionalResponse> listar() {
        return repository.findAll().stream().map(ProfissionalResponse::de).toList();
    }

    @Transactional(readOnly = true)
    public ProfissionalResponse buscar(Long id) {
        return ProfissionalResponse.de(buscarEntidade(id));
    }

    @Transactional
    public ProfissionalResponse atualizar(Long id, ProfissionalRequest dados) {
        Profissional profissional = buscarEntidade(id);
        profissional.setNome(dados.nome());
        if (dados.ativo() != null) {
            profissional.setAtivo(dados.ativo());
        }
        Set<Servico> novosServicos = buscarServicos(dados.servicoIds());
        profissional.getServicos().clear();
        profissional.getServicos().addAll(novosServicos);
        return ProfissionalResponse.de(repository.save(profissional));
    }

    @Transactional
    public void excluir(Long id) {
        repository.delete(buscarEntidade(id));
    }

    private Profissional buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Profissional não encontrado: " + id));
    }

    private Set<Servico> buscarServicos(Set<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new HashSet<>();
        }
        List<Servico> encontrados = servicoRepository.findAllById(ids);
        if (encontrados.size() != ids.size()) {
            throw new RecursoNaoEncontradoException("Um ou mais serviços informados não existem");
        }
        return new HashSet<>(encontrados);
    }
}