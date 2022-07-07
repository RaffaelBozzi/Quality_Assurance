#language: pt
#Author: Raffael Bozzi
#Version: 1.0
#Encoding: UTF-8

@Gorest
Funcionalidade: Criar e editar contas de usuários
  Eu, como administrador do sistema, quero cadastrar, editar e excluir usuários do sistema

#  Cenario: Cadastrar novo usuário API Gorest
#    Dado que possuo gorest token valido
#    Quando envio um request de cadastro de usuario com dados validos
#    Então o usuario deve ser criado corretamente
#    E o status code do request deve ser 200

  @post
  Cenario: Cadastrar novo usuario API Gorest
    Dado que possuo acesso ao gorest
    Quando envio uma requisicao de cadastro de usuario com dados validos
    Então o usuario deve ser criado corretamente

  @get
  Cenario: Buscar um usuario existente na API Gorest
    Dado que possuo acesso ao gorest
    E existe um usuario cadastrado na api
    Quando buscar este usuario
    Então o usuario deve ser retornado corretamente

  @put
  Cenario: Alterar um usuario existente na API Gorest PUT
    Dado que possuo acesso ao gorest
    E existe um usuario cadastrado na api
    Quando altero os dados deste usuario
    Então os dados devem ser atualizados corretamente

  @patch
  Cenario: Alterar um usuario existente na API Gorest Patch
    Dado que possuo acesso ao gorest
    E existe um usuario cadastrado na api
    Quando altero um ou mais dados deste usuario
    Então os dados devem ser atualizados corretamente

  @delete
  Cenario: Deletar um usuario existente na API Gorest
    Dado que possuo acesso ao gorest
    E existe um usuario cadastrado na api
    Quando deleto este usuario
    Então os dados do usuario sao excluidos