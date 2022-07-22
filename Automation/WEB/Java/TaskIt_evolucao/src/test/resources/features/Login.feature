#language: pt
#Author: Raffael Bozzi
#Version: 1.0
#Encoding: UTF-8

@AllTests
@Login
Funcionalidade: Criar novo usuário, acessar e sair de uma conta de usuário na plataforma web TaskIt
  Eu, como usuário do sistema, quero me cadastrar, acessar e sair da plataforma.

  Cenario: Cadastrar novo usuário
    Dado que acesso a plataforma do TaskIt
    Quando informo a intenção de me cadastrar na plataforma
    E preencho os dados de cadastro com informações válidas
    Entao o novo usuário deve ser criado

  Esquema do Cenario: Acessar conta de usuário existente
    Dado que acesso a plataforma do TaskIt
    Quando efetuo o login com dados do usuário "<usuario>" cadastrado previamente
    Entao é exibida a página inicial do usuário

    Exemplos:
      | usuario |
      | raffael |
      | julio   |

  Cenario: Sair da conta de usuário logada
    Dado que o usuário está logado
    Quando efetuo o logout
    Entao é exibida a página inicial da plataforma