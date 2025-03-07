CREATE TABLE tb_addresses (
    id UUID PRIMARY KEY,
    zip_code VARCHAR(10) NOT NULL,
    locality VARCHAR(30) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    neighborhood VARCHAR(30) NOT NULL,
    complement VARCHAR(30),
    number VARCHAR(10),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL
);