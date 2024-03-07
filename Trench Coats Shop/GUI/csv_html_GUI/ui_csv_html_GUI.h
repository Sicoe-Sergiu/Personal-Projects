/********************************************************************************
** Form generated from reading UI file 'csv_html_gui.ui'
**
** Created by: Qt User Interface Compiler version 6.2.4
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_CSV_HTML_GUI_H
#define UI_CSV_HTML_GUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_csv_html_GUI
{
public:
    QGridLayout *gridLayout;
    QVBoxLayout *verticalLayout;
    QLabel *label;
    QHBoxLayout *horizontalLayout;
    QPushButton *pushButton;
    QPushButton *pushButton_2;

    void setupUi(QWidget *csv_html_GUI)
    {
        if (csv_html_GUI->objectName().isEmpty())
            csv_html_GUI->setObjectName(QString::fromUtf8("csv_html_GUI"));
        csv_html_GUI->resize(381, 306);
        gridLayout = new QGridLayout(csv_html_GUI);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        label = new QLabel(csv_html_GUI);
        label->setObjectName(QString::fromUtf8("label"));
        QFont font;
        font.setPointSize(14);
        label->setFont(font);
        label->setAlignment(Qt::AlignCenter);

        verticalLayout->addWidget(label);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        pushButton = new QPushButton(csv_html_GUI);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));
        pushButton->setFont(font);

        horizontalLayout->addWidget(pushButton);

        pushButton_2 = new QPushButton(csv_html_GUI);
        pushButton_2->setObjectName(QString::fromUtf8("pushButton_2"));
        pushButton_2->setFont(font);

        horizontalLayout->addWidget(pushButton_2);


        verticalLayout->addLayout(horizontalLayout);


        gridLayout->addLayout(verticalLayout, 0, 0, 1, 1);


        retranslateUi(csv_html_GUI);

        QMetaObject::connectSlotsByName(csv_html_GUI);
    } // setupUi

    void retranslateUi(QWidget *csv_html_GUI)
    {
        csv_html_GUI->setWindowTitle(QCoreApplication::translate("csv_html_GUI", "csv_html_GUI", nullptr));
        label->setText(QCoreApplication::translate("csv_html_GUI", "Open with CSV / HTML ?", nullptr));
        pushButton->setText(QCoreApplication::translate("csv_html_GUI", "CSV", nullptr));
        pushButton_2->setText(QCoreApplication::translate("csv_html_GUI", "HTML", nullptr));
    } // retranslateUi

};

namespace Ui {
    class csv_html_GUI: public Ui_csv_html_GUI {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_CSV_HTML_GUI_H
