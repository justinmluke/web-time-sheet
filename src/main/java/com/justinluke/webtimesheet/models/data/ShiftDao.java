package com.justinluke.webtimesheet.models.data;

import com.justinluke.webtimesheet.models.Company;
import com.justinluke.webtimesheet.models.Shift;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by there on 1/2/2018.
 */

@Repository
@Transactional
public interface ShiftDao extends CrudRepository<Shift, Integer>{

    List<Shift> findByCompany (Company company);
}
