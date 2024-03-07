
class Rental:
    def __init__(self, rental_id, movie_id, client_id, rented_date, due_date, returned_date):
        self.__rental_id = rental_id
        self.__movie_id = movie_id
        self.__client_id = client_id
        self.__rented_date = rented_date
        self.__due_date = due_date
        self.__returned_day = returned_date

    @property
    def rental_id(self):
        return self.__rental_id

    @rental_id.setter
    def rental_id(self, rental_id):
        self.__rental_id = rental_id

    @property
    def movie_id(self):
        return self.__movie_id

    @property
    def client_id(self):
        return self.__client_id

    @client_id.setter
    def client_id(self, client_id):
        self.__client_id = client_id

    @property
    def rented_day(self):
        return self.__rented_date

    @rented_day.setter
    def rented_day(self, rented_date):
        self.__rented_date = rented_date

    @property
    def due_day(self):
        return self.__due_date

    @due_day.setter
    def due_day(self, due_day):
        self.__due_date = due_day

    @property
    def returned_day(self):
        return self.__returned_day

    @returned_day.setter
    def returned_day(self, returned_day):
        self.__returned_day = returned_day

    @staticmethod
    def rental_to_line(rental_id, movie_id, client_id, rented_date, due_date, returned_date):
        return f"{rental_id}, {movie_id}, {client_id}, {rented_date}, {due_date}, {returned_date}"

    @staticmethod
    def line_to_rental(line):
        words = line.split(',')
        return Rental(int(words[0]), int(words[1]), int(words[2]), words[3], words[4], words[5])