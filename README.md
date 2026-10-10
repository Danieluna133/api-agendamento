# API de Agendamento

API REST para agendamento de serviços (exemplo: barbearia), desenvolvida em Java com Spring Boot. O sistema permite cadastrar serviços e profissionais e, nas próximas etapas, consultar horários livres e agendar sem conflitos de horário.

## Tecnologias

- Java 21
- Spring Boot 4 (Web MVC, Validation, Data JPA)
- PostgreSQL 16
- Flyway (versionamento do banco)
- Docker e Docker Compose
- Lombok
- Swagger / OpenAPI (springdoc)

## Arquitetura

O projeto segue a divisão em camadas:

```
controller  ->  service  ->  repository  ->  banco de dados
(HTTP)         (regras)      (consultas)
```

- `controller`: recebe as requisições e devolve as respostas
- `service`: regras de negócio
- `repository`: acesso ao banco (Spring Data JPA)
- `model`: entidades JPA
- `dto`: objetos de entrada e saída da API, com validação
- `exception`: tratamento padronizado de erros

## Como rodar

Pré-requisitos: JDK 21, Docker Desktop e Git.

1. Clone o repositório:
```
   git clone https://github.com/SEU-USUARIO/api-agendamento.git
   cd api-agendamento
```
2. Suba o banco de dados:
```
   docker compose up -d
```
3. Rode a aplicação:
```
   ./mvnw spring-boot:run
```
   No Windows (cmd ou PowerShell): `.\mvnw spring-boot:run`
4. Acesse a documentação interativa: http://localhost:8080/swagger-ui/index.html

As tabelas são criadas automaticamente pelo Flyway na primeira execução.

## Endpoints

### Serviços

| Método | Rota | Descrição |
|---|---|---|
| POST | `/servicos` | Cadastra um serviço |
| GET | `/servicos` | Lista os serviços |
| GET | `/servicos/{id}` | Busca um serviço |
| PUT | `/servicos/{id}` | Atualiza um serviço |
| DELETE | `/servicos/{id}` | Remove um serviço |

### Profissionais

| Método | Rota | Descrição |
|---|---|---|
| POST | `/profissionais` | Cadastra um profissional e os serviços que ele realiza |
| GET | `/profissionais` | Lista os profissionais |
| GET | `/profissionais/{id}` | Busca um profissional |
| PUT | `/profissionais/{id}` | Atualiza um profissional |
| DELETE | `/profissionais/{id}` | Remove um profissional |

Exemplo de cadastro de serviço:

```json
{
  "nome": "Corte masculino",
  "duracaoMinutos": 30,
  "preco": 45.00
}
```

## Modelo do banco

O diagrama completo está em [docs/diagrama-banco.md](docs/diagrama-banco.md) e os requisitos em [docs/requisitos.md](docs/requisitos.md).

## Padrão de erros

Todas as falhas retornam o mesmo formato:

```json
{
  "status": 400,
  "mensagem": "Dados inválidos",
  "detalhes": ["nome: O nome é obrigatório"]
}
```

## Roadmap

- [x] Planejamento e modelagem do banco
- [x] Projeto Spring Boot, Docker e PostgreSQL
- [x] Migrations (Flyway) e entidades JPA
- [x] CRUD de serviços
- [x] CRUD de profissionais
- [ ] Horários de trabalho dos profissionais
- [ ] Agendamento com validação de conflito de horários
- [ ] Consulta de horários livres
- [ ] Autenticação com Spring Security e JWT
- [ ] Testes unitários e de integração
- [ ] Dockerfile e deploy

## Autor

Daniel - [GitHub](https://github.com/Danieluna133)