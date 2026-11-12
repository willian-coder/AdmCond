/// Perfis previstos. Este enum não concede permissões de acesso.
enum UserRole {
  platformAdmin('Administrador da plataforma'),
  condominiumManager('Síndico'),
  resident('Morador');

  const UserRole(this.label);

  final String label;
}
