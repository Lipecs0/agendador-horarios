package com.projectTest.agendador_horarios.infrastructure.repository;

import com.projectTest.agendador_horarios.infrastructure.entity.Agendamento;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {


    Agendamento findByServicoAndDataHoraAgendamentoBetween(String servico, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);

    @Transactional
    void deleteByClienteAndDataHoraAgendamento(String cliente, LocalDateTime dataHoraAgendamento);

    List<Agendamento> findByDataHoraAgendamentoBetween(LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);

    Agendamento findByClienteAndDataHoraAgendamento(String cliente, LocalDateTime dataHoraAgendamento);
}
