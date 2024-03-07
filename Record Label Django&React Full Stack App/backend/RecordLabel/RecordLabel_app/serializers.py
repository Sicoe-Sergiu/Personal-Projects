from rest_framework import serializers
from .models import Dj, EventFounder, Event, DjSchedule


class DjSerializer(serializers.ModelSerializer):
    class Meta:
        model = Dj
        fields = '__all__'


class EventFounderSerializer(serializers.ModelSerializer):
    class Meta:
        model = EventFounder
        fields = '__all__'


class EventSerializer(serializers.ModelSerializer):
    founder = EventFounder()
    location = serializers.CharField(max_length=150)
    start_date = serializers.DateField()
    end_date = serializers.DateField()
    capacity = serializers.IntegerField()
    access_fee = serializers.FloatField()

    class Meta:
        model = Event
        fields = '__all__'


class EventSerializerId(serializers.ModelSerializer):
    founder = EventFounder()
    # line_up = serializers.CharField(max_length=200)
    location = serializers.CharField(max_length=150)
    start_date = serializers.DateField()
    end_date = serializers.DateField()
    capacity = serializers.IntegerField()
    access_fee = serializers.FloatField()

    class Meta:
        model = Event
        fields = '__all__'
        depth = 1


class DjScheduleSerializer(serializers.ModelSerializer):
    class Meta:
        model = DjSchedule
        fields = '__all__'


class StatisticsSerializer(serializers.ModelSerializer):
    founder_rating = serializers.IntegerField(read_only=True)

    class Meta:
        model = Event
        fields = ['id', 'founder', 'location', 'start_date', 'end_date', 'capacity', 'access_fee', 'founder_rating']

class StatisticsSerializer2(serializers.ModelSerializer):
    dj_rating = serializers.IntegerField(read_only=True)

    class Meta:
        model = DjSchedule
        fields = ['id', 'dj', 'event', 'date', 'hour', 'set_length', 'dj_rating']

