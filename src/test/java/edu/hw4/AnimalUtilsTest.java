package edu.hw4;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AnimalUtilsTest {
    private final List<Animal> animals;

    public AnimalUtilsTest() {
        this.animals = new ArrayList<>();
        animals.add(new Animal("Cat", Animal.Type.CAT, Animal.Sex.M, -1, -1, 3, true));
        animals.add(new Animal("Dog", Animal.Type.DOG, Animal.Sex.M, 2, -1, 6, true));
        animals.add(new Animal("Fish", Animal.Type.FISH, Animal.Sex.M, 3, 5, 9, true));
        animals.add(new Animal("Bird", Animal.Type.BIRD, Animal.Sex.M, 4, 7, 12, true));
        animals.add(new Animal("Cat2", Animal.Type.CAT, Animal.Sex.M, 5, 4, 2, true));
        animals.add(new Animal("Dog2", Animal.Type.DOG, Animal.Sex.F, 6, 2, -1, true));
        animals.add(new Animal("Fish2", Animal.Type.FISH, Animal.Sex.F, 7, 6, 2, true));
        animals.add(new Animal("Bird Long Name", Animal.Type.BIRD, Animal.Sex.F, 8, 8, 2, true));
    }

    @Test
    @DisplayName("Test Sort By Height")
    void testTask1SortByHeight() {
        var sorted = AnimalUtils.task1SortByHeight(animals);
        assertThat(sorted.getFirst().name()).isEqualTo("Cat");
    }

    @Test
    @DisplayName("Test K Heaviest")
    void testTask2KHeaviest() {
        var heaviest = AnimalUtils.task2KHeaviest(animals, 2);
        assertThat(heaviest.getFirst().name()).isEqualTo("Bird");
        assertThat(heaviest.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Test Count Animal Of Each Type")
    void testTask3CountAnimalOfEachType() {
        var map = AnimalUtils.task3CountAnimalOfEachType(animals);
        assertThat(map.values().stream().toList()).isEqualTo(List.of(2, 2, 2, 2));
    }

    @Test
    @DisplayName("Test Animal With Longest Name")
    void testTask4AnimalWithLongestName() {
        assertThat(AnimalUtils.task4AnimalWithLongestName(animals).name()).isEqualTo("Bird Long Name");
    }

    @Test
    @DisplayName("Test Which Sex More")
    void testTask5WhichSexMore() {
        assertThat(AnimalUtils.task5WhichSexMore(animals)).isEqualTo(Animal.Sex.M);
    }

    @Test
    @DisplayName("Test Heaviest Animal Of EachT ype")
    void testTask6HeaviestAnimalOfEachType() {
        var heaviest = AnimalUtils.task6HeaviestAnimalOfEachType(animals);
        assertThat(heaviest.get(Animal.Type.BIRD).name()).isEqualTo("Bird");
        assertThat(heaviest.get(Animal.Type.FISH).name()).isEqualTo("Fish");
        assertThat(heaviest.get(Animal.Type.CAT).name()).isEqualTo("Cat");
        assertThat(heaviest.get(Animal.Type.DOG).name()).isEqualTo("Dog");
    }

    @Test
    @DisplayName("Test Oldest Animal")
    void testTask7OldestAnimal() {
        assertThat(AnimalUtils.task7OldestAnimal(animals).name()).isEqualTo("Bird Long Name");
    }

    @Test
    @DisplayName("Test Heaviest With Height Lower K")
    void testTask8HeaviestWithHeightLowerK() {
        assertThat(AnimalUtils.task8HeaviestWithHeightLowerK(animals, 10).isPresent()).isTrue();
        assertThat(AnimalUtils.task8HeaviestWithHeightLowerK(animals, 10).get().name()).isEqualTo("Bird");
        assertThat(AnimalUtils.task8HeaviestWithHeightLowerK(animals, -1).isPresent()).isFalse();
    }

    @Test
    @DisplayName("Test Total Paws")
    void testTask9TotalPaws() {
        assertThat(AnimalUtils.task9TotalPaws(animals)).isEqualTo(20);
    }

    @Test
    @DisplayName("Test Age Not Equals Paws")
    void testTask10AgeNotEqualsPaws() {
        assertThat(AnimalUtils.task10AgeNotEqualsPaws(animals).size()).isEqualTo(8);
    }

    @Test
    @DisplayName("Test Can Bite And Higher 100")
    void testTask11CanBiteAndHigher100() {
        assertThat(AnimalUtils.task11CanBiteAndHigher100(animals).size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Test Count Higher K")
    void testTask12CountHigherK() {
        assertThat(AnimalUtils.task12CountHigherK(animals, 3)).isEqualTo(5);
    }

    @Test
    @DisplayName("Test More Than 1 Word In Name")
    void testTask13MoreThan1WordInName() {
        assertThat(AnimalUtils.task13MoreThan1WordInName(animals).size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Test Have Dog Higher K")
    void testTask14HaveDogHigherK() {
        assertThat(AnimalUtils.task14HaveDogHigherK(animals, 1)).isTrue();
        assertThat(AnimalUtils.task14HaveDogHigherK(animals, 100)).isFalse();
    }

    @Test
    @DisplayName("Test Total Weight For Age From K To L")
    void testTask15TotalWeightForAgeFromKToL() {
        assertThat(AnimalUtils.task15TotalWeightForAgeFromKToL(animals, 1, 4)).isEqualTo(27);
    }

    @Test
    @DisplayName("Test Sorted By Type Sex Name")
    void testTask16SortedByTypeSexName() {
        var sorted = AnimalUtils.task16SortedByTypeSexName(animals);
        assertThat(sorted.stream().map(Animal::name).toList()).isEqualTo(List.of(
            "Cat",
            "Cat2",
            "Dog",
            "Dog2",
            "Bird",
            "Bird Long Name",
            "Fish",
            "Fish2"
        ));
    }

    @Test
    @DisplayName("Test Spiders Bite More Often Than Dogs")
    void testTask17SpidersBiteMoreOftenThanDogs() {
        assertThat(AnimalUtils.task17SpidersBiteMoreOftenThanDogs(animals)).isFalse();
    }

    @Test
    @DisplayName("Test Heaviest Fish")
    void testTask18HeaviestFish() {
        assertThat(AnimalUtils.task18HeaviestFish(animals, animals, animals).name()).isEqualTo("Fish");
    }

    @Test
    @DisplayName("Test Animals With Errors")
    void testTask19AnimalsWithErrors() {
        var map = AnimalUtils.task19AnimalsWithErrors(animals);
        assertThat(map.size()).isEqualTo(3);
        assertThat(map.keySet().stream().toList()).isEqualTo(List.of("Dog2", "Cat", "Dog"));
        assertThat(map.values().stream().toList()).isEqualTo(List.of(
            Set.of(new ValidationError("Weight could not be negative.")),
            Set.of(
                new ValidationError("Age could not be negative."),
                new ValidationError("Height could not be negative.")
            ),
            Set.of(new ValidationError("Height could not be negative.")
            )
        ));
    }

    @Test
    @DisplayName("Test Animals With Errors String")
    void testTask20AnimalsWithErrorsString() {
        var map = AnimalUtils.task20AnimalsWithErrorsString(animals);
        assertThat(map.size()).isEqualTo(3);
        assertThat(map.keySet().stream().toList()).isEqualTo(List.of("Dog2", "Cat", "Dog"));
        assertThat(map.values().stream().toList()).isEqualTo(List.of(
            "Weight could not be negative.",
            "Age could not be negative. Height could not be negative.",
            "Height could not be negative."
        ));
    }

}
