package com.projectTest.agendador_horarios.controller;

import com.projectTest.agendador_horarios.infrastructure.entity.Agendamento;
import com.projectTest.agendador_horarios.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/agendamentos")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento(@RequestBody Agendamento agendamento){

        return ResponseEntity.ok().body(agendamentoService.salvarAgendamento(agendamento));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarAgendamento(@RequestParam LocalDateTime dataHoraAgendamento, @RequestParam String cliente){

        agendamentoService.deletarAgendamento(dataHoraAgendamento,cliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> buscarAgendamentoPorData(@RequestParam LocalDate data){

        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentoPorData(data));
    }

    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamento(@RequestBody Agendamento agendamento, @RequestParam String cliente, @RequestParam LocalDateTime dataHoraAgendamento){

        return ResponseEntity.accepted().body(agendamentoService.alterarAgendamento(agendamento, cliente, dataHoraAgendamento));
    }
}
