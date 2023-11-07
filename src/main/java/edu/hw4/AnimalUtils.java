package edu.hw4;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class AnimalUtils {

    private final static int HUNDRED = 100;

    public static List<Animal> task1SortByHeight(Collection<Animal> animals) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::height)).toList();
    }

    public static List<Animal> task2KHeaviest(Collection<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparingInt(Animal::weight).reversed()).limit(k).toList();
    }

    public static Map<Animal.Type, Integer> task3CountAnimalOfEachType(Collection<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::type, Collectors.summingInt(it -> 1)));
    }

    public static Animal task4AnimalWithLongestName(Collection<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(animal -> animal.name().length())).orElse(null);
    }

    public static Animal.Sex task5WhichSexMore(Collection<Animal> animals) {
        return animals.stream().collect(Collectors.groupingBy(Animal::sex, Collectors.counting()))
            .entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow().getKey();
    }

    public static Map<Animal.Type, Animal> task6HeaviestAnimalOfEachType(Collection<Animal> animals) {
        return animals.stream()
            .collect(Collectors.toMap(
                Animal::type,
                Function.identity(),
                BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
            ));
    }

    public static Animal task7OldestAnimal(Collection<Animal> animals) {
        return animals.stream().max(Comparator.comparingInt(Animal::age)).orElse(null);
    }

    public static Optional<Animal> task8HeaviestWithHeightLowerK(Collection<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height() < k).max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer task9TotalPaws(Collection<Animal> animals) {
        return animals.stream().mapToInt(Animal::paws).sum();
    }

    public static List<Animal> task10AgeNotEqualsPaws(Collection<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    public static List<Animal> task11CanBiteAndHigher100(Collection<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && animal.height() > HUNDRED).toList();
    }

    public static Integer task12CountHigherK(Collection<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height() > k).mapToInt(e -> 1).sum();
    }

    public static List<Animal> task13MoreThan1WordInName(Collection<Animal> animals) {
        return animals.stream().filter(animal -> animal.name().split(" ").length > 1).toList();
    }

    public static Boolean task14HaveDogHigherK(Collection<Animal> animals, int k) {
        return animals.stream().anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > k);
    }

    public static Integer task15TotalWeightForAgeFromKToL(Collection<Animal> animals, int k, int l) {
        return animals.stream().filter(animal -> animal.age() >= k && animal.age() <= l)
            .mapToInt(Animal::weight).sum();
    }

    public static List<Animal> task16SortedByTypeSexName(Collection<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type).thenComparing(Animal::sex).thenComparing(Animal::name)).toList();
    }

    public static Boolean task17SpidersBiteMoreOftenThanDogs(Collection<Animal> animals) {
        return animals.stream().filter(animal -> animal.type().equals(Animal.Type.SPIDER) && animal.bites()).count()
            > animals.stream().filter(animal -> animal.type().equals(Animal.Type.DOG) && animal.bites()).count();
    }

    public static Animal task18HeaviestFish(Collection<Animal>... collections) {
        return Stream.of(collections).flatMap(Collection::stream)
            .filter(animal -> animal.type().equals(Animal.Type.FISH)).max(Comparator.comparingInt(Animal::weight))
            .orElse(null);
    }

    public static Map<String, Set<ValidationError>> task19AnimalsWithErrors(Collection<Animal> animals) {
        return animals.stream().map(animal -> new AbstractMap.SimpleEntry<>(animal.name(), getErrorsForAnimal(animal)))
            .filter(simpleEntry -> !simpleEntry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<String, String> task20AnimalsWithErrorsString(Collection<Animal> animals) {
        return animals.stream().map(animal -> new AbstractMap.SimpleEntry<>(animal.name(), getErrorsForAnimal(animal)))
            .filter(simpleEntry -> !simpleEntry.getValue().isEmpty())
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                entry -> String.join(" ", entry.getValue().stream().map(ValidationError::toString).toList())
            ));
    }

    private static Set<ValidationError> getErrorsForAnimal(Animal animal) {
        Set<ValidationError> errors = new HashSet<>();
        if (animal.age() < 0) {
            errors.add(new ValidationError("Age could not be negative."));
        }
        if (animal.height() < 0) {
            errors.add(new ValidationError("Height could not be negative."));
        }
        if (animal.weight() < 0) {
            errors.add(new ValidationError("Weight could not be negative."));
        }
        return errors;
    }

    private AnimalUtils() {
    }
}
