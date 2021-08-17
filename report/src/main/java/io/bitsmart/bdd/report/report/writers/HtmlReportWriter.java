package io.bitsmart.bdd.report.report.writers;

import io.bitsmart.bdd.report.report.model.DataReportIndex;

public class HtmlReportWriter extends AbstractReportWriter {

    public HtmlReportWriter(FileNameProvider dataFileNameProvider) {
        super(dataFileNameProvider);
    }

    public void write(DataReportIndex dataReportIndex) {
        String contents = "<html></body><h1>index</h1></body><html>";
        write(fileNameProvider.indexFile(), contents);
    }
}
