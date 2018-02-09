package agh.inf.polab;

import agh.inf.polab.act.elements.EditorialUnit;
import agh.inf.polab.act.elements.IdentifiedEditorialUnit;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.io.File;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CommandLine.Command(
        sortOptions = false,
        name = "Legal-Act-Interface",
        customSynopsis = {
                "Legal-Act-Interface [-cthv]...FILE ",
                "  or:  Legal-Act-Interface [-r=<range>-<range>]...FILE",
                "  or:  Legal-Act-Interface [-tp=<path>[,<path>]]...FILE",
                "  or:  Legal-Act-Interface [-cp=<path>[,<path>]]...FILE",
        },descriptionHeading = "%nDescription:%n%n",
        description = {
                "Parses legal act text file to object form.",
                "Prints content, table of content, range of articles.",
                "Prints content or table of content of the component specified by path."
        },parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n",
        version = {
                 "Version 1.0",
                 "(c) 2017"
        },footer = {
                "%nPath and range should by specyfied by shortcuts.",
                "Remember to use roman numerals, like in original document.",
                "Shortcuts:",
                "  dz.  - dział",
                "  roz. - rozdział",
                "  art. - artykuł",
                "  ust. - ustęp",
                "  pkt. - punkt",
                "  lit. - litera",
                "%nExample specifying path and range:",
                "  -cp=art.2,ust.2a,pkt.3,lit.a",
                "  -tp=dz.II,roz.3",
                "  -r=art.3-art.7",
                "%nCopyright(c) 2017%n"
        })

class ProgSpecyfication {

    @Option(names = { "-t", "--table" }, description = "print table of content")
    private boolean tableOfContent = false;

    @Option(names = { "-c", "--content" }, description = "print content")
    private boolean content = false;

    @Option(names = { "-r", "--range" },split = "-", description = "print specified range of articles, splited by '-'.")
    private IdentifiedEditorialUnit range[];

    @Option(names = { "-p", "--path" },split = ",", description = "expand -t,-c option by specifying path to printing component, splited by ','.")
    private Collection<IdentifiedEditorialUnit> path;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean usageHelpRequested = false;

    @Option(names = { "-v", "--version" }, versionHelp = true,description = "print version information")
    private boolean versionRequested;

    @Parameters(arity = "1", paramLabel = "FILE", description = "file to process")
    private File inputFile;

    void check(){
        if(range!=null && range.length!=2)
            throw new IllegalArgumentException("Range isn't correctly specified.");
        if(path!=null && !content && !tableOfContent)
            throw new IllegalArgumentException("Path can't be specified without printing.");
        if(path!=null && content && tableOfContent)
            throw new IllegalArgumentException("Path can't be specified to two printings");
        if(inputFile==null)
            throw new IllegalArgumentException("File isn't specified.");
    }

    static IdentifiedEditorialUnit fromComandLineToIdEditUnit(String s) throws IllegalArgumentException {
        for (EditorialUnit findingUnit : EditorialUnit.values()) {
            Pattern p = Pattern.compile(findingUnit.optionParserRegex());
            Matcher m = p.matcher(s);

            if (m.matches())
                return new IdentifiedEditorialUnit(findingUnit, m.group("id"));

        }
        throw new IllegalArgumentException("Incorrect argument: " + s);
    }

    // Getters

    boolean isTableOfContent() {
        return tableOfContent;
    }

    boolean isContent() {
        return content;
    }

    IdentifiedEditorialUnit[] getRange() {
        return range;
    }

    Collection<IdentifiedEditorialUnit> getPath() {
        return path;
    }

    boolean isUsageHelpRequested() {
        return usageHelpRequested;
    }

    boolean isVersionRequested() {
        return versionRequested;
    }

    File getInputFile() {
        return inputFile;
    }
}

