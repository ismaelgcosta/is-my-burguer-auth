Feature: Cadastro de Cliente

  Scenario: Cadastrar cliente com sucesso
    Given que o email do cliente não está cadastrado
    When eu cadastro um novo cliente
    Then o ID do cliente deve ser retornado
    And o email do cliente deve ser consultado
    And o cliente deve ser salvo

  Scenario: Cadastrar cliente com email já existente
    Given que o email do cliente já está cadastrado
    When eu tento cadastrar um novo cliente
    Then uma exceção de negócio deve ser lançada
    And o email do cliente deve ser consultado
    And o cliente não deve ser salvo

  Scenario: Cadastrar cliente com username já existente
    Given que o username do cliente já está cadastrado
    When eu tento cadastrar um novo cliente
    Then uma exceção de negócio deve ser lançada
    And o username do cliente deve ser consultado
    And o cliente não deve ser salvo

  Scenario: Cadastrar cliente com CPF já existente
    Given que o CPF do cliente já está cadastrado
    When eu tento cadastrar um novo cliente
    Then uma exceção de negócio deve ser lançada
    And o CPF do cliente deve ser consultado
    And o cliente não deve ser salvo
