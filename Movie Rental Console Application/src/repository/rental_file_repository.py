import pickle
from datetime import date

from src.repository.rental_repository import RentalRepository


class RentalFileRepository(RentalRepository):
    def __init__(self, file_path, entity_from_line, entity_to_line):
        super().__init__()
        self.__file_path = file_path
        self.__entity_from_line = entity_from_line
        self.__entity_to_line = entity_to_line

    def _append_to_file(self, rental_id, movie_id, client_id, rented_date, due_date, returned_date):
        with open(self.__file_path, 'a') as f:
            f.write(self.__entity_to_line(rental_id, movie_id, client_id, rented_date, due_date, returned_date) + '\n')

    def _read_from_file(self):
        with open(self.__file_path, 'r') as f:
            lines = f.readlines()
            self._rentals.clear()
            for line in lines:
                line = line.strip()
                if len(line) > 0:
                    entity = self.__entity_from_line(line)
                    self._rentals.append(entity)

    def _write_to_file(self):
        with open(self.__file_path, 'w') as f:
            for rental in self._rentals:
                f.write(self.__entity_to_line(rental.rental_id, rental.movie_id, rental.client_id, rental.rented_day, rental.due_day, rental.returned_day) + '\n')

    def find_rental_by_id(self, rental_id):
        self._read_from_file()
        return super().find_rental_by_id(rental_id)

    def add_new_rental(self, rental_id, movie_id, client_id, due_day):
        today = date.today()
        current_date = today.strftime('%d/%m/%Y')
        self._read_from_file()
        super().add_new_rental(rental_id, movie_id, client_id, due_day)
        self._append_to_file(rental_id, movie_id, client_id, current_date, due_day, 'not returned yet')

    def return_movie(self, rental_id):
        self._read_from_file()
        super().return_movie(rental_id)
        self._write_to_file()

    def append_rental(self, rental):
        self._read_from_file()
        super().append_rental(rental)
        self._write_to_file()

    def modify_returned_date(self, rental_id):
        self._read_from_file()
        super().modify_returned_date(rental_id)
        self._write_to_file()

    @staticmethod
    def difference_between_two_dates(date_1, date_2):

        string_to_list_day_1 = list(date_1.split("/"))
        date_1_day = int(string_to_list_day_1[0])
        date_1_month = int(string_to_list_day_1[1])
        date_1_year = int(string_to_list_day_1[2])

        string_to_list_day_2 = list(date_2.split("/"))
        date_2_day = int(string_to_list_day_2[0])
        date_2_month = int(string_to_list_day_2[1])
        date_2_year = int(string_to_list_day_2[2])

        date_1 = date(date_1_year, date_1_month, date_1_day)
        date_2 = date(date_2_year, date_2_month, date_2_day)

        delta = date_2 - date_1

        return delta.days

    def get_all_rentals(self):
        self._read_from_file()
        return super().get_all_rentals()

    def remove_rental(self, rental_id):
        self._read_from_file()
        super().remove_rental(rental_id)
        self._write_to_file()


class RentalFileRepositoryBinary(RentalRepository):
    def __init__(self, file_path, entity_from_line, entity_to_line):
        super().__init__()
        self.__file_path = file_path
        self.__entity_from_line = entity_from_line
        self.__entity_to_line = entity_to_line

    def _append_to_file(self, rental_id, movie_id, client_id, rented_date, due_date, returned_date):
        with open(self.__file_path, 'wb') as f:
            pickle.dump(self._rentals, f)

    def _read_from_file(self):
        with open(self.__file_path, 'rb') as f:
            self._rentals.clear()
            try:
                self._rentals = pickle.load(f)
            except EOFError:
                pass

    def _write_to_file(self):
        with open(self.__file_path, 'wb') as f:
            pickle.dump(self._rentals, f)



    def find_rental_by_id(self, rental_id):
        self._read_from_file()
        return super().find_rental_by_id(rental_id)

    def add_new_rental(self, rental_id, movie_id, client_id, due_day):
        today = date.today()
        current_date = today.strftime('%d/%m/%Y')
        self._read_from_file()
        super().add_new_rental(rental_id, movie_id, client_id, due_day)
        self._append_to_file(rental_id, movie_id, client_id, current_date, due_day, 'not returned yet')

    def return_movie(self, rental_id):
        self._read_from_file()
        super().return_movie(rental_id)
        self._write_to_file()

    def append_rental(self, rental):
        self._read_from_file()
        super().append_rental(rental)
        self._write_to_file()

    def modify_returned_date(self, rental_id):
        self._read_from_file()
        super().modify_returned_date(rental_id)
        self._write_to_file()

    @staticmethod
    def difference_between_two_dates(date_1, date_2):

        string_to_list_day_1 = list(date_1.split("/"))
        date_1_day = int(string_to_list_day_1[0])
        date_1_month = int(string_to_list_day_1[1])
        date_1_year = int(string_to_list_day_1[2])

        string_to_list_day_2 = list(date_2.split("/"))
        date_2_day = int(string_to_list_day_2[0])
        date_2_month = int(string_to_list_day_2[1])
        date_2_year = int(string_to_list_day_2[2])

        date_1 = date(date_1_year, date_1_month, date_1_day)
        date_2 = date(date_2_year, date_2_month, date_2_day)

        delta = date_2 - date_1

        return delta.days

    def get_all_rentals(self):
        self._read_from_file()
        return super().get_all_rentals()

    def remove_rental(self, rental_id):
        self._read_from_file()
        super().remove_rental(rental_id)
        self._write_to_file()
