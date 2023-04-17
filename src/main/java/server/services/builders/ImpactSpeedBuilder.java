package server.services.builders;

import server.exception.ApplicationException;
import server.exception.FileException;
import server.exception.ValidationException;
import server.services.BuilderType;
import util.LANGUAGE;

import java.io.BufferedReader;
import java.io.IOException;

import static client.ui.ConsoleColors.*;
import static util.Message.getError;
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
    public static float getImpactSpeed(BufferedReader reader, LANGUAGE language) {
        try {
            String num = reader.readLine();
            if (num == null) {
                throw new ValidationException(getError("impact_speed_null", language));
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
    public static float impactSpeedBuilder(BufferedReader cmdReader, BufferedReader fileReader, BuilderType type, LANGUAGE language) {
        if (type == BuilderType.CMD) {
            try {
                System.out.println(getMessage("input_impact_speed", language));
                return getImpactSpeed(cmdReader, language);
            } catch (ValidationException | ApplicationException e) {
                System.out.println(e.getMessage());
                return impactSpeedBuilder(cmdReader, fileReader, BuilderType.CMD, language);
            }
        } else {
            try {
                return getImpactSpeed(fileReader, language);
            } catch (ValidationException | ApplicationException | FileException e) {
                System.out.println(e.getMessage());
                return impactSpeedBuilder(cmdReader, fileReader, BuilderType.CMD, language);
            }
        }
    }
}
