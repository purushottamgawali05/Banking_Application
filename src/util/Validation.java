package util;

@FunctionalInterface
public interface Validation {
    void validate(String value) throws exceptions.ValidationException;
}
