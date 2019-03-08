package com.busekylin.transactional.repository;

import com.busekylin.transactional.domain.People;

import java.sql.SQLException;

public interface PeopleRepository {
    void addPeople(People people) throws SQLException;
}
