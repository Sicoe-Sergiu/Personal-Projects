#include "user_interface.h"
#include "exceptions_and_validators/validator.h"
#include "exceptions_and_validators/exceptions.h"
#include <string>
#include <iostream>
#include <windows.h>

void UserInterface::printAllCoats()
{
    std::vector<Coat> list = this->service.getAllCoatsService();
    Coat coat;

    if(list.size() == 0)
        std::cout<<"there are no coats added !";

    for(int i = 0; i < list.size(); i++)
    {
        coat = list[i];
        std::cout<<i<<") Trench coat with size: "<<coat.getSize()<<", color: "<<coat.getColor()<<", cost: "<<coat.getPrice()<<" dollars, we have: "<<coat.getQuantity()<<" pieces, and you can see the product here: "<<coat.getPhoto()<<"\n";

    }
}

void UserInterface::printMenuApplication()
{
    std::cout << "\nPlease select your mode from: admin / user\n";
    std::cout << "You are: ";
}

void UserInterface::printMenuAdmin()
{
    std::cout << "\nadd - Add a coat\n";
    std::cout << "delete - Delete a coat\n";
    std::cout << "update - Update a coat\n";
    std::cout << "list - List all coat\n\n";
}

void UserInterface:: addCoatUI()
{
    std::string size, color, quantity, photo, price;
    //double price;

    std::cout<<"Insert size:";
    getline(std::cin, size);

    std::cout<<"Insert color:";
    getline(std::cin, color);

    std::cout<<"Insert price:";
    getline(std::cin, price);
    //std::cin>>price;

    std::cout<<"Insert quantity:";
    getline(std::cin, quantity);

    std::cout<<"Insert photo link:";
    getline(std::cin, photo);
    try
    {
        this->service.addCoatService(stoi(size), color, stod(price), stoi(quantity), photo);
    }
    catch(CoatException &e)
    {
        for(const auto & string : e.getErrors())
            std::cout<<string;

    }
    catch(FileExceptions &e)
    {
        std::cout<<e.what()<<"\n";
    }
    catch(RepositoryException & e)
    {
        std::cout<<e.what()<<"\n";
    }
    catch(std::invalid_argument &e)
    {
        std::cout<<"insert numbers\n";
    }

}

void UserInterface:: deleteCoatUI()
{
    std::string pos;

    std::cout<<"Insert position:";
    getline(std::cin, pos);

    try
    {
        this->service.deleteCoatService(stoi(pos));
    }
    catch (CoatException& e)
    {
        for (const auto& string : e.getErrors())
            std::cout << string;
    }
    catch (RepositoryException& e)
    {
        std::cout << e.what() << '\n';
    }
    catch (FileExceptions& e)
    {
        std::cout << e.what() << '\n';
    }
    catch (std::invalid_argument& e)
    {
        std::cout << "Insert numbers\n";
    }


}

void UserInterface:: updateCoatUI()
{
    std::string pos, new_price, new_quantity, new_photo;

    std::cout<<"Insert position: ";
    getline(std::cin, pos);

    std::cout<<"Insert new price: ";
    getline(std::cin, new_price);

    std::cout<<"Insert new quantity: ";
    getline(std::cin, new_quantity);

    std::cout<<"Insert new photo link: ";
    getline(std::cin, new_photo);

    try
    {
        this->service.updateCoatService(stoi(pos), stod(new_price), stoi(new_quantity), new_photo);
    }
    catch (CoatException& e)
    {
        for (const auto& string : e.getErrors())
            std::cout << string;
    }
    catch (RepositoryException& e)
    {
        std::cout << e.what() << '\n';
    }
    catch (FileExceptions& e)
    {
        std::cout << e.what() << '\n';
    }
    catch (std::invalid_argument& e)
    {
        std::cout << "Insert numbers\n";
    }

}

//############################# RUN APPLICATION ###############################

void UserInterface::runApplication()
{
    while(true)
    {
        printMenuApplication();
        std::string command;
        getline(std::cin, command);

        if (command == "exit")
        {
            std::cout<<" Good Bye <3 !!\n";
            break;
        }

        if (command == "admin")
        {
            //system("cls");
            std::cout<<"\nNow you are in admin mode !";
            runAdminMode();

            std::cout<<"You are no more in admin mode ! \n";
            //Sleep(2000);
            //system("cls");
            continue;
        }
        if(command =="user")
        {
            std::cout<<"\nNow you are in user mode! \n";
            runUserMode();
            std::cout << "You are no more in User mode! ^_^\n";
            //Sleep(2000);
            //system("cls");
            continue;
        }
        std::cout<<"!! Invalid mode !! \n";
    }
}

void UserInterface::runAdminMode()
{
    while(true)
    {
        printMenuAdmin();
        std::string command;
        getline(std::cin, command);

        if(command == "exit")
            break;

        if(command == "add")
        {
            addCoatUI();
            std::cout<<"\n";
            continue;
        }

        if(command == "delete")
        {
            deleteCoatUI();
            std::cout<<"\n";
            continue;
        }

        if(command == "update")
        {
            updateCoatUI();
            std::cout<<"\n";
            continue;
        }

        if(command == "list")
        {
            printAllCoats();
            std::cout<<"\n";
            continue;
        }
        std::cout<<"!! Invalid command !!\n";
    }
}

void UserInterface::printWatchMenu()
{
    std::cout << "You can: \n";
    std::cout << "next - go to the next element\n";
    std::cout << "add - add to the shopping cart\n";
    std::cout << "exit\n";
}

void UserInterface::printShoppingCart(ShoppingBasket* cart)
{
    if(cart->isEmpty())
    {
        std::cout<<"\nNo coats in your shopping list !\n";
        return;
    }
    Coat coat;
    int cnt = 0;

    double sum = 0;

    while(!cart->isEmpty())
    {
        coat = cart->getCurrentCoat();
        sum = sum + coat.getPrice();
        std::cout<<"Trench coat with size: "<<coat.getSize()<<", color: "<<coat.getColor()<<", cost: "<<coat.getPrice()<<" dollars, we have: "<<coat.getQuantity()<<" pieces, and you can see the product here: "<<coat.getPhoto()<<"\n";
        cnt++;
        if(cnt == cart->getSize())
            break;
        cart->next();

    }
    std::cout<<"\nThe total price of your basket is: "<<sum<<"\n";
}

void UserInterface::printMenuUser()
{
    std::cout << "watch - See the trench coats by size\n";
    std::cout << "show - See the shopping cart\n";
    std::cout << "save - Save ine shopping list in file\n";
    std::cout << "open - Open the Shopping list in app\n\n";
}

void UserInterface::startWatch()
{
    std::cout<<"Please insert a size:";
    std::string size;
    size = "-1";
    std::getline(std::cin, size);

    int size_1;
    if(size == "")
        size_1 = -1;
    else
        size_1 = stoi(size);

    ShoppingBasket *cart = this->service.filterCoatsBySize(size_1, this->service.getType());

    Coat coat;
    std::string command;

    while(!cart->isEmpty())
    {
        printWatchMenu();
        coat = cart->getCurrentCoat();
        coat.readLink();
        std::cout<<"Trench coat with size: "<<coat.getSize()<<", color: "<<coat.getColor()<<", cost: "<<coat.getPrice()<<" dollars, we have: "<<coat.getQuantity()<<" pieces, and you can see the product here: "<<coat.getPhoto()<<"\n";
        std::getline(std::cin, command);
        if(command == "exit")
            break;
        if(command == "add")
        {
            this->service.addShoppingLIst(coat);
            cart->next();
        }
        if(command == "next")
            cart->next();
    }
    if(cart->isEmpty())
        std::cout<<"There are no Coats !\n";

}

void UserInterface::runUserMode()
{
    while(true)
    {
        printMenuUser();
        std::string command;
        std::getline(std::cin, command);
        if (command == "exit")
            break;
        if (command == "watch")
        {
            startWatch();
            std::cout<<"\n";
            continue;
        }
        else if(command == "show")
        {
            printShoppingCart(this->service.getShoppingBasket());
            std::cout<<"\n";
            continue;
        }
        else if (command == "save")
        {
            this->service.saveCart();
        }
        else if(command == "open")
        {
            this->service.openInAppCart();
        }
        else
            std::cout << "Invalid command!\n\n";

    }
}