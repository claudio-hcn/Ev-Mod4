// package utils;

// /**
//  * Clase de datos para tests de Registro
//  * Contiene información completa para registro de usuarios
//  */
// public class RegisterData {
//     private String firstName;
//     private String lastName;
//     private String email;
//     private String password;
//     private String confirmPassword;
//     private String phoneNumber;
//     private String dateOfBirth;
//     private String gender;
//     private String country;
//     private String acceptTerms;
    
//     /**
//      * Constructor para datos de registro
//      */
//     public RegisterData(String firstName, String lastName, String email, String password, 
//                        String confirmPassword, String phoneNumber, String dateOfBirth, 
//                        String gender, String country, String acceptTerms) {
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.email = email;
//         this.password = password;
//         this.confirmPassword = confirmPassword;
//         this.phoneNumber = phoneNumber;
//         this.dateOfBirth = dateOfBirth;
//         this.gender = gender;
//         this.country = country;
//         this.acceptTerms = acceptTerms;
//     }
    
//     // Getters con validación null-safe
//     public String getFirstName() { 
//         return firstName != null ? firstName : ""; 
//     }
    
//     public String getLastName() { 
//         return lastName != null ? lastName : ""; 
//     }
    
//     public String getEmail() { 
//         return email != null ? email : ""; 
//     }
    
//     public String getPassword() { 
//         return password != null ? password : ""; 
//     }
    
//     public String getConfirmPassword() { 
//         return confirmPassword != null ? confirmPassword : ""; 
//     }
    
//     public String getPhoneNumber() { 
//         return phoneNumber != null ? phoneNumber : ""; 
//     }
    
//     public String getDateOfBirth() { 
//         return dateOfBirth != null ? dateOfBirth : ""; 
//     }
    
//     public String getGender() { 
//         return gender != null ? gender : ""; 
//     }
    
//     public String getCountry() { 
//         return country != null ? country : ""; 
//     }
    
//     public String getAcceptTerms() { 
//         return acceptTerms != null ? acceptTerms : ""; 
//     }
    
//     // Setters
//     public void setFirstName(String firstName) {
//         this.firstName = firstName;
//     }
    
//     public void setLastName(String lastName) {
//         this.lastName = lastName;
//     }
    
//     public void setEmail(String email) {
//         this.email = email;
//     }
    
//     public void setPassword(String password) {
//         this.password = password;
//     }
    
//     public void setConfirmPassword(String confirmPassword) {
//         this.confirmPassword = confirmPassword;
//     }
    
//     public void setPhoneNumber(String phoneNumber) {
//         this.phoneNumber = phoneNumber;
//     }
    
//     public void setDateOfBirth(String dateOfBirth) {
//         this.dateOfBirth = dateOfBirth;
//     }
    
//     public void setGender(String gender) {
//         this.gender = gender;
//     }
    
//     public void setCountry(String country) {
//         this.country = country;
//     }
    
//     public void setAcceptTerms(String acceptTerms) {
//         this.acceptTerms = acceptTerms;
//     }
    
//     // Métodos de utilidad
    
//     /**
//      * Obtiene el nombre completo
//      * @return Nombre y apellido concatenados
//      */
//     public String getFullName() {
//         return getFirstName() + " " + getLastName();
//     }
    
//     /**
//      * Verifica si las contraseñas coinciden
//      * @return true si password y confirmPassword son iguales
//      */
//     public boolean doPasswordsMatch() {
//         return getPassword().equals(getConfirmPassword());
//     }
    
//     /**
//      * Verifica si acepta términos y condiciones
//      * @return true si acceptTerms es "true"
//      */
//     public boolean acceptsTerms() {
//         return "true".equalsIgnoreCase(getAcceptTerms());
//     }
    
//     /**
//      * Verifica si el email tiene formato válido básico
//      * @return true si contiene @ y .
//      */
//     public boolean hasValidEmailFormat() {
//         String emailStr = getEmail();
//         return emailStr.contains("@") && emailStr.contains(".");
//     }
    
//     /**
//      * Verifica si todos los campos requeridos están completos
//      * @return true si firstName, lastName, email y password no están vacíos
//      */
//     public boolean hasRequiredFields() {
//         return !getFirstName().isEmpty() && 
//                !getLastName().isEmpty() && 
//                !getEmail().isEmpty() && 
//                !getPassword().isEmpty();
//     }
    
//     /**
//      * Verifica si hay campos vacíos
//      * @return true si algún campo principal está vacío
//      */
//     public boolean hasEmptyFields() {
//         return getFirstName().isEmpty() || 
//                getLastName().isEmpty() || 
//                getEmail().isEmpty() || 
//                getPassword().isEmpty();
//     }
    
//     @Override
//     public String toString() {
//         return "RegisterData{" +
//                 "firstName='" + firstName + '\'' +
//                 ", lastName='" + lastName + '\'' +
//                 ", email='" + email + '\'' +
//                 ", password='***'" +
//                 ", confirmPassword='***'" +
//                 ", phoneNumber='" + phoneNumber + '\'' +
//                 ", dateOfBirth='" + dateOfBirth + '\'' +
//                 ", gender='" + gender + '\'' +
//                 ", country='" + country + '\'' +
//                 ", acceptTerms='" + acceptTerms + '\'' +
//                 '}';
//     }
// }

package utils;

/**
 * Clase de datos para tests de Registro DemoQA
 * Contiene solo los campos que realmente necesita el formulario
 */
public class RegisterData {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String dateOfBirth;
    private String gender;
    private String state;
    private String city;
    private String currentAddress;
    
    /**
     * Constructor para datos de registro DemoQA
     */
    public RegisterData(String firstName, String lastName, String email, String phoneNumber, 
                       String dateOfBirth, String gender, String state, String city, 
                       String currentAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.state = state;
        this.city = city;
        this.currentAddress = currentAddress;
    }
    
    /**
     * Constructor simplificado (con valores por defecto)
     */
    public RegisterData(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = "15 January 1990";
        this.gender = "Male";
        this.state = "NCR";
        this.city = "Delhi";
        this.currentAddress = firstName + " " + lastName + " Address\n123 Main Street\nDelhi, India";
    }
    
    // Getters con validación null-safe
    public String getFirstName() { 
        return firstName != null ? firstName : ""; 
    }
    
    public String getLastName() { 
        return lastName != null ? lastName : ""; 
    }
    
    public String getEmail() { 
        return email != null ? email : ""; 
    }
    
    public String getPhoneNumber() { 
        return phoneNumber != null ? phoneNumber : ""; 
    }
    
    public String getDateOfBirth() { 
        return dateOfBirth != null ? dateOfBirth : "15 January 1990"; 
    }
    
    public String getGender() { 
        return gender != null && !gender.isEmpty() ? gender : "Male"; 
    }
    
    public String getState() { 
        return state != null && !state.isEmpty() ? state : "NCR"; 
    }
    
    public String getCity() { 
        return city != null && !city.isEmpty() ? city : "Delhi"; 
    }
    
    public String getCurrentAddress() { 
        return currentAddress != null ? currentAddress : (getFirstName() + " " + getLastName() + " Address\n123 Main Street\nDelhi, India"); 
    }
    
    // Setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public void setCurrentAddress(String currentAddress) {
        this.currentAddress = currentAddress;
    }
    
    // Métodos de utilidad
    
    /**
     * Obtiene el nombre completo
     */
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
    
    /**
     * Verifica si el email tiene formato válido básico
     */
    public boolean hasValidEmailFormat() {
        String emailStr = getEmail();
        return emailStr.contains("@") && emailStr.contains(".");
    }
    
    /**
     * Verifica si todos los campos requeridos están completos
     * Para DemoQA: firstName, lastName, gender, phoneNumber son requeridos
     */
    public boolean hasRequiredFields() {
        return !getFirstName().isEmpty() && 
               !getLastName().isEmpty() && 
               !getGender().isEmpty() && 
               !getPhoneNumber().isEmpty();
    }
    
    /**
     * Verifica si hay campos vacíos
     */
    public boolean hasEmptyFields() {
        return getFirstName().isEmpty() || 
               getLastName().isEmpty() || 
               getPhoneNumber().isEmpty();
    }
    
    /**
     * Obtiene lista de materias por defecto
     */
    public String[] getDefaultSubjects() {
        return new String[]{"Math", "Physics", "Chemistry"};
    }
    
    /**
     * Obtiene lista de hobbies por defecto
     */
    public String[] getDefaultHobbies() {
        return new String[]{"Sports", "Reading", "Music"};
    }
    
    @Override
    public String toString() {
        return "RegisterData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", currentAddress='" + currentAddress + '\'' +
                '}';
    }
}