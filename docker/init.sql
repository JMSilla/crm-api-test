CREATE TABLE public."user" (
	id int4 NOT NULL,
	"admin" bool NOT NULL,
	name varchar(30) NOT NULL,
	CONSTRAINT user_pkey PRIMARY KEY (id)
);

CREATE TABLE public.customer (
	id int4 NOT NULL,
	"name" varchar(30) NOT NULL,
	photo oid NULL,
	photomimetype varchar(100) NULL,
	surname varchar(30) NOT NULL,
	createdbyuser_id int4 NOT NULL,
	modifiedbyuser_id int4 NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (id),
	CONSTRAINT createdbyuser_fk FOREIGN KEY (createdbyuser_id) REFERENCES public."user"(id),
	CONSTRAINT modifiedbyuser_fk FOREIGN KEY (modifiedbyuser_id) REFERENCES public."user"(id)
);
