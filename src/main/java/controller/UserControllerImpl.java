package controller;

import exception.ValidationException;
import java.util.function.Function;

public class UserControllerImpl {
    private boolean validateUserName(String userName){
        return (userName != null && !userName.equals(""));
    }

    private boolean validateId(Long id){
        return (id != null && id > 0);
    }

    private <T> void validate(T object, Function<T, Boolean> validator, String errorMessage){
        if (!validator.apply(object)){
            throw new ValidationException(errorMessage);
        }
    }
}
