# Capítulo 9: Tratamento Funcional de Erros

O objetivo deste projeto é disponibilizar exemplos práticos que demonstram a transição do tratamento de erros imperativo (baseado em exceções) para abordagens funcionais, puras e seguras usando tipos algébricos de dados (`Option`, `Either` e `Try`).

---

## Estrutura do Código

Os códigos estão organizados em pacotes de acordo com os temas do capítulo:

* **Exceptions.scala** (pacote `exceptions`):
  * Demonstração do tratamento de erros tradicional em linguagens orientadas a objetos/imperativas usando desvios de fluxo e exceções.
* **CustomOption.scala** (pacote `custom_option`):
  * A definição simplificada do tipo `Option` (`Some` e `None`) construída do zero.
* **OptionExamples.scala** (pacote `option`):
  * Exemplos práticos do uso do `Option` da biblioteca padrão do Scala.
  * Transição de funções parciais para totais (exemplo da raiz quadrada babilônica).
  * Composição de operações opcionais usando `getOrElse`, encadeamento monádico (`flatMap`/`map`) e compreensões `for`.
* **Main.java** (pacote `javaoption`):
  * Exemplo equivalente implementado em Java usando a classe `java.util.Optional`.
* **CustomEither.scala** (pacote `custom_either`):
  * A definição simplificada do tipo de resultado `Either` (`Left` e `Right`) construída do zero.
* **EitherExamples.scala** (pacote `either`):
  * Exemplos do uso do `Either` da biblioteca padrão do Scala.
  * Modelagem de erros de domínio usando uma hierarquia de tipos dedicada (`ArithmeticError`).
  * Composição sequencial de computações que podem falhar e propagação do primeiro erro encontrado.
* **TryExamples.scala** (pacote `try_examples`):
  * Exemplos do uso de `Try` (`Success` e `Failure`) para captura e conversão de exceções lançadas por APIs síncronas/legadas ou parsing em expressões seguras.
* **Guidelines.scala** (pacote `guidelines`):
  * Demonstração do erro de compilação comum ao misturar diferentes mônadas em uma compreensão `for` e como resolvê-lo via uniformização de tipos (método `.toOption`).

---

## Execução e Testes

### 1. Compilação
Para compilar todo o código Scala e Java do projeto, use o comando:
```bash
sbt compile
```

### 2. Executando os Exemplos
Para rodar um dos pontos de entrada interativos (`@main`), execute:
```bash
sbt run
```
O SBT listará todas as classes principais disponíveis para você escolher qual deseja executar (por exemplo, `option.testSafeSqrt`, `either.testMonadicComposition`, `guidelines.testGuidelines`, etc.).

Para rodar diretamente um método `@main` específico, você pode utilizar:
```bash
sbt "runMain option.testSafeSqrt"
```

### 3. Executando os Testes Unitários
Os comportamentos descritos no livro estão cobertos por testes unitários escritos com MUnit em `src/test/scala/MySuite.scala`. Para executá-los:
```bash
sbt test
```

---

Este projeto está configurado como um **DevContainer** com todas as dependências necessárias instaladas (Scala, SBT, Metals).

---

## Usando o Scala REPL

O **Scala REPL** (Read-Eval-Print Loop) é excelente para testar expressões rapidamente de forma interativa:
1. Abra o terminal integrado no VS Code.
2. Digite:
   ```bash
   sbt console
   ```
3. Você entrará no terminal interativo do Scala com todas as dependências e o código do projeto carregados no escopo.
