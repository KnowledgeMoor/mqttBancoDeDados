# Sistema de Monitoramento de Colmeias

Um sistema IoT para monitoramento de atividades de abelhas em colmeias que captura, armazena e transmite dados via MQTT.

## 📝 Visão Geral

O Sistema de Monitoramento de Colmeias é uma solução IoT projetada para apicultores que desejam monitorar a atividade e condições ambientais de suas colmeias. O sistema utiliza sensores para coletar dados como:

- Contagem de abelhas entrando e saindo da colmeia
- Temperatura interna e externa
- Umidade interna e externa
- Níveis de luminosidade
- Data e hora das medições

Estes dados são enviados para um servidor central onde são armazenados em um banco de dados SQL e simultaneamente publicados em um broker MQTT para permitir integrações e monitoramento em tempo real.

## ✨ Funcionalidades

- **Coleta de Dados**: Captura informações detalhadas sobre a atividade das abelhas e condições ambientais
- **Armazenamento Persistente**: Todos os dados são armazenados em um banco de dados SQL para análise histórica
- **Publicação MQTT**: Dados são publicados em tempo real via MQTT para integração com outros sistemas
- **API REST**: Endpoints para receber dados dos sensores e consultar informações armazenadas
- **Monitoramento em Tempo Real**: Possibilidade de construir dashboards conectados via MQTT

## 🏗️ Arquitetura do Sistema

O sistema segue uma arquitetura em três camadas:

1. **Camada de Sensores**:
   - Sensores de contagem de entrada/saída
   - Sensores de temperatura e umidade
   - Sensor de luminosidade

2. **Camada de Processamento e Armazenamento**:
   - Aplicação Spring Boot
   - Banco de dados MySQL
   - Integração com broker MQTT (HiveMQ)

3. **Camada de Visualização**:
   - Aplicações cliente que se inscrevem no tópico MQTT

## 🛠️ Tecnologias Utilizadas

- **Backend**:
  - Java 17
  - Spring Boot
  - JPA/Hibernate
  - Eclipse Paho MQTT Client
  - Jackson para serialização JSON

- **Comunicação**:
  - MQTT (via HiveMQ Broker)
  - REST API

- **Armazenamento**:
  - MySQL

- **Sensores**:
  - Arduino/ESP8266
  - Sensores infravermelhos para contagem
  - DHT22 para temperatura e umidade
  - Fotoresistor para luminosidade

## 💾 Modelo de Dados

### Entidade SensorData

A entidade principal `SensorData` contém os seguintes atributos:

| Atributo              | Tipo      | Descrição                                           |
|-----------------------|-----------|-----------------------------------------------------|
| id                    | Long      | Identificador único gerado automaticamente          |
| numSaidas             | int       | Número de abelhas que saíram da colmeia             |
| numEntradas           | int       | Número de abelhas que entraram na colmeia           |
| humidadeInterna       | double    | Percentual de umidade dentro da colmeia             |
| humidadeExterna       | double    | Percentual de umidade no ambiente externo           |
| temperaturaInterna    | double    | Temperatura em °C dentro da colmeia                 |
| temperaturaExterna    | double    | Temperatura em °C no ambiente externo               |
| luminosidade          | double    | Nível de luminosidade (em lux ou percentual)        |
| dataHora              | String    | Data e hora da coleta no formato ISO                |

## ⚙️ Configuração e Execução

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- MySQL 8.0 ou superior
- Conexão com a Internet (para o broker MQTT HiveMQ)

### Configuração do Banco de Dados

Configure seu banco de dados MySQL no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/colmeias_db
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

### Passos para Execução

1. Clone o repositório:
   ```bash
   git clone [URL_DO_REPOSITORIO]
   cd mqtt_abelhas
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

## 📡 Integração MQTT

O sistema utiliza o broker MQTT público da HiveMQ (`wss://broker.hivemq.com:8884`) para publicação dos dados. Os dados dos sensores são publicados no tópico `test/iftm/gabriel` em formato JSON.

Exemplo de payload MQTT:

```json
{
  "num_saidas": 25,
  "num_entradas": 23,
  "humidade_interna": 65.5,
  "humidade_externa": 42.3,
  "temperatura_interna": 35.2,
  "temperatura_externa": 28.7,
  "luminosidade": 75.8,
  "dataHora": "2023-04-30T14:25:36"
}
```

### Inscrição em Tópicos MQTT

Para receber os dados em tempo real, você pode assinar o tópico usando qualquer cliente MQTT:

```
Tópico: test/iftm/gabriel
QoS: 2
```

Recomendamos o uso de clientes como:
- [MQTT Explorer](http://mqtt-explorer.com/)
- [MQTT.fx](https://mqttfx.jensd.de/)
- [HiveMQ WebSocket Client](https://www.hivemq.com/demos/websocket-client/)

## 🔌 API REST

### Publicar Dados do Sensor

```
POST /publish
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "num_saidas": 25,
  "num_entradas": 23,
  "humidade_interna": 65.5,
  "humidade_externa": 42.3,
  "temperatura_interna": 35.2,
  "temperatura_externa": 28.7,
  "luminosidade": 75.8,
  "dataHora": "2023-04-30T14:25:36"
}
```

Resposta: `200 OK`
```
Data stored successfully in MySQL!
```

## 📊 Monitoramento em Tempo Real

Com os dados publicados via MQTT, você pode criar dashboards em tempo real usando ferramentas como:

- [Node-RED](https://nodered.org/)
- [Grafana](https://grafana.com/) com plugin MQTT
- [ThingsBoard](https://thingsboard.io/)

## 🗺️ Roadmap

Funcionalidade planejada para versões futuras:

**Interface Web**:
   - Dashboard personalizado para visualização de dados
   - Gráficos e estatísticas de atividade
