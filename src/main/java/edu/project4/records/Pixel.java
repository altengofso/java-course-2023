package edu.project4.records;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;
}
