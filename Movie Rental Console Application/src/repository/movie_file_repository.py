import pickle

from src.repository.movie_repository import MovieRepository


class MovieFileRepository(MovieRepository):
    def __init__(self, file_path, entity_from_line, entity_to_line):
        super().__init__()
        self.__file_path = file_path
        self.__entity_from_line = entity_from_line
        self.__entity_to_line = entity_to_line

    def _append_to_file(self, movie_id, tile, description, genre):
        with open(self.__file_path, 'a') as f:
            f.write(self.__entity_to_line(movie_id, tile, description, genre) + '\n')

    def _read_from_file(self):
        with open(self.__file_path, 'r') as f:
            lines = f.readlines()
            self._movies.clear()
            for line in lines:
                line = line.strip()
                if len(line) > 0:
                    entity = self.__entity_from_line(line)
                    self._movies.append(entity)

    def _write_to_file(self):
        with open(self.__file_path, 'w') as f:
            for movie in self._movies:
                f.write(self.__entity_to_line(movie.movie_id, movie.title,movie.description, movie.genre) + '\n')

    def find_by_id(self, movie_id):
        self._read_from_file()
        return super().find_by_id(movie_id)

    def add_movie(self, movie_id, tile, description, genre):
        self._read_from_file()
        super().add_movie(movie_id, tile, description, genre)
        self._append_to_file(movie_id, tile, description, genre)

    def delete_movie_by_id(self, movie_id):
        self._read_from_file()
        super().delete_movie_by_id(movie_id)
        self._write_to_file()

    def search_by_key_word(self, key_word):
        self._read_from_file()
        return super().search_by_key_word(key_word)

    def update_movie(self, movie_id, title, description, gendre):
        self._read_from_file()
        super().update_movie(movie_id, title, description, gendre)
        self._write_to_file()

    def get_all(self):
        self._read_from_file()
        return super().get_all()


class MovieFileRepositoryBinary(MovieRepository):
    def __init__(self, file_path, entity_from_line, entity_to_line):
        super().__init__()
        self.__file_path = file_path
        self.__entity_from_line = entity_from_line
        self.__entity_to_line = entity_to_line

    def _append_to_file(self, movie_id, tile, description, genre):
        with open(self.__file_path, 'wb') as f:
            pickle.dump(self._movies, f)

    def _read_from_file(self):
        with open(self.__file_path, 'rb') as f:
            self._movies.clear()
            try:
                self._movies = pickle.load(f)
            except EOFError:
                pass

    def _write_to_file(self):
        with open(self.__file_path, 'wb') as f:
            pickle.dump(self._movies, f)


    def find_by_id(self, movie_id):
        self._read_from_file()
        return super().find_by_id(movie_id)

    def add_movie(self, movie_id, tile, description, genre):
        self._read_from_file()
        super().add_movie(movie_id, tile, description, genre)
        self._append_to_file(movie_id, tile, description, genre)

    def delete_movie_by_id(self, movie_id):
        self._read_from_file()
        super().delete_movie_by_id(movie_id)
        self._write_to_file()

    def search_by_key_word(self, key_word):
        self._read_from_file()
        return super().search_by_key_word(key_word)

    def update_movie(self, movie_id, title, description, gendre):
        self._read_from_file()
        super().update_movie(movie_id, title, description, gendre)
        self._write_to_file()

    def get_all(self):
        self._read_from_file()
        return super().get_all()