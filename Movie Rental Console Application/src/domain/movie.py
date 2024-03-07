
class Movie:
    def __init__(self, movie_id, title, description, genre):
        self.__movie_id = movie_id
        self.__tile = title
        self.__description = description
        self.__genre = genre

    @property
    def movie_id(self):
        return self.__movie_id

    @property
    def title(self):
        return self.__tile

    @title.setter
    def title(self, title):
        self.__tile = title

    @property
    def description(self):
        return self.__description

    @description.setter
    def description(self, description):
        self.__description = description

    @property
    def genre(self):
        return self.__genre

    @genre.setter
    def genre(self, genre):
        self.__genre = genre

    @staticmethod
    def movie_to_line(movie_id, title, description, genre):
        return f"{movie_id}, {title}, {description}, {genre}"

    @staticmethod
    def line_to_movie(line):
        words = line.split(',')
        return Movie(int(words[0]), words[1], words[2], words[3])