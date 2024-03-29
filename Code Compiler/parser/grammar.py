import os
from collections import defaultdict


class Grammar:
    def __init__(self):
        with open("D:\\Semester V\\FLCD\\FLCD_project_compiler\\My_language\\parser\\grammar.txt") as file:
        # with open("D:\Semester V\FLCD\FLCD_project_compiler\My_language\parser\grammar.txt") as file:
            lines = [line.strip() for line in file.readlines()]
        self.__start_symbol = lines[0].strip()
        self.__non_terminals = lines[1].strip().split(" ")
        self.__terminals = lines[2].strip().split(" ")
        self.__productions = defaultdict(list)
        for line in lines[3:]:
            elements = line.split("->")
            self.__productions[elements[0].strip()].append(elements[1].strip().split(" "))

    def get_start_symbol(self):
        return self.__start_symbol

    def get_non_terminals(self):
        return self.__non_terminals

    def get_terminals(self):
        return self.__terminals

    def get_productions(self):
        return self.__productions

    def print_non_terminals(self):
        print(*self.__non_terminals)

    def print_terminals(self):
        print(*self.__terminals)

    def print_productions(self):
        for productions in self.__productions.items():
            for production in productions[1]:
                print(f"{productions[0]} -> {' '.join(production)}")

    def print_productions_for_non_terminal(self, non_terminal: str):
        if non_terminal in self.__non_terminals:
            for production in self.__productions[non_terminal]:
                print(f"{non_terminal} -> {' '.join(production)}")
        else:
            print(f"The non-terminal {non_terminal} does not exist!")

    def cfg_check(self):
        if set(self.__productions.keys()) != set(self.__non_terminals):
            return False
        for values in self.__productions.values():
            for items in values:
                for item in items:
                    if item not in self.__terminals and item not in self.__non_terminals:
                        return False
        return True
