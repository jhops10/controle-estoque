# 📦 Sistema de Controle de Estoque com Notificações

API RESTful desenvolvida para gerenciar produtos e enviar **notificações por e-mail** sempre que o estoque de um item estiver abaixo do valor mínimo configurado.

---

## 🚀 Funcionalidades

- Cadastro, listagem, atualização e exclusão de produtos  
- Registro de estoque mínimo por produto  
- Envio de e-mail para produtos abaixo do limite  
- Histórico de notificações armazenado no banco de dados  
- Endpoint exclusivo para consultar notificações  

---

## 🛠️ Tecnologias Utilizadas

- **Java 17**  
- **Spring Boot 3**  
- **Spring Data JPA**  
- **Spring Validation**  
- **Spring Mail (JavaMailSender)**  
- **MySQL**  
- **MailTrap** (para simular envio de e-mails)  
- **Maven**

---

## 📬 Como funciona a notificação por e-mail?

1. O produto é cadastrado com um valor de `minimumStock`  
2. Se a `quantity` for menor que esse valor, uma **notificação é criada e enviada por e-mail**  
3. Todas as notificações são armazenadas e acessíveis via endpoint  

---

## 📄 Como rodar o projeto localmente

### ⚙️ Passos

```bash
# 1. Clone o repositório
git clone https://github.com/jhops10/controle-estoque.git

# 2. Acesse a pasta do projeto
cd controle-estoque

# 3. Configure o banco de dados e e-mail
#    Edite o arquivo src/main/resources/application.properties com:
#    - URL, usuário e senha do seu banco MySQL
#    - Credenciais SMTP do MailTrap

# 4. Execute o projeto com Maven
mvnw spring-boot:run
```
---

## 📚 Documentação (Swagger)
Esta API possui uma interface interativa gerada automaticamente com o Swagger UI, permitindo visualizar e testar todos os endpoints diretamente pelo navegador.

🔗 Após rodar o projeto localmente, acesse:

http://localhost:8080/swagger-ui.html

---

---

## 📌 Alguns Exemplos de Uso da API

### 🔸 Cadastrar Produto
`POST /api/products`

```json
{
  "name": "Fone XPTO",
  "description": "Ideal para ouvir músicas.",
  "quantity": 7,
  "price": 150,
  "supplierId": 1,
  "minimumStock": 5
}
```

### 🔸 Consultar Notificações
`GET /api/notifications`

```json
[
  {
    "id": 1,
    "productName": "Mouse ABC",
    "message": "Produto abaixo do estoque mínimo"
  }
]
```

### 🔸 Atualizar Produto
`PUT /api/products/{id}`

```json
{
  "name": "Fone Atualizado",
  "quantity": 15,
  "price": 199.99,
  "supplierId": 1,
  "minimumStock": 5
}
```

### 🔸 Listar Produtos
`GET /api/products`

```json
[
    {
        "id": 1,
        "name": "Notebook XPTO",
        "description": "Um excelente notbook, na cor preta com 16gb de memória. Ideal para trabalhar com programação.",
        "quantity": 5,
        "price": 1400.50,
        "supplier": {
            "id": 1,
            "name": "Fornecedor XPTO",
            "email": "contato@xpto.com"
        },
        "minimumStock": 5
    },
    {
        "id": 2,
        "name": "Mousepad XPTO",
        "description": "Mousepad preto com logo. Ideal para jogos de fps.",
        "quantity": 2,
        "price": 75.90,
        "supplier": {
            "id": 1,
            "name": "Fornecedor XPTO",
            "email": "contato@xpto.com"
        },
        "minimumStock": 5
    },
    {
        "id": 3,
        "name": "Fone XPTO",
        "description": "Fone XPTO. Ideal para ouvir as suas músicas preferidas.",
        "quantity": 4,
        "price": 150.00,
        "supplier": {
            "id": 1,
            "name": "Fornecedor XPTO",
            "email": "contato@xpto.com"
        },
        "minimumStock": 5
    }
]
```
---


