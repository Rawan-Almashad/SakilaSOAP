package org.iti.soap.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.iti.soap.config.JPAUtil;
import org.iti.soap.dao.FilmDao;
import org.iti.soap.dto.AddActorToFilmRequest;
import org.iti.soap.dto.CreateFilmRequest;
import org.iti.soap.dto.ReturnFilm;
import org.iti.soap.entity.Actor;
import org.iti.soap.entity.Film;
import org.iti.soap.entity.FilmActorId;
import org.iti.soap.entity.Language;
import org.iti.soap.exception.LanguageNotFound;
import org.iti.soap.factory.ServiceFactory;

import java.util.List;
import java.util.Optional;

public class FilmService {
  private FilmDao filmDao;
  public FilmService(FilmDao filmDao)
  {
      this.filmDao=filmDao;
  }

        public Film create(Film film)  {
            return filmDao.save(film);
        }
        public ReturnFilm findById(Short id) {
           Film film= filmDao.findById(id).orElseThrow(()-> new RuntimeException("Film not found "));
           ReturnFilm returnFilm = new ReturnFilm();
           returnFilm.setDescription(film.getDescription());
           returnFilm.setTitle(film.getTitle());
           return returnFilm;
        }
        public List<Film> findAll(){
           return filmDao.findAll();
        }
        public void delete(Short id) {
           Film film=filmDao.findById(id).orElseThrow(()->new RuntimeException("Film not found"));
           filmDao.delete(id);
        }
        public  Film findWithLanguage(Short id)
        {
          return filmDao.findWithLanguage(id);
        }
        public void addActorToFilm(AddActorToFilmRequest request)
         {
                 filmDao.addActorToFilm(request);
         }
    }

