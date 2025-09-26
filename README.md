# Biblioteca Spring Boot

Projeto de gerenciamento de livros usando Spring Boot, Spring Data JPA e MySQL.

## Funcionalidades
- Listar todos os livros
- Buscar livro por ID, título ou autor
- Adicionar, atualizar e deletar livros

## Tecnologias
- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Maven

## Estrutura do Projeto
- controller: endpoints REST
- service: regras de negócio
- repository: acesso ao banco
- model: entidades JPA
- exception: exceções customizadas

## Executando
1. Configure o MySQL (`biblioteca_db`) e ajuste `application.properties`.
2. Rode `mvn clean install`.
3. Execute `mvn spring-boot:run`.
4. API disponível em `http://localhost:8080/livros`.

## Exemplos de Endpoints
- GET /livros
- GET /livros/{id}
- POST /livros
- PUT /livros/{id}
- DELETE /livros/{id}
- GET /livros/titulo/{titulo}
- GET /livros/autor/{autor}