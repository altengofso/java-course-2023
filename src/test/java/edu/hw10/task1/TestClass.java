package edu.hw10.task1;

import edu.hw10.task1.annotations.Max;
import edu.hw10.task1.annotations.Min;
import edu.hw10.task1.annotations.NotNull;
import lombok.Getter;

@Getter
public class TestClass {
    private final String stringField;
    private final short shortField;
    private final int intField;
    private final long longField;
    private final float floatField;
    private final double doubleField;
    private final boolean booleanField;

    public TestClass(
        @NotNull @Min(30) String stringField,
        @Min(1) @Max(15) short shortField,
        @Min(20) @Max(25) int intField,
        @Min(30) @Max(35) long longField,
        @Min(45) @Max(50) float floatField,
        @Min(55) @Max(60) double doubleField,
        boolean booleanField
    ) {
        this.stringField = stringField;
        this.shortField = shortField;
        this.intField = intField;
        this.longField = longField;
        this.floatField = floatField;
        this.doubleField = doubleField;
        this.booleanField = booleanField;
    }

    public static TestClass create(
        @NotNull @Min(30) String stringField,
        @Min(1) @Max(15) short shortField,
        @Min(20) @Max(25) int intField,
        @Min(30) @Max(35) long longField,
        @Min(45) @Max(50) float floatField,
        @Min(55) @Max(60) double doubleField,
        boolean booleanField
    ) {
        return new TestClass(stringField, shortField, intField, longField, floatField, doubleField, booleanField);
    }
}
