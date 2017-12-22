package agh.inf.polab;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.*;

@CommandLine.Command(name = "Legal Act Interface", footer = "Copyright(c) 2017",
        description = "blabla")

public class ProgSpecyfication {

    @Option(names = { "-t", "--table" }, description = "print table of content")
    public boolean tableOfContent = false;

    @Option(names = { "-c", "--content" }, description = "print content")
    public boolean content = false;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    public boolean usageHelpRequested = false;

    @Option(names = { "-r", "--range" },split = "-", description = "print specified range")
    IdentifiedEditorialUnit range[];

    @Option(names = { "-p", "--path" },split = ",", description = "specify path splited by ','")
    Collection<IdentifiedEditorialUnit> path;

    @Parameters(arity = "1", paramLabel = "FILE", description = "file to process.")
    public File inputFile;
}

