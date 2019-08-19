create database insurance_db;
grant all on insurance_db.* to 'pjaiswal'@'%';

CREATE TABLE insurance_events
(
   events_id int PRIMARY KEY AUTO_INCREMENT NOT NULL,
   insurance_id text NOT NULL,
   content text NOT NULL,
   created_at date NOT NULL
);

Swagger UI:	http://localhost:8100/swagger-ui.html
