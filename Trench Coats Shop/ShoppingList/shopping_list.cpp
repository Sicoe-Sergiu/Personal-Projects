#include "shopping_list.h"

ShoppingBasket::ShoppingBasket()
{
    this->current = 0;
    this->total = 0;
}

void ShoppingBasket::add(const Coat coat)
{
    this->total += coat.getPrice();
    this->coats.push_back(coat);
}

int ShoppingBasket::getTotal()
{
    return this->total;
}

Coat ShoppingBasket::getCurrentCoat()
{
    return this->coats[this->current];
}

Coat ShoppingBasket::findCoatBySize(const int size)
{
    for (auto & coat : this->coats)
    {
        if(coat.getSize() == size)
            return coat;
    }
    Coat c;
    return c;
}

void ShoppingBasket::next()
{
    if(this->current + 1 == this->coats.size())
    {
        this->current = 0;
        return;
    }
    this->current++;
}

bool ShoppingBasket::isEmpty()
{
    return this->coats.size() == 0;
}

void ShoppingBasket::setCoat(const std::vector<Coat>& newCoats)
{
    this->coats = newCoats;
}
