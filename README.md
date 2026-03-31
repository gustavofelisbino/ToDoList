# To Do List Compose - Exercício de Persistência com Room Database

Este projeto é a base para um exercício prático da disciplina de Desenvolvimento Mobile. O objetivo principal é implementar a camada de persistência de dados em um aplicativo de lista de tarefas (To Do List) utilizando **Room Database** e **SharedPreferences** no Android, com interface construída em **Jetpack Compose**.

## Objetivo do Exercício

Compreender e aplicar conceitos de persistência de dados no desenvolvimento de aplicativos Android nativos, utilizando:
- **SQLite com Room Database** para armazenamento local.
- **SharedPreferences** para persistência de configurações simples.

## Funcionalidades do App

O aplicativo possui uma interface pronta, que permite:
- Visualizar uma lista de tarefas.
- Criar novas tarefas.
- Marcar tarefas como concluídas.

O objetivo do exercício é implementar a camada de persistência para que:
1. As tarefas criadas sejam salvas no banco de dados.
2. Ao abrir o aplicativo, a lista de tarefas seja carregada a partir do banco de dados.

## Instruções para o Exercício

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/thyerrimezzari/BaseToDoListCompose.git
   ```

2. **Analise a camada visual**:
   - Compreenda o funcionamento da interface construída com **Jetpack Compose**.
   - Identifique os pontos onde será necessário integrar a persistência de dados.

3. **Implemente o Room Database**:
   - Crie as entidades, DAOs e o banco de dados para armazenar as tarefas.
   - Garanta que as tarefas criadas sejam salvas no banco de dados.

4. **Integre a persistência com a interface**:
   - Ao criar uma nova tarefa, insira-a no banco de dados e atualize a lista exibida.
   - Ao abrir o aplicativo, carregue a lista de tarefas a partir do banco de dados.

5. **Utilize SharedPreferences**:
   - Adicione persistência para configurações simples, se necessário.

6. **Teste o aplicativo**:
   - Certifique-se de que as tarefas são salvas e carregadas corretamente.
   - Verifique se a interface reflete as alterações no banco de dados.

## Recursos Adicionais

- **Slides da aula**: Consulte os materiais fornecidos na disciplina.
- **Exemplos de código**: Utilize os aplicativos de exemplo como referência.
- **Documentação oficial**:
  - [Room Database](https://developer.android.com/training/data-storage/room)
  - [Jetpack Compose](https://developer.android.com/jetpack/compose)

## Conclusão

Este exercício é uma oportunidade prática para consolidar os conceitos de persistência de dados no desenvolvimento Android. Ao final, o aplicativo estará funcional, com uma camada de persistência integrada à interface.

