package com.daniel.agendamento.repository;

import com.daniel.agendamento.model.HorarioTrabalho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioTrabalhoRepository extends JpaRepository<HorarioTrabalho, Long> {
}