from src.domain.validators import DuplicateIdError, IdMustBePositive, ThisIdDoesNotExist


class ClientService:
    def __init__(self, repository):
        self.__repository = repository

    def add_client(self, client_id, name):
        if self.__repository.find_by_id(client_id) is not None:
            raise DuplicateIdError("This id already exist!")
        elif client_id < 0:
            raise IdMustBePositive("The id need to be positive!")
        else:
            self.__repository.add_client(client_id, name)

    def remove_client(self, client_id):
        if self.__repository.find_by_id(client_id) is None:
            raise ThisIdDoesNotExist("This id doesn't exist!")
        else:
            self.__repository.delete_client_by_id(client_id)

    def update_client(self, client_id, name):
        self.__repository.update_client(client_id, name)

    def find_client_by_id(self, client_id):
        return self.__repository.find_by_id(client_id)

    def get_all_clients(self):
        return self.__repository.get_all()

    def search_client(self, key_word):
        return self.__repository.search_by_key_word(key_word)

    def validate_if_id_exist(self, client_id):
        if ClientService.find_client_by_id(self, client_id) is None:
            raise ThisIdDoesNotExist("There is no client with this id!")

    def validate_if_id_already_exist(self, client_id):
        if ClientService.find_client_by_id(self, client_id) is not None:
            raise DuplicateIdError("duplicate id")