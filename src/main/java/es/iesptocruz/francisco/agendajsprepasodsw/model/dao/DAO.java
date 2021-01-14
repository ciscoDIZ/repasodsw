package es.iesptocruz.francisco.agendajsprepasodsw.model.dao;

import java.util.List;

public interface DAO<T> {
    T add();
    T edit();
    T findById();
    List<T> findAll();
    void delete();
}
