import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Compile : javac -cp .:json-simple-1.1.1.jar WeatherParse.java
// Execute : java -cp .:json-simple-1.1.1.jar WeatherParse

@SuppressWarnings("unused")
public class WeatherParse {

    private static String JSON_WEATHER_PATH = "weather.json";

    public static void main(String[] args) {

        FileReader jsonFile = null;
        try {
            // lecture du fichier json
            jsonFile = new FileReader(JSON_WEATHER_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // TODO parser le fichier
        JSONParser parser = new JSONParser();
        Object jsonParsed = null;
        try {
            jsonParsed = parser.parse(jsonFile);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        // TODO récupérer la racine du document
        JSONObject root = (JSONObject) jsonParsed;

        // TODO afficher la valeur de l'attribut "name" de la racine
        System.out.println("City name : " + root.get("name"));
        
        // TODO afficher les valeurs des attributs "lat" et "lon" de l'élément "coord" contenu dans la racine
        JSONObject coords = (JSONObject) root.get("coord");
        System.out.println("City latitude: "+ coords.get("lat"));
    	System.out.println("City longitude: "+ coords.get("lon"));
        // TODO parcourir tous les éléments de "weather" et afficher le contenu de "main"
    	JSONArray weathers = (JSONArray) root.get("weather");
    	for (int i = 0; i < weathers.size(); i++) {
    	    JSONObject w = (JSONObject) weathers.get(i);
    	    System.out.println("Weather = " + w.get("main"));
    	}
        /*
            Résultat attendu :
            * City name: London
            * City latitude: 51.51
            * City longitude: -0.13
            * Weather: Drizzle
            * Weather: Clear
        */
    }
}
