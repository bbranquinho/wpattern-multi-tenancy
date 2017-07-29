# WPattern Multi-Tenancy

Projeto que demonstra um exemplo de aplicação REST usando Multi-Tenancy. Para deteminar qual tenancy usar é preciso apenas passar no header da requisição qual é o tenant.

**Observação:** foi utilizada uma versão Milestone do Spring Boot, já que estava testando essa nova versão do Spring Boot. Para utilizar uma versão anterior que seja estável "não" devem ser necessárias alterações no projeto.

## 1. Requisitos

Para executar o projeto é necessária a instalação das seguintes ferramentas:

    1. JDK 1.8
    2. Gradle (versão 3.4+)
    3. Docker
    4. Docker Compose

Apesar de não ser necessário para rodar o projeto, é indicado o uso da IDE IntelliJ ([thank you Jetbrains](https://www.jetbrains.com/idea/)) para realizar novos desenvolvimentos.

## 2. Configuração do Projeto

Se for utilizado o Docker Compose, não é necessária realizar nenhuma configuração. Se optar por não usar o Docker Compose é preciso que tenha um PostgreSQL instalado e a seguinte configuração é preciso que seja feita. Crie no PostgreSQL um banco de dados com o nome **wpattern_multi_tenancy**, usuário **user** e senha **password**. Posteriormente, execute o script **./scripts/database.sql** no banco **wpattern_multi_tenancy**. Este script realiza a criação dos esquemas e tabelas.

## 3. Executando o Projeto

Para executar o projeto, inicialmente é preciso iniciar o Docker Compose. Para isso, é preciso apenas executar o seguinte comando.

```sh
$ docker-compose up
```

Posteriormente, para executar o projeto é necessário rodar os seguintes comandos dentro da pasta raiz.

```sh
$ gradle clean build
$ java -jar build/libs/wpattern-multi-tenancy-1.0-SNAPSHOT.jar
```

A aplicação conta com um servidor de aplicação embarcado, sendo acessível na porta 8080.

## 4. Testando o Projeto

Sem contar o schema *public*, foram criados por padrão 2 schemas: *tenant_01* e *tenant_02*. Para a escolha de qual tenant utilizar é preciso apenas passar no Header da requisição **Tenant-Header** e o valor do tenant. Caso não seja informado um tenant, por padrão é utilizado o schema *public*.

Foi disponibilizada uma interface no Swagger para acessar os serviços. Contudo, até o momento ele acessa apenas o tenant padrão (**public**).

A seguir são mostrados alguns exemplos de serviços para o tenant **tenant_01**, ou seja, no schema **tenant_01**.

* Buscar todos os usuários do **tenant_01**.

```sh
$ curl -v --header 'Tenant-Header: tenant_01' http://localhost:8080/user
```

* Adicionar o usuário com nome *User 1* no tenant **tenant_01**.

```sh
$ curl -v -X POST --header 'Content-Type: application/json' --header 'Tenant-Header: tenant_01' -d '{ "name": "User 1" }' http://localhost:8080/user
```

* Atualizar o usuário com o ID *1* do tenant **tenant_01**.

```sh
$ curl -v -X PUT --header 'Content-Type: application/json' --header 'Tenant-Header: tenant_01' -d '{ "id": 1, "name": "Update 1" }' http://localhost:8080/user
```

* Deletar o usuário com o ID *1* do tenant **tenant_01**.

```sh
$ curl -v -X DELETE --header 'Content-Type: application/json' --header 'Tenant-Header: tenant_01' -d '{ "id": 1 }' http://localhost:8080/user
```

* Buscar o usuário de ID 1 pelo ID.

```sh
$ curl -v --header 'Tenant-Header: tenant_01' http://localhost:8080/user/1
```
