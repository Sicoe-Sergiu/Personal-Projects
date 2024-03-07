SIZE = 1000
NULL = [-1, -1]


class SymTable:
    def __init__(self):
        self.__capacity = SIZE
        self.__size = 0
        self.__data = [[] for _ in range(self.__capacity)]

    def __len__(self):
        return self.__size

    def hash(self, value):
        hash_value = 0
        for char in value:
            hash_value += ord(char)

        return hash_value % self.__capacity

    def add(self, elem):
        self.__size += 1
        index = self.hash(elem)

        if len(self.__data[index]) == 0:
            self.__data[index].append(elem)
            return [index, 0]
        else:
            i = 0
            while i < len(self.__data[index]) and self.__data[index][i] != elem:
                i += 1
            if i < len(self.__data[index]):
                self.__size -= 1
                return [index, i]
            self.__data[index].append(elem)
            return [index, i]

    def find(self, elem):
        index = self.hash(elem)
        if len(self.__data[index]) == 0:
            return NULL
        else:
            for i, x in enumerate(self.__data[index]):
                if x == elem:
                    return [index, i]
            return NULL

    def remove(self, elem):
        self.__size -= 1
        index = self.hash(elem)
        i = -1
        if len(self.__data[index]) != 0:
            for j, x in enumerate(self.__data[index]):
                if x == elem:
                    self.__data[index] = self.__data[index][:j] + self.__data[index][j + 1:]
                    i = j
        if i == -1:
            self.__size += 1
            return False
        return True

    def size(self):
        return self.__size

    def is_empty(self):
        return self.__size == 0

    def get_all(self):
        return self.__data

    def get_as_list(self):
        result = []
        for index_hash, item_list in enumerate(self.__data):
            if len(item_list) == 0:
                continue
            for index_position, item in enumerate(item_list):
                result.append([[index_hash, index_position], item])
        return result

    def __str__(self):
        return f"{'Position':<15} Value\n" + "\n".join(
            [f"{str(entry[0]):15} {entry[1]}" for entry in self.get_as_list()])
