package json.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ValidateJson {
    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private static final Validator validator = validatorFactory.getValidator();

    public static <T> void validate(T object) throws ConstraintViolated {
        List<String> violations = validator.validate(object).stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        if (!violations.isEmpty()) {
            throw new ConstraintViolated(String.join(", ", violations));
        }
    }
}
