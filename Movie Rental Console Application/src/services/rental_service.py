from datetime import date

from src.domain.validators import ThisIdDoesNotExist, IdMustBePositive, DuplicateIdError, Validators, \
    DueDateNeedToBeGraterThanTodayDate, ThisClientCanTRentAMovie


class RentalService:
    def __init__(self, repository, movie_repository, client_repository):
        self.__rental_repository = repository
        self.__movie_repository = movie_repository
        self.__client_repository = client_repository

    def return_movie(self, rental_id):
        if self.__rental_repository.find_rental_by_id(rental_id) is None:
            raise ThisIdDoesNotExist("This id doesn't exist!")
        else:
            self.__rental_repository.return_movie(rental_id)

    def get_rentals(self):
        return self.__rental_repository.get_all_rentals()

    def find_rental_by_id(self, rental_id):
        return self.__rental_repository.find_rental_by_id(rental_id)

    @staticmethod
    def validate_due_day(due_date):
        if Validators.validate_if_due_date_grater_today_date(due_date) is False:
            raise DueDateNeedToBeGraterThanTodayDate("The due date need to be grater than today date!")

    def validate_if_a_client_can_rent(self, client_id):
        rentals = RentalService.get_rentals(self)

        for rental in rentals:
            if rental.client_id == client_id and rental.returned_day == 'Not returned yet':
                if Validators.validate_if_due_date_grater_today_date(rental.due_day) is False:
                    raise ThisClientCanTRentAMovie("This client can t rent a movie, he have to return another one")


    def rent_a_movie(self, rental_id, movie_id, client_id, due_day):

        if rental_id < 0:
            raise IdMustBePositive("The id need to be positive!")
        if RentalService.find_rental_by_id(self, rental_id) is not None:
            raise DuplicateIdError("duplicate id")
        self.__rental_repository.add_new_rental(rental_id, movie_id, client_id, due_day)

    @staticmethod
    def takeSecond(element):
        return element[1]

    def remove_rental(self, rental_id):
        self.__rental_repository.remove_rental(rental_id)

    def most_rented_movies(self):
        movies_and_days_rented = []
        today = date.today()
        current_date = today.strftime('%d/%m/%Y')

        for movie in self.__movie_repository.get_all():
            movie_rented_days = 0
            for rental in self.__rental_repository.get_all_rentals():
                if movie.movie_id == rental.movie_id:
                    if rental.returned_day == 'Not returned yet':
                        movie_rented_days += self.__rental_repository.difference_between_two_dates(rental.rented_day, current_date)
                    else:
                        movie_rented_days += self.__rental_repository.difference_between_two_dates(rental.rented_day, rental.returned_day)
            movies_and_days_rented.append([movie, movie_rented_days])

        movies_and_days_rented.sort(reverse=True, key=RentalService.takeSecond)
        return movies_and_days_rented

    def most_active_clients(self):
        clients_and_number_of_days_rented = []
        today = date.today()
        current_date = today.strftime('%d/%m/%Y')

        for client in self.__client_repository.get_all():
            client_rent_days = 0
            for rental in self.__rental_repository.get_all_rentals():
                if client.client_id == rental.client_id:
                    if rental.returned_day == 'Not returned yet':
                        client_rent_days += self.__rental_repository.difference_between_two_dates(rental.rented_day, current_date)
                    else:
                        client_rent_days += self.__rental_repository.difference_between_two_dates(rental.rented_day, rental.returned_day)
            clients_and_number_of_days_rented.append([client, client_rent_days])

        clients_and_number_of_days_rented.sort(reverse=True, key=RentalService.takeSecond)

        return clients_and_number_of_days_rented

    def append_rental_(self, rental):
        self.__rental_repository.append_rental(rental)

    def modify_returned_date_(self, rental_id):
        self.__rental_repository.modify_returned_date(rental_id)

    def late_rentals(self):
        rentals_and_numberOfLate_days = []
        today = date.today()
        current_date = today.strftime('%d/%m/%Y')

        for rental in self.__rental_repository.get_all_rentals():
            number_ofDays_late = 0
            if rental.returned_day == 'Not returned yet' and self.__rental_repository.difference_between_two_dates(rental.due_day, current_date) > 0:
                number_ofDays_late += self.__rental_repository.difference_between_two_dates(rental.due_day, current_date)
                rentals_and_numberOfLate_days.append([rental, number_ofDays_late])

        rentals_and_numberOfLate_days.sort(reverse=True, key=RentalService.takeSecond)
        return rentals_and_numberOfLate_days
