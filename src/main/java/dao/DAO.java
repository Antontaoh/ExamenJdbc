package dao;

import java.util.List;

public interface DAO<T> {
    public List<T> findAll();
    public T findById(Integer id);
    public void save(T t);
    public void update(T t);
    public void delete(T t);
    public List<T> findByYearRange(Integer yearA, Integer yearB);
    public Integer totalNumber();
}
