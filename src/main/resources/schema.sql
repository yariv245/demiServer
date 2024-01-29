CREATE TABLE IF NOT EXISTS "CUSTOMER" (
    "customer_id" bigint AUTO_INCREMENT  PRIMARY KEY,
    "name" varchar(100) NOT NULL,
    "email" varchar(100) NOT NULL,
    "mobile_number" varchar(20) NOT NULL,
    "created_at" DATETIME NOT NULL,
    "created_by" varchar(20) NOT NULL,
    "updated_at" DATETIME DEFAULT NULL,
    "updated_by" varchar(20) DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS "ACCOUNT" (
    "customer_id" bigint NOT NULL,
    "account_number" bigint AUTO_INCREMENT  PRIMARY KEY,
    "account_type" varchar(100) NOT NULL,
    "branch_address" varchar(200) NOT NULL,
    "created_at" DATETIME NOT NULL,
    "created_by" varchar(20) NOT NULL,
    "updated_at" DATETIME DEFAULT NULL,
    "updated_by" varchar(20) DEFAULT NULL
    );