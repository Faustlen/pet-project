DO
$$
BEGIN
  IF NOT EXISTS (
    SELECT FROM pg_catalog.pg_roles WHERE rolname = 'admin'
  ) THEN
    CREATE ROLE admin WITH LOGIN ENCRYPTED PASSWORD '123';
  END IF;
END
$$;

SELECT 'CREATE DATABASE crm_db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'crm_db')\gexec;
SELECT 'CREATE DATABASE price_history_db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'price_history_db')\gexec;
SELECT 'CREATE DATABASE content_processor_db' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'content_processor_db')\gexec;

GRANT ALL PRIVILEGES ON DATABASE crm_db TO admin;
GRANT ALL PRIVILEGES ON DATABASE price_history_db TO admin;
GRANT ALL PRIVILEGES ON DATABASE content_processor_db TO admin;