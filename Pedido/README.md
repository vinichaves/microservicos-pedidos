# 📦 Tech Challenge FIAP — Fase 4: Sistema de Pedidos Distribuído

Este projeto é composto por uma arquitetura de microsserviços com RabbitMQ para orquestração, simulando o fluxo completo de pedidos: **criação, processamento, pagamento e controle de estoque.**

## 🧱 Microsserviços

| Serviço                     | Porta  | Responsável por...                        |
|----------------------------|--------|-------------------------------------------|
| 📦 `pedido-service`        | 8083   | Criar pedidos, calcular total, enviar à fila |
| 📥 `pedido-receiver-service` | 8081 | Consumir fila RabbitMQ, orquestrar pagamento e estoque |
| 💳 `pagamento-service`     | 8084   | Simular processamento de pagamento        |
| 🧮 `estoque-service`       | 8082   | Gerenciar e baixar itens em estoque       |

---

## ⚙️ Tecnologias utilizadas

- Java 21
- Spring Boot 3.5.4
- Spring Cloud OpenFeign
- Spring Validation
- Spring AMQP (RabbitMQ)
- PostgreSQL
- Maven
- Swagger (Springdoc OpenAPI)
- Lombok
- Jacoco (Test Coverage)

---

## 📡 Arquitetura

```text
[ Front-end (futuro) ]
        |
        v
[pedido-service]
        |
   envia para ➡️ [RabbitMQ]
        |
        v
[pedido-receiver-service]
        ├──➤ [pagamento-service]
        └──➤ [estoque-service]
```

---

## 🚀 Como executar

### Pré-requisitos

- Java 21
- Docker
- RabbitMQ rodando na porta padrão (`5672` para AMQP, `15672` para UI)
- PostgreSQL rodando (configurado em `application.yml` de cada serviço)

### Subir RabbitMQ com Docker:

```bash
docker run -d --hostname my-rabbit --name rabbitmq \
  -p 5672:5672 -p 15672:15672 \
  rabbitmq:3-management
```

Acesse em: http://localhost:15672  
Login: `guest` • Senha: `guest`

---

### 📦 Build e execução local

```bash
# A partir da raiz de cada projeto:
./mvnw clean install
./mvnw spring-boot:run
```

---

## 📬 Endpoints principais

### `pedido-service`

| Método | Rota            | Descrição                  |
|--------|------------------|----------------------------|
| POST   | `/pedidos`       | Criar novo pedido e enviar à fila |
| GET    | `/pedidos`       | Listar todos os pedidos    |
| DELETE | `/pedidos/{id}`  | Deletar pedido por ID      |

### `pedido-receiver-service`

| Método | Rota                   | Descrição                  |
|--------|------------------------|----------------------------|
| POST   | `/entrada-pedidos`     | Recebe pedido (teste local, sem Rabbit) |

> Consome a fila `pedido.queue` do RabbitMQ para processar pedidos reais.

### `estoque-service`

| Método | Rota                         | Descrição            |
|--------|------------------------------|----------------------|
| POST   | `/estoques`                  | Cadastrar item       |
| PATCH  | `/estoques/{sku}/baixar`     | Baixar item          |
| PATCH  | `/estoques/{sku}/repor`      | Repor item           |

### `pagamento-service`

| Método | Rota            | Descrição                      |
|--------|------------------|--------------------------------|
| POST   | `/pagamentos`    | Simula aprovação/recusa de pagamento |

---

## 📚 Swagger

Após subir cada projeto, acesse:

- `pedido-service`: [http://localhost:8083/swagger-ui.html](http://localhost:8083/swagger-ui.html)
- `pedido-receiver-service`: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
- `pagamento-service`: [http://localhost:8084/swagger-ui.html](http://localhost:8084/swagger-ui.html)
- `estoque-service`: [http://localhost:8082/swagger-ui.html](http://localhost:8082/swagger-ui.html)

---

## ✅ Testes

Para gerar relatório de cobertura com **JaCoCo**:

```bash
./mvnw test
```

Abra o relatório em:

```
/target/site/jacoco/index.html
```

---

## ✨ Autor

Vinicius Chaves — *Tech Challenge FIAP - Fase 4*
