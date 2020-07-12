package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;
import java.util.Optional;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    @Modifying
//    @Query(name = Meal.DELETE)
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Query("select m from Meal m where m.user.id in :ids ORDER BY m.id DESC")
    List<Meal> findByUserIds(@Param("ids") Iterable<Integer> ids);

    @Query("select m from Meal m where m.id=:id AND m.user.id=:userId")
    Optional<Meal> findById(@Param("id") Integer id, @Param("userId") Integer userId);

//    @Transactional
//    @Modifying
//    @Query(name = Meal.UPDATE)
//    Meal save(@Param("id") Meal meal, @Param("userId") int userId);
}
