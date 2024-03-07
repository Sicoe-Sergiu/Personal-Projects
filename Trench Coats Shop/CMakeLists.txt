cmake_minimum_required(VERSION 3.21)
project(a11-12-916-Sicoe-Sergiu)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)
set(CMAKE_AUTOUIC ON)

set(CMAKE_PREFIX_PATH "C:/Qt/6.2.4/mingw_64/lib/cmake")

find_package(Qt6 COMPONENTS
        Core
        Gui
        Widgets
        REQUIRED)
include_directories(.)

add_executable(a11-12-916-Sicoe-Sergiu
        GUI/ui_gui.h
        Domain/coat.cpp
        Domain/coat.h
        DynamicVector/dynamic_vector.cpp
        DynamicVector/dynamic_vector.h
        repo/repository.cpp repo/repository.h
        Service/service.cpp Service/service.h
        UserInterface/user_interface.cpp UserInterface/user_interface.h
        main.cpp
        ShoppingList/shopping_list.cpp ShoppingList/shopping_list.h
        exceptions_and_validators/exceptions.cpp exceptions_and_validators/exceptions.h exceptions_and_validators/validator.cpp exceptions_and_validators/validator.h
        ShoppingList/HTML_shopping_list.cpp ShoppingList/HTML_shopping_list.h ShoppingList/CSV_shopping_list.cpp ShoppingList/CSV_shopping_list.h GUI/gui.cpp GUI/gui.h GUI/gui.ui GUI/csv_html_GUI/csv_html_gui.cpp GUI/csv_html_GUI/csv_html_gui.h GUI/csv_html_GUI/csv_html_gui.ui Domain/operation.cpp Domain/operation.h undo_redo/undo_redo.cpp undo_redo/undo_redo.h)

target_link_libraries(a11-12-916-Sicoe-Sergiu
        Qt::Core
        Qt::Gui
        Qt::Widgets
        )

if (WIN32)
    set(DEBUG_SUFFIX)
    set(QT_INSTALL_PATH "${CMAKE_PREFIX_PATH}")
    if (NOT EXISTS "${QT_INSTALL_PATH}/bin")
        set(QT_INSTALL_PATH "${QT_INSTALL_PATH}/..")
        if (NOT EXISTS "${QT_INSTALL_PATH}/bin")
            set(QT_INSTALL_PATH "${QT_INSTALL_PATH}/..")
        endif ()
    endif ()
    if (EXISTS "${QT_INSTALL_PATH}/plugins/platforms/qwindows${DEBUG_SUFFIX}.dll")
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E make_directory
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>/plugins/platforms/")
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E copy
                "${QT_INSTALL_PATH}/plugins/platforms/qwindows${DEBUG_SUFFIX}.dll"
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>/plugins/platforms/")
    endif ()
    foreach (QT_LIB Core Gui Widgets)
        add_custom_command(TARGET ${PROJECT_NAME} POST_BUILD
                COMMAND ${CMAKE_COMMAND} -E copy
                "${QT_INSTALL_PATH}/bin/Qt6${QT_LIB}${DEBUG_SUFFIX}.dll"
                "$<TARGET_FILE_DIR:${PROJECT_NAME}>")
    endforeach (QT_LIB)
endif ()
