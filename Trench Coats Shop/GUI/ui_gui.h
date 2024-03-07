/********************************************************************************
** Form generated from reading UI file 'gui.ui'
**
** Created by: Qt User Interface Compiler version 6.2.4
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_GUI_H
#define UI_GUI_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_gui
{
public:
    QPushButton *undoButton;
    QPushButton *redoButton;
    QWidget *widget;
    QVBoxLayout *verticalLayout_4;
    QLabel *label;
    QLineEdit *filterBox;
    QListWidget *coatsList;
    QVBoxLayout *verticalLayout_3;
    QHBoxLayout *horizontalLayout_2;
    QVBoxLayout *verticalLayout_2;
    QLabel *label_1;
    QLabel *label_2;
    QLabel *label_3;
    QLabel *label_4;
    QLabel *label_5;
    QVBoxLayout *verticalLayout;
    QLineEdit *lineEdit_1;
    QLineEdit *lineEdit_2;
    QLineEdit *lineEdit_3;
    QLineEdit *lineEdit_4;
    QLineEdit *lineEdit_5;
    QHBoxLayout *horizontalLayout_3;
    QPushButton *addButton;
    QPushButton *deleteButton;
    QPushButton *updateButton;
    QPushButton *toShoppingCart;
    QWidget *widget1;
    QVBoxLayout *verticalLayout_7;
    QLabel *label_6;
    QListWidget *ShoppingCart;
    QVBoxLayout *verticalLayout_6;
    QVBoxLayout *verticalLayout_5;
    QLineEdit *total_line;
    QHBoxLayout *horizontalLayout;
    QPushButton *openLinkButton;
    QPushButton *open_save;

    void setupUi(QWidget *gui)
    {
        if (gui->objectName().isEmpty())
            gui->setObjectName(QString::fromUtf8("gui"));
        gui->resize(730, 502);
        undoButton = new QPushButton(gui);
        undoButton->setObjectName(QString::fromUtf8("undoButton"));
        undoButton->setGeometry(QRect(330, 270, 80, 24));
        redoButton = new QPushButton(gui);
        redoButton->setObjectName(QString::fromUtf8("redoButton"));
        redoButton->setGeometry(QRect(330, 300, 80, 24));
        widget = new QWidget(gui);
        widget->setObjectName(QString::fromUtf8("widget"));
        widget->setGeometry(QRect(11, 11, 292, 481));
        verticalLayout_4 = new QVBoxLayout(widget);
        verticalLayout_4->setObjectName(QString::fromUtf8("verticalLayout_4"));
        verticalLayout_4->setContentsMargins(0, 0, 0, 0);
        label = new QLabel(widget);
        label->setObjectName(QString::fromUtf8("label"));
        QFont font;
        font.setFamilies({QString::fromUtf8("Segoe UI Historic")});
        font.setPointSize(12);
        label->setFont(font);

        verticalLayout_4->addWidget(label);

        filterBox = new QLineEdit(widget);
        filterBox->setObjectName(QString::fromUtf8("filterBox"));
        filterBox->setFont(font);

        verticalLayout_4->addWidget(filterBox);

        coatsList = new QListWidget(widget);
        coatsList->setObjectName(QString::fromUtf8("coatsList"));
        QFont font1;
        font1.setFamilies({QString::fromUtf8("Sylfaen")});
        font1.setPointSize(12);
        coatsList->setFont(font1);

        verticalLayout_4->addWidget(coatsList);

        verticalLayout_3 = new QVBoxLayout();
        verticalLayout_3->setObjectName(QString::fromUtf8("verticalLayout_3"));
        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setObjectName(QString::fromUtf8("verticalLayout_2"));
        label_1 = new QLabel(widget);
        label_1->setObjectName(QString::fromUtf8("label_1"));
        label_1->setFont(font);

        verticalLayout_2->addWidget(label_1);

        label_2 = new QLabel(widget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setFont(font);

        verticalLayout_2->addWidget(label_2);

        label_3 = new QLabel(widget);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setFont(font);

        verticalLayout_2->addWidget(label_3);

        label_4 = new QLabel(widget);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setFont(font);

        verticalLayout_2->addWidget(label_4);

        label_5 = new QLabel(widget);
        label_5->setObjectName(QString::fromUtf8("label_5"));
        label_5->setFont(font);

        verticalLayout_2->addWidget(label_5);


        horizontalLayout_2->addLayout(verticalLayout_2);

        verticalLayout = new QVBoxLayout();
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        lineEdit_1 = new QLineEdit(widget);
        lineEdit_1->setObjectName(QString::fromUtf8("lineEdit_1"));
        lineEdit_1->setFont(font);

        verticalLayout->addWidget(lineEdit_1);

        lineEdit_2 = new QLineEdit(widget);
        lineEdit_2->setObjectName(QString::fromUtf8("lineEdit_2"));
        lineEdit_2->setFont(font);

        verticalLayout->addWidget(lineEdit_2);

        lineEdit_3 = new QLineEdit(widget);
        lineEdit_3->setObjectName(QString::fromUtf8("lineEdit_3"));
        lineEdit_3->setFont(font);

        verticalLayout->addWidget(lineEdit_3);

        lineEdit_4 = new QLineEdit(widget);
        lineEdit_4->setObjectName(QString::fromUtf8("lineEdit_4"));
        lineEdit_4->setFont(font);

        verticalLayout->addWidget(lineEdit_4);

        lineEdit_5 = new QLineEdit(widget);
        lineEdit_5->setObjectName(QString::fromUtf8("lineEdit_5"));
        lineEdit_5->setFont(font);

        verticalLayout->addWidget(lineEdit_5);


        horizontalLayout_2->addLayout(verticalLayout);


        verticalLayout_3->addLayout(horizontalLayout_2);

        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setObjectName(QString::fromUtf8("horizontalLayout_3"));
        addButton = new QPushButton(widget);
        addButton->setObjectName(QString::fromUtf8("addButton"));
        addButton->setEnabled(true);
        addButton->setFont(font);

        horizontalLayout_3->addWidget(addButton);

        deleteButton = new QPushButton(widget);
        deleteButton->setObjectName(QString::fromUtf8("deleteButton"));
        deleteButton->setFont(font);

        horizontalLayout_3->addWidget(deleteButton);

        updateButton = new QPushButton(widget);
        updateButton->setObjectName(QString::fromUtf8("updateButton"));
        updateButton->setFont(font);

        horizontalLayout_3->addWidget(updateButton);


        verticalLayout_3->addLayout(horizontalLayout_3);


        verticalLayout_4->addLayout(verticalLayout_3);

        toShoppingCart = new QPushButton(gui);
        toShoppingCart->setObjectName(QString::fromUtf8("toShoppingCart"));
        toShoppingCart->setGeometry(QRect(309, 234, 115, 30));
        toShoppingCart->setFont(font);
        widget1 = new QWidget(gui);
        widget1->setObjectName(QString::fromUtf8("widget1"));
        widget1->setGeometry(QRect(430, 10, 286, 481));
        verticalLayout_7 = new QVBoxLayout(widget1);
        verticalLayout_7->setObjectName(QString::fromUtf8("verticalLayout_7"));
        verticalLayout_7->setContentsMargins(0, 0, 0, 0);
        label_6 = new QLabel(widget1);
        label_6->setObjectName(QString::fromUtf8("label_6"));
        QFont font2;
        font2.setFamilies({QString::fromUtf8("Snap ITC")});
        font2.setPointSize(14);
        label_6->setFont(font2);
        label_6->setAlignment(Qt::AlignCenter);

        verticalLayout_7->addWidget(label_6);

        ShoppingCart = new QListWidget(widget1);
        ShoppingCart->setObjectName(QString::fromUtf8("ShoppingCart"));
        ShoppingCart->setFont(font1);

        verticalLayout_7->addWidget(ShoppingCart);

        verticalLayout_6 = new QVBoxLayout();
        verticalLayout_6->setObjectName(QString::fromUtf8("verticalLayout_6"));
        verticalLayout_5 = new QVBoxLayout();
        verticalLayout_5->setObjectName(QString::fromUtf8("verticalLayout_5"));
        total_line = new QLineEdit(widget1);
        total_line->setObjectName(QString::fromUtf8("total_line"));
        total_line->setFont(font2);

        verticalLayout_5->addWidget(total_line);


        verticalLayout_6->addLayout(verticalLayout_5);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        openLinkButton = new QPushButton(widget1);
        openLinkButton->setObjectName(QString::fromUtf8("openLinkButton"));
        openLinkButton->setFont(font);

        horizontalLayout->addWidget(openLinkButton);

        open_save = new QPushButton(widget1);
        open_save->setObjectName(QString::fromUtf8("open_save"));
        open_save->setFont(font);

        horizontalLayout->addWidget(open_save);


        verticalLayout_6->addLayout(horizontalLayout);


        verticalLayout_7->addLayout(verticalLayout_6);


        retranslateUi(gui);

        QMetaObject::connectSlotsByName(gui);
    } // setupUi

    void retranslateUi(QWidget *gui)
    {
        gui->setWindowTitle(QCoreApplication::translate("gui", "gui", nullptr));
        undoButton->setText(QCoreApplication::translate("gui", "Undo", nullptr));
        redoButton->setText(QCoreApplication::translate("gui", "Redo", nullptr));
        label->setText(QCoreApplication::translate("gui", "Filter by size...", nullptr));
        filterBox->setText(QString());
        label_1->setText(QCoreApplication::translate("gui", "Size:", nullptr));
        label_2->setText(QCoreApplication::translate("gui", "Color:", nullptr));
        label_3->setText(QCoreApplication::translate("gui", "Price:", nullptr));
        label_4->setText(QCoreApplication::translate("gui", "Quantity", nullptr));
        label_5->setText(QCoreApplication::translate("gui", "Photo Link:", nullptr));
        addButton->setText(QCoreApplication::translate("gui", "ADD Coat", nullptr));
        deleteButton->setText(QCoreApplication::translate("gui", "DELETE Coat", nullptr));
        updateButton->setText(QCoreApplication::translate("gui", "UPDATE Coat", nullptr));
        toShoppingCart->setText(QCoreApplication::translate("gui", "ADD coat to list", nullptr));
        label_6->setText(QCoreApplication::translate("gui", "Shopping List", nullptr));
        openLinkButton->setText(QCoreApplication::translate("gui", "Open link", nullptr));
        open_save->setText(QCoreApplication::translate("gui", "Open Shopping List in App", nullptr));
    } // retranslateUi

};

namespace Ui {
    class gui: public Ui_gui {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_GUI_H
