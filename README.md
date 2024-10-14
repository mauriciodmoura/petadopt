
# PetAdopt - Sistema de Gestão de Adoção de Animais

Este projeto é uma API para gestão de adoção de animais focada inicialmente em cães e gatos, mas com potencial de ser expandida para outros tipos de animais no futuro. O sistema permite criar, atualizar e buscar informações sobre animais disponíveis para adoção, e utiliza **Spring Boot** como framework de desenvolvimento, **PostgreSQL** como banco de dados e **JaCoCo** para relatórios de cobertura de código.

## Funcionalidades

- Criar um animal com nome, descrição, URL da imagem, categoria, data de nascimento e status.
- Atualizar informações de um animal.
- Buscar animais por ID, status ou categoria.
- Listar todos os animais.
- API documentada com **Swagger**.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.4**
- **PostgreSQL**
- **Gradle**
- **JaCoCo** para cobertura de testes
- **Swagger** para documentação da API
- **Lombok** para simplificar o código

## Requisitos

Certifique-se de ter os seguintes itens instalados:

- **Java 21**
- **Gradle**
- **PostgreSQL**
- **Git**

## Instruções de Configuração

### Configurando o Banco de Dados

1. Instale o PostgreSQL (se ainda não estiver instalado).

   Para usuários de macOS usando Homebrew:

   ```bash
   brew install postgresql
   ```

2. Inicie o serviço do PostgreSQL:

   ```bash
   brew services start postgresql
   ```

3. Acesse o PostgreSQL:

   ```bash
   psql postgres
   ```

4. Crie o banco de dados e o usuário:

   ```sql
   CREATE DATABASE pet_adopt;
   CREATE USER admin WITH ENCRYPTED PASSWORD 'admin';
   GRANT ALL PRIVILEGES ON DATABASE pet_adopt TO admin;
   ```

### Configuração da Aplicação

A configuração do banco de dados está definida no arquivo `src/main/resources/application.properties`. Verifique se está correta:

```properties
# Configuração do banco de dados PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/pet_adopt
spring.datasource.username=admin
spring.datasource.password=admin

# Configurações do JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

Se necessário, ajuste as credenciais de banco de dados e URL conforme sua configuração local.

## Executando a Aplicação

### Clone o Repositório

```bash
git clone https://github.com/username/petadopt.git
cd petadopt
```

### Rodar a Aplicação

Execute o seguinte comando para rodar a aplicação localmente:

```bash
./gradlew bootRun
```

A API estará disponível em `http://localhost:8080`.

### Testando a Aplicação

Execute o seguinte comando para rodar os testes e gerar um relatório de cobertura:

```bash
./gradlew test jacocoTestReport
```

O relatório de cobertura será gerado em `build/jacocoHtml/index.html`.

## Documentação da API

A API está documentada com **Swagger**. Após rodar a aplicação, acesse a documentação através do link:

```
http://localhost:8080/swagger-ui/index.html
```

Aqui você poderá visualizar e testar os endpoints da API.

## Dependências Principais

- **Spring Boot**: Framework para criar aplicações Java rapidamente.
- **Spring Data JPA**: Integração com o banco de dados via JPA.
- **PostgreSQL**: Banco de dados relacional.
- **Lombok**: Gera automaticamente getters, setters e outros métodos para reduzir boilerplate.
- **Swagger**: Ferramenta para gerar documentação da API.
- **JaCoCo**: Plugin para medir a cobertura de testes.

### Dependências Definidas no `build.gradle`

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.3.4'
    id 'io.spring.dependency-management' version '1.1.6'
    id 'jacoco'
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0'
    compileOnly 'org.projectlombok:lombok'
    runtimeOnly 'org.postgresql:postgresql'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}

tasks.named('test') {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.8"
}

tasks.jacocoTestReport {
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation = file("${buildDir}/jacocoHtml")
    }
}
```

## Autor

- [Maurício de Moura](https://github.com/mauriciodmoura)

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
