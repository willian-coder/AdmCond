import 'package:material_ui/material_ui.dart';

abstract final class AppTheme {
  static ThemeData get light => ThemeData(
    colorScheme: ColorScheme.fromSeed(seedColor: const Color(0xFF176B5B)),
    useMaterial3: true,
  );
}
