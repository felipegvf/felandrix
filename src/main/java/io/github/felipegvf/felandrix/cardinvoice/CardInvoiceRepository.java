package io.github.felipegvf.felandrix.cardinvoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(path = "card_invoices")
public interface CardInvoiceRepository extends JpaRepository<CardInvoice, UUID> {
}