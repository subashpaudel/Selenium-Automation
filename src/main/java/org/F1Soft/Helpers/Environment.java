package org.F1Soft.Helpers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class Environment {
    public static String browser;
    public static String dbConnectionString;
    public static String username;
    public static String password;

     static {
         try {
             JSONParser parser = new JSONParser();
             Object obj = parser.parse(new FileReader("src/test/resources/envVar.json"));
             String env =System.getProperty("env");
             env = env!=null ? env.toUpperCase() : "DEBUG";
             JSONObject jsonObject;
             try {
                  jsonObject = (JSONObject) ((JSONObject) obj).get(env);
             }
             catch (Exception e)
             {
                 Log.warn(e.getMessage());
                 jsonObject = (JSONObject) ((JSONObject) obj).get("DEBUG");
             }
             browser= (String) jsonObject.get("browser");
             dbConnectionString= (String) jsonObject.get("dbConnectionString");
             username= (String) jsonObject.get("username");
             password= (String) jsonObject.get("password");

         } catch(Exception e) {
             e.printStackTrace();
         }
    }


}
