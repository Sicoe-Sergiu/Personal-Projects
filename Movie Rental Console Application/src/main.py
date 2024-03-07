from src.domain.client import Client
from src.domain.movie import Movie
from src.domain.rental import Rental
from src.repository.client_file_repository import ClientFileRepository, ClientFileRepositoryBinary
from src.repository.client_repository import ClientRepository
from src.repository.movie_file_repository import MovieFileRepository, MovieFileRepositoryBinary
from src.repository.movie_repository import MovieRepository
from src.repository.rental_file_repository import RentalFileRepository, RentalFileRepositoryBinary
from src.repository.rental_repository import RentalRepository
from src.services.undo_redo_service import UndoService
from src.services.client_service import ClientService
from src.services.movie_service import MovieService
from src.services.rental_service import RentalService
from src.ui.user_interface import UserInterface, Console

filepath = "settings.properties"
commentCharacter = '#'
separator = "="
properties = {}

with open(filepath, "rt") as file:
    for line in file:
        currentLine: str = line.strip()
        if currentLine and not currentLine.startswith(commentCharacter):
            keyValue = currentLine.split(separator)
            key = keyValue[0].strip()
            value = separator.join(keyValue[1:]).strip().strip('"')
            properties[key] = value


if properties["repository"] == "inmemory":
    movie_repository = MovieRepository()
    client_repository = ClientRepository()
    rental_repository = RentalRepository()

elif properties["repository"] == "textfiles":
    client_repository = ClientFileRepository('client.txt', Client.line_to_client, Client.client_to_line)
    movie_repository = MovieFileRepository('movie.txt', Movie.line_to_movie, Movie.movie_to_line)
    rental_repository = RentalFileRepository('rental.txt',Rental.line_to_rental, Rental.rental_to_line)

elif properties["repository"] == "binaryfiles":
    client_repository = ClientFileRepositoryBinary('client', Client.line_to_client, Client.client_to_line)
    movie_repository = MovieFileRepositoryBinary('movie', Movie.line_to_movie, Movie.movie_to_line)
    rental_repository = RentalFileRepositoryBinary('rental', Rental.line_to_rental, Rental.rental_to_line())

else:
    print("No valid repository type found. Selected \"inmemory\" type.")
    movie_repository = MovieRepository()
    client_repository = ClientRepository()
    rental_repository = RentalRepository()





movie_service = MovieService(movie_repository)
client_service = ClientService(client_repository)
rental_service = RentalService(rental_repository, movie_repository, client_repository)

undo_redo_service = UndoService()

ui = UserInterface(movie_service, client_service, rental_service, undo_redo_service)
console = Console(ui)
console.run_console()