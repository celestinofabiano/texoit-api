# texoit-api
Aplicação com API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards

## Execução da Aplicação
Para obter o projeto, primeiro clona o repositório e faça download do mesmo em sua IDE.
Após clonar o projeto, a aplicação poderá ser inicializada executando a classe principal "TexoitApiApplication" em sua IDE.
Ao inicializar a aplicação, o sistema vai ler um arquivo CSV "movielist.csv" que está contido no diretório de resources da aplicação e irá popular uma tabela do banco de dados em memória H2.
Caso deseje alterar os dados de entrada no arquivo CSV, reinicie a aplicação após finalizar a edição dos dados no arquivo.

## API's disponíveis
As API's foram implementadas utilizando o padrão RESTful e os endpoints podem ser consultados pela URL de documentação do projeto: http://localhost:8080/swagger-ui/

## Testes
Para executar os testes abra a classe AppTest.java, clique em Run -> Run As -> JUnit Test. Isso fará com que todos os testes de integração implementados sejam executados.
