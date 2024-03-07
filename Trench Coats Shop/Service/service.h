#pragma once
#include "repo/repository.h"
#include "ShoppingList/shopping_list.h"

class Service
{
private:
    Repository repo;
    ShoppingBasket *shopping_basket;

public:
    explicit Service(const Repository &repository, ShoppingBasket *shoppingBasket);

    Repository getRepo() const { return repo; }

    // add, delete, update, get all
    void addCoatService(int size, std::string color, double price, int quantity, std::string photo);
    void deleteCoatService(int pos);
    void updateCoatService(int pos,double new_price, int new_quantity, std::string new_photo);

    std::vector<Coat> getAllCoatsService() const {return this->repo.getCoats(); }

    ShoppingBasket* getShoppingBasket() const { return shopping_basket; }

    void undoService();

    void saveCart();
    void openInAppCart();

    std::vector<Coat> filterCoats__for_GUI(std::vector<Coat> coats, const int size);
    void addShoppingLIst(const Coat& coat);
    ShoppingBasket* filterCoatsBySize(const int& size, const std::string& type);
    std::vector<Coat>getAllShoppingList(){return this->shopping_basket->getCoats(); }
    std::vector<Coat>getAllRepo(){ return this->repo.getCoats(); }
    std::string getType(){ return this->shopping_basket->getType(); }
    int getTotal(){ return this->shopping_basket->getTotal(); }

};