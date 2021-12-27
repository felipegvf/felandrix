package io.github.felipegvf.felandrix.card;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "card")
public class Card implements Serializable {

    @Serial
    private static final long serialVersionUID = 3485468811786404342L;

    @Id
    @GeneratedValue
    @Column(name = "card_id")
    private UUID cardId;

    @Column(name = "card_name", nullable = false)
    private String cardName;

    @Column(name = "card_status", nullable = false)
    private boolean cardStatus;

    @Column(name = "card_due_day", nullable = false)
    private int cardDueDay;

    public UUID getCardId() {
        return cardId;
    }

    public void setCardId(UUID cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public boolean isCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(boolean cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getCardDueDay() {
        return cardDueDay;
    }

    public void setCardDueDay(int cardDueDay) {
        this.cardDueDay = cardDueDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId.equals(card.cardId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId);
    }
}
