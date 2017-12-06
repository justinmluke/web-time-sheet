package com.justinluke.webtimesheet.models.data;

import com.justinluke.webtimesheet.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by there on 8/22/2017.
 */
public interface UserDao extends CrudRepository<User, Integer> {

    User findByEmail(String email);



}
