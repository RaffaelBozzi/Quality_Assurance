#language: pt
#Author: Raffael Bozzi
#Version: 1.0
#Encoding: UTF-8

@AllTests
@Tasks
Funcionalidade: Adicionar e remover atividades do usuário na plataforma web TaskIt
  Eu, como usuário do sistema, quero gerenciar minhas atividades cadastradas na plataforma.

  Cenario: Adicionar uma atividade
    Dado que acesso a página de atividades do usuário logado
    Quando informo a intenção de cadastrar uma nova atividade
    E preencho os dados da atividade a ser realizada
    Entao a nova atividade deve estar listada

#  Cenario: Remover uma atividade
#    Dado que acesso a página de atividades do usuário logado
#    E o possui ao menos uma atividade cadastrada
#    Quando deleto a atividade
#    Entao a atividade deve ser removida da lista
