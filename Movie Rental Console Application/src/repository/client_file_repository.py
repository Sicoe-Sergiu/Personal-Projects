import pickle

from src.repository.client_repository import ClientRepository


class ClientFileRepository(ClientRepository):
    def __init__(self, file_path, entity_from_line, entity_to_line):
        super().__init__()
        self.__file_path = file_path
        self.__entity_from_line = entity_from_line
        self.__entity_to_line = entity_to_line

    def _append_to_file(self, client_id, name):
        with open(self.__file_path, 'a') as f:
            f.write(self.__entity_to_line(client_id, name) + '\n')

    def _read_from_file(self):
        with open(self.__file_path, 'r') as f:
            lines = f.readlines()
            self._clients.clear()
            for line in lines:
                line = line.strip()
                if len(line) > 0:
                    entity = self.__entity_from_line(line)
                    self._clients.append(entity)

    def _write_to_file(self):
        with open(self.__file_path, 'w') as f:
            for client in self._clients:
                f.write(self.__entity_to_line(client.client_id,client.name) + '\n')



    def add_client(self, client_id, name):
        self._read_from_file()
        super().add_client(client_id, name)
        self._append_to_file(client_id, name)

    def find_by_id(self, client_id):
        self._read_from_file()
        return super().find_by_id(client_id)

    def get_all(self):
        self._read_from_file()
        return super().get_all()

    def delete_client_by_id(self, client_id):
        self._read_from_file()
        super().delete_client_by_id(client_id)
        self._write_to_file()

    def update_client(self, client_id, name):
        self._read_from_file()
        super().update_client(client_id, name)
        self._write_to_file()

    def search_by_key_word(self, key_word):
        self._read_from_file()
        return super().search_by_key_word(key_word)


class ClientFileRepositoryBinary(ClientRepository):
    def __init__(self, file_path, entity_from_line, entity_to_line):
        super().__init__()
        self.__file_path = file_path
        self.__entity_from_line = entity_from_line
        self.__entity_to_line = entity_to_line

    def _append_to_file(self, client_id, name):
        with open(self.__file_path, 'wb') as f:
            pickle.dump(self._clients, f)

    def _read_from_file(self):
        with open(self.__file_path, 'rb') as f:
            self._clients.clear()
            try:
                self._clients = pickle.load(f)
            except EOFError:
                pass

    def _write_to_file(self):
        with open(self.__file_path, 'wb') as f:
            pickle.dump(self._clients, f)



    def add_client(self, client_id, name):
        self._read_from_file()
        super().add_client(client_id, name)
        self._append_to_file(client_id, name)

    def find_by_id(self, client_id):
        self._read_from_file()
        return super().find_by_id(client_id)

    def get_all(self):
        self._read_from_file()
        return super().get_all()

    def delete_client_by_id(self, client_id):
        self._read_from_file()
        super().delete_client_by_id(client_id)
        self._write_to_file()

    def update_client(self, client_id, name):
        self._read_from_file()
        super().update_client(client_id, name)
        self._write_to_file()

    def search_by_key_word(self, key_word):
        self._read_from_file()
        return super().search_by_key_word(key_word)