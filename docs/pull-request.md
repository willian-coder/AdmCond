# feat: prepara base Flutter do AdmCond 2.0 preservando legado Java

## Mudanças

Adiciona `flutter_app/` como aplicativo independente, gerado com Flutter stable
3.50.0 para Android. Mantém todos os arquivos originais do projeto Java sem
alteração e sem acoplar os dois builds Gradle.

Organiza a base por funcionalidade e responsabilidade (`app`, `core`, `features`),
com domínio dos três perfis em Dart puro e tela inicial informativa em pt-BR usando
Material 3. Documenta as fronteiras dos futuros módulos de autenticação,
condomínios, moradores, consumo, relatórios e administração da plataforma.

Inclui README de execução, análise do legado, decisões de arquitetura e instruções
para expansão futura a iOS. Firebase permanece desconectado, sem novas credenciais,
sem alterações nos arquivos de configuração antigos e sem operações remotas.
Não implementa anúncios ou IA.

## Validação

- `flutter pub get`, formatação e `flutter analyze`: passaram, sem problemas.
- `flutter test`: 1 teste de widget passou, cobrindo inicialização local e perfis.
- Comparação dos 101 arquivos originais com a base: mesmos bytes e modos Git.
- `git diff --cached --check`: passou.
- APK e execução Android bloqueados pela ausência do Android SDK/dispositivo.
- iOS não testado; projeto nativo ainda não gerado e ambiente sem Xcode.
- Testes Java bloqueados no download do Gradle legado por conexão recusada.

Build de release, autenticação, autorização e integração Firebase ainda não foram
implementados/validados. Revisar a estrutura inicial; não realizar merge automático.
