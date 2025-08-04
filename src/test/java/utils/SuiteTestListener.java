package utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import java.lang.reflect.Field;

/**
 * Listener simplificado y compatible con todas las versiones de TestNG
 * Maneja automáticamente screenshots, reportes y logging sin métodos deprecated
 */
public class SuiteTestListener implements ITestListener {
    
    @Override
    public void onStart(ITestContext context) {
        System.out.println("🚀 ===== INICIANDO SUITE: " + context.getName() + " =====");
        ExtentReportManager.initReport();
        
        ExtentReportManager.logInfo("Suite iniciada: " + context.getName());
        System.out.println("📋 Tests programados: " + context.getAllTestMethods().length);
    }
    
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        String description = getTestDescription(result);
        
        System.out.println("🧪 Iniciando test: " + testName + " [" + getSimpleClassName(className) + "]");
        
        // Crear test en el reporte
        ExtentReportManager.createTest(testName + " - " + getSimpleClassName(className), description);
        ExtentReportManager.logInfo("Iniciando ejecución del test: " + testName);
        ExtentReportManager.logInfo("Clase: " + className);
        
        // Registrar parámetros del test si existen
        Object[] parameters = result.getParameters();
        if (parameters != null && parameters.length > 0) {
            logTestParameters(parameters);
        }
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        
        System.out.println("✅ Test EXITOSO: " + testName);
        
        ExtentReportManager.logPass("Test completado exitosamente");
        ExtentReportManager.logInfo("Estado: SUCCESS");
        
        // Tomar screenshot de éxito opcional
        WebDriver driver = getDriverFromTestInstance(result.getInstance());
        if (driver != null) {
            String screenshotPath = ExtentReportManager.takeScreenshot(driver, testName + "_SUCCESS");
            if (screenshotPath != null) {
                ExtentReportManager.attachScreenshot(screenshotPath, "✅ Estado final exitoso");
            }
        }
    }
    
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        Throwable exception = result.getThrowable();
        
        System.err.println("❌ Test FALLIDO: " + testName + " [" + getSimpleClassName(className) + "]");
        System.err.println("🐛 Error: " + exception.getMessage());
        
        ExtentReportManager.logFail("Test falló: " + exception.getMessage());
        ExtentReportManager.logInfo("Estado: FAILED");
        
        // Tomar screenshot del fallo automáticamente
        WebDriver driver = getDriverFromTestInstance(result.getInstance());
        if (driver != null) {
            String screenshotPath = ExtentReportManager.takeScreenshotOnFailure(driver, testName, exception);
            if (screenshotPath != null) {
                System.out.println("📸 Screenshot del error guardado: " + screenshotPath);
            }
        }
        
        // Registrar stack trace detallado
        if (exception != null) {
            String stackTrace = getFormattedStackTrace(exception);
            ExtentReportManager.logFail("<details><summary>🔍 Ver Stack Trace Completo</summary><pre style='background-color: #f8f9fa; padding: 10px; border-radius: 5px;'>" + 
                stackTrace + "</pre></details>");
        }
        
        // Log de información adicional del contexto
        logTestContext(result);
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String className = result.getTestClass().getName();
        
        System.out.println("⏭️ Test OMITIDO: " + testName + " [" + getSimpleClassName(className) + "]");
        
        ExtentReportManager.createTest(testName + " - " + getSimpleClassName(className), "Test omitido");
        ExtentReportManager.logSkip("Test omitido: " + testName);
        
        if (result.getThrowable() != null) {
            ExtentReportManager.logSkip("Razón: " + result.getThrowable().getMessage());
        }
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        System.out.println("⚠️ Test PARCIALMENTE EXITOSO: " + testName);
        ExtentReportManager.logWarning("Test completado con éxito parcial: " + testName);
    }
    
    @Override
    public void onFinish(ITestContext context) {
        System.out.println("🏁 ===== FINALIZANDO SUITE: " + context.getName() + " =====");
        
        // Calcular y mostrar estadísticas finales
        int passed = context.getPassedTests().size();
        int failed = context.getFailedTests().size();
        int skipped = context.getSkippedTests().size();
        int total = passed + failed + skipped;
        
        double successRate = total > 0 ? (double) passed / total * 100 : 0;
        
        System.out.println("📊 ESTADÍSTICAS FINALES:");
        System.out.println("   🎯 Total de Tests: " + total);
        System.out.println("   ✅ Exitosos: " + passed);
        System.out.println("   ❌ Fallidos: " + failed);
        System.out.println("   ⏭️ Omitidos: " + skipped);
        System.out.println("   📈 Tasa de Éxito: " + String.format("%.1f", successRate) + "%");
        
        // Finalizar reporte con estadísticas
        ExtentReportManager.logInfo("=== RESUMEN FINAL DE LA SUITE ===");
        ExtentReportManager.logInfo("Total de tests ejecutados: " + total);
        ExtentReportManager.logInfo("Tests exitosos: " + passed);
        ExtentReportManager.logInfo("Tests fallidos: " + failed);
        ExtentReportManager.logInfo("Tests omitidos: " + skipped);
        ExtentReportManager.logInfo("Tasa de éxito: " + String.format("%.1f", successRate) + "%");
        
        ExtentReportManager.flushReport();
        
        System.out.println("📄 Reporte HTML disponible en: " + ExtentReportManager.getReportPath());
        System.out.println("📂 Screenshots disponibles en: screenshots/");
        
        // Mostrar resumen final con emojis según resultado
        if (failed == 0) {
            System.out.println("🎉 ¡SUITE COMPLETADA EXITOSAMENTE! Todos los tests pasaron.");
        } else if (passed > failed) {
            System.out.println("⚠️ Suite completada con algunos fallos. Revisar reporte para detalles.");
        } else {
            System.out.println("🚨 Suite completada con múltiples fallos. Atención requerida.");
        }
    }
    
    /**
     * Obtiene el WebDriver desde la instancia del test
     */
    private WebDriver getDriverFromTestInstance(Object testInstance) {
        try {
            // Buscar campo 'driver' en la clase y clases padre
            Class<?> clazz = testInstance.getClass();
            while (clazz != null) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    if (WebDriver.class.isAssignableFrom(field.getType()) || 
                        field.getName().toLowerCase().contains("driver")) {
                        field.setAccessible(true);
                        Object driver = field.get(testInstance);
                        if (driver instanceof WebDriver) {
                            return (WebDriver) driver;
                        }
                    }
                }
                clazz = clazz.getSuperclass();
            }
        } catch (Exception e) {
            System.err.println("⚠️ No se pudo obtener WebDriver para screenshot: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * Obtiene descripción del test desde anotaciones
     */
    private String getTestDescription(ITestResult result) {
        try {
            String methodName = result.getMethod().getMethodName();
            if (methodName.toLowerCase().contains("login")) {
                return "Test de funcionalidad de inicio de sesión";
            } else if (methodName.toLowerCase().contains("register")) {
                return "Test de funcionalidad de registro de usuarios";
            } else if (methodName.toLowerCase().contains("form")) {
                return "Test de formulario con datos desde CSV";
            } else {
                return "Test de funcionalidad del sistema";
            }
        } catch (Exception e) {
            return "Test automatizado";
        }
    }
    
    /**
     * Obtiene nombre simple de la clase
     */
    private String getSimpleClassName(String fullClassName) {
        String[] parts = fullClassName.split("\\.");
        return parts[parts.length - 1];
    }
    
    /**
     * Registra parámetros del test si son datos de CSV
     */
    private void logTestParameters(Object[] parameters) {
        for (Object param : parameters) {
            if (param instanceof UserData) {
                ExtentReportManager.logLoginTestData((UserData) param);
            } else if (param instanceof RegisterData) {
                ExtentReportManager.logRegisterTestData((RegisterData) param);
            // } else if (param instanceof FormData) {
            //     ExtentReportManager.logFormTestData((FormData) param);
            } else {
                ExtentReportManager.logInfo("Parámetro del test: " + param.toString());
            }
        }
    }
    
    /**
     * Formatea stack trace para mejor legibilidad
     */
    private String getFormattedStackTrace(Throwable throwable) {
        StringBuilder sb = new StringBuilder();
        sb.append(throwable.getClass().getSimpleName()).append(": ").append(throwable.getMessage()).append("\n\n");
        
        // Mostrar solo las líneas más relevantes del stack trace
        StackTraceElement[] elements = throwable.getStackTrace();
        int count = 0;
        for (StackTraceElement element : elements) {
            if (count++ > 10) break; // Limitar a 10 líneas más relevantes
            
            String className = element.getClassName();
            if (className.contains("suiteTest") || className.contains("org.testng") || 
                className.contains("selenium")) {
                sb.append("  en ").append(element.toString()).append("\n");
            }
        }
        
        if (throwable.getCause() != null && throwable.getCause() != throwable) {
            sb.append("\nCausado por: ").append(getFormattedStackTrace(throwable.getCause()));
        }
        
        return sb.toString();
    }
    
    /**
     * Registra información adicional del contexto del test
     */
    private void logTestContext(ITestResult result) {
        try {
            ExtentReportManager.logInfo("=== INFORMACIÓN DEL CONTEXTO ===");
            ExtentReportManager.logInfo("Método: " + result.getMethod().getMethodName());
            ExtentReportManager.logInfo("Clase: " + result.getTestClass().getName());
            ExtentReportManager.logInfo("Thread ID: " + Thread.currentThread().getId());
            ExtentReportManager.logInfo("Timestamp: " + new java.util.Date(System.currentTimeMillis()));
            
            if (result.getParameters() != null && result.getParameters().length > 0) {
                ExtentReportManager.logInfo("Número de parámetros: " + result.getParameters().length);
            }
        } catch (Exception e) {
            ExtentReportManager.logWarning("Error al registrar contexto: " + e.getMessage());
        }
    }
}