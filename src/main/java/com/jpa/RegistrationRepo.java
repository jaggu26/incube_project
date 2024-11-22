package com.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepo extends JpaRepository<Registartion, Integer> {
	@Query(value = "SELECT r.cource_id FROM registartion r WHERE r.user_id = :userId", nativeQuery = true)
	public List<Long> findCourseIdsByUserId(@Param("userId") Long userId);

	@Query(value = "SELECT r.cource_id FROM registartion r WHERE r.user_id = :userId AND r.cource_id = :courceId", nativeQuery = true)
	public Long findCourceIdByUserIdAndCourceId(@Param("userId") Long userId, @Param("courceId") Long courceId);
}
