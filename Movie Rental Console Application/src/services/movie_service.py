from src.domain.validators import DuplicateIdError, IdMustBePositive, ThisIdDoesNotExist


class MovieService:
    def __init__(self, repository):
        self.__repository = repository

    def add_movie(self, movie_id, title, description, gendre):
        if self.__repository.find_by_id(movie_id) is not None:
            raise DuplicateIdError("This id already exist!")
        elif int(movie_id) < 0:
            raise IdMustBePositive("The id need to be positive!")
        else:
            self.__repository.add_movie(movie_id, title, description, gendre)

    def remove_movie(self, movie_id):
        if self.__repository.find_by_id(movie_id) is None:
            raise ThisIdDoesNotExist("This id doesn't exist!")
        else:
            self.__repository.delete_movie_by_id(movie_id)

    def update_movie(self, movie_id, title, description, gendre):
         self.__repository.update_movie(movie_id, title, description, gendre)

    def get_all_movies(self):
        return self.__repository.get_all()

    def search_movie(self, key_word):
        return self.__repository.search_by_key_word(key_word)

    def find_movie_by_id(self, movie_id):
        return self.__repository.find_by_id(movie_id)

    def validate_if_id_exist(self, movie_id):
        if MovieService.find_movie_by_id(self, movie_id) is None:
            raise ThisIdDoesNotExist("There is no movie with this id!")
