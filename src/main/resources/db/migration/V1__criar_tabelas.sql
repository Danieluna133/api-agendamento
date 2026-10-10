CREATE TABLE usuario (
    id          BIGSERIAL PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    senha_hash  VARCHAR(100) NOT NULL,
    perfil      VARCHAR(20)  NOT NULL
);

CREATE TABLE profissional (
    id     BIGSERIAL PRIMARY KEY,
    nome   VARCHAR(100) NOT NULL,
    ativo  BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE servico (
    id               BIGSERIAL PRIMARY KEY,
    nome             VARCHAR(100) NOT NULL,
    duracao_minutos  INT NOT NULL,
    preco            NUMERIC(10,2) NOT NULL
);

CREATE TABLE profissional_servico (
    profissional_id  BIGINT NOT NULL REFERENCES profissional(id),
    servico_id       BIGINT NOT NULL REFERENCES servico(id),
    PRIMARY KEY (profissional_id, servico_id)
);

CREATE TABLE horario_trabalho (
    id               BIGSERIAL PRIMARY KEY,
    profissional_id  BIGINT NOT NULL REFERENCES profissional(id),
    dia_semana       INT NOT NULL,
    hora_inicio      TIME NOT NULL,
    hora_fim         TIME NOT NULL
);

CREATE TABLE agendamento (
    id                 BIGSERIAL PRIMARY KEY,
    usuario_id         BIGINT NOT NULL REFERENCES usuario(id),
    profissional_id    BIGINT NOT NULL REFERENCES profissional(id),
    servico_id         BIGINT NOT NULL REFERENCES servico(id),
    data_hora_inicio   TIMESTAMP NOT NULL,
    data_hora_fim      TIMESTAMP NOT NULL,
    status             VARCHAR(20) NOT NULL
);

CREATE INDEX idx_agendamento_profissional_inicio
    ON agendamento (profissional_id, data_hora_inicio);