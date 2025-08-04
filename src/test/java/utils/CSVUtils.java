// package utils;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// /**
//  * CSVUtils MINIMALISTA - Solo lo esencial para el proyecto
//  * Versión simplificada que hace exactamente lo que necesitas
//  */
// public class CSVUtils {
    
//     /**
//      * Lee datos de login desde User.csv
//      * @param filePath Ruta del archivo CSV
//      * @return Lista de UserData
//      */
//     public static List<UserData> readUserDataFromCSV(String filePath) {
//         List<UserData> userList = new ArrayList<>();
        
//         try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//             br.readLine(); // Saltar header
//             String line;
            
//             while ((line = br.readLine()) != null) {
//                 if (!line.trim().isEmpty()) {
//                     String[] data = line.split(",");
//                     if (data.length >= 4) {
//                         userList.add(new UserData(
//                             data[0].trim(), // username
//                             data[1].trim(), // password
//                             data[2].trim(), // expectedResult
//                             data[3].trim()  // description
//                         ));
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             System.err.println("Error leyendo User.csv: " + e.getMessage());
//         }
        
//         return userList;
//     }
    
//     /**
//      * Lee datos de registro desde Register.csv
//      * @param filePath Ruta del archivo CSV
//      * @return Lista de RegisterData
//      */
//     public static List<RegisterData> readRegisterDataFromCSV(String filePath) {
//         List<RegisterData> registerList = new ArrayList<>();
        
//         try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//             br.readLine(); // Saltar header
//             String line;
            
//             while ((line = br.readLine()) != null) {
//                 if (!line.trim().isEmpty()) {
//                     String[] data = line.split(",");
//                     if (data.length >= 6) {
//                         registerList.add(new RegisterData(
//                             data[0].trim(), // firstName
//                             data[1].trim(), // lastName
//                             data[2].trim(), // email
//                             data[3].trim(), // password
//                             data[4].trim(), // confirmPassword
//                             data[5].trim(), // phoneNumber
//                             data.length > 6 ? data[6].trim() : "", // dateOfBirth
//                             data.length > 7 ? data[7].trim() : "", // gender
//                             data.length > 8 ? data[8].trim() : "", // country
//                             data.length > 9 ? data[9].trim() : ""  // acceptTerms
//                         ));
//                     }
//                 }
//             }
//         } catch (IOException e) {
//             System.err.println("Error leyendo Register.csv: " + e.getMessage());
//         }
        
//         return registerList;
//     }
    
//     /**
//      * Verifica si un archivo CSV existe
//      * @param filePath Ruta del archivo
//      * @return true si existe
//      */
//     public static boolean validateCSVFile(String filePath) {
//         try {
//             return new java.io.File(filePath).exists();
//         } catch (Exception e) {
//             return false;
//         }
//     }
// }

package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSVUtils ACTUALIZADO - Para nuevo formato Register.csv sin passwords
 */
public class CSVUtils {
    
    /**
     * Lee datos de login desde User.csv (mantener igual)
     */
    public static List<UserData> readUserDataFromCSV(String filePath) {
        List<UserData> userList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Saltar header
            String line;
            
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] data = line.split(",");
                    if (data.length >= 4) {
                        userList.add(new UserData(
                            data[0].trim(), // username
                            data[1].trim(), // password
                            data[2].trim(), // expectedResult
                            data[3].trim()  // description
                        ));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo User.csv: " + e.getMessage());
        }
        
        return userList;
    }
    
    /**
     * Lee datos de registro desde Register.csv - NUEVO FORMATO
     * CSV: firstName,lastName,email,phoneNumber,dateOfBirth,gender,state,city,currentAddress
     */
    public static List<RegisterData> readRegisterDataFromCSV(String filePath) {
        List<RegisterData> registerList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine(); // Leer header
            System.out.println("📋 Header CSV: " + headerLine);
            
            String line;
            int lineNumber = 1;
            
            while ((line = br.readLine()) != null) {
                lineNumber++;
                if (!line.trim().isEmpty()) {
                    // Manejar campos con comillas que pueden contener comas (como currentAddress)
                    String[] data = parseCSVLine(line);
                    
                    System.out.println("🔍 Línea " + lineNumber + ": " + line);
                    System.out.println("📊 Campos parseados: " + data.length);
                    
                    // Validar que tengamos al menos los campos mínimos (firstName, lastName, email, phone)
                    if (data.length >= 4) {
                        try {
                            // Mapeo según el nuevo formato CSV
                            RegisterData registerData = new RegisterData(
                                data[0].trim(), // firstName
                                data[1].trim(), // lastName  
                                data[2].trim(), // email
                                data[3].trim(), // phoneNumber
                                data.length > 4 ? data[4].trim() : "15 January 1990", // dateOfBirth
                                data.length > 5 ? data[5].trim() : "Male", // gender
                                data.length > 6 ? data[6].trim() : "NCR", // state
                                data.length > 7 ? data[7].trim() : "Delhi", // city
                                data.length > 8 ? cleanAddress(data[8].trim()) : "" // currentAddress
                            );
                            
                            registerList.add(registerData);
                            
                            System.out.println("✅ RegisterData creado: " + registerData.getFullName() + 
                                             " | " + registerData.getState() + "/" + registerData.getCity());
                            
                        } catch (Exception e) {
                            System.err.println("❌ Error procesando línea " + lineNumber + ": " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        System.err.println("⚠️ Línea " + lineNumber + " tiene pocos campos (" + data.length + "): " + line);
                    }
                }
            }
            
            System.out.println("📊 Total RegisterData creados: " + registerList.size());
            
        } catch (IOException e) {
            System.err.println("❌ Error leyendo Register.csv: " + e.getMessage());
            e.printStackTrace();
        }
        
        return registerList;
    }
    
    /**
     * Parsea una línea CSV manejando campos con comillas
     */
    private static String[] parseCSVLine(String line) {
        List<String> fields = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder currentField = new StringBuilder();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }
        
        // Agregar el último campo
        fields.add(currentField.toString());
        
        return fields.toArray(new String[0]);
    }
    
    /**
     * Limpia el campo de dirección (remueve comillas y convierte \n)
     */
    private static String cleanAddress(String address) {
        if (address == null) return "";
        
        // Remover comillas
        address = address.replace("\"", "");
        
        // Convertir \n literales a saltos de línea reales
        address = address.replace("\\n", "\n");
        
        return address;
    }
    
    /**
     * Verifica si un archivo CSV existe
     */
    public static boolean validateCSVFile(String filePath) {
        try {
            java.io.File file = new java.io.File(filePath);
            boolean exists = file.exists();
            System.out.println("🔍 Validando CSV: " + filePath + " -> " + (exists ? "EXISTE" : "NO EXISTE"));
            
            if (exists) {
                // Mostrar primeras líneas para debug
                try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                    System.out.println("📄 Primeras líneas del CSV:");
                    String line;
                    int count = 0;
                    while ((line = br.readLine()) != null && count < 3) {
                        System.out.println("   " + (count + 1) + ": " + line);
                        count++;
                    }
                } catch (Exception e) {
                    System.err.println("Error leyendo contenido del CSV: " + e.getMessage());
                }
            }
            
            return exists;
        } catch (Exception e) {
            System.err.println("❌ Error validando CSV: " + e.getMessage());
            return false;
        }
    }
}