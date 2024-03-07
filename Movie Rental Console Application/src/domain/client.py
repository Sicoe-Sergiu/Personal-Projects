
class Client:
    def __init__(self, client_id, name):
        self.__client_id = client_id
        self.__name = name

    @property
    def client_id(self):
        return self.__client_id
    @property
    def name(self):
        return self.__name

    @name.setter
    def name(self, name):
        self.__name = name

    @staticmethod
    def client_to_line(client_id, name):
        return f"{client_id}, {name}"

    @staticmethod
    def line_to_client(line):
        words = line.split(',')
        return Client(int(words[0]), words[1])