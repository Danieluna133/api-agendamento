package com.daniel.agendamento.repository;

import com.daniel.agendamento.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
}