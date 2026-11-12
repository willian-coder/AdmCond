import 'package:admcond/app/admcond_app.dart';
import 'package:material_ui/material_ui.dart';
import 'package:flutter_test/flutter_test.dart';

void main() {
  testWidgets('inicia localmente e apresenta os três perfis em português', (
    tester,
  ) async {
    await tester.pumpWidget(const AdmCondApp());
    await tester.pumpAndSettle();

    expect(find.text('AdmCond 2.0'), findsOneWidget);
    expect(find.text('Administrador da plataforma'), findsOneWidget);
    expect(find.text('Síndico'), findsOneWidget);
    expect(find.text('Morador'), findsOneWidget);
    await tester.scrollUntilVisible(
      find.text('Modo local • Firebase não conectado'),
      150,
      scrollable: find.byType(Scrollable),
    );
    expect(find.text('Modo local • Firebase não conectado'), findsOneWidget);
    expect(tester.takeException(), isNull);
  });
}
