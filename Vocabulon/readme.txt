0123456789+0123456789+0123456789+0123456789+0123456789+1234567890+1234567890+1234567890+

This file contains some useful informations to setup vocabulon.
================================================================

1. Introduction
===============

Vocabulon is a program to help you or your kids to learn other language. This program is
configurable in his data files by setting the source language and the destination 
language.

2. Setup
========
	2.1. Download the program espak from http://espeak.sourceforge.net/download.html
		2.1.1 If you don't want to reconfigure the program, install it in 
			C:\\Program Files (x86)\\eSpeak\\command_line\\espeak.exe
			else, if you installed this program in other directory, CHANGE the file 
			vocabulon.properties by setting the following keys : pathespeak
			
			For example :
			pathespeak=C:\\whatever\\eSpeak\\command_line\\espeak.exe
			
			Rem: the "\\" is inhérent to Windows, if you use Linux just use "/".
			
			I didn't test it under linux, but usually it has work too, because espeak is
			developed for Windows and Linux.
	2.2 Unzip the file Vocabulon-0.0.1.zip where you want.  As this program is small, I suggest
		you to extract it directly on your desktop.
	   
	2.3 Run it, to run this program mostly users will have two choices:
		2.3.1 By command line:
			  java -jar Vocabulon-0.0.1
			  or by running the included runme.cmd (dbl click on it and it's done)
		2.3.2 Directly by double click on it, if you installed your JVM from Oracle (most common choice)

3.) Data file description.
==========================
	The data file, Vocabulon doesn't take care about the data file extension, but it use his own and 
	very simple structure.
	
	3.1 Description
	===============
	Aud In Unter ...						(a)
	de|fr									(b)
	Mot à traduire|traduction|réponse		(c)
	auf|sur									(d)
	vor|devant		
	
	The separator is the pipe "|"
	(a) This line is the title of the window
	(b) This is line use the ISO Code of the langage in with two letters.
	    the one on the left indicates the langage ins the left column
	    the one on the right indicates the langage in the right column
	(c) the title for the three columns separated by the "|"
		In this example: 
			the title of the first column will be "Mot à traduire" (Word to translate)
			the title of the second column will be "Traduction" (Translation)
			the title of the third column will be "Réponse" (answer)
			
	(d) The vocabulary columns, two columns.
		In this example the word in german is on the left and the word in french is on the right
		spearated by e pipe "|" without spaces.
		
		
			
	
	    
	
	
	
			
