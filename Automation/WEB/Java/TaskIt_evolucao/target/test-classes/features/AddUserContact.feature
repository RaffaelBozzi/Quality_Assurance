#language: pt
#Author: Raffael Bozzi
#Version: 1.0
#Encoding: UTF-8

@AllTests
@Contacts
Funcionalidade: Adicionar e remover informações de contato do usuário na plataforma web TaskIt
  Eu, como usuário do sistema, quero gerenciar minhas informações de contato na plataforma.

  Cenario: Adicionar e-mail
    Dado que acesso a página de contatos do usuário logado
    Quando informo a intenção de cadastrar um novo meio de contato
    E preencho os dados do "email" do usuário
    Entao o novo contato deve estar listado

  Cenario: Adicionar telefone
    Dado que acesso a página de contatos do usuário logado
    Quando informo a intenção de cadastrar um novo meio de contato
    E preencho os dados do "telefone" do usuário
    Entao o novo contato deve estar listado

  Cenario: Remover e-mail cadastrado
    Dado que acesso a página de contatos do usuário logado
    E o possui ao menos um "email" cadastrado
    Quando deleto o "email"
    Entao o contato deve ser removido da lista

  Cenario: Remover telefone cadastrado
    Dado que acesso a página de contatos do usuário logado
    E o possui ao menos um "telefone" cadastrado
    Quando deleto o "telefone"
    Entao o contato deve ser removido da lista
