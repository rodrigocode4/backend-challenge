# Desafio de código Back-end

O objetivo deste teste é avaliar conhecimento e experiência com Back-end com foco em Spring Boot.

O código existente foi criado com o [Spring initializr](https://start.spring.io/) e tem como objetivo fornecer dados iniciais em banco (h2) para a realização do teste. Acesse [Projeto Base](docs/projetoBase.md) para instruções de como rodar o projeto base.

## Instruções

- Faça um fork desse projeto.
- Siga as especificações abaixo.
  - Crie um README que explique como rodar a aplicação e acessar seus serviços. Ele será utilizado por nós para verificar a aplicação.
  - Testes automatizados são opcionais, mas super recomendados.
  - Para entrega deve ser realizado um PR (Pull request) para seu fork do projeto e o link do PR deverá ser enviado para o e-mail ***guilherme.miranda@intertrack.com.br*** com o título **Teste Back-End**
  - Você pode entregar o teste em até 10 dias, mas se precisar de mais tempo é só falar com gente!

## Especificações técnicas

Desenvolver uma aplicação REST que seja possível a recuperação de uma `lista de viagens` de determinado veículo. Este projeto possui um banco em memória (h2) que deve ser utilizado para tal.

Uma viagem deve possuir:

- vehicleId
- initialDateTime
- finalDateTime
- initialAddress
- finalAddress
- distanceInMeters (Diferença entre hodômetro final e inicial)
- totalTimeInMinutes

### Estruturas do banco:

```
Vehicle:
- id
- name
- description
- brand
- model

Position:
- id
- vehicle_id
- datetime  (timestamp em milisegundos)
- latitude
- longitude
- address
- ignition
- hodometro (distância percorrida em metros)
```

Uma `viagem` neste projeto é dado como a primeira posição com ignição ligada, até uma posição com ignição desligada, ex:

Dado estas posições de um determinado veículo:

```
Position1: ignition - on
Position2: ignition - on
Position3: ignition - off
Position4: ignition - off
Position5: ignition - on
Position6: ignition - off
Position7: ignition - on
```

O resultado deve ser:

```
Travel1: Position1 à Position3
Travel2: Position5 à Position6
Travel3: Position7
```

## Obrigatório utilizar

- Arquitetura de APIs RESTFUL
- Git

## Critérios de Avaliação

- Sua aplicação atende todos os requisitos e regras?
- Organização do projeto
- Qualidade do código
- Uso do Git
- Testes automatizados (opcional)

## Boa sorte!

Sinta-se a vontade para tirar **qualquer dúvida** que surgir durante o desenvolvimento.
