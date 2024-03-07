# SymTable Class

The `SymTable` class represents a symbol table, which is a data structure used to store and manage a collection of unique elements. It's implemented as a hash table with separate chaining to handle collisions. The class contains methods for adding, finding, and removing elements, as well as various other operations related to the symbol table.

## Class Constants

- `SIZE`: A constant that determines the initial capacity of the symbol table.
- `NULL`: A constant representing an invalid or non-existent value in the symbol table.

## Constructor

The class constructor initializes an instance of the symbol table with the given capacity (`SIZE`). It sets the size of the symbol table (`__size`) to 0 and creates an empty data structure to hold the elements.

## Public Methods

- `hash(self, value)`: Calculates a hash value for a given input value based on the ASCII values of its characters.
- `add(self, elem)`: Inserts an element into the symbol table. It calculates the hash of the element's value and handles collisions using separate chaining.
- `find(self, elem)`: Locates an element within the symbol table.
- `remove(self, elem)`: Removes an element from the symbol table.
- `size(self)`: Returns the current size of the symbol table.
- `is_empty(self)`: Checks whether the symbol table is empty.
- `get_all(self)`: Returns the entire data structure containing the elements of the symbol table, including empty buckets.
- `get_as_list(self)`: Converts the symbol table into a list format, where each element is represented as a pair containing its position and value within the table.
- `__str__(self)`: Provides a string representation of the symbol table, displaying the position and value of each element.

## PifStructure Class

`PifStructure` is a class for managing a list of token-index pairs.

### Attributes:

- `__data (list)`: A list to store token-index pairs.

### Methods:

- `__init__()`: Initializes an empty `PifStructure`.
- `__len__()`: Returns the number of token-index pairs in the `PifStructure`.
- `__getitem__(item)`: Retrieves a token-index pair by index.
- `add(token, index)`: Adds a token-index pair to the `PifStructure`.
- `__str__()`: Returns a formatted string representation of the `PifStructure`.

## Scan Function

Scans the program text and populates the PIF and symbol table.

This method iterates through the lines of the program text, tokenizes them, and classifies tokens into program symbols, identifiers, and constants. It adds them to the PIF and symbol table accordingly. If a lexical error is encountered, it records the error message.

### Regular Expressions Explained:

- `re.match(r'[a-zA-Z]+', token)`: Checks if 'token' consists of one or more alphabetical characters.
- `re.match(r'\d+|[a-zA-Z]+', token)`: Checks if 'token' is either a sequence of one or more digits or a sequence of one or more alphabetical characters.

## Scanner Class

`Scanner` is a class responsible for lexical analysis of a program text, dividing it into tokens and managing the Program Internal Form (PIF) and Symbol Table (ST).

### Attributes:

- `__symbol_table (SymTable)`: An instance of the Symbol Table to store identifiers.
- `__pif (PifStructure)`: An instance of the Program Internal Form (PIF) to store token-index pairs.
- `__program_text (list[str])`: A list containing the lines of the input program text.
- `__program_symbols (list[str])`: A list of predefined program symbols.

### Methods:

- `__init__(program_path: str)`: Initializes a `Scanner` object with the provided program path, setting up the scanner for lexical analysis.
- `__scan()`: Scans the program text, identifies tokens, and populates the PIF and symbol table.
- `divide_in_tokens(program: str) -> list[str]`: Divides a given program string into a list of tokens based on specific lexical rules.
- `write_to_file_st_pif(error: str)`: Writes the Symbol Table (ST) and Program Internal Form (PIF) to output files.
# DFA Class

The `DFA` class represents a Deterministic Finite Automaton. It loads the DFA elements from a file and provides methods to display its elements, check if a sequence is accepted, and determine if a token is an identifier or an integer constant.

## Constructor

- `__init__(self)`: Initializes the DFA by loading elements from a file using `load_from_file()`.

## Methods

- `load_from_file(self)`: Loads DFA elements (states, alphabet, transitions, initial state, final states) from a specified file.
- `display_elements(self)`: Displays the states, alphabet, transitions, initial state, and final states of the DFA.
- `is_sequence_accepted(self, sequence)`: Checks if a given sequence is accepted by the DFA.
- `is_identifier(self, token)`: Checks if a given token is an identifier according to the DFA.
- `is_integer_constant(self, token)`: Checks if a given token is an integer constant according to the DFA.

# ProductionRule Class

The `ProductionRule` class represents a production rule in a context-free grammar.

## Constructor

- `__init__(self, left, right)`: Initializes a production rule with a left-hand side (non-terminal) and right-hand side (sequence of symbols).

# Grammar Class

The `Grammar` class represents a context-free grammar. It loads the grammar from a file and provides methods to print non-terminals, terminals, productions, and productions for a specific non-terminal.

## Constructor

- `__init__(self)`: Initializes the Grammar by loading elements from a specified file using `load_from_file()`.

## Methods

- `load_from_file(self)`: Loads grammar elements (non-terminals, terminals, productions) from a specified file.
- `parse_production(self, production_str)`: Parses a production rule from a string and adds it to the grammar.
- `print_non_terminals(self)`: Prints the non-terminals of the grammar.
- `print_terminals(self)`: Prints the terminals of the grammar.
- `print_productions(self)`: Prints all productions of the grammar.
- `print_productions_for_non_terminal(self, non_terminal)`: Prints productions for a specific non-terminal.
- `cfg_check(self)`: Checks if the grammar is in Chomsky Normal Form (CNF).

# Parser Class

The `Parser` class is responsible for building the FIRST sets for each non-terminal in a given grammar.

## Constructor

- `__init__(self)`: Initializes the Parser with a Grammar instance and an empty dictionary for FIRST sets.

## Methods

- `build_first_sets(self)`: Builds the FIRST sets for each non-terminal in the grammar.
# Parser Class Documentation

The `Parser` class is designed to build and manage First and Follow sets for a given context-free grammar using the provided `Grammar` class.

## Constructor

### `__init__(self, grammar: Grammar)`

- **Parameters:**
  - `grammar` (Grammar): An instance of the Grammar class representing the context-free grammar.

- **Description:**
  - Initializes the Parser with the provided grammar.
  - Builds First sets and computes Follow sets during initialization.

## Public Methods

### `print_first_sets()`

- **Description:**
  - Prints the First sets for each non-terminal in the grammar.

### `print_follow_sets()`

- **Description:**
  - Prints the Follow sets for each non-terminal in the grammar.

### `build_first_sets()`

- **Description:**
  - Builds the First sets for each non-terminal in the grammar.
  - Uses the provided grammar's terminals and non-terminals.

### `compute_follow_sets()`

- **Description:**
  - Computes the Follow sets for each non-terminal in the grammar.
  - Uses the First sets previously built.
  - Handles epsilon productions appropriately.

## Example Usage

```python
from collections import defaultdict
from grammar import Grammar

# Create a Grammar instance (assumed to be implemented elsewhere)
my_grammar = Grammar(...)

# Create a Parser instance with the Grammar
my_parser = Parser(my_grammar)

# Print First sets
my_parser.print_first_sets()

# Print Follow sets
my_parser.print_follow_sets()
```

# Parser Class Documentation

The `Parser` class is designed to build and manage First and Follow sets for a given context-free grammar using the provided `Grammar` class.

## Constructor

### `__init__(self, grammar: Grammar)`

- **Parameters:**
  - `grammar` (Grammar): An instance of the Grammar class representing the context-free grammar.

- **Description:**
  - Initializes the Parser with the provided grammar.
  - Builds First sets and computes Follow sets during initialization.

## Public Methods

### `print_first_sets()`

- **Description:**
  - Prints the First sets for each non-terminal in the grammar.

### `print_follow_sets()`

- **Description:**
  - Prints the Follow sets for each non-terminal in the grammar.

### `print_parsing_table()`

- **Description:**
  - Prints the parsing table generated from the First and Follow sets.

### `print_parse_tree()`

- **Description:**
  - Prints the parse tree constructed during parsing.

### `build_first_sets()`

- **Description:**
  - Builds the First sets for each non-terminal in the grammar.
  - Iteratively processes production rules until no further updates are needed.

### `compute_follow_sets()`

- **Description:**
  - Computes the Follow sets for each non-terminal in the grammar.
  - Handles epsilon productions and updates Follow sets accordingly.
  - Iteratively processes production rules until no further updates are needed.

### `compute_parsing_table()`

- **Description:**
  - Computes the parsing table using the First and Follow sets.
  - Populates the parsing table with production rules for non-terminals and terminals.

### `parse(input_string: str) -> str`

- **Parameters:**
  - `input_string` (str): The input string to be parsed.

- **Returns:**
  - str: A message indicating whether parsing was successful or if an error occurred.

- **Description:**
  - Parses the given input string using the constructed parsing table.
  - Implements a stack-based parsing approach with error handling.
  - Returns a success message if parsing is successful; otherwise, returns an error message.

### `error()`

- **Description:**
  - Static method that prints a generic error message related to parsing.

## Example Usage

```python
from collections import defaultdict
from My_language.parser.grammar import Grammar
from My_language.parser.parser import Parser

# Create a Grammar instance (assumed to be implemented elsewhere)
my_grammar = Grammar(...)

# Create a Parser instance with the Grammar
my_parser = Parser(my_grammar)

# Print First sets
my_parser.print_first_sets()

# Print Follow sets
my_parser.print_follow_sets()

# Print Parsing Table
my_parser.print_parsing_table()

# Print Parse Tree
my_parser.print_parse_tree()

# Example Parsing
input_string = "..."
result = my_parser.parse(input_string)
print(result)
```

# ParseTree Class Documentation

The `ParseTree` class represents a parse tree data structure used in the context of parsing. It allows for the construction of a parse tree during the parsing process.

## Constructor

### `__init__(self)`

- **Description:**
  - Initializes a `ParseTree` instance with an empty tree and an index counter set to 0.

## Public Methods

### `add(self, symbols: list, parent: int)`

- **Parameters:**
  - `symbols` (list): List of symbols to be added to the parse tree.
  - `parent` (int): Index of the parent node in the parse tree.

- **Description:**
  - Adds a sequence of symbols to the parse tree.
  - Each symbol is represented as a node with a parent index and a right sibling index.
  - Handles the case when adding the first symbol to the tree.

### `get_parent(self, top_symbol: tuple) -> int`

- **Parameters:**
  - `top_symbol` (tuple): A tuple representing the top symbol of a production rule.

- **Returns:**
  - int: The index of the parent node in the parse tree.

- **Description:**
  - Retrieves the parent index of a node in the parse tree based on the provided top symbol.
  - Used during the parsing process to link nodes in the parse tree.

### `clear(self)`

- **Description:**
  - Clears the parse tree by resetting the index counter and clearing the tree structure.

### `__str__(self) -> str`

- **Returns:**
  - str: A formatted string representation of the parse tree.

- **Description:**
  - Provides a string representation of the parse tree, displaying the index, symbol, parent index, and right sibling index for each node in the tree.

## Example Usage

```python
# Create a ParseTree instance
my_parse_tree = ParseTree()

# Add symbols to the parse tree
my_parse_tree.add(['S', 'A', 'B'], 0)
my_parse_tree.add(['a', 'b', 'c'], 1)

# Get the parent index based on a top symbol
parent_index = my_parse_tree.get_parent(('A', 0))

# Clear the parse tree
my_parse_tree.clear()

# Display the parse tree
print(my_parse_tree)
```