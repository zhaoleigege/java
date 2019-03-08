package com.busekylin.transactional.repository.impl;

import com.busekylin.transactional.domain.People;
import com.busekylin.transactional.repository.PeopleRepository;
import com.busekylin.transactional.util.JdbcTemplate;

import java.sql.SQLException;

public class DefaultPeopleRepositoryImpl implements PeopleRepository {
    private JdbcTemplate template;

    public DefaultPeopleRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    public void addPeople(People people) throws SQLException {
        template.execute("insert into person values ('" + people.getName() + "'" + ", " + people.getAge() + ")");
    }
}
