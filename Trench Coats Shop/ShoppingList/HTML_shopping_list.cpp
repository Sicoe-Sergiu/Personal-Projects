#include "HTML_shopping_list.h"
#include <Windows.h>
#include <shellapi.h>
#include <fstream>

HTMLShoppingList::HTMLShoppingList() = default;


HTMLShoppingList::~HTMLShoppingList() = default;

void HTMLShoppingList::SaveToFile()
{
    std::ofstream f(R"(C:\Users\sergi\CLionProjects\a8-9-916-Sicoe-Sergiu\files\shopping_list.html)");//

    f << "<!DOCTYPE html>" << "\n";
    f << "<html>" << "\n";

    f << "<head>" << "\n";
    f << "<title>Coats List</title>" << "\n";
    f << "</head>" << "\n";

    f << "<body>" << "\n";

    f << "<table border=\"1\">" << "\n";

    f << "<tr>" << "\n";
    f << "<td>Size</td>" << "\n";
    f << "<td>Color</td>" << "\n";
    f << "<td>Price</td>" << "\n";
    f << "<td>Quantity</td>" << "\n";
    f << "<td>Photo Link</td>" << "\n";
    f << "</tr>" << "\n";

    for (const auto& coat : this->coats)
    {
        f << "<tr>" << "\n";

        f << "<td>" << coat.getSize() << "</td>" << "\n";
        f << "<td>" << coat.getColor() << "</td>" << "\n";
        f << "<td>" << coat.getPrice() << "</td>" << "\n";
        f << "<td>" << coat.getQuantity() << "</td>" << "\n";
        f << "<td><a href =" << coat.getPhoto() << ">Link</a></td>";

        f << "</tr>" << "\n";
    }

    f << "</table>" << "\n";

    f << "</body>" << "\n";

    f << "</html>" << "\n";
    f.close();

}

void HTMLShoppingList::OpenInApp()
{
    ShellExecuteA(nullptr, nullptr, "opera.exe", R"(C:\Users\sergi\CLionProjects\a8-9-916-Sicoe-Sergiu\files\shopping_list.html)", nullptr, SW_SHOWMAXIMIZED);
}

