#include "exceptions.h"

FileExceptions::FileExceptions(std::string msg) : message(std::move(msg)){}
const char * FileExceptions::what()
{
    return message.c_str();
}

RepositoryException::RepositoryException() : exception{} {}
RepositoryException::RepositoryException(std::string  msg) : message{std::move( msg )} {}
const char * RepositoryException::what()
{
    return this->message.c_str();
}

const char * DuplicateCoat::what()
{
    return "There ia another coat like this!!";
}

const char * CoatNotFound::what()
{
    return "Coat not found!";
}


const char * IncorrectPosition::what()
{
    return "Incorrect Position";
}