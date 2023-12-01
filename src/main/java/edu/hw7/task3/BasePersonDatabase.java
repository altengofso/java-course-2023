package edu.hw7.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BasePersonDatabase implements PersonDatabase {
    private final Map<Integer, Person> idCache = new HashMap<>();
    private final Map<String, List<Person>> nameCache = new HashMap<>();
    private final Map<String, List<Person>> addressCache = new HashMap<>();
    private final Map<String, List<Person>> phoneCache = new HashMap<>();

    @Override
    public void add(Person person) {
        idCache.put(person.id(), person);
        addToCache(nameCache, person.name(), person);
        addToCache(addressCache, person.address(), person);
        addToCache(phoneCache, person.phoneNumber(), person);
    }

    @Override
    public void delete(int id) {
        Person person = idCache.get(id);
        if (person == null) {
            throw new IllegalArgumentException("Invalid person id.");
        }
        idCache.remove(id);
        removeFromCache(nameCache, person.name(), person);
        removeFromCache(addressCache, person.address(), person);
        removeFromCache(phoneCache, person.phoneNumber(), person);
    }

    @Override
    public List<Person> findByName(String name) {
        var persons = nameCache.getOrDefault(name, new ArrayList<>());
        return excludePersonWithNullValues(persons);
    }

    @Override
    public List<Person> findByAddress(String address) {
        var persons = addressCache.getOrDefault(address, new ArrayList<>());
        return excludePersonWithNullValues(persons);
    }

    @Override
    public List<Person> findByPhone(String phone) {
        var persons = phoneCache.getOrDefault(phone, new ArrayList<>());
        return excludePersonWithNullValues(persons);
    }

    private void addToCache(Map<String, List<Person>> cache, String key, Person person) {
        cache.computeIfAbsent(key, k -> new ArrayList<>()).add(person);
    }

    private void removeFromCache(Map<String, List<Person>> cache, String key, Person person) {
        cache.computeIfPresent(key, (k, persons) -> {
            persons.remove(person);
            if (persons.isEmpty()) {
                return null;
            }
            return persons;
        });
    }

    private List<Person> excludePersonWithNullValues(List<Person> persons) {
        return persons
            .stream()
            .filter(person -> person.name() != null && person.address() != null && person.phoneNumber() != null)
            .toList();
    }
}
