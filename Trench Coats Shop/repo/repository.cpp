#include "repository.h"
#include <algorithm>
#include <fstream>
#include <utility>
#include <cwchar>
#include "exceptions_and_validators/exceptions.h"
#include "Domain/operation.h"

Repository::Repository(std::string filename)
{
    this->filename = std::move(filename);
    this->readFromFile();
}

std::vector<Coat> Repository::filterCoatsGUI(std::vector<Coat> coatss, const int size)
{
    std::vector<Coat> new_coats;

    for (const auto coat : coatss)
    {
        if(coat.getSize() == size)
            new_coats.push_back(coat);
    }

    return coats;

}

void Repository::addOperationUndo(Operation op)
{
    this->operations.push_back(op);
}

std::vector<Operation> Repository::getRepoOperations()
{
    return this->operations;
}

void Repository::addCoat(const Coat coat)
{
    int pos = this->findCoat(coat.getSize(), coat.getColor(), coat.getPrice());
    if(pos == -1)
    {
        this->coats.push_back(coat);
        this->writeToFile();
    }
    else
        throw DuplicateCoat();

    Operation op {ADD, coat, coat};
    addOperationUndo(op);

}

void Repository::deleteCoat(const int pos)
{

    auto iter = this->coats.begin();
    std::advance(iter, pos);

    if(pos < 0 || pos >= this->coats.size())
        throw IncorrectPosition();
    else
    {
        Operation op {DELETE, this->coats[pos], this->coats[pos]};
        addOperationUndo(op);

        this->coats.erase(iter);
        this->writeToFile();
    }
}

void Repository::updateCoat(const int pos,const double new_price, const int new_quantity, const std::string new_photo)
{

    auto iter = this->coats.begin();
    std::advance(iter, pos);

    Coat old_coat;
    old_coat = this->coats[pos];

    if (pos < 0 || pos >= this->coats.size())
        throw IncorrectPosition();
    else
    {
        this->coats[pos].setPrice(new_price);
        this->coats[pos].setQuantity(new_quantity);
        this->coats[pos].setPhoto(new_photo);

        this->writeToFile();
    }
    Operation op {UPDATE, this->coats[pos], old_coat};
    addOperationUndo(op);


}

int Repository::findCoat(const int size, const std::string colour, const double price)
{
    Coat coat;
    for (int i = 0; i < coats.size(); ++i)
    {
        coat = this->coats[i];
        if(coat.getSize() == size && coat.getColor() == colour && coat.getPrice() == price)
            return i;
    }
    return -1;
}

void Repository::readFromFile()
{
    std::ifstream file(this->filename);

    if(!file.is_open())
        throw FileExceptions("This file could not be opened!");
    Coat coat;
    while(file >> coat)
        this->coats.push_back(coat);
    file.close();

}
void Repository::writeToFile()
{
    std::ofstream file(this->filename);
    if(!file.is_open())
        throw FileExceptions("The file could not be opened!");
    for(const auto& coat : this->coats)
        file << coat;
    file.close();
}

Coat& Repository::operator[](int index) {
    return this->coats[index];
}

// UNDO _______ REDO #######################################################

void Repository::undo()
{
    if (this->operations.size() == 0)
        throw std::exception();

    Operation operation = this->operations[this->operations.size() - 1];


    if (operation.getType() == ADD)
    {

        deleteCoat(findCoat(operation.getOpCoat().getSize(), operation.getOpCoat().getColor(), operation.getOpCoat().getPrice()));
        this->operations.pop_back();
    }
    else if ( operation.getType() == DELETE)
    {
        addCoat(operation.getOpCoat());
        this->operations.pop_back();

    }
    else if (operation.getType() == UPDATE)
    {
        int pos = findCoat(operation.getOpCoat().getSize(), operation.getOpCoat().getColor(), operation.getOpCoat().getPrice());
        updateCoat(pos, operation.getAdditional().getPrice(), operation.getAdditional().getQuantity(), operation.getAdditional().getPhoto());
        this->operations.pop_back();

    }
}

void Repository::redo()
{
    if (this->redo_operations.size() == 0)
        throw std::exception();

    Operation operation = this->redo_operations[this->redo_operations.size() - 1];


    if (operation.getType() == ADD)
    {
        Operation op {DELETE, operation.getOpCoat(), operation.getOpCoat()};
        deleteCoat(findCoat(operation.getOpCoat().getSize(), operation.getOpCoat().getColor(), operation.getOpCoat().getPrice()));
        this->redo_operations.pop_back();
    }
    else if ( operation.getType() == DELETE)
    {
        addCoat(operation.getOpCoat());
        this->redo_operations.pop_back();

    }
    else if (operation.getType() == UPDATE)
    {
        int pos = findCoat(operation.getOpCoat().getSize(), operation.getOpCoat().getColor(), operation.getOpCoat().getPrice());
        updateCoat(pos, operation.getAdditional().getPrice(), operation.getAdditional().getQuantity(), operation.getAdditional().getPhoto());
        this->redo_operations.pop_back();

    }
}
