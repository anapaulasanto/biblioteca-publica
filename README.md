# 📚 Public Library

Bem-vindo à **Public Library**, uma aplicação Java robusta e moderna para gerenciamento de biblioteca de livros. Aqui, usuários podem cadastrar livros, categorias, avaliações, aluguéis e muito mais — tudo isso com integração à API do Google Books para uma experiência completa e inteligente. 🔍✨

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **JPA + Hibernate**
- **PostgreSQL**
- **Maven**
- **Google Books API**
- **Exceções customizadas com mensagens amigáveis**

---

## 🧠 Funcionalidades

### 👤 Usuários
- Criar, editar, deletar e listar usuários da biblioteca.
- Cada usuário pode alugar livros e deixar avaliações.

### 📖 Livros
- CRUD completo de livros.
- Caso o livro não seja encontrado, a aplicação tem a opção de consultar a [API do Google Books](https://developers.google.com/books), disponibilizando um pdf do livro escolhido.
- Suporte a categorias e avaliações por usuários.

### 🏷️ Categorias
- Cadastro e gerenciamento de categorias literárias (ex: Romance, Fantasia, Biografia).
- Cada livro está associado a uma categoria.

### 🔄 Aluguéis
- Registro de aluguéis de livros com controle de datas (retirada e devolução).
- Status do aluguel (pendente, devolvido, atrasado, reservado).

### 🌟 Avaliações
- Usuários podem avaliar livros com comentários e notas de 0 a 5.
- Exibe avaliações por usuários e por livros.

### 🚨 Tratamento de Erros
- Todas as rotas possuem tratamento elegante de exceções.
- Mensagens amigáveis e informativas para facilitar a depuração e o uso da API.

---

## 🌐 Integração com Google Books

Não encontrou o livro que deseja? Sem problemas!  
A aplicação dá a opção de consultar a **Google Books API**, podendo buscar por título do livro, pelo nome do autor ou pelo assunto.
A API irá retornar diversas informações sobre o livro - e o mais legal, disponibiliza um pdf do livro escolhido! 

---

## 🛠️ Como rodar o projeto localmente

1. **Clone o repositório:**

```bash
git clone https://github.com/seu-usuario/public-library.git
cd public-library
