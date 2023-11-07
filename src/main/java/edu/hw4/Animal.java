package edu.hw4;

public record Animal(
    String name,
    Type type,
    Sex sex,
    int age,
    int height,
    int weight,
    boolean bites
) {
    enum Type {
        CAT, DOG, BIRD, FISH, SPIDER
    }

    enum Sex {
        M, F
    }

    enum Paws {
        CAT(4), DOG(4), BIRD(2), FISH(0), SPIDER(8);
        private final int value;

        Paws(int value) {
            this.value = value;
        }
    }

    public int paws() {
        return switch (type) {
            case CAT -> Paws.CAT.value;
            case DOG -> Paws.DOG.value;
            case BIRD -> Paws.BIRD.value;
            case FISH -> Paws.FISH.value;
            case SPIDER -> Paws.SPIDER.value;
        };
    }
}
