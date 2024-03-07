from django.contrib import admin

from .models import Dj, Event, EventFounder, DjSchedule

# Register your models here.
admin.site.register(Dj)
admin.site.register(EventFounder)
admin.site.register(Event)
admin.site.register(DjSchedule)