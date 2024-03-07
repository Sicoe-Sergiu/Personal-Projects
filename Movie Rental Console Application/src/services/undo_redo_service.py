from datetime import date

from src.domain.client import Client
from src.domain.movie import Movie
from src.domain.rental import Rental
from src.domain.validators import NothingToUndo, NothingToRedo
from src.repository.client_repository import ClientRepository
from src.repository.movie_repository import MovieRepository
from src.repository.rental_repository import RentalRepository


class UndoService:
    def __init__(self):
        self.__undo_repository = []
        self.__redo_repository = []
        self.__movie_repository = MovieRepository()
        self.__client_repository = ClientRepository()
        self.__rental_repository = RentalRepository()

    def add_movie(self, movie_id, title, description, genre):
        movie = Movie(movie_id, title, description, genre)
        self.__undo_repository.append(['movie', 'add', movie])

    def remove_movie(self, movie_id):
        movie = self.__movie_repository.find_by_id(movie_id)
        self.__undo_repository.append(['movie', 'remove', movie])

    def update_movie(self, movie_id):
        movie = self.__movie_repository.find_by_id(movie_id)
        self.__undo_repository.append(['movie', 'update', movie])

    def add_client(self, client_id, name):
        client = Client(client_id, name)
        self.__undo_repository.append(['client', 'add', client])

    def remove_client(self, client_id):
        client = self.__client_repository.find_by_id(client_id)
        self.__undo_repository.append(['client', 'remove', client])

    def update_client(self, client_id):
        client = self.__client_repository.find_by_id(client_id)
        self.__undo_repository.append(['client', 'update', client])

    def return_movie(self, rental_id):
        rental = self.__rental_repository.find_rental_by_id(rental_id)
        self.__undo_repository.append(['rental', 'return', rental])

    def rent_movie(self, rental_id, movie_id, client_id, due_day):
        today = date.today()
        current_date = today.strftime('%d/%m/%Y')
        rental = Rental(rental_id, movie_id, client_id, current_date, due_day, 'Not returned yet')
        self.__undo_repository.append(['rental', 'rent', rental])


    def undo(self, movie_service, client_service, rental_service):
        if len(self.__undo_repository) == 0:
            raise NothingToUndo("There is nothing to undo!")

        last_command_to_undo = self.__undo_repository[-1]

        category = last_command_to_undo[0]
        action = last_command_to_undo[1]
        element = last_command_to_undo[2]

        if category == 'movie':
            if action == 'add':
                movie_service.remove_movie(element.movie_id)
                self.__redo_repository.append(['movie', 'remove', element])

            elif action == 'remove':
                movie_service.add_movie(element.movie_id, element.title, element.description, element.genre)
                self.__redo_repository.append(['movie', 'add', element])

            elif action == 'update':
                movie_service.update_movie(element.movie_id, element.title, element.description, element.genre)
                self.__redo_repository.append(['movie', 'update', element])

        elif category == 'client':
            if action == 'add':
                client_service.remove_client(element.client_id)
                self.__redo_repository.append(['client', 'remove', element])

            elif action == 'remove':
                client_service.add_client(element.client_id, element.name)
                self.__redo_repository.append(['client', 'add', element])

            elif action == 'update':
                client_service.update_client(element.client_id, element.name)
                self.__redo_repository.append(['client', 'update', element])

        elif category == 'rental':

            if action == 'return':
                rental_service.modify_returned_date_(element.rental_id)
                self.__redo_repository.append(['rental', 'not_returned', element])

            if action == 'rent':
                rental_service.remove_rental(element.rental_id)
                self.__redo_repository.append(['rental', 'remove', element])

            if action == 'remove':
                rental_service.append_rental_(element)
                self.__redo_repository.append(['rental', 'rent', element])

            if action == 'not_returned':
                rental_service.return_movie(element.rental_id)
                self.__redo_repository.append(['rental', 'return', element])

        self.__undo_repository.pop()

    def redo(self, movie_service, client_service, rental_service):
        if len(self.__redo_repository) == 0:
            raise NothingToRedo("There is nothing to redo!")

        last_command_to_redo = self.__redo_repository[-1]

        category = last_command_to_redo[0]
        action = last_command_to_redo[1]
        element = last_command_to_redo[2]

        if category == 'movie':
            if action == 'add':
                movie_service.remove_movie(element.movie_id)
                self.__undo_repository.append(['movie', 'remove', element])

            elif action == 'remove':
                movie_service.add_movie(element.movie_id, element.title, element.description, element.genre)
                self.__undo_repository.append(['movie', 'add', element])

            elif action == 'update':
                movie_service.update_movie(element.movie_id, element.title, element.description, element.genre)
                self.__undo_repository.append(['movie', 'update', element])

        elif category == 'client':
            if action == 'add':
                client_service.remove_client(element.client_id)
                self.__undo_repository.append(['client', 'remove', element])

            elif action == 'remove':
                client_service.add_client(element.client_id, element.name)
                self.__undo_repository.append(['client', 'add', element])

            elif action == 'update':
                client_service.update_client(element.client_id, element.name)
                self.__undo_repository.append(['client', 'update', element])
        elif category == 'rental':

            if action == 'return':
                rental_service.modify_returned_date_(element.rental_id)
                self.__undo_repository.append(['rental', 'not_returned', element])

            if action == 'rent':
                rental_service.remove_rental(element.rental_id)
                self.__undo_repository.append(['rental', 'remove', element])

            if action == 'remove':
                rental_service.append_rental_(element)
                self.__undo_repository.append(['rental', 'rent', element])

            if action == 'not_returned':
                rental_service.return_movie(element.rental_id)
                self.__undo_repository.append(['rental', 'return', element])

        self.__redo_repository.pop()