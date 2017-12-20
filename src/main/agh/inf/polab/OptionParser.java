package agh.inf.polab;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@CommandLine.Command(name = "Legal Act Interface", footer = "Copyright(c) 2017",
        description = "blabla")

public class OptionParser {

    // spis treści ustawy
    // spis treści działu o numerze

    // wyświetlanie specyficznych elementów składowych artykułu
    // treści artykułu o określonym numerze lub zakresu artykułów

    // -t - Print Table Of Contents;
    // -a - Print All;

    // -ap - Print All in Path;
    // -ar - Print All in range
    // -tp - Print Table Of Contents; in Path



    @Option(names = { "-t", "--table" }, description = "print table of content")
    public boolean tableOfContent = false;

    @Option(names = { "-a", "--all" }, description = "print all")
    public boolean all = false;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    public boolean usageHelpRequested = false;

    @Parameters(index = "0", paramLabel = "FILE", description = "file to process.")
    public File inputFile;

    @Parameters(index = "1..*", description = "path")
    LinkedList<IdentifiedEditorialUnit> path;

}

