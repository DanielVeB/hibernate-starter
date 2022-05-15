package config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DatabaseTest {

    protected static SessionFactory sessionFactory = null;
    protected Session session = null;

    @BeforeAll
    void setup() {
        try {
            StandardServiceRegistry standardRegistry
                    = new StandardServiceRegistryBuilder()
                    .configure("hibernate-test.cfg.xml")
                    .build();

            sessionFactory = getMetadata(standardRegistry)
                    .getSessionFactoryBuilder().build();

        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }

        initData();
    }


    private void initData() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        loadData();
        session.getTransaction().commit();
    }

    protected abstract void loadData();

    protected abstract Metadata getMetadata(StandardServiceRegistry standardRegistry);

    @BeforeEach
    void setupThis() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }

    @AfterEach
    void tearThis() {
        session.getTransaction().commit();
    }

    @AfterAll
    static void tear() {
        sessionFactory.close();
    }

}
