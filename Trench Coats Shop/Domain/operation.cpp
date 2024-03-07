#include "operation.h"

Operation::Operation(operationType op_type, Coat op_coat, Coat additional)
{
    this->op_type = op_type;
    this->op_coat = op_coat;
    this->additional = additional;
}