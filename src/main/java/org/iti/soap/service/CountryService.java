package org.iti.soap.service;

import jakarta.persistence.EntityManager;
import org.iti.soap.config.JPAUtil;

import org.iti.soap.dao.CountryDao;
import org.iti.soap.dto.CreateCountry;
import org.iti.soap.entity.Country;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class CountryService {
    private CountryDao countryDao;
    public CountryService(CountryDao countryDao)
    {
        this.countryDao=countryDao;
    }
    public Country findById(Short id) throws RuntimeException{
        return countryDao.findById(id).orElseThrow(() -> new RuntimeException("Country not found"));
    }

    public List<Country> findAll() {
      return countryDao.findAll();
    }

    public Country save(CreateCountry request) {
        Country country = new Country();
        country.setLastUpdate(Instant.now());
        country.setCountry(request.getCountry());
        return countryDao.save(country);
    }

    public Country update(Country request) {

       return countryDao.update(request);
    }

    public boolean deleteById(Short id) {
       return countryDao.deleteById(id);
    }
}
