> #### Épico de Conta

A ideia desse épico é trazer uma mini documentação que ira auxiliar o desenvolvimento durante o processo de desenvolvimento.

Campos obrigatório para criação de um Pagamento.
* id (obrigatório)
* client (obrigatório)
* description(obrigatório)
* validate_date (obrigatório)
* payment_date(obrigatório)
* price (obrigatório)
* amountpaid
* billType
* billStatus
* billpaymentType

> #### Requisitos Funcionais.

* [X] O sistema deve permtir que o usuário crie uma conta.
* [X] O sistema deve listar todas contas criadas.
* [X] O sistema deve permitir listar as contas paginadas.
* [X] O sistema deve ter um filtro de busca por descricao.
* [X] O sistema deve pesquisar uma conta  por id.
* [ ] O sistema não deve cadastrar uma conta sem um usuário.

> #### Requisitos não Funcionais.

* [X] O sistema não deve permitir cadastrar uma conta sem o id do cliente.
* [X] O sistema não deve permitir cadastrar uma conta com um id ínvalido ou nula.
* [ ] O sistema não deve permitir cadastrar uma conta com uma descrição vázia ou nula.
* [ ] O sistema não deve permitir cadastrar uma conta com data de vencimento com o valor vázio ou nulo.
* [ ] O sistema não deve permitir cadsatrar uma conta data de pagamengo com o valor vázio ou nulo.
* [ ] O sistema não deve permitir cadastrar uma com o preço negativo ou nulo.