package lotto.repository;

import java.util.Collection;
import java.util.List;

public interface Repository<T>  {
    void add(T t);

    void addAll(Collection<T> c);

    void delete(T t);

    List<T> findAll();

    void clear();
}
