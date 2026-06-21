CREATE TABLE tokens (
    id          SERIAL       PRIMARY KEY,
    value       VARCHAR(255) UNIQUE NOT NULL,
    user_handle VARCHAR(255)        NOT NULL,
    expires_at  TIMESTAMP           NOT NULL
);
