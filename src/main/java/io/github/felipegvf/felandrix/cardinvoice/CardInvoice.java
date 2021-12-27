package io.github.felipegvf.felandrix.cardinvoice;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "card_invoice")
public class CardInvoice implements Serializable {

    @Serial
    private static final long serialVersionUID = -8967299636668429850L;

    @Id
    @GeneratedValue
    @Column(name = "card_invoice_id")
    private UUID cardInvoiceId;

    @Column(name = "card_invoice_due_date")
    private LocalDate cardInvoiceDueDate;

    @Column(name = "card_id")
    private UUID cardId;

    public UUID getCardInvoiceId() {
        return cardInvoiceId;
    }

    public void setCardInvoiceId(UUID cardInvoiceId) {
        this.cardInvoiceId = cardInvoiceId;
    }

    public LocalDate getCardInvoiceDueDate() {
        return cardInvoiceDueDate;
    }

    public void setCardInvoiceDueDate(LocalDate cardInvoiceDueDate) {
        this.cardInvoiceDueDate = cardInvoiceDueDate;
    }

    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardInvoice that = (CardInvoice) o;
        return cardInvoiceId.equals(that.cardInvoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardInvoiceId);
    }
}
