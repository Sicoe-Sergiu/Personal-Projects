#include <iostream>
#include <fstream>
#include "UserInterface/user_interface.h"
#include "ShoppingList/CSV_shopping_list.h"
#include "ShoppingList/HTML_shopping_list.h"
#include <QApplication>
#include <QPushButton>
#include "GUI/gui.h"
#include "GUI/csv_html_GUI/csv_html_gui.h"

int main(int argc, char* argv[])
{

    QApplication a{ argc, argv };
    Repository repo { R"(C:\Users\sergi\CLionProjects\a11-12-916-Sicoe-Sergiu\files\coats.txt)" };

    csv_html_GUI start_GUI;
    start_GUI.show();

    QApplication::exec();

    ShoppingBasket* shopping_list;
    if(start_GUI.csvOrHtml())
    {
        shopping_list = new CSVShoppingList();
        shopping_list->setType("cvs");
    }
    else
    {
        shopping_list = new HTMLShoppingList();
        shopping_list->setType("html");
    }

    Service service{repo, shopping_list};
    gui gui_app{service};

    gui_app.show();

    return QApplication::exec();
}
