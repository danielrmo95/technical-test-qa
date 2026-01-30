@ui
Feature: Login y página principal
  Como usuario
  Quiero iniciar sesión en la aplicación demo
  Para ver la tabla de gastos y validar la información mostrada

  Background:
    Given que estoy en la página de login
    When inicio sesión con usuario "testuser" y contraseña "testpassword"

  Scenario: Redirección correcta después del login
    Then debo ser redirigido a la página de gastos

  Scenario: La tabla muestra exactamente seis transacciones
    Then la tabla de transacciones debe mostrar exactamente 6 filas

  Scenario: El balance contiene el valor esperado
    Then el balance debe contener "350"

  Scenario: El crédito disponible contiene el valor esperado
    Then el crédito disponible debe contener "17,800"

  Scenario: Los montos positivos y negativos tienen colores correctos
    Then los montos positivos deben mostrarse en verde
    And los montos negativos deben mostrarse en rojo
