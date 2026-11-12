# Validação da base Flutter

Validação realizada em 12/11/2026, em Linux (Debian 13).

## Ferramentas

- Flutter stable 3.50.0, revisão `ab2912035e160d9cad6306df307576da6128ebdb`.
- Dart 3.14.0.
- Java disponível no ambiente: OpenJDK 21.0.13.
- Host Android criado pelo gerador oficial do Flutter, com Kotlin, AGP 9.3.1 e
  Gradle 9.5.0. SDK Android não instalado neste ambiente.
- `material_ui` é o pacote Material recomendado pelo Flutter desta versão;
  `pubspec.lock` registra as dependências resolvidas, incluindo `material_ui` 1.11.0.

## Resultados

| Verificação | Resultado |
| --- | --- |
| `flutter pub get` | Passou; lockfile incluído |
| `dart format --output=none --set-exit-if-changed lib test` | Passou, 6 arquivos sem alteração |
| `flutter analyze` | Passou, sem problemas |
| `flutter test` | Passou, 1 teste de widget |
| Preservação do legado | Todos os 101 caminhos versionados na base mantidos com os mesmos bytes e modos Git |
| `git diff --cached --check` | Passou |
| `flutter doctor -v` | Executado; aponta ausência do Android SDK |
| `flutter build apk --debug` | Bloqueado: `No Android SDK found` |
| Android em dispositivo/emulador | Não executado: sem SDK ou dispositivo Android |
| iOS | Não executado; host iOS ainda não gerado e ambiente sem macOS/Xcode |
| `bash gradlew testDebugUnitTest` (legado) | Bloqueado no download do Gradle 6.1.1: `java.net.ConnectException: Connection refused` |
| Firebase | Não integrado nem acessado, conforme escopo |

O teste inicia a aplicação sem configuração Firebase, verifica os três perfis e
rola a tela para verificar a indicação de modo local. Não prova funcionamento de
autenticação, regras, banco remoto ou build Android nativo.

Os avisos iniciais de APIs Material antigas foram resolvidos com os imports de
`material_ui`. As ferramentas usaram caches dentro do diretório de trabalho
(`PUB_CACHE`, `XDG_CONFIG_HOME`, `ANALYZER_STATE_LOCATION_OVERRIDE`) e telemetria
suprimida para respeitar o sistema de arquivos do ambiente. Isso não faz parte
da configuração necessária em uma máquina de desenvolvimento comum.

## Publicação para revisão

A branch local é `feature/flutter-migration`, baseada no commit
`e076a3a7743be6b9094aef6b01b5394aea606455` de `master`.
Na primeira tentativa, a criação da branch remota e o push foram recusados com
HTTP 403. Após a liberação de permissões pelo proprietário, uma nova verificação
de push em 12/11/2026 confirmou acesso de escrita via CLI.

A entrega será publicada nessa branch, com PR direcionado a `master`, sem merge
ou auto-merge. O texto de revisão está em [pull-request.md](pull-request.md).
