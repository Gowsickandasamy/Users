package com.thbs.usercreation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.thbs.usercreation.entity.Token;

//Interface extending JpaRepository to handle Token entity operations
public interface TokenRepository extends JpaRepository<Token, Long> {

// Custom query to find all valid tokens for a given user ID
@Query(value = """
   select t from Token t inner join User u\s
   on t.user.id = u.id\s
   where u.id = :id and (t.expired = false or t.revoked = false)\s
   """)
List<Token> findAllValidTokenByUser(Long id);

// Method to find a token by its value
Optional<Token> findByToken(String token);
}
