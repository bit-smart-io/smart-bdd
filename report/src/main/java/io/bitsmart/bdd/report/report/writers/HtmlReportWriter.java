package io.bitsmart.bdd.report.report.writers;

import io.bitsmart.bdd.report.report.model.DataReportIndex;
import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.List;

public class HtmlReportWriter extends AbstractReportWriter {

    public HtmlReportWriter(FileNameProvider dataFileNameProvider) {
        super(dataFileNameProvider);
    }

    public void write(DataReportIndex dataReportIndex) {
        List<TestSuiteNameToFile> testSuites = dataReportIndex.getLinks().getTestSuites();
        String links = "<p> Place holder data: </p><pre>" + testSuites + "</pre>";

        String contents = "<html></body><h1>Index</h1>" + links + "</body><html>";
        write(fileNameProvider.indexFile(), contents);
    }

    public void write(TestSuite testSuite) {
        write(fileNameProvider.file(testSuite), testSuiteToHTML(testSuite));
    }

    private String testSuiteToHTML(TestSuite testSuite) {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML);
        templateEngine.setTemplateResolver(resolver);
        Context ct = new Context();
        ct.setVariable("testSuite", testSuite);
        ct.setVariable("testCases", testSuite.getTestCases());
        return templateEngine.process("test-suite.html", ct);
    }
}
