// package utils;

// import com.aventstack.extentreports.ExtentReports;
// import com.aventstack.extentreports.ExtentTest;
// import com.aventstack.extentreports.Status;
// import com.aventstack.extentreports.reporter.ExtentSparkReporter;
// import com.aventstack.extentreports.reporter.configuration.Theme;
// import org.apache.commons.io.FileUtils;
// import org.openqa.selenium.OutputType;
// import org.openqa.selenium.TakesScreenshot;
// import org.openqa.selenium.WebDriver;

// import java.io.File;
// import java.io.IOException;
// import java.text.SimpleDateFormat;
// import java.util.Date;

// /**
//  * Gestor mejorado de reportes Extent HTML con screenshots integrados
//  * Compatible con LoginTest, RegisterTest y otros tests de la suite
//  */
// public class ExtentReportManager {
//     private static ExtentReports extent;
//     private static ExtentTest test;
//     private static String reportPath;
//     private static String screenshotDir = "screenshots";
//     private static String reportsDir = "reports";
    
//     /**
//      * Inicializa el reporte Extent con configuración personalizada
//      */
//     public static void initReport() {
//         if (extent == null) {
//             createDirectories();
            
//             String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
//             reportPath = reportsDir + "/AutomationSuite_Report_" + timestamp + ".html";
            
//             ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
//             configureReporter(sparkReporter);
            
//             extent = new ExtentReports();
//             extent.attachReporter(sparkReporter);
//             setSystemInfo();
            
//             System.out.println("✅ Reporte Extent inicializado: " + reportPath);
//         }
//     }
    
//     /**
//      * Configura el reporter con tema y estilos personalizados
//      */
//     private static void configureReporter(ExtentSparkReporter sparkReporter) {
//         sparkReporter.config().setDocumentTitle("Suite de Automatización Funcional");
//         sparkReporter.config().setReportName("Reporte de Tests Automatizados");
//         sparkReporter.config().setTheme(Theme.STANDARD);
//         sparkReporter.config().setEncoding("utf-8");
//         sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");
        
//         // CSS personalizado para mejor apariencia
//         sparkReporter.config().setCss(
//             ".badge-primary { background-color: #007bff !important; } " +
//             ".badge-success { background-color: #28a745 !important; } " +
//             ".badge-danger { background-color: #dc3545 !important; } " +
//             ".badge-warning { background-color: #ffc107 !important; color: #212529 !important; } " +
//             ".card-header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%) !important; color: white !important; } " +
//             ".test-content { padding: 15px !important; } " +
//             ".step-details { margin: 10px 0 !important; }"
//         );
        
//         // JavaScript personalizado
//         sparkReporter.config().setJs(
//             "document.querySelector('.brand-logo').innerHTML = '🚀 Suite Automatización';"
//         );
//     }
    
//     /**
//      * Establece información del sistema en el reporte
//      */
//     private static void setSystemInfo() {
//         extent.setSystemInfo("🖥️ Sistema Operativo", System.getProperty("os.name"));
//         extent.setSystemInfo("☕ Versión Java", System.getProperty("java.version"));
//         extent.setSystemInfo("👤 Usuario", System.getProperty("user.name"));
//         extent.setSystemInfo("🌍 Zona Horaria", System.getProperty("user.timezone"));
//         extent.setSystemInfo("🔧 Entorno", "QA Testing");
//         extent.setSystemInfo("🌐 Navegador", "Chrome WebDriver");
//         extent.setSystemInfo("📅 Fecha Ejecución", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
//     }
    
//     /**
//      * Crea un nuevo test en el reporte
//      * @param testName Nombre del test
//      * @param description Descripción del test
//      * @return ExtentTest instance
//      */
//     public static ExtentTest createTest(String testName, String description) {
//         test = extent.createTest(testName, description);
//         test.assignCategory(detectTestCategory(testName));
//         return test;
//     }
    
//     /**
//      * Detecta la categoría del test basado en el nombre
//      */
//     private static String detectTestCategory(String testName) {
//         if (testName.toLowerCase().contains("login")) {
//             return "🔐 Login Tests";
//         } else if (testName.toLowerCase().contains("register")) {
//             return "📝 Register Tests";
//         } else if (testName.toLowerCase().contains("form")) {
//             return "📋 Form Tests";
//         } else {
//             return "🧪 General Tests";
//         }
//     }
    
//     /**
//      * Registra información general
//      */
//     public static void logInfo(String message) {
//         if (test != null) {
//             test.log(Status.INFO, "ℹ️ " + message);
//         }
//     }
    
//     /**
//      * Registra un paso exitoso
//      */
//     public static void logPass(String message) {
//         if (test != null) {
//             test.log(Status.PASS, "✅ " + message);
//         }
//     }
    
//     /**
//      * Registra un fallo
//      */
//     public static void logFail(String message) {
//         if (test != null) {
//             test.log(Status.FAIL, "❌ " + message);
//         }
//     }
    
//     /**
//      * Registra un fallo con screenshot automático
//      */
//     public static void logFailWithScreenshot(String message, String screenshotPath) {
//         if (test != null) {
//             test.log(Status.FAIL, "❌ " + message);
//             if (screenshotPath != null) {
//                 try {
//                     test.addScreenCaptureFromPath(screenshotPath, "Screenshot del Error");
//                 } catch (Exception e) {
//                     test.log(Status.WARNING, "⚠️ Error al adjuntar screenshot: " + e.getMessage());
//                 }
//             }
//         }
//     }
    
//     /**
//      * Registra advertencia
//      */
//     public static void logWarning(String message) {
//         if (test != null) {
//             test.log(Status.WARNING, "⚠️ " + message);
//         }
//     }
    
//     /**
//      * Registra skip
//      */
//     public static void logSkip(String message) {
//         if (test != null) {
//             test.log(Status.SKIP, "⏭️ " + message);
//         }
//     }
    
//     /**
//      * Toma screenshot y lo adjunta al reporte
//      */
//     public static String takeScreenshot(WebDriver driver, String testName) {
//         try {
//             createDirectories();
            
//             String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
//             String fileName = testName + "_" + timestamp + ".png";
//             String filePath = screenshotDir + "/" + fileName;
            
//             TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//             File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
//             File destinationFile = new File(filePath);
            
//             FileUtils.copyFile(sourceFile, destinationFile);
            
//             String absolutePath = destinationFile.getAbsolutePath();
//             logInfo("Screenshot capturado: " + fileName);
//             return absolutePath;
            
//         } catch (IOException e) {
//             logWarning("Error al tomar screenshot: " + e.getMessage());
//             return null;
//         }
//     }
    
//     /**
//      * Toma screenshot en caso de fallo y lo adjunta automáticamente
//      */
//     public static String takeScreenshotOnFailure(WebDriver driver, String methodName, Throwable exception) {
//         String screenshotPath = takeScreenshot(driver, methodName + "_FAILED");
//         if (screenshotPath != null) {
//             logFailWithScreenshot("Test falló: " + exception.getMessage(), screenshotPath);
//         }
//         return screenshotPath;
//     }
    
//     /**
//      * Adjunta screenshot existente al reporte
//      */
//     public static void attachScreenshot(String screenshotPath, String title) {
//         if (test != null && screenshotPath != null) {
//             try {
//                 test.addScreenCaptureFromPath(screenshotPath, title);
//                 logInfo("Screenshot adjuntado: " + title);
//             } catch (Exception e) {
//                 logWarning("Error al adjuntar screenshot: " + e.getMessage());
//             }
//         }
//     }
    
//     /**
//      * Registra datos del test de login
//      */
//     public static void logLoginTestData(UserData userData) {
//         if (test != null) {
//             test.log(Status.INFO, "<div style='background-color: #f8f9fa; padding: 10px; border-radius: 5px;'>" +
//                 "<h5>📊 Datos del Test de Login:</h5>" +
//                 "<ul>" +
//                 "<li><b>Usuario:</b> " + userData.getUsername() + "</li>" +
//                 "<li><b>Password:</b> " + maskPassword(userData.getPassword()) + "</li>" +
//                 "<li><b>Resultado Esperado:</b> " + userData.getExpectedResult() + "</li>" +
//                 "<li><b>Descripción:</b> " + userData.getDescription() + "</li>" +
//                 "</ul></div>");
//         }
//     }
    
//     /**
//      * Registra datos del test de registro
//      */
//     public static void logRegisterTestData(RegisterData registerData) {
//         if (test != null) {
//             test.log(Status.INFO, "<div style='background-color: #f8f9fa; padding: 10px; border-radius: 5px;'>" +
//                 "<h5>📊 Datos del Test de Registro:</h5>" +
//                 "<ul>" +
//                 "<li><b>Nombre:</b> " + registerData.getFirstName() + " " + registerData.getLastName() + "</li>" +
//                 "<li><b>Email:</b> " + registerData.getEmail() + "</li>" +
//                 "<li><b>Teléfono:</b> " + registerData.getPhoneNumber() + "</li>" +
//                 "<li><b>Género:</b> " + registerData.getGender() + "</li>" +
//                 "<li><b>País:</b> " + registerData.getCountry() + "</li>" +
//                 "</ul></div>");
//         }
//     }
    
//     /**
//      * Registra datos del test de formulario
//      */
//     // public static void logFormTestData(FormData formData) {
//     //     if (test != null) {
//     //         test.log(Status.INFO, "<div style='background-color: #f8f9fa; padding: 10px; border-radius: 5px;'>" +
//     //             "<h5>📊 Datos del Test de Formulario:</h5>" +
//     //             "<ul>" +
//     //             "<li><b>Nombre:</b> " + formData.getFirstName() + " " + formData.getLastName() + "</li>" +
//     //             "<li><b>Email:</b> " + formData.getEmail() + "</li>" +
//     //             "<li><b>Género:</b> " + formData.getGender() + "</li>" +
//     //             "<li><b>Teléfono:</b> " + formData.getMobile() + "</li>" +
//     //             "<li><b>Ubicación:</b> " + formData.getState() + ", " + formData.getCity() + "</li>" +
//     //             "</ul></div>");
//     //     }
//     // }
    
//     /**
//      * Enmascara contraseñas para logs seguros
//      */
//     private static String maskPassword(String password) {
//         if (password == null || password.length() <= 2) {
//             return "****";
//         }
//         return password.charAt(0) + "***" + password.charAt(password.length() - 1);
//     }
    
//     /**
//      * Registra estadísticas del test
//      */
//     public static void logTestStatistics(String testName, long duration, boolean passed) {
//         if (test != null) {
//             String statusIcon = passed ? "✅" : "❌";
//             String statusText = passed ? "EXITOSO" : "FALLIDO";
            
//             test.log(Status.INFO, "<div style='background-color: " + (passed ? "#d4edda" : "#f8d7da") + 
//                 "; padding: 10px; border-radius: 5px; margin-top: 10px;'>" +
//                 "<h5>" + statusIcon + " Resumen del Test</h5>" +
//                 "<ul>" +
//                 "<li><b>Nombre:</b> " + testName + "</li>" +
//                 "<li><b>Estado:</b> " + statusText + "</li>" +
//                 "<li><b>Duración:</b> " + duration + " ms</li>" +
//                 "</ul></div>");
//         }
//     }
    
//     /**
//      * Crea directorios necesarios
//      */
//     private static void createDirectories() {
//         String[] directories = {screenshotDir, reportsDir, "test-output"};
        
//         for (String dir : directories) {
//             File directory = new File(dir);
//             if (!directory.exists()) {
//                 directory.mkdirs();
//             }
//         }
//     }
    
//     /**
//      * Finaliza y guarda el reporte
//      */
//     public static void flushReport() {
//         if (extent != null) {
//             extent.flush();
//             System.out.println("📄 Reporte HTML generado: " + reportPath);
//             System.out.println("🔗 Abrir en navegador: file:///" + 
//                 System.getProperty("user.dir").replace("\\", "/") + "/" + reportPath);
//         }
//     }
    
//     /**
//      * Obtiene la ruta del reporte actual
//      */
//     public static String getReportPath() {
//         return reportPath;
//     }
    
//     /**
//      * Obtiene el test actual
//      */
//     public static ExtentTest getCurrentTest() {
//         return test;
//     }
// }

package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Gestor mejorado de reportes Extent HTML con screenshots integrados
 * Compatible con LoginTest, RegisterTest y otros tests de la suite
 * CORREGIDO para la nueva versión de RegisterData sin password/country
 */
public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;
    private static String screenshotDir = "screenshots";
    private static String reportsDir = "reports";
    
    /**
     * Inicializa el reporte Extent con configuración personalizada
     */
    public static void initReport() {
        if (extent == null) {
            createDirectories();
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            reportPath = reportsDir + "/AutomationSuite_Report_" + timestamp + ".html";
            
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            configureReporter(sparkReporter);
            
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            setSystemInfo();
            
            System.out.println("✅ Reporte Extent inicializado: " + reportPath);
        }
    }
    
    /**
     * Configura el reporter con tema y estilos personalizados
     */
    private static void configureReporter(ExtentSparkReporter sparkReporter) {
        sparkReporter.config().setDocumentTitle("Suite de Automatización Funcional");
        sparkReporter.config().setReportName("Reporte de Tests Automatizados");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setTimeStampFormat("dd/MM/yyyy HH:mm:ss");
        
        // CSS personalizado para mejor apariencia
        sparkReporter.config().setCss(
            ".badge-primary { background-color: #007bff !important; } " +
            ".badge-success { background-color: #28a745 !important; } " +
            ".badge-danger { background-color: #dc3545 !important; } " +
            ".badge-warning { background-color: #ffc107 !important; color: #212529 !important; } " +
            ".card-header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%) !important; color: white !important; } " +
            ".test-content { padding: 15px !important; } " +
            ".step-details { margin: 10px 0 !important; }"
        );
        
        // JavaScript personalizado
        sparkReporter.config().setJs(
            "document.querySelector('.brand-logo').innerHTML = '🚀 Suite Automatización';"
        );
    }
    
    /**
     * Establece información del sistema en el reporte
     */
    private static void setSystemInfo() {
        extent.setSystemInfo("🖥️ Sistema Operativo", System.getProperty("os.name"));
        extent.setSystemInfo("☕ Versión Java", System.getProperty("java.version"));
        extent.setSystemInfo("👤 Usuario", System.getProperty("user.name"));
        extent.setSystemInfo("🌍 Zona Horaria", System.getProperty("user.timezone"));
        extent.setSystemInfo("🔧 Entorno", "QA Testing");
        extent.setSystemInfo("🌐 Navegador", "Chrome WebDriver");
        extent.setSystemInfo("📅 Fecha Ejecución", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
    }
    
    /**
     * Crea un nuevo test en el reporte
     * @param testName Nombre del test
     * @param description Descripción del test
     * @return ExtentTest instance
     */
    public static ExtentTest createTest(String testName, String description) {
        test = extent.createTest(testName, description);
        test.assignCategory(detectTestCategory(testName));
        return test;
    }
    
    /**
     * Detecta la categoría del test basado en el nombre
     */
    private static String detectTestCategory(String testName) {
        if (testName.toLowerCase().contains("login")) {
            return "🔐 Login Tests";
        } else if (testName.toLowerCase().contains("register")) {
            return "📝 Register Tests";
        } else if (testName.toLowerCase().contains("form")) {
            return "📋 Form Tests";
        } else {
            return "🧪 General Tests";
        }
    }
    
    /**
     * Registra información general
     */
    public static void logInfo(String message) {
        if (test != null) {
            test.log(Status.INFO, "ℹ️ " + message);
        }
    }
    
    /**
     * Registra un paso exitoso
     */
    public static void logPass(String message) {
        if (test != null) {
            test.log(Status.PASS, "✅ " + message);
        }
    }
    
    /**
     * Registra un fallo
     */
    public static void logFail(String message) {
        if (test != null) {
            test.log(Status.FAIL, "❌ " + message);
        }
    }
    
    /**
     * Registra un fallo con screenshot automático
     */
    public static void logFailWithScreenshot(String message, String screenshotPath) {
        if (test != null) {
            test.log(Status.FAIL, "❌ " + message);
            if (screenshotPath != null) {
                try {
                    test.addScreenCaptureFromPath(screenshotPath, "Screenshot del Error");
                } catch (Exception e) {
                    test.log(Status.WARNING, "⚠️ Error al adjuntar screenshot: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Registra advertencia
     */
    public static void logWarning(String message) {
        if (test != null) {
            test.log(Status.WARNING, "⚠️ " + message);
        }
    }
    
    /**
     * Registra skip
     */
    public static void logSkip(String message) {
        if (test != null) {
            test.log(Status.SKIP, "⏭️ " + message);
        }
    }
    
    /**
     * Toma screenshot y lo adjunta al reporte
     */
    public static String takeScreenshot(WebDriver driver, String testName) {
        try {
            createDirectories();
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = screenshotDir + "/" + fileName;
            
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(filePath);
            
            FileUtils.copyFile(sourceFile, destinationFile);
            
            String absolutePath = destinationFile.getAbsolutePath();
            logInfo("Screenshot capturado: " + fileName);
            return absolutePath;
            
        } catch (IOException e) {
            logWarning("Error al tomar screenshot: " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Toma screenshot en caso de fallo y lo adjunta automáticamente
     */
    public static String takeScreenshotOnFailure(WebDriver driver, String methodName, Throwable exception) {
        String screenshotPath = takeScreenshot(driver, methodName + "_FAILED");
        if (screenshotPath != null) {
            logFailWithScreenshot("Test falló: " + exception.getMessage(), screenshotPath);
        }
        return screenshotPath;
    }
    
    /**
     * Adjunta screenshot existente al reporte
     */
    public static void attachScreenshot(String screenshotPath, String title) {
        if (test != null && screenshotPath != null) {
            try {
                test.addScreenCaptureFromPath(screenshotPath, title);
                logInfo("Screenshot adjuntado: " + title);
            } catch (Exception e) {
                logWarning("Error al adjuntar screenshot: " + e.getMessage());
            }
        }
    }
    
    /**
     * Registra datos del test de login
     */
    public static void logLoginTestData(UserData userData) {
        if (test != null) {
            test.log(Status.INFO, "<div style='background-color: #f8f9fa; padding: 10px; border-radius: 5px;'>" +
                "<h5>📊 Datos del Test de Login:</h5>" +
                "<ul>" +
                "<li><b>Usuario:</b> " + userData.getUsername() + "</li>" +
                "<li><b>Password:</b> " + maskPassword(userData.getPassword()) + "</li>" +
                "<li><b>Resultado Esperado:</b> " + userData.getExpectedResult() + "</li>" +
                "<li><b>Descripción:</b> " + userData.getDescription() + "</li>" +
                "</ul></div>");
        }
    }
    
    /**
     * Registra datos del test de registro - CORREGIDO para nueva RegisterData
     */
    public static void logRegisterTestData(RegisterData registerData) {
        if (test != null) {
            test.log(Status.INFO, "<div style='background-color: #f8f9fa; padding: 10px; border-radius: 5px;'>" +
                "<h5>📊 Datos del Test de Registro DemoQA:</h5>" +
                "<ul>" +
                "<li><b>Nombre Completo:</b> " + registerData.getFullName() + "</li>" +
                "<li><b>Email:</b> " + registerData.getEmail() + "</li>" +
                "<li><b>Teléfono:</b> " + registerData.getPhoneNumber() + "</li>" +
                "<li><b>Fecha Nacimiento:</b> " + registerData.getDateOfBirth() + "</li>" +
                "<li><b>Género:</b> " + registerData.getGender() + "</li>" +
                "<li><b>Estado:</b> " + registerData.getState() + "</li>" +
                "<li><b>Ciudad:</b> " + registerData.getCity() + "</li>" +
                "<li><b>Dirección:</b> " + registerData.getCurrentAddress().replace("\n", "<br/>") + "</li>" +
                "</ul></div>");
        }
    }
    
    /**
     * Enmascara contraseñas para logs seguros
     */
    private static String maskPassword(String password) {
        if (password == null || password.length() <= 2) {
            return "****";
        }
        return password.charAt(0) + "***" + password.charAt(password.length() - 1);
    }
    
    /**
     * Registra estadísticas del test
     */
    public static void logTestStatistics(String testName, long duration, boolean passed) {
        if (test != null) {
            String statusIcon = passed ? "✅" : "❌";
            String statusText = passed ? "EXITOSO" : "FALLIDO";
            
            test.log(Status.INFO, "<div style='background-color: " + (passed ? "#d4edda" : "#f8d7da") + 
                "; padding: 10px; border-radius: 5px; margin-top: 10px;'>" +
                "<h5>" + statusIcon + " Resumen del Test</h5>" +
                "<ul>" +
                "<li><b>Nombre:</b> " + testName + "</li>" +
                "<li><b>Estado:</b> " + statusText + "</li>" +
                "<li><b>Duración:</b> " + duration + " ms</li>" +
                "</ul></div>");
        }
    }
    
    /**
     * Registra información específica de browser para cross-browser testing
     */
    public static void logBrowserInfo(String browserName, String version) {
        if (test != null) {
            String browserIcon = getBrowserIcon(browserName);
            test.log(Status.INFO, "<div style='background-color: #e7f3ff; padding: 10px; border-radius: 5px;'>" +
                "<h5>" + browserIcon + " Información del Navegador:</h5>" +
                "<ul>" +
                "<li><b>Navegador:</b> " + browserName + "</li>" +
                "<li><b>Versión:</b> " + (version != null ? version : "Automática") + "</li>" +
                "</ul></div>");
        }
    }
    
    /**
     * Obtiene el icono según el navegador
     */
    private static String getBrowserIcon(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return "🔵";
            case "firefox":
                return "🟠";
            case "edge":
                return "🔷";
            case "safari":
                return "🌐";
            default:
                return "🌍";
        }
    }
    
    /**
     * Registra detalles específicos del formulario DemoQA
     */
    public static void logDemoQAFormDetails(RegisterData registerData) {
        if (test != null) {
            test.log(Status.INFO, "<div style='background-color: #fff3cd; padding: 10px; border-radius: 5px;'>" +
                "<h5>📝 Detalles del Formulario DemoQA:</h5>" +
                "<table style='width: 100%; border-collapse: collapse;'>" +
                "<tr><td style='border: 1px solid #ddd; padding: 8px;'><b>Campo</b></td><td style='border: 1px solid #ddd; padding: 8px;'><b>Valor</b></td></tr>" +
                "<tr><td style='border: 1px solid #ddd; padding: 8px;'>First Name</td><td style='border: 1px solid #ddd; padding: 8px;'>" + registerData.getFirstName() + "</td></tr>" +
                "<tr><td style='border: 1px solid #ddd; padding: 8px;'>Last Name</td><td style='border: 1px solid #ddd; padding: 8px;'>" + registerData.getLastName() + "</td></tr>" +
                "<tr><td style='border: 1px solid #ddd; padding: 8px;'>Email</td><td style='border: 1px solid #ddd; padding: 8px;'>" + registerData.getEmail() + "</td></tr>" +
                "<tr><td style='border: 1px solid #ddd; padding: 8px;'>Mobile</td><td style='border: 1px solid #ddd; padding: 8px;'>" + registerData.getPhoneNumber() + "</td></tr>" +
                "<tr><td style='border: 1px solid #ddd; padding: 8px;'>Gender</td><td style='border: 1px solid #ddd; padding: 8px;'>" + registerData.getGender() + "</td></tr>" +
                "<tr><td style='border: 1px solid #ddd; padding: 8px;'>Date of Birth</td><td style='border: 1px solid #ddd; padding: 8px;'>" + registerData.getDateOfBirth() + "</td></tr>" +
                "<tr><td style='border: 1px solid #ddd; padding: 8px;'>State</td><td style='border: 1px solid #ddd; padding: 8px;'>" + registerData.getState() + "</td></tr>" +
                "<tr><td style='border: 1px solid #ddd; padding: 8px;'>City</td><td style='border: 1px solid #ddd; padding: 8px;'>" + registerData.getCity() + "</td></tr>" +
                "</table></div>");
        }
    }
    
    /**
     * Crea directorios necesarios
     */
    private static void createDirectories() {
        String[] directories = {screenshotDir, reportsDir, "test-output"};
        
        for (String dir : directories) {
            File directory = new File(dir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
        }
    }
    
    /**
     * Finaliza y guarda el reporte
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("📄 Reporte HTML generado: " + reportPath);
            System.out.println("🔗 Abrir en navegador: file:///" + 
                System.getProperty("user.dir").replace("\\", "/") + "/" + reportPath);
        }
    }
    
    /**
     * Obtiene la ruta del reporte actual
     */
    public static String getReportPath() {
        return reportPath;
    }
    
    /**
     * Obtiene el test actual
     */
    public static ExtentTest getCurrentTest() {
        return test;
    }
}