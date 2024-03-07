from src.domain.rental import Rental
from datetime import date

class RentalRepository:
    def __init__(self):
        self._rentals = []
        self._rentals.append(Rental(334, 511, 456, '21/5/2021', '21/6/2021', '28/9/2021'))
        self._rentals.append(Rental(335, 999, 742, '23/4/2021', '15/5/2021', '15/5/2021'))
        self._rentals.append(Rental(336, 511, 909, '23/11/2021', '1/1/2022', 'Not returned yet'))
        self._rentals.append(Rental(337, 443, 554, '24/11/2021', '25/11/2021', 'Not returned yet'))
        self._rentals.append(Rental(338, 567, 456, '30/9/2021', '1/11/2021', 'Not returned yet'))
        self._rentals.append(Rental(339, 676, 445, '30/10/2021', '4/11/2021', 'Not returned yet'))
        self._rentals.append(Rental(340, 677, 123, '15/8/2021', '30/9/2021', '25/10/2021'))

    def find_rental_by_id(self, rental_id):
        for rental in self._rentals:
            if rental.rental_id == rental_id:
                return rental

        return None

    def add_new_rental(self, rental_id, movie_id, client_id, due_day):
        today = date.today()
        current_date = today.strftime('%d/%m/%Y')

        self._rentals.append(Rental(rental_id, movie_id, client_id, current_date, due_day, 'Not returned yet'))

    def return_movie(self, rental_id):
        today = date.today()
        current_date = today.strftime("%d/%m/%Y")

        for rental in self._rentals:
            if rental.rental_id == rental_id:
                rental.returned_day = current_date
                break

    def append_rental(self, rental):
        self._rentals.append(rental)

    def modify_returned_date(self, rental_id):
        for rental in self._rentals:
            if rental.rental_id == rental_id:
                rental.returned_day = 'Not returned yet'

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
        return self._rentals

    def remove_rental(self, rental_id):
        result_after_remove = []
        for rental in self._rentals:
            if rental.rental_id != rental_id:
                result_after_remove.append(rental)
        self._rentals = result_after_remove