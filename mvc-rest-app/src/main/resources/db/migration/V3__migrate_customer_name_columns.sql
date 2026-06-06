DROP PROCEDURE IF EXISTS migrate_customer_name_columns;

DELIMITER //

CREATE PROCEDURE migrate_customer_name_columns()
BEGIN
    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = DATABASE()
          AND table_name = 'customer'
          AND column_name = 'firstname'
    ) THEN
        IF EXISTS (
            SELECT 1
            FROM information_schema.columns
            WHERE table_schema = DATABASE()
              AND table_name = 'customer'
              AND column_name = 'first_name'
        ) THEN
            UPDATE customer
            SET first_name = firstname
            WHERE first_name IS NULL
              AND firstname IS NOT NULL;

            ALTER TABLE customer DROP COLUMN firstname;
        ELSE
            ALTER TABLE customer RENAME COLUMN firstname TO first_name;
        END IF;
    END IF;

    IF EXISTS (
        SELECT 1
        FROM information_schema.columns
        WHERE table_schema = DATABASE()
          AND table_name = 'customer'
          AND column_name = 'lastname'
    ) THEN
        IF EXISTS (
            SELECT 1
            FROM information_schema.columns
            WHERE table_schema = DATABASE()
              AND table_name = 'customer'
              AND column_name = 'last_name'
        ) THEN
            UPDATE customer
            SET last_name = lastname
            WHERE last_name IS NULL
              AND lastname IS NOT NULL;

            ALTER TABLE customer DROP COLUMN lastname;
        ELSE
            ALTER TABLE customer RENAME COLUMN lastname TO last_name;
        END IF;
    END IF;
END//

DELIMITER ;

CALL migrate_customer_name_columns();

DROP PROCEDURE migrate_customer_name_columns;
