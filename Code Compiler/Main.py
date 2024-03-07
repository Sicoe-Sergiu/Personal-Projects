from My_language.Scanner import Scanner
from My_language.parser.grammar import Grammar
from My_language.parser.parser import Parser

scanner = Scanner("D:\Semester V\FLCD\FLCD_project_compiler\Lab_1a\P5")

grammar = Grammar()
parser = Parser(grammar)
print("FIRST")
parser.print_first_sets()
print("FOLLOW")
parser.print_follow_sets()
print("PARSING TABLE")
parser.print_parsing_table()
# print(parser.parse([x for x in "xxyzza"]))
print(parser.parse(scanner.get_pif()))
print("TREE")
parser.print_parse_tree()















