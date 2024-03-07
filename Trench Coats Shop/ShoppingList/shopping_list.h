#pragma once
#include "DynamicVector/dynamic_vector.h"
#include "Domain/coat.h"
#include <vector>

class ShoppingBasket
{
protected:
    std::vector<Coat> coats;
    int current;
    std::string type;

    double total;

public:
    ShoppingBasket();
    ~ShoppingBasket() = default;
    int getSize()const { return this->coats.size(); }
    void add(Coat coat);

    Coat getCurrentCoat();

    Coat findCoatBySize(int size);

    int getTotal();

    void next();

    bool isEmpty();

    void setType(const std::string& typeString){this->type = typeString; }
    std::string getType(){ return this->type; }

    virtual void SaveToFile() = 0;
    virtual void OpenInApp() = 0;

    void setCoat(const std::vector<Coat> &newCoats);
    std::vector<Coat>getCoats(){ return this->coats; }
};