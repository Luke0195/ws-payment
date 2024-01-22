> #### Épico de Cliente

A ideia desse épico é trazer uma mini documentação que ira auxiliar o desenvolvimento durante o processo de desenvolvimento.

Campos obrigatório para criação de um Cliente.
 * id (obrigatório)
 * name(obrigatório)
 * code (obrigatório)
 * email (obrigatório)
 * phone 
 * endereço 
 * bairro 
 * cidade 


> #### Requisitos Funcionais.

* [X] O sistema deve permitir o usuário listar todos os usuários
* [X] O sistema deve permitir listar os usuários paginados.
* [X] O sistema deve ter um filtro de busca por nome.
* [ ] O sistema deve pesquisar o usuário por id.
* [X] O sistema deve permitir a criação de um cliente.
* [ ] O sistema deve permitir que o usuário possa deletar um registro.
* [ ] O sistema deve permitir que o usuário possa atualizar um registro.


> #### Requisitos não Funcionais.
* [X] O sistema não deve permitir cadastrar um cliente com um código já existente.
* [X] O sistema não deve permitir cadastrar um cliente com um email já existente.
* [X] O sistema não deve permitir cadastrar um cliente com um nome vazio ou nulo.
* [X] O sistema não deve permitir cadastrar um cliente com um code vazio ou nulo.
* [X] O sistema não deve permitir cadastrar um cliente com um email vazio ou nulo.