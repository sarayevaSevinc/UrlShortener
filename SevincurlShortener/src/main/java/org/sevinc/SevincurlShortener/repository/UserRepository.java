package org.sevinc.SevincurlShortener.repository;

import org.sevinc.SevincurlShortener.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, Integer> {
     public Optional<Person> findAllByEmailAndPassword(String email, String password);

   public Optional<Person> findByFullName(String s);
   public  Optional<Person> findByEmail(String s);
}
