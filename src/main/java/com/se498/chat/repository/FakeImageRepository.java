package com.se498.chat.repository;

import com.se498.chat.model.Image;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class FakeImageRepository implements ImageRepository {

    private final Map<String, Image> db = new HashMap<>();


    public void delete(Image arg0) {
    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    public void deleteAll() {
    }

    public void deleteAll(Iterable<? extends Image> arg0) {
    }

    public void deleteById(String arg0) {
    }

    public boolean existsById(String arg0) {
        return false;
    }

    public Iterable<Image> findAll() {
        return this.db.values();
    }

    public Iterable<Image> findAllById(Iterable<String> arg0) {

        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    public Optional<Image> findById(String arg0) {
        return Optional.of(this.db.get(arg0));
    }

    public <S extends Image> S save(S arg0) {
        this.db.put(arg0.getUrl(), arg0);
        return arg0;
    }

    public <S extends Image> Iterable<S> saveAll(Iterable<S> arg0) {
        return null;
    }
}
