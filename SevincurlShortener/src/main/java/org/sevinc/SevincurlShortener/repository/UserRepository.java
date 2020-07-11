package org.sevinc.SevincurlShortener.repository;

import org.sevinc.SevincurlShortener.entity.db.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, Integer> {
      Optional<Person> findAllByEmailAndPassword(String email, String password);
      Optional<Person> findByEmail(String s);
}
