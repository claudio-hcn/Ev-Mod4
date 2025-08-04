package utils;

/**
 * Clase de datos para tests de Login
 * Contiene información de usuario para autenticación
 */
public class UserData {
    private String username;
    private String password;
    private String expectedResult;
    private String description;
    
    /**
     * Constructor para datos de usuario
     * @param username Nombre de usuario o email
     * @param password Contraseña del usuario
     * @param expectedResult Resultado esperado (success/failed)
     * @param description Descripción del caso de test
     */
    public UserData(String username, String password, String expectedResult, String description) {
        this.username = username;
        this.password = password;
        this.expectedResult = expectedResult;
        this.description = description;
    }
    
    // Getters
    public String getUsername() { 
        return username != null ? username : ""; 
    }
    
    public String getPassword() { 
        return password != null ? password : ""; 
    }
    
    public String getExpectedResult() { 
        return expectedResult != null ? expectedResult : ""; 
    }
    
    public String getDescription() { 
        return description != null ? description : ""; 
    }
    
    // Setters (opcional)
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Verifica si el resultado esperado es exitoso
     * @return true si se espera éxito
     */
    public boolean isSuccessExpected() {
        return expectedResult != null && 
               (expectedResult.toLowerCase().contains("success") || 
                expectedResult.toLowerCase().contains("valid"));
    }
    
    /**
     * Verifica si las credenciales están vacías
     * @return true si username o password están vacíos
     */
    public boolean hasEmptyCredentials() {
        return (username == null || username.trim().isEmpty()) ||
               (password == null || password.trim().isEmpty());
    }
    
    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", password='***'" +
                ", expectedResult='" + expectedResult + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
