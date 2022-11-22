## texoit-api
Aplicação com API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards

## Execução da Aplicação
Para obter o projeto, primeiro clona o repositório à partir do Git Hub pela URL https://github.com/celestinofabiano/texoit-api e faça download do mesmo em sua IDE.
Após clonar o projeto, a aplicação poderá ser inicializada executando a classe principal "TexoitApiApplication.java" em sua IDE.
Ao inicializar a aplicação, o sistema vai ler um arquivo CSV "movielist.csv" que está contido no diretório "src/main/resources" da aplicação e irá popular uma tabela do banco de dados em memória H2.
Caso deseje alterar os dados de entrada no arquivo CSV, reinicie a aplicação após finalizar a edição dos dados no arquivo.

## API's disponíveis
As API's foram implementadas utilizando o padrão RESTful e os endpoints podem ser consultados pela documentação do projeto implementada com a suíte swagger disponibilizada na URL http://localhost:8080/swagger-ui/ 
Para obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido consulte o endpoint pela URL http://localhost:8080/awards-interval.

## Testes
Para executar os testes de integração, execute a classe "AwardIntervalControllerTest.java" em sua IDE.
