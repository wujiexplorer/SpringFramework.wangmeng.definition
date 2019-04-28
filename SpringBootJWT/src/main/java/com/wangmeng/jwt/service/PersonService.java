package com.wangmeng.jwt.service;
import com.wangmeng.jwt.model.Person;

/**
 * Interface for handling SQL operator in PersonCache
 * @program: users
 * @author: wangmeng
 * @create: 2018-02-27 19:18
 **/

public interface PersonService {
    /**
     *
     * @param person Person Object
     * @return The Person object saved in Ignite DB.
     */
    Person save(Person person);

    /**
     * Find a Person from Ignite DB with given name.
     * @param name Person name.
     * @return The person found in Ignite DB
     */
    Person findPersonByUsername(String name);
}
