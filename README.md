


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
