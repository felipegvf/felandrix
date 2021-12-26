package io.github.felipegvf.felandrix.card;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface CardRepository extends CrudRepository<Card, UUID> {
}
