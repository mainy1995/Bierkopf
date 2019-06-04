#!/usr/bin/python
# -*- coding: utf-8 -*-
import os
os.system("ls")
#This will list all the files in present #working directory

farbe = ["Herz","Blatt", "Schell", "Eichel"]
art = ["Ass", "Koenig", "Ober", "Unter", "Zehner","Neuner", "Achter", "Siebener", "Sechser"]

page_counter = 1

for i in art:

	for j in farbe:
		#Bsp Ass_Herz
		s = i + "_" + j
		befehl = "pdftoppm Tarock_FXS_Vektor.pdf %s -png -f %d -singlefile" % (s, page_counter)
		print(befehl)
		os.system(befehl)
		page_counter = page_counter +1




		#"Studying in %s" % "Germany"