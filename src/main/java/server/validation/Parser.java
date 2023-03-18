package server.validation;

public class Parser {
    public static boolean stringToBoolean(String line){
        boolean response = false;
        if (line.equals("true") || line.equals("t") || line.equals("y")){
            response = true;
        }
        return response;
    }
}
