#pragma once
#include <iostream>

class Coat
{
private:
    int size;
    std::string color;
    double price;
    int quantity;
    std::string photo; // link

public:
    Coat();
    ~Coat() = default;
    Coat(int size, std::string color, double price, int quantity, std::string photo);

    int getSize() const { return size; }
    std::string getColor() const { return color; }
    double getPrice() const { return price; }
    int getQuantity() const { return quantity; }
    std::string getPhoto() const { return photo; }
    void setQuantity(int new_quantity) {this->quantity = new_quantity; }
    void setPhoto(std::string new_photo) {this->photo = new_photo; }
    void setPrice(int new_price) {this->price = new_price; }

    void readLink();

    friend std::istream& operator>>(std::istream& is, Coat& s);
    friend std::ostream& operator<<(std::ostream& os, const Coat& s);

//
//
//    bool operator==(const Coat coat) const
//    {
//        return Coat(this->size, this->color, this->price, this->quantity, this->photo) == coat;
//    }
};
