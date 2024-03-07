//#include "undo_redo.h"
//
//Undo::Undo()
//{
//    this->operations = ge
//}
//
//void Undo::undo()
//{
//    if (this->operations.size() == 0)
//        throw std::exception();
//
//    Operation operation = this->operations[this->operations.size() - 1];
//
//
//    if (operation.getType() == ADD)
//    {
//        deleteCoat(findCoat(operation.getOpCoat().getSize(), operation.getOpCoat().getColor(), operation.getOpCoat().getPrice()));
//    }
//    else if ( operation.getType() == DELETE)
//    {
//        addCoat(operation.getOpCoat());
//    }
//    else if (operation.getType() == UPDATE)
//    {
//        int pos = findCoat(operation.getOpCoat().getSize(), operation.getOpCoat().getColor(), operation.getOpCoat().getPrice());
//        updateCoat(pos, operation.getAdditional().getPrice(), operation.getAdditional().getQuantity(), operation.getAdditional().getPhoto());
//
//    }
//}
