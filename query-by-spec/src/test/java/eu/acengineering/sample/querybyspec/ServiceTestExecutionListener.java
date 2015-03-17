package eu.acengineering.sample.querybyspec;

import java.io.File;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;

public class ServiceTestExecutionListener implements TestExecutionListener {

    private IDatabaseTester databaseTester;

    public void afterTestClass(TestContext arg0) throws Exception {
        // TODO Auto-generated method stub
    }

    public void afterTestMethod(TestContext arg0) throws Exception {
        // Clear up testing data if exists
        if (databaseTester != null) {
            databaseTester.onTearDown();
        }
    }

    public void beforeTestClass(TestContext arg0) throws Exception {
        // TODO Auto-generated method stub
    }

    public void beforeTestMethod(TestContext testCtx) throws Exception {
        // Check for existence of DataSets annotation for the method under
        // testing
        DataSets dataSetAnnotation = testCtx.getTestMethod().getAnnotation(
                DataSets.class);
        if (dataSetAnnotation == null) {
            return;
        }
        String dataSetName = dataSetAnnotation.setUpDataSet();
        // Populate data set if data set name exists
        if (!dataSetName.equals("")) {
            databaseTester = (IDatabaseTester) testCtx.getApplicationContext().getBean("databaseTester");
            FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
            builder.setColumnSensing(true);
            builder.setDtdMetadata(false);
            IDataSet dataSet = builder.build(new File(dataSetName));
            databaseTester.setDataSet(dataSet);
            databaseTester.onSetup();
        }
    }

    public void prepareTestInstance(TestContext arg0) throws Exception {
        // TODO Auto-generated method stub
    }
}