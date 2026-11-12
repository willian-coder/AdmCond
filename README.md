# AdmCond

Migração incremental do aplicativo Android Java de 2020 para **AdmCond 2.0**, em
Flutter, com Android como primeira plataforma e possibilidade de iOS no futuro.
O aplicativo será gratuito. AdMob é uma possibilidade futura; anúncios e IA não
fazem parte desta etapa.

## Código existente

O módulo `app/`, os arquivos Gradle da raiz, os recursos e todo o código Java
foram preservados. O novo aplicativo está em `flutter_app/` e não é um módulo do
build Gradle antigo. Abra essa pasta diretamente na IDE para trabalhar com Flutter.

A análise do legado e as decisões estão em
[docs/flutter-architecture.md](docs/flutter-architecture.md).

## Executar o novo aplicativo

Requisitos: Flutter stable (a versão validada está em
[docs/validation.md](docs/validation.md)), Dart incluído no Flutter, Android Studio
com Android SDK e JDK compatíveis com a versão do Flutter, e emulador ou dispositivo
Android com depuração USB habilitada. Adicione `flutter/bin` ao PATH.

```sh
cd flutter_app
flutter doctor -v
flutter pub get
flutter devices
flutter run -d <id-do-dispositivo-android>
```

A tela inicial é informativa e apresenta os perfis Administrador da plataforma,
Síndico e Morador. Não há seleção de perfil, login nem concessão de permissões.
O aplicativo funciona localmente, sem configuração ou conexão Firebase.
O identificador `br.com.admcond.admcond` é provisório para desenvolvimento;
a identidade de publicação e assinatura deverão ser definidas antes do lançamento.

## Verificações

Dentro de `flutter_app/`:

```sh
dart format --output=none --set-exit-if-changed lib test
flutter analyze
flutter test
flutter build apk --debug
```

O último comando exige Android SDK instalado. Os resultados e limitações desta
etapa estão em [docs/validation.md](docs/validation.md).

## Arquitetura e próximos passos

- `lib/app/`: composição e configuração do aplicativo.
- `lib/core/`: recursos transversais, como tema.
- `lib/features/`: módulos por funcionalidade; `domain`, `data` e `presentation`
  surgem conforme cada implementação precisa deles.
- `test/`: testes automatizados do aplicativo local.

A base usa Material 3 e localização pt-BR. O domínio dos perfis é Dart puro.
Não há dependência de gerenciamento de estado, roteamento ou Firebase antecipada.
Isso mantém a primeira etapa pequena e evita uma integração parcial com produção.

Firebase Auth e Realtime Database são usados pelo legado. A integração Flutter
será adicionada em uma etapa própria, começando por emuladores e um projeto de
desenvolvimento separado. Não executar `flutterfire configure` apontando ao projeto
existente. Os arquivos `google-services.json` antigos permanecem no legado, sem
cópia para Flutter; nenhum dado, regra ou configuração remota foi alterado.

Para iOS, futuramente, gerar o projeto nativo em uma branch própria com
`flutter create --platforms=ios --org br.com.admcond --project-name admcond .`
dentro de `flutter_app/`, revisar o diff e configurar assinatura e identificador
em macOS com Xcode. A camada Dart foi mantida independente de Android.
