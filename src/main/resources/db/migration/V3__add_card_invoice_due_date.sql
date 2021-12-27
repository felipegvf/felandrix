drop table card_invoice;
create table card_invoice
(
    card_invoice_id       uuid primary key,
    card_invoice_due_date date not null,
    card_id               uuid not null references card on delete cascade
)