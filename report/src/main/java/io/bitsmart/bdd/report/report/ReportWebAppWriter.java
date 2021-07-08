package io.bitsmart.bdd.report.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.nio.file.Path;

/** TODO the concept doesn't work */
public class ReportWebAppWriter {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final TestSuiteFileUtil testSuiteFileUtil = new TestSuiteFileUtil();
    private final FileUtils fileUtils = new FileUtils();

    public void prepare() throws Exception {
        File dest = testSuiteFileUtil.outputDirectory();
        Path src =  fileUtils.pathForProjectResource("web");
        fileUtils.copyFolder(src, dest.toPath());
    }
}
