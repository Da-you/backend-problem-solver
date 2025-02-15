package ding.co.backendportfolio.chapter4.repository;

import ding.co.backendportfolio.chapter4.entity.Event;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select e from Event e where e.id = :id")
    Optional<Event> findByIdWithPessimisticLock(@Param("id") Long id);
    
    @Lock(LockModeType.OPTIMISTIC)
    @Query("select e from Event e where e.id = :id")
    Optional<Event> findByIdWithOptimisticLock(@Param("id") Long id);
} 