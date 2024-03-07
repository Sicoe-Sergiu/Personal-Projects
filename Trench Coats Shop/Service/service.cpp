#include "service.h"
#include "exceptions_and_validators/validator.h"
#include "ShoppingList/CSV_shopping_list.h"
#include "ShoppingList/HTML_shopping_list.h"

Service::Service(const Repository &repository, ShoppingBasket *shoppingBasket)
{

    this->repo = repository;
    this->shopping_basket = shoppingBasket;
}

void Service::undoService()
{
    this->repo.undo();
}

void Service::addCoatService(const int size, const std::string color, const double price, const int quantity, const std::string photo)
{
    Coat coat = Coat(size, color, price, quantity, photo);

    Validator::validate_coat(coat);

    this->repo.addCoat(coat);
}

void Service::deleteCoatService(const int pos)
{
    this->repo.deleteCoat(pos);
}

void Service::updateCoatService(const int pos,const double new_price, const int new_quantity, const std::string new_photo)
{
    this->repo.updateCoat(pos, new_price, new_quantity, new_photo);
}

void Service::saveCart()
{
    this->shopping_basket->SaveToFile();
}

void Service::openInAppCart()
{
    this->shopping_basket->OpenInApp();
}

void Service::addShoppingLIst(const Coat &coat)
{
    this->shopping_basket->add(coat);
}

std::vector<Coat> Service::filterCoats__for_GUI(std::vector<Coat> coats, const int size)
{
    return this->repo.filterCoatsGUI(coats, size);
}

ShoppingBasket* Service::filterCoatsBySize(const int &size, const std::string &type)
{
    std::vector<Coat> coats;
    if(size == -1)
    {
        coats = this->repo.getCoats();
    }
    for (const auto& coat : this->repo.getCoats())
    {
        if(coat.getSize() == size)
            coats.push_back(coat);
    }

    ShoppingBasket *newSL;

    if(type == "csv")
    {
        newSL = new CSVShoppingList();
        newSL->setType("csv");
    }
    else
    {
        newSL = new HTMLShoppingList();
        newSL->setType("html");
    }

    newSL->setCoat(coats);
    return newSL;

}

