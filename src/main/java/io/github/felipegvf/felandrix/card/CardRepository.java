package io.github.felipegvf.felandrix.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "cards")
public interface CardRepository extends JpaRepository<Card, UUID> {
}
