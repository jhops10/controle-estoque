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

## 🧪 Exemplo de JSON para cadastro de produto

```json
POST /api/products

{
  "name": "Fone XPTO",
  "description": "Fone XPTO. Ideal para ouvir as suas músicas preferidas.",
  "quantity": 7,
  "price": 150,
  "supplierId": 1,
  "minimumStock": 5
}
```
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

