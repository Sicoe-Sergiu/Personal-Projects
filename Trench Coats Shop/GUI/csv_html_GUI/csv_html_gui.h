//
// Created by sergi on 5/17/2022.
//

#ifndef A11_12_916_SICOE_SERGIU_CSV_HTML_GUI_H
#define A11_12_916_SICOE_SERGIU_CSV_HTML_GUI_H

#include <QWidget>


QT_BEGIN_NAMESPACE
namespace Ui { class csv_html_GUI; }
QT_END_NAMESPACE

class csv_html_GUI : public QWidget {
Q_OBJECT
private:
    bool csv;

public:
    explicit csv_html_GUI(QWidget *parent = nullptr);

    ~csv_html_GUI() override;
    bool csvOrHtml();

private:
    Ui::csv_html_GUI *ui;


    void connectSignals();
    void pushCVS();
    void pushHTML();

};


#endif //A11_12_916_SICOE_SERGIU_CSV_HTML_GUI_H
