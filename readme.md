## 🧪 Proyecto de Pruebas Automatizadas de Registro y Login
---
Este proyecto contiene pruebas automatizadas para validar funcionalidades de registro y login en una aplicación web. Utiliza Java junto con herramientas como Selenium WebDriver, JUnit/TestNG, y ExtentReports para generar reportes detallados.
---
📦 Estructura del Proyecto (Maven)
El proyecto está organizado como un proyecto Maven, lo que facilita la gestión de dependencias y la ejecución de pruebas. La estructura típica incluye:
```
└── 📁Ev-Mod4
    └── 📁reports
    └── 📁screenshots
    └── 📁src
        └── 📁test
            └── 📁java
                └── 📁suiteTest
                    ├── LoginTest.java
                    ├── RegisterTest.java
                └── 📁utils
                    ├── BrowserManager.java
                    ├── CSVUtils.java
                    ├── ExtentReportManager.java
                    ├── RegisterData.java
                    ├── SuiteTestListener.java
                    ├── UserData.java
            └── 📁resources
                ├── Register.csv
                ├── test-image.txt
                ├── User.csv
    └── 📁target
    ├── pom.xml
    └── testng.xml
```
---
⬇️ Cómo Descargar el Proyecto desde GitHub
---
git clone https://github.com/claudio-hcn/Ev-Mod4

---
🚀 Cómo Ejecutar las Pruebas
---
Asegúrate de tener Java y Maven instalados.

Ejecución de pruebas:

# TODAS LAS PRUEBAS
mvn test

# Solo LoginTest (todos los métodos)
mvn test -Dtest=LoginTest

# Solo LoginTest en Chrome o FireFox
mvn test -Dtest=LoginTest -Dbrowser=chrome
mvn test -Dtest=LoginTest -Dbrowser=firefox


# Solo RegisterTest (todos los métodos)
mvn test -Dtest=RegisterTest

# Solo RegisterTest en Chrome o Firefox
mvn test -Dtest=RegisterTest -Dbrowser=chrome
mvn test -Dtest=RegisterTest -Dbrowser=firefox



Los reportes se generarán automáticamente en la carpeta reports.

---
🧰 Tecnologías Utilizadas
---
    Java
    Maven
    Selenium WebDriver
    JUnit o TestNG
    ExtentReports
    Archivos CSV para datos de prueba
---

✅ Casos de Prueba, pruebas se realizan con Chrome y Firefox
---
*Login*
Credenciales válidas (usuario y administrador)
Contraseña incorrecta
Usuario Incorrecto

*Registro*
Datos Válidos
Campos incompletos
---

## 👩‍💻 Autores
---

Samuel Millelche https://github.com/Millelche
Claudio Carrasco https://github.com/claudio-hcn
Lili Cedeño https://github.com/LilianaCedeno