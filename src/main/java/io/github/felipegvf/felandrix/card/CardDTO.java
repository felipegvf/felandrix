package io.github.felipegvf.felandrix.card;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.Link;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CardDTO {

    @JsonProperty(value = "card_name")
    private String cardName;

    @JsonProperty(value = "card_status")
    private boolean cardStatus;

    @JsonProperty(value = "card_due_day")
    private int cardDueDay;

    @JsonProperty(value = "_links")
    private CardLink link;

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

    public CardLink getLink() {
        return link;
    }

    public void setLink(CardLink link) {
        this.link = link;
    }

    public static class CardLink {
        @JsonProperty("self")
        private Link self;
        @JsonProperty("card")
        private Link card;

        public Link getSelf() {
            return self;
        }

        public void setSelf(Link self) {
            this.self = self;
        }

        public Link getCard() {
            return card;
        }

        public void setCard(Link card) {
            this.card = card;
        }
    }

}
