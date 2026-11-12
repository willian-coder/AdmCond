# condominiums

Módulo reservado para implementação incremental. Não há operações de backend nesta etapa.

- `domain/`: entidades, contratos de repositório e regras em Dart puro.
- `data/`: futuras implementações dos contratos, DTOs e fontes de dados Firebase.
- `presentation/`: futuras páginas, widgets e controladores de estado.

Criar arquivos e subpastas conforme houver implementação real. Apresentação depende
do domínio; dados implementam contratos do domínio. O domínio não importa Flutter
nem Firebase. A composição das implementações pertence a `app/`.
