# Technical Test QA

Este proyecto contiene tests automatizados de interfaz de usuario usando Selenium y tests de API REST con RestAssured. Es parte de un technical test para QA.

## Requisitos

Antes de empezar, necesitas tener instalado:

- Java JDK 8 o superior
- Maven 3.6 o superior
- Chrome Browser (para los tests de Selenium)
- Git (opcional, solo si vas a clonar el repo)

## Instalación

Primero clona el repositorio:

```bash
git clone https://github.com/danielrmo95/technical-test-qa.git
cd technical-test-qa
```

Luego instala las dependencias con Maven:

```bash
mvn clean install
```

## Tests incluidos

El proyecto tiene dos suites de tests principales:

### TestLoginAndHome

Estos tests validan el flujo de login y la página principal de una aplicación demo. La URL que se prueba es `https://demo.applitools.com/hackathonV2.html`.

Los tests que incluye son:

- `verificarRedireccionALaTablaDeGastos` - Verifica que después del login te redirige correctamente a la página de gastos
- `verificarQueExistenExactamenteSeisTransacciones` - Valida que la tabla muestra exactamente 6 transacciones
- `verificarBalanceContieneValorEsperado` - Verifica que el balance contiene el valor esperado (350)
- `verificarCreditoDisponibleContieneValorEsperado` - Valida que el crédito disponible muestra el valor esperado (17,800)
- `verificarColoresDeMontos` - Verifica que los montos positivos aparecen en verde y los negativos en rojo

En total son 5 tests en esta suite.

### ReqresApiTest

Estos tests validan endpoints de la API pública de Reqres. La base URI es `https://reqres.in/api`.

Los tests que incluye son:

- `postUsersDebeDevolver201` - Verifica que al crear un usuario con POST /users devuelve código 201
- `getUserByIdDebeDevolver200` - Valida que al obtener un usuario por ID devuelve código 200
- `getUserByIdDebeMantenerNombreYTrabajo` - Verifica que los datos del usuario (nombre y trabajo) se guardan y recuperan correctamente
- `getUserId1DebeDevolver200` - Valida que obtener el usuario con ID 1 devuelve código 200

En total son 4 tests en esta suite.

**Total de tests en el proyecto: 9**

## Cómo ejecutar los tests

Para ejecutar todos los tests:

```bash
mvn test
```

Si quieres ejecutar solo una suite específica:

```bash
# Solo los tests de login y home
mvn test -Dtest=TestLoginAndHome

# Solo los tests de API
mvn test -Dtest=ReqresApiTest
```

También puedes ejecutar un test individual si necesitas:

```bash
mvn test -Dtest=TestLoginAndHome#verificarBalanceContieneValorEsperado
```

## Generar el reporte

Para generar el reporte HTML de los tests ejecutados, usa este comando:

```bash
mvn surefire-report:report
```

Esto generará un reporte HTML en la carpeta `target/site/`.

## Ver el reporte

Una vez que ejecutes `mvn surefire-report:report`, el reporte estará disponible en:

**Ruta:** `target/site/surefire-report.html`

Para abrirlo puedes:

1. Navegar manualmente a la carpeta `target/site/` y abrir el archivo `surefire-report.html` en tu navegador
2. O ejecutar desde la línea de comandos:
   ```bash
   # Windows
   start target/site/surefire-report.html
   
   # Mac/Linux
   open target/site/surefire-report.html
   ```

El reporte te muestra:
- Resumen general de cuántos tests se ejecutaron
- Tests exitosos (marcados con check verde)
- Tests fallidos o con errores (marcados con X roja)
- Tiempo de ejecución de cada test
- Detalles de errores y stack traces si algo falló

## Tecnologías usadas

- Selenium WebDriver 4.10.0 - Para automatizar el navegador
- WebDriverManager 5.8.0 - Para gestionar los drivers automáticamente
- JUnit 5.9.3 - Framework de testing
- RestAssured 5.4.0 - Para hacer tests de APIs REST
- Maven Surefire Report Plugin - Para generar los reportes HTML
- Java 8 o superior

## Estructura del proyecto

```
src/
├── main/
│   └── java/
│       └── com/miproyecto/
│           └── pages/          # Page Object Model
│               ├── LoginPage.java
│               └── HomePage.java
└── test/
    └── java/
        └── com/miproyecto/
            └── tests/           # Clases de test
                ├── TestLoginAndHome.java
                └── ReqresApiTest.java
target/
└── site/
    └── surefire-report.html    # Reporte HTML generado
```

## Tests BDD (Cucumber)

El proyecto incluye escenarios BDD en Gherkin (español) `src/test/resources/com/miproyecto/features/` con los casos de prueba de los puntos 1 y 2.

**Forma recomendada de ejecutar los tests BDD** (perfil Maven, sin Suite):

```bash
mvn test -Pbdd
```

Con esto se ejecuta Cucumber por línea de comandos y se evita el error "TestEngine with ID 'junit-platform-suite' failed to discover tests".

**Nota:** Por defecto, `mvn test` ejecuta todos los tests **excepto** el runner de Cucumber (CucumberRunnerTest), para que la build no falle. Los escenarios BDD se ejecutan con el perfil `bdd`.

**Dónde ver el reporte de Cucumber:** después de ejecutar `mvn test -Pbdd`, el reporte HTML se genera en:

- **HTML:** `target/reports/cucumber-report.html` (ábrelo en el navegador)
- **JSON:** `target/reports/cucumber.json`

Si aparece **AccessDeniedException** al copiar los `.feature` a `target`, cierra el IDE o ejecuta `mvn clean` antes.

## Notas importantes

- Los tests de UI necesitan que tengas Chrome instalado
- Los tests de API usan la API pública de Reqres (https://reqres.in), así que necesitas conexión a internet
- El reporte se genera en `target/site/` después de ejecutar `mvn surefire-report:report`
- Cada vez que generas un nuevo reporte, se sobrescribe el anterior

