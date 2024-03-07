class DFA:
    def __init__(self):
        self.states = set()
        self.alphabet = set()
        self.transitions = {}
        self.initial_state = None
        self.final_states = set()
        self.load_from_file()

    def load_from_file(self):
        with open("D:\Semester V\FLCD\FLCD_project_compiler\My_language\FiniteAutomata\FA.in", 'r') as file:
            for line in file:
                line = line.strip()
                if line.startswith('states'):
                    self.states = set(line.split()[1:])
                elif line.startswith('alphabet'):
                    self.alphabet = set(line.split()[1:])
                elif line.startswith('transitions'):
                    transitions_info = line.split()[1:]
                    current_state = transitions_info[0]
                    for i in range(1, len(transitions_info)):
                        source_transition = transitions_info[i].split('-')
                        token = source_transition[0]
                        next_state = source_transition[1]
                        self.transitions[(current_state, token)] = next_state
                elif line.startswith('initial'):
                    self.initial_state = line.split()[1]
                elif line.startswith('final'):
                    self.final_states = set(line.split()[1:])

    def display_elements(self):
        print("States:", self.states)
        print("Alphabet:", self.alphabet)
        print("Transitions:")
        for transition, destination in self.transitions.items():
            print(f"  {transition[0]} --({transition[1]})--> {destination}")
        print("Initial State:", self.initial_state)
        print("Final States:", self.final_states)

    def is_sequence_accepted(self, sequence):
        current_state = self.initial_state
        for symbol in sequence:
            if (current_state, symbol) not in self.transitions:
                return False
            current_state = self.transitions[(current_state, symbol)]
        return current_state in self.final_states

    def is_identifier(self, token):
        return self.is_sequence_accepted(token) and 'q1' in self.final_states

    def is_integer_constant(self, token):
        return self.is_sequence_accepted(token) and ('q2' in self.final_states or 'q3' in self.final_states)

# fa = DFA()
# fa.display_elements()
#
# identifiers = ["variable", "0variable", "Invalid123", "_underscore"]
# integer_constants = [ "123", "07", "Invalid123", "1a"]
#
# print("\nIdentifiers:")
# for identifier in identifiers:
#     print(f"{identifier}: {fa.is_identifier(identifier)}")
#
# print("\nInteger Constants:")
# for integer_constant in integer_constants:
#     print(f"{integer_constant}: {fa.is_integer_constant(integer_constant)}")
