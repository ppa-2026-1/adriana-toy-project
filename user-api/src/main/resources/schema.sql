-- SQLite
CREATE TABLE IF NOT EXISTS users (
    id         INTEGER      PRIMARY KEY AUTOINCREMENT,
    handle     VARCHAR(255) UNIQUE NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS roles (
    id   INTEGER      PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS profiles (
    id      INTEGER       PRIMARY KEY AUTOINCREMENT,
    name    VARCHAR(255),
    company VARCHAR(255),
    type    VARCHAR(255),
    FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS tickets (
    id   INTEGER      PRIMARY KEY AUTOINCREMENT,
    id_solicitante INTEGER NOT NULL,
    id_responsavel INTEGER NOT NULL,
    id_destinatario INTEGER NOT NULL,
    equipamento VARCHAR(255) NOT NULL,
    detalhes VARCHAR(255) NOT NULL,
    status TEXT CHECK(status IN ('PENDENTE', 'CANCELADO', 'CONCLUIDO')) NOT NULL,
    observadores VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (id_solicitante) REFERENCES users(id),
    FOREIGN KEY (id_responsavel) REFERENCES users(id),
    FOREIGN KEY (id_destinatario) REFERENCES users(id)
);