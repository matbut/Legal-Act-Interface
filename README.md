# Legal-Act-Interface
Parses legal act text file to object form. 
Works only for:
- Polish Constitution (konstytucja.txt)
- Poish Act on competition and consumer protection (uokik.txt)

## Table of Contents
- [Features](#Features)
- [Usage](#usage)
- [Built-With](#Built-With)
- [Author](#Author)

## Features
* Prints content
* Prints content specyfied by path
* Prints content specyfied by range of articles
* Prints table of content
* Prints table of content specyfied by path

## Usage
Also avaliable in usage help.
Usage:
```
Legal-Act-Interface [-cthv]...FILE
Legal-Act-Interface [-r=<range>-<range>]...FILE
Legal-Act-Interface [-tp=<path>[,<path>]]...FILE
Legal-Act-Interface [-cp=<path>[,<path>]]...FILE
```
Parameters:
      FILE                    file to process
      
Options:
  -t, --table                 print table of content
  -c, --content               print content
  -r, --range=<range>[-<range>]...
                              print specified range of articles, splited by '-'.
  -p, --path=<path>[,<path>]...
                              expand -t,-c option by specifying path to
                                printing component, splited by ','.
  -h, --help                  display a help message
  -v, --version               print version information
  
Path and range should by specyfied by shortcuts.
Remember to use roman numerals, like in original document.
Shortcuts:
- dz.  - dział
- roz. - rozdział
- art. - artykuł
- ust. - ustęp
- pkt. - punkt
- lit. - litera

Example specifying path and range:
```
-cp=art.2,ust.2a,pkt.3,lit.a
-tp=dz.II,roz.3
-r=art.3-art.7
```                

## Built-With
* [Picocli](http://picocli.info/) - framework for creating Java command line

## Author
**Mateusz Buta**
