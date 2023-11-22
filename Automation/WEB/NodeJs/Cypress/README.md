Estudo do framework Cypress

Listar tecnologias utilizadas
  - node v20.9.0npm
  - npm 10.2.3
  - cypress 13.4.0


*Inicialização do projeto*
- comando npm init -y
- comando npm install cypress --save-dev
- comando npx cypress open

Na tela do Cypress configurar o test e2e e a estrutura base do programa será criada automaticamente

Notas: 
- a pasta screenshots é criada com imagens das falhas nas execuções dos testes
- a pasta videos é criada com a execução do teste quando executado o comando npx cypress run. obs: deve-se habilitar no cypress.config.js a gravação do vídeo
- é necessário instalar uma extensão para o cypress manusear xpaths -> npm install -D cypress-xpath <- Após é necessário adicionar no arquivo support/e2e.js o código *require('cypress-xpath')*