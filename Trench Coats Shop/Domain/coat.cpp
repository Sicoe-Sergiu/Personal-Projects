#include "coat.h"
#include <windows.h>
#include <shellapi.h>
#include <vector>
Coat::Coat() : size(0), color(""), price(0), quantity(0), photo(""){}

Coat::Coat(const int size, const std::string color, const double price, const int quantity, const std::string photo)
{
    this->size = size;
    this->color = color;
    this->price = price;
    this->quantity = quantity;
    this->photo = photo;
}

void Coat::readLink()
{
    ShellExecuteA(nullptr, nullptr,"chrome.exe", this->photo.c_str(), nullptr, SW_SHOWMAXIMIZED);
}

std::istream& operator>>(std::istream& is, Coat& coat)
{
    if(is.eof())
        is.setstate(std::ios_base::failbit);
    else
    {
        std::string string;
        std::vector<std::string> out;
        getline(is, string);
        if(!string.empty())
        {
            size_t start;
            size_t end = 0;
            while((start = string.find_first_not_of(',', end)) != std::string::npos)
            {
                end = string.find(',', start);
                out.push_back(string.substr(start, end - start));
            }
            coat.size = stoi(out[0]);
            coat.color = out[1];
            coat.price = stoi(out[2]);
            coat.quantity = stoi(out[3]);
            coat.photo = out[4];
        }
    }
    return is;
}

std::ostream& operator<<(std::ostream& os, const Coat& coat)
{
    os << coat.size <<","<<coat.color<<","<<coat.price<<","<<coat.quantity<<","<<coat.photo<<"\n";
    return os;
}
