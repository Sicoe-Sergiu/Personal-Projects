//
// Created by sergi on 5/17/2022.
//

// You may need to build the project (run Qt uic code generator) to get "ui_csv_html_GUI.h" resolved

#include "csv_html_gui.h"
#include "ui_csv_html_GUI.h"


csv_html_GUI::csv_html_GUI(QWidget *parent) :
        QWidget(parent), ui(new Ui::csv_html_GUI) {

    ui->setupUi(this);
    this->connectSignals();
}

csv_html_GUI::~csv_html_GUI()
{
    delete ui;
}

void csv_html_GUI::connectSignals() {

    QObject::connect(this->ui->pushButton, &QPushButton::clicked, this, &csv_html_GUI::pushCVS);
    QObject::connect(this->ui->pushButton_2, &QPushButton::clicked, this, &csv_html_GUI::pushHTML);
}

void csv_html_GUI::pushCVS() {

    this->csv = true;
    csv_html_GUI::close();
}

void csv_html_GUI::pushHTML() {

    this->csv = false;
    csv_html_GUI::close();
}

bool csv_html_GUI::csvOrHtml()
{
    return this->csv;
}


