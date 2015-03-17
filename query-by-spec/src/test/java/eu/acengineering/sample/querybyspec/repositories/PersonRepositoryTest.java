package eu.acengineering.sample.querybyspec.repositories;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.acengineering.sample.querybyspec.DataSets;
import eu.acengineering.sample.querybyspec.ServiceTestConfig;
import eu.acengineering.sample.querybyspec.ServiceTestExecutionListener;
import eu.acengineering.sample.querybyspec.model.Person;
import eu.acengineering.sample.querybyspec.model.PersonSpecification;

@ContextConfiguration(classes = {ServiceTestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ServiceTestExecutionListener.class})
@ActiveProfiles("test")
public class PersonRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PersonRepository repository;

    @DataSets(setUpDataSet = "./src/test/resources/personRepositoryTestDataSet.xml")
    @Test
    public void shouldFindByExample() {

        Person filter = new Person();
        filter.setName("Mario");
        filter.setSurname("Verdi");
        filter.setAge(25);

        Specification<Person> spec = new PersonSpecification(filter);

        List<Person> result = repository.findAll(spec);

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());

    }

}
