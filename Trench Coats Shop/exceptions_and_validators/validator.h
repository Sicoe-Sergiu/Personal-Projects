#pragma once
#include <exception>
#include <string>
#include <vector>
#include "Domain/coat.h"

class Validator
{
public:
    Validator();
    ~Validator();
    static void validate_coat(const Coat& coat);

};


class CoatException : public std::exception
{
private:
    std::vector<std::string> errors;
public:
    std::vector<std::string> getErrors() const;
    explicit CoatException(std::vector<std::string> errors);
};