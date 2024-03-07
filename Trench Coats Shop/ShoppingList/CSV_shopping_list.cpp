#include <fstream>
#include "CSV_shopping_list.h"

CSVShoppingList::CSVShoppingList() = default;
CSVShoppingList::~CSVShoppingList() = default;

void CSVShoppingList::SaveToFile()
{
    std::ofstream f(R"(C:\Users\sergi\CLionProjects\a8-9-916-Sicoe-Sergiu\files\shopping_list.csv)");
    for(const auto& coat : this->coats)
        f << coat;
    f.close();
}

void CSVShoppingList::OpenInApp()
{
    system("notepad.exe C:\\Users\\sergi\\CLionProjects\\a8-9-916-Sicoe-Sergiu\\files\\shopping_list.csv");
}