package edu.hw7.task3;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.SneakyThrows;

public class ReadWriteLockPersonDatabase extends BasePersonDatabase {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    @Override
    @SneakyThrows
    public void add(Person person) {
        boolean isLockAcquired = lock.writeLock().tryLock(1, TimeUnit.SECONDS);
        if (isLockAcquired) {
            try {
                super.add(person);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    @Override
    @SneakyThrows
    public void delete(int id) {
        boolean isLockAcquired = lock.writeLock().tryLock(1, TimeUnit.SECONDS);
        if (isLockAcquired) {
            try {
                super.delete(id);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    @Override
    @SneakyThrows
    public List<Person> findByName(String name) {
        boolean isLockAcquired = lock.readLock().tryLock(1, TimeUnit.SECONDS);
        if (isLockAcquired) {
            try {
                return super.findByName(name);
            } finally {
                lock.readLock().unlock();
            }
        }
        return null;
    }

    @Override
    @SneakyThrows
    public List<Person> findByAddress(String address) {
        boolean isLockAcquired = lock.readLock().tryLock(1, TimeUnit.SECONDS);
        if (isLockAcquired) {
            try {
                return super.findByAddress(address);
            } finally {
                lock.readLock().unlock();
            }
        }
        return null;
    }

    @Override
    @SneakyThrows
    public List<Person> findByPhone(String phone) {
        boolean isLockAcquired = lock.readLock().tryLock(1, TimeUnit.SECONDS);
        if (isLockAcquired) {
            try {
                return super.findByPhone(phone);
            } finally {
                lock.readLock().unlock();
            }
        }
        return null;
    }
}
