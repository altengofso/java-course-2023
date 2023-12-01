package edu.hw7.task3;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.Assertions.assertThat;

public class PersonDatabaseTest {
    static Stream<Arguments> PersonDatabaseArguments() {
        return Stream.of(
            Arguments.of(new SynchronizedPersonDatabase(), new Person(1, "Vasya", "Moscow", "555-44-33")),
            Arguments.of(new ReadWriteLockPersonDatabase(), new Person(1, "Vasya", "Moscow", "555-44-33"))
        );
    }

    @ParameterizedTest
    @MethodSource("PersonDatabaseArguments")
    void testPersonCouldBeFoundByAnyField(BasePersonDatabase database, Person person) {
        List<Thread> threads = List.of(
            new Thread(() -> database.add(person)),
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                assertThat(database.findByName(person.name())).containsExactly(person);
            }),
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                assertThat(database.findByAddress(person.address())).containsExactly(person);
            }),
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                assertThat(database.findByPhone(person.phoneNumber())).containsExactly(person);
            })
        );
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("PersonDatabaseArguments")
    void testSearchingThreadLocksPerson(BasePersonDatabase database, Person person) {
        database.add(person);
        List<Thread> threads = List.of(
            new Thread(() -> assertThat(database.findByName(person.name())).containsExactly(person)),
            new Thread(() -> assertThat(database.findByAddress(person.address())).containsExactly(person)),
            new Thread(() -> assertThat(database.findByPhone(person.phoneNumber())).containsExactly(person)),
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                database.delete(person.id());
            })
        );
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @ParameterizedTest
    @MethodSource("PersonDatabaseArguments")
    void testDeletingThreadLocksPerson(BasePersonDatabase database, Person person) {
        database.add(person);
        List<Thread> threads = List.of(
            new Thread(() -> database.delete(person.id())),
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                assertThat(database.findByName(person.name())).isEmpty();
            }),
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                assertThat(database.findByAddress(person.address())).isEmpty();
            }),
            new Thread(() -> {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                assertThat(database.findByPhone(person.phoneNumber())).isEmpty();
            })
        );
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
