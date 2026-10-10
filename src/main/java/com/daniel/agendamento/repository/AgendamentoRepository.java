package com.daniel.agendamento.repository;

import com.daniel.agendamento.model.Agendamento;
import com.daniel.agendamento.model.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    @Query("""
        SELECT COUNT(a) > 0 FROM Agendamento a
        WHERE a.profissional.id = :profissionalId
          AND a.status = :status
          AND :inicio < a.dataHoraFim
          AND :fim > a.dataHoraInicio
        """)
    boolean existeConflito(@Param("profissionalId") Long profissionalId,
                           @Param("status") StatusAgendamento status,
                           @Param("inicio") LocalDateTime inicio,
                           @Param("fim") LocalDateTime fim);
}