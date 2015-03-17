package eu.acengineering.sample.querybyspec;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = { ServiceTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({ ServiceTestExecutionListener.class })
@ActiveProfiles("test")
public class AbstractServiceTests extends AbstractTransactionalJUnit4SpringContextTests {

}
