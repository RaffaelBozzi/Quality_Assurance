#language: pt
#Author: Raffael Bozzi
#Version: 1.0
#Encoding: UTF-8

@Correios
Funcionalidade: Calcular o valor do frete.

  @frete
  Cenario: Calcular o valor do frete com dados validos
    Dado que possuo acesso ao sistema
    Quando informo todos os dados necessarios para consulta do frete
    Entao o valor do frete é exibido

  @freteDataTable
  Cenario: Calcular o valor do frete com dados validos
    Dado que possuo acesso ao sistema
    Quando informo todos os dados necessarios para consulta do frete datatable
      | sCepOrigem     | 74371520 |
      | sCepDestino    | 13175613 |
      | nVlPeso        | 2.00     |
      | nVlComprimento | 36.00    |
      | nVlAltura      | 18.00    |
      | nVlLargura     | 12.00    |
#      | sCepOrigem | sCepDestino | nVlPeso | nVlComprimento | nVlAltura | nVlLargura |
#      | 74371520   | 13175613    | 2.00    | 36.00          | 18.00     | 12.00      |
    Entao o valor do frete é exibido

  @freteEsquemaDeCenario
  Esquema do Cenario: Calcular o valor do frete com dados validos
    Dado que possuo acesso ao sistema
    Quando informo "<CEP de origem>", "<CEP de destino>", "<Peso>", "<Comprimento>", "<Altura>" e "<Largura>" para consulta do frete
    Entao o valor do frete é exibido

    Exemplos:
      | CEP de origem | CEP de destino | Peso | Comprimento | Altura | Largura |
      | 74371520      | 13175613       | 2.00 | 36.00       | 18.00  | 12.00   |
      | 12345678      | 12345678       | 2.00 | 36.00       | 18.00  | 12.00   |
