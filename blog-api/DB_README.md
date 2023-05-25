## Create Database, User and Password

Login to Postgres and run the following commands:

```postgresql
CREATE DATABASE blog_db;
CREATE USER blog_db_user WITH ENCRYPTED PASSWORD 'blog_db_passw0rd';
GRANT ALL PRIVILEGES ON DATABASE blog_db TO blog_db_user;
```