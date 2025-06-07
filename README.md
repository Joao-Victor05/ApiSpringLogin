# ApiSpringLogin

API RESTful em Java com Spring Boot, Docker, H2 e Swagger para operações CRUD de produtos.

## Funcionalidades
- CRUD de produtos (Create, Read, Update, Delete)
- Documentação automática com Swagger
- Banco de dados em memória H2
- Deploy via Docker

## Como rodar com Docker
1. Gere o JAR do projeto:
   ```sh
   ./mvnw clean package -DskipTests
   ```
   Ou no Windows:
   ```sh
   .\mvnw.cmd clean package -DskipTests
   ```
2. Construa a imagem Docker:
   ```sh
   docker build -t apispringlogin .
   ```
3. Rode o container:
   ```sh
   docker run -d -p 8080:8080 --name apispringlogin apispringlogin
   ```

## Endpoints principais
- Listar produtos: `GET /produtos`
- Buscar produto por ID: `GET /produtos/{id}`
- Criar produto: `POST /produtos`
- Atualizar produto: `PUT /produtos/{id}`
- Deletar produto: `DELETE /produtos/{id}`

## Documentação Swagger
Acesse em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Console do banco H2
Acesse em: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuário: `sa`
- Senha: (em branco)

## Exemplo de JSON para criar produto
```json
{
  "nome": "Produto Teste",
  "preco": 10.5
}
```

## Observações
- O campo `id` é gerado automaticamente.
- O projeto é para fins didáticos e pode ser expandido conforme necessidade.
