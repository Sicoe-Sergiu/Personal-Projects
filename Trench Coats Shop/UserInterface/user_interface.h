#pragma once
#include "Service/service.h"

class UserInterface
{
private:
    Service service;
public:
    UserInterface(const Service s) : service(s) {}
    ~UserInterface() = default;

    void runApplication();
    void runAdminMode();


private:
    static void printMenuApplication();
    static void printMenuAdmin();
    void printWatchMenu();
    void printMenuUser();



    void printAllCoats();
    void addCoatUI();
    void deleteCoatUI();
    void updateCoatUI();


    void printShoppingCart(ShoppingBasket* cart);

    void startWatch();

    void runUserMode();
};