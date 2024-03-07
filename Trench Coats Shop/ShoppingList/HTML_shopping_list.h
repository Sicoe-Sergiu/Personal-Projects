#pragma once
#include "shopping_list.h"

class HTMLShoppingList : public ShoppingBasket
{
public:
    HTMLShoppingList();
    ~HTMLShoppingList();

    void SaveToFile() override;
    void OpenInApp() override;
};
