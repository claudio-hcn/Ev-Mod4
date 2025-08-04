# 🧪 Ev-Mod4 - Proyecto de Pruebas Automatizadas

Este proyecto contiene pruebas automatizadas para validar funcionalidades de **registro** y **login** en una aplicación web. Utiliza Java junto con herramientas como Selenium WebDriver, TestNG, y ExtentReports para generar reportes detallados.

## 📦 Estructura del Proyecto

El proyecto está organizado como un proyecto Maven:

```
└── 📁Ev-Mod4
    ├── 📁reports
    ├── 📁screenshots
    ├── 📁src
    │   └── 📁test
    │       ├── 📁java
    │       │   ├── 📁suiteTest
    │       │   │   ├── LoginTest.java
    │       │   │   └── RegisterTest.java
    │       │   └── 📁utils
    │       │       ├── BrowserManager.java
    │       │       ├── CSVUtils.java
    │       │       ├── ExtentReportManager.java
    │       │       ├── RegisterData.java
    │       │       ├── SuiteTestListener.java
    │       │       └── UserData.java
    │       └── 📁resources
    │           ├── Register.csv
    │           ├── test-image.txt
    │           └── User.csv
    ├── 📁target
    ├── pom.xml
    └── testng.xml
```

## ⬇️ Instalación

### Prerrequisitos
- Java 8 o superior
- Maven 3.6 o superior
- Git

### Clonar el repositorio
```bash
git clone https://github.com/claudio-hcn/Ev-Mod4.git
cd Ev-Mod4
```

## 🚀 Ejecución de Pruebas

### Ejecutar todas las pruebas
```bash
mvn test
```

### Ejecutar pruebas específicas

**Solo LoginTest:**
```bash
mvn test -Dtest=LoginTest
```

**Solo RegisterTest:**
```bash
mvn test -Dtest=RegisterTest
```

### Ejecutar con navegadores específicos

**Chrome:**
```bash
mvn test -Dtest=LoginTest -Dbrowser=chrome
mvn test -Dtest=RegisterTest -Dbrowser=chrome
```

**Firefox:**
```bash
mvn test -Dtest=LoginTest -Dbrowser=firefox
mvn test -Dtest=RegisterTest -Dbrowser=firefox
```

## 📊 Reportes

Los reportes se generan automáticamente en la carpeta `reports/` después de ejecutar las pruebas. Incluyen:
- Reportes HTML detallados
- Screenshots de errores
- Logs de ejecución

## 🧰 Tecnologías Utilizadas

- **Java** - Lenguaje de programación
- **Maven** - Gestión de dependencias y construcción
- **Selenium WebDriver** - Automatización de navegadores
- **TestNG** - Framework de pruebas
- **ExtentReports** - Generación de reportes
- **CSV** - Archivos de datos de prueba

## ✅ Casos de Prueba

### 🔐 Login
- ✅ Credenciales válidas (usuario y administrador)
- ❌ Contraseña incorrecta
- ❌ Usuario incorrecto

### 📝 Registro
- ✅ Datos válidos
- ❌ Campos incompletos

> **Nota:** Las pruebas se ejecutan tanto en Chrome como en Firefox para garantizar compatibilidad cross-browser.

## 👥 Autores

| Nombre | GitHub |
|--------|--------|
| Samuel Millelche | [@Millelche](https://github.com/Millelche) |
| Claudio Carrasco | [@claudio-hcn](https://github.com/claudio-hcn) |
| Lili Cedeño | [@LilianaCedeno](https://github.com/LilianaCedeno) |

---

⭐ **¡No olvides dar una estrella al proyecto si te fue útil!**