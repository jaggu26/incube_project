package com.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MasterCourseRepository extends JpaRepository<MasterCourse, Integer>{

}
