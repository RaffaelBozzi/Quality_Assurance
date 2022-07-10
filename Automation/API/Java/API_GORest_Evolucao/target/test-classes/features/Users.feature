#language: pt
#Author: Raffael Bozzi
#Version: 1.0
#Encoding: UTF-8

@Gorest
Funcionalidade: Criar e editar contas de usuários na API Gorest
  Eu, como administrador do sistema, quero cadastrar, editar e excluir usuários do sistema

  @post
  Cenario: Cadastrar novo usuário
    Dado que possuo acesso ao gorest
    Quando envio uma requisicao de cadastro de usuario com dados validos
    Entao o usuario deve ser criado corretamente

  @get
  Cenario: Buscar um usuário existente
    Dado que possuo acesso ao gorest
    E existe um usuario cadastrado na api
    Quando buscar este usuario
    Entao o usuario deve ser retornado corretamente

  @put
  Cenario: Alterar um usuário existente usando metodo PUT
    Dado que possuo acesso ao gorest
    E existe um usuario cadastrado na api
    Quando altero os dados deste usuario
    Entao os dados devem ser atualizados corretamente

  @patch
  Cenario: Alterar um usuário existente usando metodo Patch
    Dado que possuo acesso ao gorest
    E existe um usuario cadastrado na api
    Quando altero um ou mais dados deste usuario
    Entao os dados devem ser atualizados corretamente

  @delete
  Cenario: Deletar um usuário existente
    Dado que possuo acesso ao gorest
    E existe um usuario cadastrado na api
    Quando deleto este usuario
    Entao os dados do usuario sao excluidos

# Visto que a API é em inglês, as mensagens de erro apresentadas no Gerkin foram adaptadas
#   para gerar entendimento e serão mapeadas com as mensagens originais em código
  Esquema do Cenario: Cadastrar novo usuário sem informar dados obrigatórios
    Dado que possuo acesso ao gorest
    Quando envio uma requisicao de cadastro de usuario sem informar "<dados>"
    Entao é exibida mensagem de erro de "<mensagem>"

    Exemplos:
      | dados  | mensagem             |
      | nome   | nome nao informado   |
      | email  | email nao informado  |
      | genero | genero nao informado |
      | status | status nao informado |

  Esquema do Cenario: Cadastrar novo usuário usando dados inválidos
    Dado que possuo acesso ao gorest
    Quando envio uma requisicao de cadastro de usuario informando "<dados>" invalido
    Entao é exibida mensagem de erro de "<mensagem>"

    Exemplos:
      | dados  | mensagem             |
      | email  | email invalido       |
      | genero | genero nao informado |
      | status | status nao informado |

  Cenario: Cadastrar novo usuario usando e-mail já utilizado
    Dado que possuo acesso ao gorest
    Quando envio uma requisicao de cadastro de usuario com email cadastrado anteriormente
    Entao é exibida mensagem de erro de "email cadastrado"

  Cenario: Cadastrar novo usuario sem enviar nenhum dado
    Dado que possuo acesso ao gorest
    Quando envio uma requisicao de cadastro de usuario sem informar nenhum dado
    Entao é exibida mensagem de erro de "dados ausentes"

  Cenario: Consultar usuário inexistente
    Dado que possuo acesso ao gorest
    Quando buscar um usuario nao cadastrado
    Entao é exibida mensagem de erro de "usuario não encontrado"