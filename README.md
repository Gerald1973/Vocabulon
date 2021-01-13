# Vocabulon

WARNING: FIRST OF ALL INSTALL:

<http://espeak.sourceforge.net/download.html>

## Introduction

Vocabulon is a program to help you or your kids to learn another languages. This program is
configurable via  data text files by setting the source language and the destination language.

## Setup

### eSpeak

Download the program espak from : <http://espeak.sourceforge.net/download.html>  
If you don't want to reconfigure the program, install it in:
  
  ```dos
  C:\Program Files (x86)\eSpeak\command_line\
  ````

 Else, if you installed this program in other directory, **modify** the file
*vocabulon.properties* by setting the following key : pathespeak
For example :

```javaproperties
pathespeak=C:\\whatever\\eSpeak\\command_line\\espeak.exe
```

>The "`\\`" is inherent to Windows, if you use Linux just use "/".
I didn't test it under linux, but usually it has work too, because eSpeak is developed for Windows and Linux.

Unzip the file Vocabulon-0.0.1.zip where you want.  As this program is small, I suggest
you to extract it directly on your desktop.

Run it, to run this program mostly users will have two choices:

By command line:  

```sh
java -jar Vocabulon-0.0.1
```

By the included runme.cmd (dbl click on it and it's done)
Directly by double click on it, if you installed your JVM from Oracle (most common choice)

## Data file description

The data file, Vocabulon doesn't take care about the data file extension, but it uses his own and  very simple structure.

### Description

Aud In Unter ...      (a)  
de|fr         (b)  
Mot à traduire|traduction|réponse  (c)  
auf|sur         (d)
vor|devant  

The separator is the pipe "|"  
(a) This line is the title of the window  
(b) This line uses the ISO Code of the language with two letters.
the one on the left indicates the language in the left column  
the one on the right indicates the language in the right column  
(c) the title for the three columns, separated by the "|"  
In this example:  
The title of the first column will be "Mot à traduire" (Word to translate)  
The title of the second column will be "Traduction" (Translation)  
The title of the third column will be "réponse" (answer)  
(d) The vocabulary columns, two columns.  
In this example the word in german is on the left and the word in french is on the right spearated by a pipe "|" without spaces.
