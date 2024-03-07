#pragma once
#include <exception>
#include <string>

class FileExceptions : public std::exception
{
protected:
    std::string message;
public:
    explicit FileExceptions(std::string msg);
    virtual const char *what();
};

class RepositoryException : public std::exception
{
protected:
    std::string message;
public:
    RepositoryException();
    explicit RepositoryException(std::string  msg);
    ~RepositoryException() override = default;
    virtual const char* what();
};

class DuplicateCoat : public RepositoryException
{
public:
    const char* what() override;

};

class CoatNotFound : public RepositoryException
{
public:
    const char* what() override;

};


class IncorrectPosition : public RepositoryException
{
public:
    const char* what() override;
};