# Gestão salarial de Professores

## 📌 O que o código faz?

Este sistema calcula e armazena o salário de professores indicados, oferecendo suporte a três modalidades contratuais:

- **CLT**
- **PJ**
- **Horista**

Na sua primeira versão, o sistema apenas realizava os cálculos e exibia os resultados no console. Agora, com a adição de um sistema CRUD, os dados também são armazenados em um banco de dados, permitindo maior flexibilidade e persistência das informações.

---

## 🧾 Primeira Versão

A versão inicial do projeto era bastante simples:

- O usuário informava o nome do professor, o tipo de contrato e o valor recebido.
- O sistema processava essas informações e imprimia os dados no console.
- O código **não era orientado a objetos**, faltava tratamento de erros e seguia poucas boas práticas de programação.
- Continha muitos comentários e repetições desnecessárias.

---

## ✅ Versão Final

A versão atual apresenta diversas melhorias significativas:

- Código **orientado a objetos**, facilitando manutenção e extensibilidade.
- Adoção de **boas práticas de programação**, resultando em um código mais limpo e legível.
- Implementação de **tratamento de erros** e **testes unitários**, reduzindo a chance de falhas.
- Integração com **banco de dados** para armazenamento e gerenciamento de professores.
- Além dos cálculos, agora é possível **salvar**, **editar** e **consultar** professores e suas informações.

---


## Testes

Este projeto utiliza o framework [JUnit 5](https://junit.org/junit5/) para a execução de testes automatizados.

### Como rodar os testes

1. Compile o projeto no terminal:
   ```sh
   javac -cp "lib/*" -d bin src/*.java test/CalculadoraTest.java
   ```

2. Execute os testes usando o JUnit Platform Console:
   ```sh
   java -jar lib/junit-platform-console-standalone-1.9.3.jar --class-path bin --scan-class-path
   ```

### O que está sendo testado

Atualmente, os seguintes cenários são testados:

- **Cálculo do salário de diferentes tipos de professores:**
  - Professor CLT
  - Professor Horista
  - Professor PJ

Cada teste verifica se o método `calcularSalario()` retorna o valor esperado para cada tipo de professor, de acordo com os parâmetros fornecidos.

#### Exemplo de saída dos testes

```
JUnit Jupiter [OK]
  CalculadoraTest [OK]
    salarioProfessorCLT() [OK]
    salarioProfessorHorista() [OK]
    salarioProfessorPJ() [OK]
```

Todos os testes estão passando, indicando que os cálculos estão corretos para os casos testados.

### Próximos passos

Pretendo adicionar testes para:
- Integração com o banco de dados (utilizando H2 em memória nos testes)

---






Este repositorio é destinado a avaliação A3 da unidade curricular gestão e qualidade de software, gerido pelos professores Henrique Poyatos e Arquelau Pasta, cujo o objetivo é refatorar um código "lixo".

Alunos:

Marcelo Henrique Silvestre da Silva- RA 1352312397 <br>
Anne Caroline da Silva Medeiros - RA 1282413952 <br>
Guilherme Lima Macario - RA 1352423478 <br>
Marcos Felipe da Silva Oliveira - RA: 722312100 <br>
Felipe Henrique Siqueira Ferreira - RA: 32321477
