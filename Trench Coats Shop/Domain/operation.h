#pragma once
#include "coat.h"

typedef enum
{
    ADD, DELETE, UPDATE
}operationType;

class Operation
{
private:
    operationType op_type;
    Coat op_coat;
    Coat additional;

public:
    Operation() = default;
    explicit Operation(operationType , Coat , Coat );
    operationType getType(){return this->op_type; }
    Coat getOpCoat(){return this->op_coat; }
    Coat getAdditional(){return this->additional; }
};
