#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	INSERT INTO $POSTGRES_DB.public."user" (id, admin, "name") 
	values (1, true, '$ADMIN_USER');
EOSQL
