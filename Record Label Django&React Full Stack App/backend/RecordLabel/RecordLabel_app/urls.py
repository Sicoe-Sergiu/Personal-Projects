from django.urls import path
from rest_framework.urlpatterns import format_suffix_patterns
from .views import *

urlpatterns =[
    path('dj/', DjsList.as_view()),
    path('dj/<int:id>/', DjsList.as_view()),
    path('dj-rating-filter/<int:rating>/', DjRatingFilter.as_view()),

    path('event-founder/', EventFoundersList.as_view()),
    path('event-founder/<int:id>/', EventFoundersList.as_view()),
    path('event-founder/<int:id>/add-event/', EventFoundersList.as_view()),
    path('founder-rating-filter/<int:rating>/', FounderRatingFilter.as_view()),

    path('event/', EventsList.as_view()),
    path('event/<int:id>/', EventsList.as_view()),
    path('event-fee-filter/<int:fee>/', EventFeeFilter.as_view()),

    path('event/filter', Statistics.statistics),
    path('dj-schedule/filter', Statistics2.statistics),

    path('schedule/', DjScheduleList.as_view()),
    path('schedule/<int:id>/', DjScheduleList.as_view()),

    path('dj/<int:dj_id>/event/', DjScheduleList.as_view()),
    path('dj/<int:dj_id>/event/<int:event_id>/', DjScheduleList.as_view()),
    path('event/<int:event_id>/dj/', DjScheduleList.as_view()),
    path('event/<int:event_id>/dj/<int:dj_id>/', DjScheduleList.as_view()),


]

urlpatterns = format_suffix_patterns(urlpatterns)

