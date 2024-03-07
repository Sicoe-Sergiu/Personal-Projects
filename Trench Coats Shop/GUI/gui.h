//
// Created by sergi on 5/16/2022.
//

#ifndef A11_12_916_SICOE_SERGIU_GUI_H
#define A11_12_916_SICOE_SERGIU_GUI_H

#include <QWidget>
#include "../Service/service.h"

QT_BEGIN_NAMESPACE
namespace Ui { class gui; }
QT_END_NAMESPACE

class gui : public QWidget {
Q_OBJECT

public:
    explicit gui(Service& service, QWidget *parent = nullptr);

    ~gui() override;

private:
    Ui::gui *ui;
    Service& service;

    void connectSignalsAndSlots();
    int getSelectedIndex();

    // When an item in the list is clicked, the text boxes get filled with the item's information
    void listItemChanged();

    void populateList(std::vector<Coat> coats);
    void addCoatButtonHandler();
    void deleteCoatGUI();
    void updateCoatButtonHandler();
    void saveAndOpen();
    void filterCoatsGUI();
    void openLink();
    void totalFunction();
    void Undo_GUI();

public slots:
    void addCoatGUI(const std::string& size, const std::string& color, const std::string& price, const std::string& quantity, const std::string& photo);
    void updateCoatGUI(const int pos, const std::string& price, const std::string& quantity, const std::string& photo);
    void addToCartGUI();

signals:
    void totalSignal();
    void coatsUpdateSignal(std::vector<Coat> coats);
    void addCoatSignal(const std::string& size, const std::string& color, const std::string& price, const std::string& quantity, const std::string& photo);
    void updateCoatSignal(const int pos, const std::string& price, const std::string& quantity, const std::string& photo);

};


#endif //A11_12_916_SICOE_SERGIU_GUI_H
