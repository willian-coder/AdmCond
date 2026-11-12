import 'package:material_ui/material_ui.dart';

import '../../../auth/domain/entities/user_role.dart';

/// Página informativa local; não representa uma sessão autenticada.
class HomePage extends StatelessWidget {
  const HomePage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('AdmCond 2.0')),
      body: SafeArea(
        child: Center(
          child: ConstrainedBox(
            constraints: const BoxConstraints(maxWidth: 640),
            child: ListView(
              padding: const EdgeInsets.all(24),
              children: [
                Text(
                  'Seu condomínio, mais conectado',
                  style: Theme.of(context).textTheme.headlineMedium,
                ),
                const SizedBox(height: 16),
                const Text(
                  'Base inicial do novo AdmCond. Autenticação e funcionalidades '
                  'serão disponibilizadas nas próximas etapas.',
                ),
                const SizedBox(height: 24),
                Text(
                  'Perfis previstos',
                  style: Theme.of(context).textTheme.titleLarge,
                ),
                const SizedBox(height: 8),
                for (final role in UserRole.values)
                  Card(
                    child: ListTile(
                      leading: const Icon(Icons.person_outline),
                      title: Text(role.label),
                    ),
                  ),
                const SizedBox(height: 24),
                const Text('Modo local • Firebase não conectado'),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
