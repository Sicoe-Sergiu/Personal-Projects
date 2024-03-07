import datetime
from datetime import date

class DuplicateIdError(Exception):
    pass

class IdMustBePositive(Exception):
    pass

class ThisIdDoesNotExist(Exception):
    pass

class IncorectDataFormat(Exception):
    pass

class DueDateNeedToBeGraterThanTodayDate(Exception):
    pass

class ThisClientCanTRentAMovie(Exception):
    pass

class NothingToUndo(Exception):
    pass
class NothingToRedo(Exception):
    pass

class Validators:
    @staticmethod
    def validate_date_format(date_text):
        try:
            datetime.datetime.strptime(date_text, '%d/%m/%Y')
        except ValueError:
            raise IncorectDataFormat("Incorrect data format, should be DD/MM/YYYY")

    @staticmethod
    def validate_if_due_date_grater_today_date(due_date):

        string_to_list_due_day = list(due_date.split("/"))
        due_day = int(string_to_list_due_day[0])
        due_month = int(string_to_list_due_day[1])
        due_year = int(string_to_list_due_day[2])

        today = date.today()
        current_date = today.strftime('%d/%m/%Y')

        string_to_list_current_day = list(current_date.split("/"))
        current_day = int(string_to_list_current_day[0])
        current_month = int(string_to_list_current_day[1])
        current_year = int(string_to_list_current_day[2])

        due__date = datetime.datetime(due_year, due_month, due_day)
        today__date = datetime.datetime(current_year, current_month, current_day)

        return due__date > today__date
