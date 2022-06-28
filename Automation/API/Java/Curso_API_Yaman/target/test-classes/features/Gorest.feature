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

  Cenario: Cadastrar novo usuário API Gorest
    Dado que possuo acesso ao gorest
    Quando envio uma requisicao de cadastro de usuario com dados validos
    Então o usuario deve ser criado corretamente