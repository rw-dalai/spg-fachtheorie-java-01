package at.spengergasse.fachtheorie.aufgabe1.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * GRADING TESTS THAT VALIDATE THE DATABASE SCHEMA AND CONSTRAINTS.
 * DO NOT MODIFY THIS FILE.
 */
@DataJpaTest
public class GradingTests {

    @Autowired
    JdbcTemplate jdbc;

    @BeforeEach
    void disableFk() {
        jdbc.execute("SET REFERENTIAL_INTEGRITY FALSE");
    }

    @Test
    void T00_CanCreateDatabaseTest() {
        Integer count = jdbc.queryForObject(
            "SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'PUBLIC'",
            Integer.class);

        assertThat(count)
            .as("Less than 5 tables found. Check your entities & mappings.")
            .isNotNull()
            .isGreaterThanOrEqualTo(6);
    }

    @Test
    void T00_SchemaTest() {
        insertOk(
            "INSERT INTO article (number, name, price) VALUES (1, 'Laptop', 999.9900)",
            "INSERT INTO company (id, name, street, zip, city) VALUES (1, 'SpengTech', 'Spengergasse 20', '1050', 'Wien')",
            "INSERT INTO users (id, dtype, first_name, last_name, email, company_id) VALUES (1, 'Employee', 'Max', 'Muster', 'max@spg.at', 1)",
            "INSERT INTO users (id, dtype, first_name, last_name, email, type, note) VALUES (2, 'Customer', 'Anna', 'Kunde', 'anna@example.com', 'B2C', null)",
            "INSERT INTO customer_addresses (customer_id, street, zip, city) VALUES (2, 'Teststr. 1', '1010', 'Wien')",
            "INSERT INTO invoice (id, number, date, customer_id, employee_id) VALUES (1, 1001, '2025-01-15T10:30:00', 2, 1)",
            "INSERT INTO invoice_item (id, invoice_id, article_number, quantity, price) VALUES (1, 1, 1, 2, 999.9900)"
        );
    }

    @Test
    void T01_InsertArticleTest() {
        insertOk(
            "INSERT INTO article (number, name, price) VALUES (1, 'Laptop', 999.9900)"
        );
    }

    @Test
    void T02_InsertCompanyTest() {
        insertOk(
            "INSERT INTO company (id, name, street, zip, city) VALUES (1, 'SpengTech', 'Spengergasse 20', '1050', 'Wien')"
        );
    }

    @Test
    void T03_InsertEmployeeTest() {
        insertOk(
            "INSERT INTO users (id, dtype, first_name, last_name, email, company_id) VALUES (1, 'Employee', 'Max', 'Muster', 'max@spg.at', 1)"
        );
    }

    @Test
    void T04_InsertCustomerTest() {
        insertOk(
            "INSERT INTO users (id, dtype, first_name, last_name, email, type, note) VALUES (1, 'Customer', 'Anna', 'Kunde', 'anna@example.com', 'B2C', null)"
        );
    }

    @Test
    void T05_InsertCustomerWithAddressesTest() {
        insertOk(
            "INSERT INTO users (id, dtype, first_name, last_name, email, type, note) VALUES (1, 'Customer', 'Anna', 'Kunde', 'anna@example.com', 'B2C', null)",
            "INSERT INTO customer_addresses (customer_id, street, zip, city) VALUES (1, 'Teststr. 1', '1010', 'Wien')",
            "INSERT INTO customer_addresses (customer_id, street, zip, city) VALUES (1, 'Hauptstr. 5', '1020', 'Wien')"
        );
    }

    @Test
    void T06_InsertInvoiceTest() {
        insertOk(
            "INSERT INTO invoice (id, number, date, customer_id, employee_id) VALUES (1, 1001, '2025-01-15T10:30:00', 1, 1)"
        );
    }

    @Test
    void T07_InsertInvoiceItemTest() {
        insertOk(
            "INSERT INTO invoice_item (id, invoice_id, article_number, quantity, price) VALUES (1, 1, 1, 2, 999.9900)"
        );
    }

    private void insertOk(String... sqls) {
        for (String sql : sqls) {
            jdbc.update(sql);
        }
    }

    private void insertShouldFail(String... sqls) {
        boolean failed = false;
        for (String sql : sqls) {
            try {
                jdbc.update(sql);
            } catch (DataIntegrityViolationException ex) {
                failed = true;
            }
        }
        if (!failed) {
            fail("Query should fail, but it didn't.\n" + String.join("\n", sqls));
        }
    }
}
