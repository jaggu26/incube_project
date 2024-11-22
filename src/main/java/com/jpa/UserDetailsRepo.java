package com.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Integer> {
	List<UserDetails> findByMobileNum(String mobileNum);

	Optional<UserDetails> findByUserName(String userName);
}
