# Estudo de Playwright em Typescript seguindo o curso no youtube: 
https://www.youtube.com/watch?v=iijmmick7XE&list=PLsjUcU8CQXGGV3LzJqIPgZCtoja2zohwo&index=1 

## Iniciando um projeto playwright
```
$ npm init playwright@latest
Need to install the following packages:
create-playwright@1.17.139
Ok to proceed? (y) y

> npx
> create-playwright

Getting started with writing end-to-end tests with Playwright:
Initializing project in '.'
√ Do you want to use TypeScript or JavaScript? · TypeScript
√ Where to put your end-to-end tests? · tests
√ Add a GitHub Actions workflow? (Y/n) · false
√ Install Playwright browsers (can be done manually via 'npx playwright install')? (Y/n) · true
```

## Rodando o primeiro teste apenas para o chrome e desabilitando o headless mode
```
npx playwright test --project=chromium --headed
```

## Exibir relatório com resultados
```
npx playwright show-report
```

