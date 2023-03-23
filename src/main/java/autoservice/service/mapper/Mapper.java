package autoservice.service.mapper;

public interface Mapper<M, N, T> {
    N toDto(T object);

    T toModel(M dto);
}
