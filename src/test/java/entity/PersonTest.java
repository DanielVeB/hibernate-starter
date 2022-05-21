package entity;

import config.DatabaseTest;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.junit.jupiter.api.*;

import java.util.List;


class PersonTest extends DatabaseTest {


    @Override
    protected void loadData() {

    }

    @Override
    protected MetadataSources addAnnotatedClasses(MetadataSources sources) {
        return sources.addAnnotatedClass(Person.class);
    }

    @Test
    void testAddPerson() {
        Person emp = new Person();
        emp.setFirstName("John");
        emp.setLastName("Smith");

        Assertions.assertNull(emp.getId());

        session.persist(emp);

        Assertions.assertNotNull(emp.getId());
    }

    @Test
    void testQuery() {
        session.createQuery("DELETE FROM Person");
        session.persist(new Person());
        session.persist(new Person());

        @SuppressWarnings("unchecked")
        List<Person> people = session.createQuery("SELECT p FROM Person p").getResultList();

        Assertions.assertEquals(2, people.size());
    }

}