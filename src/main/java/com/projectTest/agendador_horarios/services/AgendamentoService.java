package com.projectTest.agendador_horarios.services;

import com.projectTest.agendador_horarios.infrastructure.entity.Agendamento;
import com.projectTest.agendador_horarios.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {

        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusMinutes(30);

        Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(),
                horaAgendamento, horaFim);


        if (Objects.nonNull(agendados)) {
            throw new RuntimeException("Este horário já está preenchido para o serviço selecionado.");
        }
        return agendamentoRepository.save(agendamento);

    }
    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente) {

        agendamentoRepository.deleteByClienteAndDataHoraAgendamento(cliente, dataHoraAgendamento);
    }

    public List<Agendamento> buscarAgendamentoPorData(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime horaFinalDia = data.atTime(19, 0);

        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, horaFinalDia);
    }

    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento) {
        Agendamento agenda = agendamentoRepository.findByClienteAndDataHoraAgendamento(cliente, dataHoraAgendamento);

        if (Objects.isNull(agenda)) {
            throw new RuntimeException("Horário não está preenchido");
        }

        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);
    }
}
