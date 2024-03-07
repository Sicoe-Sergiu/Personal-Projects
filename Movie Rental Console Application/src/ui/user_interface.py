from src.domain.validators import DuplicateIdError, IdMustBePositive, ThisIdDoesNotExist, IncorectDataFormat, \
    Validators, DueDateNeedToBeGraterThanTodayDate, ThisClientCanTRentAMovie, NothingToRedo, NothingToUndo


class UserInterface:
    def __init__(self, movieService, clientService, rentalService, undo_redoService):
        self.__movieService = movieService
        self.__clientService = clientService
        self.__rentalService = rentalService
        self.__undo_redo_service = undo_redoService

    def ui_add_movie(self):
        try:
            movie_id = int(input("  movie id:"))
            title = input("  title:")
            description = input("  description:")
            genre = input("  genre:")
            self.__movieService.add_movie(movie_id, title, description, genre)
            self.__undo_redo_service.add_movie(movie_id, title, description, genre)

        except ValueError:
            print(UserInterface.prRed("movie id must be int!"))
        except DuplicateIdError:
            print(UserInterface.prRed("A movie with this id already exist!"))
        except IdMustBePositive:
            print(UserInterface.prRed("The movie id need to be positive!"))


    def ui_remove_movie(self):
        try:
            movie_id = int(input("  Enter the id from the movie you want to delete:"))
            self.__movieService.remove_movie(movie_id)
            self.__undo_redo_service.remove_movie(movie_id)

        except ValueError:
            print(UserInterface.prRed("Movie id must be int!"))
        except IdMustBePositive:
            print(UserInterface.prRed("Movie id need to be positive!"))
        except ThisIdDoesNotExist:
            print(UserInterface.prRed("There is no movie with this id!"))

    def ui_update_movie(self):
        try:
            movie_id = int(input("  choose the id of the movie you want to update:"))
            self.__movieService.validate_if_id_exist(movie_id)
            title = input("  choose a new title:")
            description = input("  choose a new description:")
            genre = input("  choose a new genre:")
            self.__movieService.update_movie(movie_id, title, description, genre)
            self.__undo_redo_service.update_movie(movie_id)

        except ValueError:
            print(UserInterface.prRed("movie id must be int!"))
        except IdMustBePositive:
            print(UserInterface.prRed("The id need to be positive!"))
        except ThisIdDoesNotExist:
            print(UserInterface.prRed("There is no movie with this id!"))

    def print_all_movies(self):
        movies_list = self.__movieService.get_all_movies()
        id = UserInterface.prCyan("id:")
        title = UserInterface.prCyan("title:")
        description = UserInterface.prCyan("description:")
        genre = UserInterface.prCyan("genre:")

        print("\n########## MOVIES ##########")
        for movie in movies_list:
            print(f"{id} {movie.movie_id}  {title} {movie.title}  {description} {movie.description}  {genre} {movie.genre}")

    def ui_add_client(self):
        try:
            client_id = int(input("  client id:"))
            name = input("  name:")
            self.__clientService.add_client(client_id, name)
            self.__undo_redo_service.add_client(client_id, name)

        except ValueError:
            print(UserInterface.prRed("Client id must be int!"))
        except DuplicateIdError:
            print(UserInterface.prRed("A client with this id already exist!"))
        except IdMustBePositive:
            print(UserInterface.prRed("The client id need to be positive!"))


    def ui_remove_client(self):
        try:
            client_id = int(input("  Enter the client id you want to delete:"))
            self.__clientService.remove_client(client_id)
            self.__undo_redo_service.remove_client(client_id)

        except ValueError:
            print(UserInterface.prRed("Client id must be int!"))
        except IdMustBePositive:
            print(UserInterface.prRed("Client id need to be positive!"))
        except ThisIdDoesNotExist:
            print(UserInterface.prRed("There is no client with this id!"))

    def ui_update_client(self):
        try:
            client_id = int(input("  choose the id of the client you want to update:"))
            self.__clientService.validate_if_id_exist(client_id)
            name = input("  choose a new name:")
            self.__clientService.update_client(client_id, name)
            self.__undo_redo_service.update_client(client_id)

        except ValueError:
            print(UserInterface.prRed("Client id must be int!"))
        except IdMustBePositive:
            print(UserInterface.prRed("The client id need to be positive!"))
        except ThisIdDoesNotExist:
            print(UserInterface.prRed("There is no client with this id!"))

    def print_all_clients(self):
        clients_list = self.__clientService.get_all_clients()
        id = UserInterface.prCyan("id:")
        name = UserInterface.prCyan("name:")

        print("\n########## CLIENTS ##########")
        for client in clients_list:
            print(f"{id} {client.client_id}  {name} {client.name}")

    def print_all_rentals(self):
        rentals_list = self.__rentalService.get_rentals()
        rental_id = UserInterface.prCyan("rental id:")
        movie_id = UserInterface.prCyan("movie id:")
        client_id = UserInterface.prCyan("client id:")
        rented_date = UserInterface.prCyan("rented date:")
        due_date = UserInterface.prCyan("due date:")
        returned_date = UserInterface.prCyan("returned date:")

        print("\n########## RENTALS ##########")
        for rental in rentals_list:
            print(f"{rental_id} {rental.rental_id}  {movie_id} {rental.movie_id}  {client_id} {rental.client_id}  {rented_date} {rental.rented_day}  {due_date} {rental.due_day}  {returned_date} {rental.returned_day}")


    def ui_return_movie(self):
        try:
            rental_id = int(input("Please enter the rental id:"))
            self.__rentalService.return_movie(rental_id)
            self.__undo_redo_service.return_movie(rental_id)

        except ThisIdDoesNotExist:
            print(UserInterface.prRed("There is no rental with this id!"))
        except ValueError:
            print(UserInterface.prRed("Rental id must be int!"))


    @staticmethod
    def prCyan(text):
        return "\033[96m {}\033[00m".format(text)

    @staticmethod
    def prLightPurple(text):
        return "\033[94m {}\033[00m" .format(text)

    @staticmethod
    def prRed(text):
        return "\033[91m {}\033[00m".format(text)

    def ui_rentA_movie(self):
        UserInterface.print_all_movies(self)
        UserInterface.print_all_clients(self)
        UserInterface.print_all_rentals(self)
        print("\n")
        try:
            new_client = input("If you are a new client type *yes* if you're not type *no* : ")
            if new_client == 'yes':
                try:
                    client_id = int(input("  client id:"))
                    name = input("  name:")
                    self.__clientService.add_client(client_id, name)

                    movie_id = int(input("Choose the id from the movie you want to rent: "))
                    self.__movieService.validate_if_id_exist(movie_id)

                    due_day = input("Choose the date by witch the movie must be returned (date format: dd/mm/yyyy): ")
                    Validators.validate_date_format(due_day)
                    self.__rentalService.validate_due_day(due_day)

                    rental_id = int(input("Choose an id for this rental: "))
                    self.__rentalService.rent_a_movie(rental_id, movie_id, client_id, due_day)
                    self.__undo_redo_service.rent_movie(rental_id, movie_id, client_id, due_day)

                except ThisIdDoesNotExist:
                    print(UserInterface.prRed("There is no movie with this id !"))
                except DuplicateIdError:
                    print(UserInterface.prRed("This id is already taken"))
                except ValueError:
                    print(UserInterface.prRed("The id need to be int!"))
                except IncorectDataFormat:
                    print(UserInterface.prRed("Incorrect data format, should be DD/MM/YYYY"))
                except IdMustBePositive:
                    print(UserInterface.prRed("The id need to be positive!"))
                except DueDateNeedToBeGraterThanTodayDate:
                    print(UserInterface.prRed("The due date need to be grater than today date!"))
                except ThisClientCanTRentAMovie:
                    print(UserInterface.prRed("This client can t rent a movie, he have to return another one"))

            elif new_client == 'no':
                try:
                    client_id = int(input("  client id:"))
                    self.__clientService.validate_if_id_exist(client_id)
                    self.__rentalService.validate_if_a_client_can_rent(client_id)

                    movie_id = int(input("Choose the id from the movie you want to rent: "))
                    self.__movieService.validate_if_id_exist(movie_id)

                    due_day = input("Choose the date by witch the movie must be returned (date format: dd/mm/yyyy): ")
                    Validators.validate_date_format(due_day)
                    self.__rentalService.validate_due_day(due_day)

                    rental_id = int(input("Choose an id for this rental: "))
                    self.__rentalService.rent_a_movie(rental_id, movie_id, client_id, due_day)
                    self.__undo_redo_service.rent_movie(rental_id, movie_id, client_id, due_day)

                except ThisIdDoesNotExist:
                    print(UserInterface.prRed("There is no movie with this id !"))
                except DuplicateIdError:
                    print(UserInterface.prRed("This id is already taken"))
                except ValueError:
                    print(UserInterface.prRed("The id need to be int!"))
                except IncorectDataFormat:
                    print(UserInterface.prRed("Incorrect data format, should be DD/MM/YYYY"))
                except IdMustBePositive:
                    print(UserInterface.prRed("The id need to be positive!"))
                except DueDateNeedToBeGraterThanTodayDate:
                    print(UserInterface.prRed("The due date need to be grater than today date!"))
                except ThisClientCanTRentAMovie:
                    print(UserInterface.prRed("This client can t rent a movie, he have to return another one"))
            else:
                raise ValueError
        except ValueError:
            print(UserInterface.prRed("You have to type yes or no!"))

    def ui_search(self):
        key_word = input("Please enter the keyword you want to search for: ")

        matching_movies = self.__movieService.search_movie(key_word)
        matching_clients = self.__clientService.search_client(key_word)

        id_movie = UserInterface.prCyan("id:")
        title = UserInterface.prCyan("title:")
        description = UserInterface.prCyan("description:")
        genre = UserInterface.prCyan("genre:")

        id_client = UserInterface.prCyan("id:")
        name = UserInterface.prCyan("name:")

        print(f"\n -> {len(matching_movies) + len(matching_clients)} results have been found for your search")

        if len(matching_movies) != 0 and len(matching_clients) != 0:
            print("\n########## MATCHING MOVIES ##########")
            for movie in matching_movies:
                print(
                    f"{id_movie} {movie.movie_id}  {title} {movie.title}  {description} {movie.description}  {genre} {movie.genre}")

            print("\n########## MATCHING clients ##########")
            for client in matching_clients:
                print(f"{id_client} {client.client_id}  {name} {client.name} ")
        elif len(matching_movies) != 0:
            print("\n########## MATCHING MOVIES ##########")
            for movie in matching_movies:
                print(f"{id_movie} {movie.movie_id}  {title} {movie.title}  {description} {movie.description}  {genre} {movie.genre}")

        elif len(matching_clients) != 0:
            print("\n########## MATCHING clients ##########")
            for client in matching_clients:
                print(f"{id_client} {client.client_id}  {name} {client.name} ")


    def most_rented_movies(self):
        most_rented_movies_classament = self.__rentalService.most_rented_movies()

        id_movie = UserInterface.prCyan("id:")
        title = UserInterface.prCyan("title:")
        description = UserInterface.prCyan("description:")
        genre = UserInterface.prCyan("genre:")

        print("\n ######### Most rented movies: #########")
        for movie in most_rented_movies_classament:
            print(f"{id_movie} {movie[0].movie_id}  {title} {movie[0].title}  {description} {movie[0].description}  {genre} {movie[0].genre}   -> was rented for {UserInterface.prCyan(movie[1])} days")

    def most_active_clients(self):

        most_active_clients_clasament = self.__rentalService.most_active_clients()

        id_client = UserInterface.prCyan("id:")
        name = UserInterface.prCyan("name:")
        print("\n ######### Most active clients: #########")

        for client in most_active_clients_clasament:
            print(f"{id_client} {client[0].client_id}  {name}{client[0].name}    -> rented movies for {client[1]} days")

    def late_rentals(self):

        the_latest_rentals = self.__rentalService.late_rentals()

        rental_id = UserInterface.prCyan("rental id:")
        movie_id = UserInterface.prCyan("movie id:")
        client_id = UserInterface.prCyan("client id:")
        rented_date = UserInterface.prCyan("rented date:")
        due_date = UserInterface.prCyan("due date:")
        returned_date = UserInterface.prCyan("returned date:")

        print("\n########## Late Rentals: ##########")
        for rental in the_latest_rentals:
            print(f"{rental_id} {rental[0].rental_id}  {movie_id} {rental[0].movie_id}  {client_id} {rental[0].client_id}  {rented_date} {rental[0].rented_day}  {due_date} {rental[0].due_day}  {returned_date} {rental[0].returned_day}  ->this rental is late {rental[1]} days")

    def undo(self):
        try:
            self.__undo_redo_service.undo(self.__movieService, self.__clientService, self.__rentalService)
        except NothingToUndo:
            print(UserInterface.prRed("There is nothing to undo!"))

    def redo(self):
        try:
            self.__undo_redo_service.redo(self.__movieService, self.__clientService, self.__rentalService)
        except NothingToRedo:
            print(UserInterface.prRed("There is nothing to redo!"))

class Console:
    def __init__(self, userInterface):
        self.__user_interface = userInterface

    @staticmethod
    def print_menu():
        print("\n")
        print(UserInterface.prLightPurple(
                "########### MENU########### \n"
                "Movies options: \n"
                "   1: add movie\n"
                "   2: remove movie\n"
                "   3: update movie\n"
                "   4: display all movies\n \n"
                "Clients options: \n"
                "   5: add client\n"
                "   6: remove client\n"
                "   7: update client\n"
                "   8: display all clients\n\n"
                "Rentals options: \n"
                "   9: rent movie\n"
                "  10: return movie\n"
                "  11: display all rentals\n"
                "  12: Search\n"
                "  13: display the most rented movies statistic\n"
                "  14: display the most active clients statistic\n"
                "  15: display the most late rentals statistic \n\n"
                
                "  16: undo \n"
                "  17: redo\n \n"
                
                " x: exit"))

    def run_console(self):
        commands = {
            "1": self.__user_interface.ui_add_movie,
            "2": self.__user_interface.ui_remove_movie,
            "3": self.__user_interface.ui_update_movie,
            "4": self.__user_interface.print_all_movies,
            "5": self.__user_interface.ui_add_client,
            "6": self.__user_interface.ui_remove_client,
            "7": self.__user_interface.ui_update_client,
            "8": self.__user_interface.print_all_clients,
            "9": self.__user_interface.ui_rentA_movie,
            "10": self.__user_interface.ui_return_movie,
            "11": self.__user_interface.print_all_rentals,
            "12": self.__user_interface.ui_search,
            "13": self.__user_interface.most_rented_movies,
            "14": self.__user_interface.most_active_clients,
            "15": self.__user_interface.late_rentals,
            "16": self.__user_interface.undo,
            "17": self.__user_interface.redo

        }

        while True:
            self.print_menu()
            try:
                option = input("  option:")
                if option == "x":
                    break
                commands[option]()
            except KeyError:
                print(UserInterface.prRed(f"Option {option} is not yet implemented"))