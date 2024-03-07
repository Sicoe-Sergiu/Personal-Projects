from src.domain.movie import Movie


class MovieRepository:
    def __init__(self):
        self._movies = []
        self._movies.append(Movie(567, 'Resident Evil: Welcome to Raccoon City', 'A new movie version of the chilling survival horror game', 'Horror'))
        self._movies.append(Movie(511, 'Spider-Man: No Way Home', 'Marvel s spider-sequel to Homecoming and Far From Home.', 'Action, Comedy'))
        self._movies.append(Movie(325, 'The Matrix 4', 'Keanu Reeves steps into the Matrix in a new cyberpunk action thriller', 'Action, Triller'))
        self._movies.append(Movie(676, 'The King s Man', 'A WWI-era prequel to the Kingsman series of movies.', 'War, Action'))
        self._movies.append(Movie(900, 'Uncharted', 'om Holland plays video game hero Nathan Drake in Uncharted', 'Action'))
        self._movies.append(Movie(456, 'The Batman', 'DC Comics fans are getting another version of the Caped Crusader in The Batman', 'Action'))
        self._movies.append(Movie(443, 'John Wick: Chapter 4', 'Yeah, we re thinking he s back.', 'Action'))
        self._movies.append(Movie(567, 'Lightyear ', 'Chris Evans replaces Toy Story actor Tim Allen to provide the voice of Buzz Lightyear', 'Family'))
        self._movies.append(Movie(999, 'Mission: Impossible 7', 'Tom Cruise runs back onto the big screen for the next Mission: Impossible movie.', 'Action'))
        self._movies.append(Movie(677, 'Free Guy', 'The story of a video game s nonplayable character coming to life.', 'Comedy'))

    def find_by_id(self, movie_id):
        for movie in self._movies:
            if movie.movie_id == movie_id:
                return movie

        return None

    def add_movie(self, movie_id, title, description, gendre):
        self._movies.append(Movie(movie_id, title, description, gendre))

    def delete_movie_by_id(self, movie_id):
        for movie in self._movies:
            if movie.movie_id == movie_id:
                self._movies.remove(movie)

    def search_by_key_word(self, key_word):
        results = []

        for movie in self._movies:
            match_result = False

            search_in_id = key_word.lower() in str(movie.movie_id).lower()
            search_in_title = key_word.lower() in str(movie.title).lower()
            search_in_description = key_word.lower() in str(movie.description).lower()
            search_in_gendre = key_word.lower() in str(movie.genre).lower()

            if search_in_id:
                match_result = True
            elif search_in_title:
                match_result = True
            elif search_in_description:
                match_result = True
            elif search_in_gendre:
                match_result = True

            if match_result is True:
                results.append(movie)

        return results

    def update_movie(self, movie_id, title, description, gendre):
        for movie in self._movies:
            if movie.movie_id == movie_id:
                movie.title = title
                movie.description = description
                movie.genre = gendre

    def get_all(self):
        return self._movies
