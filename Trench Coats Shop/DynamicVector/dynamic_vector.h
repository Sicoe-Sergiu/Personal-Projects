#pragma once

template<typename TElem>

class DynamicVector
{
private:

    TElem* elements;
    int size{};
    int capacity{};

public:

    explicit DynamicVector(int capacity = 20);

    DynamicVector(const DynamicVector& vector);

    ~DynamicVector();

    void addItem(const TElem& element);

    void deleteItem(const int position);

    void updateItem(const int position, const TElem element);

    int getSize() const;


    int getCapacity() const;

    TElem getElement(const int position) const;

private:
    void resize(int factor = 2);

};

template <typename TElem>
DynamicVector<TElem>::DynamicVector(int capacity)
{
    //constructor for DynamicVector
    this->size = 0;
    this->capacity = capacity;
    this->elements = new TElem[this->capacity];
}

template <typename TElem>
DynamicVector<TElem>::DynamicVector(const DynamicVector<TElem>& vector)
{
    // copy constructor for DynamicVector

    this->size = vector.getSize();
    this->capacity = vector.getCapacity();
    this->elements = new TElem[this->capacity];

    for (int i = 0; i < this->size; ++i)
    {
        this->elements[i] = vector.getElement(i);
    }
}

template <typename TElem>
DynamicVector<TElem>::~DynamicVector()
{
    //destructor for DynamicVector
    delete[] this->elements;
}

template<typename TElem>
void DynamicVector<TElem>::addItem(const TElem& element)
{
    //add an element to DynamicVector
    if(this->size == this->capacity)
        this->resize();
    this->elements[this->size] = element;
    this->size++;

}

template<typename TElem>
void DynamicVector<TElem>::resize(int factor)
{
    //resize the DynamicVector and store the elem in the new one
    this->capacity *= factor;
    TElem* elem = new TElem[this->capacity];

    for (int i = 0; i < this->size; ++i)
    {
        elem[i] = this->elements[i];
    }
    delete[] this->elements;
    this->elements = elem;
}

template<typename TElem>
int DynamicVector<TElem>::getSize()const
{
    //return the size of the vector
    return this->size;
}

template<typename TElem>
int DynamicVector<TElem>::getCapacity()const
{
    //return the capacity
    return this->capacity;
}

template<typename TElem>
void DynamicVector<TElem>::deleteItem(const int position)
{
    //delete an element from a given position
    TElem* elem = new TElem[this->capacity];
    int cnt = 0;

    for (int i = 0; i < this->size; ++i)
    {
        if(cnt == position)
            cnt++;
        elem[i] = this->elements[cnt];
        cnt++;
    }
    delete[] this->elements;
    this->elements = elem;
    this->size--;
}

template<typename TElem>
void DynamicVector<TElem>::updateItem(const int position, const TElem element)
{
    //replace an element from the vector with another
    this->elements[position] = element;
}

template<typename TElem>
TElem DynamicVector<TElem>::getElement(const int position) const
{
    //return an element from a position
    return this->elements[position];
}