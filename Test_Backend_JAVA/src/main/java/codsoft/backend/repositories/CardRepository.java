package codsoft.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import codsoft.backend.models.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
