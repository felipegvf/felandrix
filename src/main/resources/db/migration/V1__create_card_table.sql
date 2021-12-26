create table card(
  card_id uuid primary key,
  card_name varchar(255) not null,
  card_due_day int not null,
  card_status boolean not null
);