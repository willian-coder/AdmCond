import 'package:material_ui/material_ui.dart';

import '../core/theme/app_theme.dart';
import '../features/home/presentation/pages/home_page.dart';

class AdmCondApp extends StatelessWidget {
  const AdmCondApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'AdmCond 2.0',
      debugShowCheckedModeBanner: false,
      theme: AppTheme.light,
      locale: const Locale('pt', 'BR'),
      supportedLocales: const [Locale('pt', 'BR')],
      localizationsDelegates: GlobalMaterialLocalizations.delegates,
      home: const HomePage(),
    );
  }
}
