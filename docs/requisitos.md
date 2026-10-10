Requisitos funcionais
• Cadastro e login: o usuário pode ser cliente ou admin.
• Serviços: o admin cadastra serviços com nome, duração e preço.
• Profissionais: o admin cadastra profissionais e define quais serviços cada um faz e em quais dias e horários trabalha.
• Consulta de horários livres: o cliente escolhe serviço, profissional e data, e o sistema lista os horários disponíveis.
• Agendar, remarcar e cancelar: o cliente gerencia os próprios agendamentos.
• Painel do admin: lista da agenda do dia e por profissional.
Regras de negócio
Essas regras diferenciam o projeto de um CRUD simples:
1. Um profissional não pode ter dois agendamentos que se sobreponham no tempo.
2. O agendamento só pode ocorrer dentro do horário de trabalho do profissional.
3. O profissional precisa oferecer o serviço escolhido.
4. Não é permitido agendar no passado.
5. O cancelamento só é permitido até X horas antes (por exemplo, 2h).
6. O horário de término é calculado: início + duração do serviço.
Para a regra 1, a condição de conflito é: um novo agendamento conflita se novo.inicio < existente.fim e novo.fim > existente.inicio. Isso vira uma query no repository e um teste unitário.
Requisitos não funcionais
Senhas com hash (BCrypt), validação de entrada, respostas de erro padronizadas, documentação Swagger e cobertura mínima de testes nas regras de negócio.
Diagrama do banco
Entidades e relacionamentos:
• USUARIO 1:N AGENDAMENTO (realiza)
• PROFISSIONAL 1:N AGENDAMENTO (atende)
• SERVICO 1:N AGENDAMENTO (é agendado em)
• PROFISSIONAL 1:N HORARIO_TRABALHO (define)
• PROFISSIONAL N:N SERVICO, por meio de PROFISSIONAL_SERVICO (oferece)