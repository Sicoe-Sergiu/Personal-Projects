#include "validator.h"

Validator::Validator() = default;
Validator::~Validator() = default;

void Validator::validate_coat(const Coat &coat)
{
    std::vector<std::string> errors;
    if(coat.getSize() < 0)
        errors.emplace_back("the size need to be a positive integer!\n");
    if(coat.getColor().empty())
        errors.emplace_back("choose a color\n");
    if(coat.getQuantity() < 0)
        errors.emplace_back("the quantity need to be a positive integer!\n");

    //search for www or http in link

    unsigned long long posWWW = coat.getPhoto().find("www");
    unsigned long long posHTTP = coat.getPhoto().find("http");

    if (posWWW != 0 && posHTTP != 0)
        errors.emplace_back("The youtube source must start with one of the following strings: \"www\" or \"http\"\n");

    if(!errors.empty())
        throw CoatException(errors);
}


CoatException::CoatException(std::vector<std::string> _errors)
{
    this->errors = std::move(_errors);
}

std::vector<std::string> CoatException::getErrors() const
{
    return this->errors;
}