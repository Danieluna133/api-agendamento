package com.daniel.agendamento.exception;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<String> detalhes) {
}