package autoservice.service;

public interface GenericService<T> {
    T saveOrUpdate(T entity);

    T get(Long id);
}
