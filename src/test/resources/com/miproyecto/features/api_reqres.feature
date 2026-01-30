Feature: API Reqres - Usuarios
  Como consumidor de la API
  Quiero crear y consultar usuarios
  Para validar que los endpoints responden correctamente

  Scenario: Crear usuario devuelve 201
    When creo un usuario con nombre "Test User" y trabajo "Automation Engineer"
    Then la respuesta debe tener código 201

  Scenario: Obtener usuario por ID devuelve 200
    Given que he creado un usuario
    When obtengo el usuario por su ID
    Then la respuesta debe tener código 200

  Scenario: Los datos del usuario se mantienen al consultar
    Given que he creado un usuario
    When obtengo el usuario por su ID
    Then el nombre debe ser "Test User"
    And el trabajo debe ser "Automation Engineer"

  Scenario: Obtener usuario con ID 1 devuelve 200
    When obtengo el usuario con ID "1"
    Then la respuesta debe tener código 200
