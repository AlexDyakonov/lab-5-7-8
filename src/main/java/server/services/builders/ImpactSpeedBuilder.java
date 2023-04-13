package server.services.builders;

import server.exception.ApplicationException;
import server.exception.FileException;
import server.exception.ValidationException;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.*;
import static util.Message.getMessage;

/**
 * The type Impact speed builder.
 */
public class ImpactSpeedBuilder {
    /**
     * Get impact speed float.
     *
     * @param reader the reader
     * @return the float
     */
    public static float getImpactSpeed(BufferedReader reader) {
        try {
            String num = reader.readLine();
            if (num == null) {
                throw new ValidationException(error("Встречен null при чтения строки для поля impact speed."));
            }
            return Float.parseFloat(num);
        } catch (NumberFormatException e) {
            throw new ValidationException(unsuccess("Значение impactSpeed должно быть числом. Введите еще раз."), e);
        } catch (IOException e2) {
            throw new ApplicationException(error("HumanBeingRequestDTOBuilder.build -> Ошибка чтения из клавиатуры"));
        }
    }

    /**
     * Impact speed builder float.
     *
     * @param cmdReader  the cmd reader
     * @param fileReader the file reader
     * @param type       the type
     * @return the float
     */
    public static float impactSpeedBuilder(BufferedReader cmdReader, BufferedReader fileReader, BuilderType type) {
        if (type == BuilderType.CMD) {
            try {
                System.out.println(getMessage("input_impact_speed", LANGUAGE.RU));
                return getImpactSpeed(cmdReader);
            } catch (ValidationException | ApplicationException e) {
                System.out.println(e.getMessage());
                return impactSpeedBuilder(cmdReader, fileReader, BuilderType.CMD);
            }
        } else {
            try {
                return getImpactSpeed(fileReader);
            } catch (ValidationException | ApplicationException | FileException e) {
                System.out.println(e.getMessage());
                return impactSpeedBuilder(cmdReader, fileReader, BuilderType.CMD);
            }
        }
    }
}
