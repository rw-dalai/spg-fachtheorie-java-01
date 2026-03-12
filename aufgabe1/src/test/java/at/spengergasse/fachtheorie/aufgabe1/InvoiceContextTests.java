package at.spengergasse.fachtheorie.aufgabe1;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
public class InvoiceContextTests {

    @Autowired
    EntityManager entityManager;

    // TODO: Autowire the repositories you created

    @Test
    void persistEnumSuccessTest() {
        throw new UnsupportedOperationException("TODO: Implement this test");
    }

    @Test
    void persistValueObjectInCompanySuccessTest() {
        throw new UnsupportedOperationException("TODO: Implement this test");
    }

    @Test
    void persistValueObjectsInCustomerSuccessTest() {
        throw new UnsupportedOperationException("TODO: Implement this test");
    }

    @Test
    void persistInvoiceItemSuccessTest() {
        throw new UnsupportedOperationException("TODO: Implement this test");
    }
}
