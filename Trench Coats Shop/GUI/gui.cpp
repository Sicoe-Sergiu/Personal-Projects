//
// Created by sergi on 5/16/2022.
//

// You may need to build the project (run Qt uic code generator) to get "ui_gui.h" resolved

#include "gui.h"
#include "ui_gui.h"
#include <stdlib.h>
#include <iomanip>
#include "exceptions_and_validators/validator.h"
#include "exceptions_and_validators/exceptions.h"
#include <QMessageBox>



gui::gui(Service & service, QWidget *parent) : QWidget(parent), ui(new Ui::gui), service(service)
{
    ui->setupUi(this);
    this->connectSignalsAndSlots();
    this->populateList(this->service.getAllRepo());
    this->totalFunction();
}

gui::~gui() {
    delete ui;
}

void gui::populateList(std::vector<Coat> coats)
{
    if (this->ui->coatsList->count() > 0)
        this->ui->coatsList->clear();

    for (auto coat : coats)
    {
        QString itemInList = QString::fromStdString(std::to_string(coat.getSize()) + ", " + coat.getColor() + ", " + std::to_string((int)coat.getPrice()) + ", " +
                                                    std::to_string(coat.getQuantity()) + ", " + coat.getPhoto());
        this->ui->coatsList->addItem(itemInList);
    }

    if(!this->service.getAllRepo().empty())
        this->ui->coatsList->setCurrentRow(0);
}

void gui::connectSignalsAndSlots()
{

    // when the vector of coats is updated - re-populate the list
    QObject::connect(this, &gui::coatsUpdateSignal, this, &gui::populateList);

    //add button connection
    QObject::connect(this->ui->addButton, &QPushButton::clicked, this, &gui::addCoatButtonHandler);

    //connect the add coat signal to add coat slot
    QObject::connect(this, SIGNAL(addCoatSignal(const std::string &, const std::string &, const std::string &, const std::string &, const std::string &)),
                     this, SLOT(addCoatGUI(const std::string &, const std::string &, const std::string &, const std::string &, const std::string &)));

    // add a connection: function listItemChanged() will be called when an item in the list is selected
    QObject::connect(this->ui->coatsList, &QListWidget::itemSelectionChanged, this, [this](){ this->listItemChanged(); });

    //delete button
    QObject::connect(this->ui->deleteButton, &QPushButton::clicked, this, &gui::deleteCoatGUI);

    //update button
    QObject::connect(this->ui->updateButton, &QPushButton::clicked, this, &gui::updateCoatButtonHandler);
    QObject::connect(this, SIGNAL(updateCoatSignal(const int, const std::string &, const std::string &, const std::string &)),
                     this, SLOT(updateCoatGUI(const int, const std::string &, const std::string &, const std::string &)));

    //to shopping list button
    QObject::connect(this->ui->toShoppingCart, &QPushButton::clicked, this, &gui::addToCartGUI);

    // filter by size
    QObject::connect(this->ui->filterBox, &QLineEdit::textChanged, this, &gui::filterCoatsGUI);

    // save/open
    QObject::connect(this->ui->open_save, &QPushButton::clicked, this, &gui::saveAndOpen);

    //open link
    QObject::connect(this->ui->openLinkButton, &QPushButton::clicked, this, &gui::openLink);

    // total
    QObject::connect(this, &gui::totalSignal, this, &gui::totalFunction);

    //undo
    QObject::connect(this->ui->undoButton, &QPushButton::clicked, this, &gui::Undo_GUI);
}

void gui::Undo_GUI()
{
    this->service.undoService();

    emit coatsUpdateSignal(this->service.getAllRepo());
}

void gui::totalFunction()
{
    QString tot;
    tot.append(QString::fromStdString("TOTAL:  "));
    tot.append(QString::fromStdString(std::to_string(this->service.getTotal())));
    tot.append(QString::fromStdString(" $"));

    this->ui->total_line->setText(tot);

}

void gui::openLink()
{
    int index = this->getSelectedIndex();

    if(index < 0 || index >= this->service.getAllRepo().size())
        return;

    Coat coat = this->service.getAllRepo()[index];

    coat.readLink();
}

void gui::filterCoatsGUI()
{

    QString size = this->ui->filterBox->text();

    if(size.isEmpty())
    {
        emit coatsUpdateSignal(this->service.getAllRepo());
        return;
    }

    try
    {
        int int_size = std::stoi(size.toStdString());
        std::vector<Coat> new_coats;

        for (const auto coat : this->service.getAllRepo())
        {
            if(coat.getSize() == int_size)
                new_coats.push_back(coat);
        }

        emit coatsUpdateSignal(new_coats);
    }
    catch(std::invalid_argument& e)
    {
        QMessageBox::critical(this, "ERROR!!", "The size need to be integer!!");
        this->ui->filterBox->clear();
    }


}

void gui::saveAndOpen()
{
    this->service.saveCart();
    this->service.openInAppCart();
}

void gui::addToCartGUI()
{
    int index = this->getSelectedIndex();

    if(index < 0 || index >= this->service.getAllRepo().size())
        return;

    QString size = this->ui->lineEdit_1->text();
    QString color = this->ui->lineEdit_2->text();
    QString price = this->ui->lineEdit_3->text();
    QString quantity = this->ui->lineEdit_4->text();
    QString link = this->ui->lineEdit_5->text();
    QString space = QString::fromStdString(", ");

    this->service.addShoppingLIst(Coat(std::stoi(size.toStdString()), color.toStdString(), std::stod(price.toStdString()), std::stoi(quantity.toStdString()), link.toStdString()));
    QString item;
    item.append(size);
    item.append(space);
    item.append(color);
    item.append(space);
    item.append(price);
    item.append(space);
    item.append(quantity);
    item.append(space);
    item.append(link);

    this->ui->ShoppingCart->addItem(item);

    emit totalSignal();

}

void gui::addCoatGUI(const std::string& size, const std::string& color, const std::string& price, const std::string& quantity, const std::string& photo)
{

    try
    {
        this->service.addCoatService(std::stoi(size), color, std::stod(price), std::stoi(quantity), photo);
    }
    catch(CoatException &e)
    {
        std::string errors;
        for(const auto & string : e.getErrors())
            errors += string;
        QMessageBox::critical(this, "ERROR!!!", QString::fromStdString(errors));

    }
    catch(FileExceptions &e)
    {
        QMessageBox::critical(this, "ERROR!!!", QString::fromStdString(e.what()));
    }
    catch(RepositoryException & e)
    {
        QMessageBox::critical(this, "ERROR!!!", QString::fromStdString(e.what()));
    }
    catch(std::invalid_argument &e)
    {
        QMessageBox::critical(this, "ERROR!!!", "Insert numbers!");
    }

    // emit the signal: the coats were updated

    emit coatsUpdateSignal(this->service.getAllRepo());
}



void gui::addCoatButtonHandler()
{
    QString size = this->ui->lineEdit_1->text();
    QString color = this->ui->lineEdit_2->text();
    QString price = this->ui->lineEdit_3->text();
    QString quantity = this->ui->lineEdit_4->text();
    QString link = this->ui->lineEdit_5->text();

    emit addCoatSignal(size.toStdString(), color.toStdString(), price.toStdString(), quantity.toStdString(), link.toStdString());
}

void gui::updateCoatGUI(const int pos, const std::string& price, const std::string& quantity, const std::string& photo)
{

    try
    {
        this->service.updateCoatService(pos, std::stod(price),std::stoi(quantity) , photo);
    }

    catch(CoatException &e)
    {
        std::string errors;
        for(const auto & string : e.getErrors())
            errors += string;
        QMessageBox::critical(this, "ERROR!!!", QString::fromStdString(errors));

    }
    catch(FileExceptions &e)
    {
        QMessageBox::critical(this, "ERROR!!!", QString::fromStdString(e.what()));
    }
    catch(RepositoryException & e)
    {
        QMessageBox::critical(this, "ERROR!!!", QString::fromStdString(e.what()));
    }
    catch(std::invalid_argument &e)
    {
        QMessageBox::critical(this, "ERROR!!!", "Insert numbers!");
    }

    emit coatsUpdateSignal(this->service.getAllRepo());
}

void gui::updateCoatButtonHandler()
{
    int index = this->getSelectedIndex();

    if(index < 0 || index >= this->service.getAllRepo().size())
        return;

    QString price = this->ui->lineEdit_3->text();
    QString quantity = this->ui->lineEdit_4->text();
    QString link = this->ui->lineEdit_5->text();

    emit updateCoatSignal(index, price.toStdString(), quantity.toStdString(), link.toStdString());


}

int gui::getSelectedIndex()
{
    if(this->ui->coatsList->count() == 0)
        return -1;
    //get the index
    QModelIndexList index = this->ui->coatsList->selectionModel()->selectedIndexes();

    if(index.size() == 0)
    {
        this->ui->lineEdit_1->clear();
        this->ui->lineEdit_2->clear();
        this->ui->lineEdit_3->clear();
        this->ui->lineEdit_4->clear();
        this->ui->lineEdit_5->clear();
        return -1;
    }

    int inx = index.at(0).row();
    return inx;
}

void gui::listItemChanged()
{
    int index = this->getSelectedIndex();
    if(index < 0)
        return;

    if(index >= this->service.getAllRepo().size())
        return;

    Coat coat = this->service.getAllRepo()[index];

    this->ui->lineEdit_1->setText(QString::fromStdString(std::to_string(coat.getSize())));
    this->ui->lineEdit_2->setText(QString::fromStdString(coat.getColor()));
    this->ui->lineEdit_3->setText(QString::fromStdString(std::to_string((int)coat.getPrice())));
    this->ui->lineEdit_4->setText(QString::fromStdString(std::to_string(coat.getQuantity())));
    this->ui->lineEdit_5->setText(QString::fromStdString(coat.getPhoto()));

}

void gui::deleteCoatGUI()
{
    int index = this->getSelectedIndex();

    if(index < 0 || index >= this->service.getAllRepo().size())
        return;

    try
    {
        this->service.deleteCoatService(index);
    }
    catch(CoatException &e)
    {
        std::string errors;
        for(const auto & string : e.getErrors())
            errors += string;
        QMessageBox::critical(this, "ERROR!!!", QString::fromStdString(errors));

    }
    catch(FileExceptions &e)
    {
        QMessageBox::critical(this, "ERROR!!!", QString::fromStdString(e.what()));
    }
    catch(RepositoryException & e)
    {
        QMessageBox::critical(this, "ERROR!!!", QString::fromStdString(e.what()));
    }
    catch(std::invalid_argument &e)
    {
        QMessageBox::critical(this, "ERROR!!!", "Insert numbers!");
    }


    emit coatsUpdateSignal(this->service.getAllRepo());
}