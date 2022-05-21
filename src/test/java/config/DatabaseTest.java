package config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
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

            sessionFactory = addAnnotatedClasses(new MetadataSources(standardRegistry))
                    .getMetadataBuilder()
                    .build()
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

    protected abstract MetadataSources addAnnotatedClasses(MetadataSources sources);

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
