create table card_invoice(
    card_invoice_id uuid primary key,
    card_invoice_year int not null constraint minimum_card_invoice_year check ( card_invoice_year > 2020 ),
    card_invoice_month int not null constraint valid_card_invoice_month check ( card_invoice_month > 1 and card_invoice_month <= 12),
    card_id uuid not null references card on delete cascade
)