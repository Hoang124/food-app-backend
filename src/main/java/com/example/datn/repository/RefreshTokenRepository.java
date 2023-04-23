package com.example.datn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.datn.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
	@Query(value = """
		      select t from RefreshToken t inner join Account a\s
		      on t.account.email = a.email\s
		      where a.email = :email\s
		      """)
	List<RefreshToken> findAllValidTokenByUser(String email);

	Optional<RefreshToken> findByToken(String token);
}
