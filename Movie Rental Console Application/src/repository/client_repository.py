from src.domain.client import Client


class ClientRepository:
    def __init__(self):
        self._clients = []
        self._clients.append(Client(123, 'Marti Vlad'))
        self._clients.append(Client(456, 'Sicoe Sergiu'))
        self._clients.append(Client(554, 'Duda Sonia'))
        self._clients.append(Client(742, 'Halmaciu Christian'))
        self._clients.append(Client(333, 'Moldovan Claudia'))
        self._clients.append(Client(579, 'Gaspar Antonia'))
        self._clients.append(Client(909, 'Nicolau Fabi'))

    def find_by_id(self, client_id):
        for client in self._clients:
            if client.client_id == client_id:
                return client
        return None

    def add_client(self, client_id, name):
        self._clients.append(Client(client_id, name))

    def delete_client_by_id(self, client_id):
        for client in self._clients:
            if client.client_id == client_id:
                self._clients.remove(client)

    def search_by_key_word(self, key_word):
        results = []

        for client in self._clients:
            match_result = False

            search_in_id = key_word.lower() in str(client.client_id).lower()
            search_in_name = key_word.lower() in str(client.name).lower()

            if search_in_id:
                match_result = True
            elif search_in_name:
                match_result = True

            if match_result is True:
                results.append(client)

        return results

    def update_client(self, client_id, name):
        for client in self._clients:
            if client.client_id == client_id:
                client.name = name

    def get_all(self):
        return self._clients
