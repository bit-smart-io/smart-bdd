package io.bitsmart.bdd.report.report.writers;

import io.bitsmart.bdd.report.report.model.DataReportIndex;
import io.bitsmart.bdd.report.report.model.TestSuite;
import io.bitsmart.bdd.report.report.model.TestSuiteNameToFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.List;
import java.util.stream.Collectors;

public class HtmlReportWriter extends AbstractReportWriter {

    public HtmlReportWriter(FileNameProvider dataFileNameProvider) {
        super(dataFileNameProvider);
    }

    public void write(DataReportIndex dataReportIndex) {
        String html = templateEngine().process("index.html",  contextForReportIndex(dataReportIndex));
        write(fileNameProvider.indexFile(), html);
    }

    public void write(TestSuite testSuite) {
        String html = templateEngine().process("test-suite.html",  contextForTextSuite(testSuite));
        write(fileNameProvider.file(testSuite), html);
    }

    private TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode(TemplateMode.HTML);
        templateEngine.setTemplateResolver(resolver);
        return templateEngine;
    }

    private Context contextForReportIndex(DataReportIndex dataReportIndex) {
        List<TestSuiteNameToFile> links = dataReportIndex.getLinks().getTestSuites().stream()
            .map(l ->
                new TestSuiteNameToFile(l.getName(), l.getFile().replace(".json", ".html")))
            .collect(Collectors.toList());

        Context context = new Context();
        context.setVariable("dataReportIndex", dataReportIndex);
        context.setVariable("summary", dataReportIndex.getSummary());
        context.setVariable("links", links);
        return context;
    }

    private Context contextForTextSuite(TestSuite testSuite) {
        Context context = new Context();
        context.setVariable("testSuite", testSuite);
        context.setVariable("testCases", testSuite.getTestCases());
        return context;
    }
}
