package server.services.builders;

import server.exception.ValidationException;
import server.services.BuilderType;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.error;
import static client.ui.ConsoleColors.whiteStr;
import static server.validation.Parser.stringToBoolean;

public class BooleanBuilder {
    public static boolean getBool(BufferedReader reader){
        boolean response = false;
        String request = "";
        try {
            request = reader.readLine().toLowerCase();
            response = stringToBoolean(request);
        } catch (IOException e) {
            System.out.println(error("HumanBeingRequestDTOBuilder.build -> Ошибка чтения из клавиатуры"));
        }
        return response;
    }
    public static boolean boolBuilder(BufferedReader cmdreader, BufferedReader filereader, String message, BuilderType type){
        if (type == BuilderType.CMD){
            try {
                System.out.println(whiteStr(message));
                return getBool(cmdreader);
            } catch (ValidationException e){
                System.out.println(e.getMessage());
                return boolBuilder(cmdreader, filereader, message, BuilderType.CMD);
            }
        } else {
            try {
                return getBool(filereader);
            } catch (ValidationException e){
                System.out.println(e.getMessage());
                return boolBuilder(cmdreader, filereader, message, BuilderType.CMD);
            }
        }
    }

}
