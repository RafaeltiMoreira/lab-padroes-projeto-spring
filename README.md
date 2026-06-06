# Sistema de Entrega por CEP - Design Patterns com Spring Boot

Projeto desenvolvido como desafio prático de Padrões de Projeto utilizando Java e Spring Boot.
O projeto consiste na criação de uma API REST simples para o gerenciamento de clientes e sistema de pedidos. 
Para enriquecer o fluxo, a API integra-se com o microsserviço externo do **ViaCEP** para buscar dados de endereço automaticamente com base no CEP fornecido, persistindo as informações em um banco de dados em memória.

## 🎯 Padrões Implementados

### Strategy

Cálculo de frete por modalidade de entrega:

* PAC
* SEDEX
* EXPRESSO

### Proxy

Cache de consulta de CEP utilizando integração ViaCEP.

### Chain of Responsibility

Validação de pedidos:

* ClienteValidator
* CepValidator
* ValorValidator

### Facade

Centralização das regras de negócio na classe PedidoServiceImpl.

### Singleton

Gerenciamento dos componentes pelo Spring Framework.

## 🖥️ Tecnologias

* Java
* Spring Boot
* Spring Data JPA - Persistência de dados
* Spring Web - Criação da API REST
* Spring Cloud OpenFeign - Consumo de API externa de forma declarativa
* H2 Database - Banco de dados em memória para testes rápidos
* Swagger/OpenAPI - Documentação interativa da API
* Maven

## 📌 Endpoints

### Clientes

```
GET /clientes/{id} - Buscar cliente por ID

PUT /clientes/{id}

DELETE /clientes/{id}

GET /clientes - Listar todos os clientes

POST /clientes - Cadastrar cliente
```

### Pedidos

```
POST /pedidos

GET /pedidos

GET /pedidos/{id}
```

## 👨‍💻 Autor

- Desenvolvido por [Rafael Moreira](https://github.com/RafaeltiMoreira)
