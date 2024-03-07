#pragma once
#include "shopping_list.h"

class CSVShoppingList : public ShoppingBasket
{
public:
    CSVShoppingList();
    ~CSVShoppingList();

    void SaveToFile() override;
    void OpenInApp() override;
};
