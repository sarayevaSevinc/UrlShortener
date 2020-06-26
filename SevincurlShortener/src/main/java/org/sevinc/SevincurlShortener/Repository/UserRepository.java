package org.sevinc.SevincurlShortener.Repository;

import org.sevinc.SevincurlShortener.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, Integer> {
     public Optional<Person> findAllByEmailAndPassword(String email, String password);

}
