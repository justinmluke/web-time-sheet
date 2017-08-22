package com.justinluke.webtimesheet.models.data;

import com.justinluke.webtimesheet.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by there on 8/22/2017.
 */

@Repository
@Transactional
public interface CompanyDao extends CrudRepository<Company, Integer> {

}
