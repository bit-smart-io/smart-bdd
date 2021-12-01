/*
 * Smart BDD - The smart way to do behavior-driven development.
 * Copyright (C)  2021  James Bayliss
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
        resolver.setPrefix("/WEB-INF/templates/");
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
