#pragma once
#include "Domain/coat.h"
#include "DynamicVector/dynamic_vector.h"
#include <vector>
//#include "undo_redo/undo_redo.h"
#include "Domain/operation.h"

class Repository //: public Undo
{
private:
    std::vector<Coat> coats;
    std::string filename;

public:
    Repository() = default;
    explicit Repository(std::string);
    std::vector<Coat> filterCoatsGUI(std::vector<Coat> coatss, const int size);

    void addCoat(Coat coat);
    /*
     * Check if the coat already exists, otherwise insert in the container
     * input: Coat entity
     */
    void deleteCoat(int pos);
    /*
     * Check if the coat exists and remove it from the container
     * input: Coat entity
     */
    void updateCoat(int pos,double new_price, int new_quantity, std::string new_photo);
    /*
     * Check if the coat exists and update it in the container
     * input: Coat entity
     */
//    __gnu_cxx::__normal_iterator<Coat *, std::vector<Coat>>
//    findCoat(Coat coat);
    int findCoat(int size, std::string colour, double price);
    /*
     * Check if the coat exist in container
     * input: the size, the colour and the price
     * return: the position where the Coat was found, -1 otherwise
     */

    std::vector<Coat> getCoats() const {return coats; } // return all the coats

    Coat& operator[](int index);

private:
    void readFromFile();
    void writeToFile();
    std::vector<Operation> operations;
    std::vector<Operation> redo_operations;

public:
    void addOperationUndo(Operation op);
    std::vector<Operation> getRepoOperations();
    void undo();
    void redo();

};