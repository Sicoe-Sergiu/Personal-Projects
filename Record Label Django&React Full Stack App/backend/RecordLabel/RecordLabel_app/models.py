from django.db import models
from datetime import timedelta
from datetime import date
from django.core.validators import MaxValueValidator, MinValueValidator

class EventFounder(models.Model):
    name = models.CharField(max_length=50)
    rating = models.FloatField(validators=[MinValueValidator(0.0), MaxValueValidator(5.0)])
    email = models.EmailField()
    phone = models.CharField(max_length=15)

    def __str__(self):
        return self.name


class Dj(models.Model):
    AVAIABLE_NICK = [
        ('Priku', 'Priku'),
        ('Prichindel', 'Prichindel'),
        ('PetreInspirescu', 'Petre Inspirescu'),
        ('RQZ', 'RQZ'),
        ('Sepp', 'Sepp'),
        ('NuZau', 'Nu Zau'),
        ('Cosmjn', 'Cosmjn'),
        ('Lizz', 'LIZZ'),
        ('DanAndrei', 'DanAndrei'),
        ('Cap', 'Cap'),
        ('Sublee', 'Sublee')
    ]

    nick_name = models.CharField(blank=True, choices=AVAIABLE_NICK, max_length=50, unique=True)
    full_name = models.CharField(max_length=100)
    nationality = models.CharField(max_length=50)
    fee = models.IntegerField()
    rating = models.FloatField(validators=[MinValueValidator(0.0), MaxValueValidator(5.0)])

    def __str__(self):
        return self.nick_name


class Event(models.Model):
    founder = models.ForeignKey(EventFounder, on_delete=models.CASCADE, related_name='events')
    location = models.CharField(max_length=150)
    start_date = models.DateField(validators=[MinValueValidator(limit_value=date.today())])
    end_date = models.DateField(validators=[MinValueValidator(limit_value=date.today())])
    capacity = models.IntegerField()
    access_fee = models.FloatField()

    def __str__(self):
        return self.founder.name + ' - ' + self.start_date.__str__()


class DjSchedule(models.Model):
    dj = models.ForeignKey(Dj, on_delete=models.CASCADE, related_name='line_up')
    event = models.ForeignKey(Event, on_delete=models.CASCADE, related_name='event_schedule')
    date = models.DateField(validators=[MinValueValidator(limit_value=date.today())])
    hour = models.TimeField()
    set_length = models.DurationField(default=timedelta)

    def __str__(self):
        return self.dj.nick_name + " at " + self.event.founder.name + ' - ' + self.event.start_date.__str__()
