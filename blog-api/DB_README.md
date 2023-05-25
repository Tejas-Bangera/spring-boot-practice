## Create Database, User and Password

## Postgresql
Login to Postgres and run the following commands:

```postgresql
CREATE DATABASE blog_db;
CREATE USER blog_db_user WITH ENCRYPTED PASSWORD 'blog_db_passw0rd';
GRANT ALL PRIVILEGES ON DATABASE blog_db TO blog_db_user;
\c blog_db postgres
GRANT ALL ON SCHEMA public TO blog_db_user;
```

## MySQL
Login to MySQL and run the following commands:

```bash
mysql -u root # if root has no password
mysql -u root -p # if root has a password
```

```mysql
CREATE DATABASE blog_db;
CREATE USER 'blog_db_user'@'localhost' IDENTIFIED BY 'blog_db_passw0rd';
GRANT ALL PRIVILEGES ON blog_db.* TO 'blog_db_user'@'localhost';
```

Login to new db with created user

```bash
mysql blog_db -u blog_db_user -p
```