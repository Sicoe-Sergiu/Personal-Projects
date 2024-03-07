from My_language.FiniteAutomata.DeterministicFiniteAutomaton import DFA
from My_language.PifStructure import PifStructure
from My_language.SymTable import SymTable
import re


class Scanner:

    def __init__(self, program_path: str):
        self.__symbol_table = SymTable()
        self.__pif = PifStructure()
        self.__DFA = DFA()

        # program text
        with open(program_path) as file:
            self.__program_text = file.readlines()

        # program symbols
        with open("D:\Semester V\FLCD\FLCD_project_compiler\Lab_1b\Tokens") as file:
            self.__program_symbols = [line.strip() for line in file.readlines()]

        self.__scan()

    def __scan(self):

        lexical_correct = True
        for index, line in enumerate(self.__program_text):
            if line == '\n':
                continue
            new_line = line.strip()

            tokens = self.divide_in_tokens(new_line)
            for token in tokens:
                if token in self.__program_symbols:
                    self.__pif.add(token, -1)

                elif self.__DFA.is_identifier(token):
                    index = self.__symbol_table.add(token)
                    self.__pif.add("ID", index)

                elif re.match(r'"([^"]+)"', token) or self.__DFA.is_integer_constant(token):
                    index = self.__symbol_table.add(token)
                    self.__pif.add("CONST", index)

                else:
                    index_error = self.__program_text[index].find(token)
                    error = f"Lexical Error! Line {index } Col {index_error }\n"
                    lexical_correct = False
                    break
        if lexical_correct:
            self.write_to_file_st_pif("Lexically Correct")
        else:
            self.write_to_file_st_pif(error)

    @staticmethod
    def divide_in_tokens(program: str) -> list[str]:
        tokens = []
        simple_tokens = [",", ";", "(", ")", "[", "]", "{", "}", " ", "+", "-", "*", "/", "%", ">", "<", "="]
        current_pos = 0

        while current_pos < len(program):
            lookahead = program[current_pos]

            if lookahead.isspace():
                current_pos += 1
            elif lookahead in simple_tokens:
                if lookahead in ["=", "<", ">"]:
                    next_lookahead = program[current_pos + 1]
                    if next_lookahead in ["=", "<", ">"]:
                        current_pos += 2
                        tokens.append(lookahead + next_lookahead)
                    else:
                        current_pos += 1
                        tokens.append(lookahead)
                else:
                    current_pos += 1
                    tokens.append(lookahead)


            elif lookahead.isdigit():
                text = ""
                while current_pos < len(program) and program[current_pos].isdigit():
                    text += program[current_pos]
                    current_pos += 1
                tokens.append(text)

            elif lookahead == '"':
                text = ""
                while current_pos < len(program) and (program[current_pos].isalpha() or program[current_pos] == '"' or program[current_pos] == ' '):
                    text += program[current_pos]
                    current_pos += 1
                tokens.append(text)

            elif lookahead.isalpha():
                text = ""
                while current_pos < len(program) and program[current_pos].isalpha():
                    text += program[current_pos]
                    current_pos += 1
                tokens.append(text)
            else:
                raise ValueError(f"Unknown character '{lookahead}' at position {current_pos}")

        return tokens

    def write_to_file_st_pif(self, error):
        with open("D:\Semester V\FLCD\FLCD_project_compiler\My_language\PIF.out", "w") as file:
            file.write(str(self.__pif) + "\n\n" + error)
        with open("D:\Semester V\FLCD\FLCD_project_compiler\My_language\ST.out", "w") as file:
            file.write("Symbol Table:\n" + str(self.__symbol_table))

    def get_pif(self):
        return [item[0] for item in self.__pif]
