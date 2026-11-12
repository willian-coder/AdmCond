# Arquitetura e migração

## Diagnóstico do legado

Base analisada: `e076a3a7743be6b9094aef6b01b5394aea606455`, branch `master`.

| Área | Estrutura atual |
| --- | --- |
| Aplicativo Android | `app/`, Java, pacote `com.projeto.admcond` |
| Telas | Activities na raiz, `login/` e `cadastro/`; layouts XML em `res/` |
| Listas | `adpter/` com adaptadores e interação do RecyclerView |
| Persistência | `configfirebase/ConfigDb.java`: Firebase Auth e Realtime Database |
| Modelos | `NovoUsuario`, `NovoCondominio`, `NovoInquilino`, `NovaLeitura`; também executam operações de persistência |
| Funcionalidades | Login/reset de senha, cadastro, moradores/inquilinos, leituras de consumo, relatórios e suporte |
| Toolchain | Gradle 6.1.1, Android Gradle Plugin 4.0.0, compile/target SDK 29, min SDK 24, JCenter/JitPack |
| Firebase | Auth 19.3.2, Database 19.3.1, Core 17.4.4; configurações JSON já versionadas na raiz e em `app/` |
| Testes | Dependências JUnit/Espresso declaradas; não há fontes de teste versionadas |

O domínio e a persistência estão acoplados às telas e aos modelos Java.
Há referências às árvores `Usuario`, `Condominio`, `Inquilino`, `Leituras` e `Agua`.
Isso é evidência do código, não uma auditoria dos dados ou das regras remotas.
Também existe uma divergência preexistente entre o arquivo
`TipoConsumoActivity.java` e a classe pública `TipoConsumo`; não foi corrigida
porque a tarefa preserva o legado.

## Estrutura Flutter

```text
flutter_app/
  android/                       # host Android gerado oficialmente, Kotlin
  lib/
    main.dart                    # entrada local, sem inicialização Firebase
    app/admcond_app.dart          # composição e localização
    core/theme/app_theme.dart    # Material 3
    features/
      auth/domain/entities/      # UserRole, sem dependência de SDK
      home/presentation/pages/   # página inicial informativa
      condominiums/              # fronteiras documentadas para próximas etapas
      residents/
      consumption/
      reports/
      platform_admin/
  test/widget_test.dart
```

Diretórios sem implementação são representados por README, evitando classes e
abstrações vazias. Cada módulo cresce com `domain/` (entidades, contratos, regras),
`data/` (DTOs, fontes de dados, implementações de repositório) e `presentation/`
(páginas, widgets, estado). Fluxo previsto: apresentação → contratos do domínio;
dados implementam esses contratos; `app/` injeta as implementações. O domínio
não importa Flutter ou Firebase. `core/` não deve acumular regras de funcionalidades.

O host Android novo usa Kotlin somente para iniciar o runtime Flutter; não migra
nem modifica Java. O build nativo segue o template do SDK Flutter registrado na
validação, independente do Gradle 6.1.1 antigo. A identidade de pacote é provisória,
diferente da do legado, permitindo instalação paralela em desenvolvimento.

## Perfis e autorização futura

`UserRole` define Administrador da plataforma, Síndico e Morador. É um modelo,
não um mecanismo de segurança. Não há usuários reais ou papéis atribuídos aqui.
A implementação futura deve obter papéis de fonte confiável, validar vínculo ao
condomínio e aplicar autorização também nas regras Firebase/backend. Esconder
botões ou selecionar um enum no cliente não concede acesso. A matriz exata de
permissões ainda precisa ser definida antes da implementação de cada fluxo.

## Firebase e evolução

Não incluímos SDKs Firebase nem `firebase_options.dart` nesta etapa. A composição
local torna impossível um acesso acidental ao banco existente por este aplicativo.
Não há comandos de deploy, novas regras ou migração de dados.

Passos futuros: definir contratos e modelo por condomínio; implementar autenticação
e autorização com Firebase Emulator Suite; validar regras e isolamento por perfil;
integrar projeto de desenvolvimento separado; migrar cada funcionalidade com testes.
Manter Realtime Database inicialmente é uma hipótese de compatibilidade; eventual
adoção de Firestore exige decisão e plano de migração próprios. iOS, assinatura,
release, AdMob e IA não estão implementados.
