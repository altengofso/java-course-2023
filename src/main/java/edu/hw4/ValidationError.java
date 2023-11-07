package edu.hw4;

import java.util.Objects;

public class ValidationError {
    private final String errorDescription;

    public ValidationError(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    @Override
    public String toString() {
        return errorDescription;
    }

    @Override public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ValidationError that = (ValidationError) o;
        return Objects.equals(getErrorDescription(), that.getErrorDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getErrorDescription());
    }
}
