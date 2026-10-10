package com.daniel.agendamento.repository;

import com.daniel.agendamento.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
}