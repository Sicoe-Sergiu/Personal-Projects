from django.db.models import Max
from rest_framework.decorators import api_view
from rest_framework.views import APIView
from .serializers import *
from rest_framework.response import Response
from rest_framework import status
from django.shortcuts import get_object_or_404
from django.contrib import messages
from django.core.exceptions import ObjectDoesNotExist


class DjRatingFilter(APIView):
    def get(self, request, rating):
        djs = Dj.objects.filter(rating__gt=rating)
        serializer = DjSerializer(djs, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

class FounderRatingFilter(APIView):
    def get(self, request, rating):
        founders = EventFounder.objects.filter(rating__gt=rating)
        serializer = EventFounderSerializer(founders, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

class EventFeeFilter(APIView):
    def get(self, request, fee):
        events = Event.objects.filter(access_fee__lt=fee)
        serializer = EventSerializer(events, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

class DjsList(APIView):
    queryset = Dj.objects.all()
    serializer_class = DjSerializer

    def get(self, request, id=None):
        if id:

            dj = Dj.objects.get(id=id)
            serializer = DjSerializer(dj)
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

        items = Dj.objects.all()
        serializer = DjSerializer(items, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

    def post(self, request):
        serializer = DjSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_201_CREATED)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id=None):
        item = Dj.objects.get(id=id)
        serializer = DjSerializer(item, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id=None):
        item = get_object_or_404(Dj, id=id)
        item.delete()
        return Response({"status": "success", "data": "Item deleted"}, status=status.HTTP_200_OK)

class EventFoundersList(APIView):
    queryset = EventFounder.objects.all()
    serializer_class = EventFounderSerializer

    def get(self, request, id=None):
        try:
            if id:
                event_founder = EventFounder.objects.get(id=id)
                serializer_event_founder = EventFounderSerializer(event_founder)
                serializer_events = EventSerializer(event_founder.events.all(), many=True)

                serialized_event_founder_data =serializer_event_founder.data
                serialized_event_founder_data['events'] = serializer_events.data

                for i in range(len(serialized_event_founder_data['events'])):
                    del serialized_event_founder_data['events'][i]['founder']

                return Response({"status": "success", "data": serialized_event_founder_data}, status=status.HTTP_200_OK)
        except EventFounder.DoesNotExist:
            return Response("status: Fail")

        items = EventFounder.objects.all()
        serializer_event_founder = EventFounderSerializer(items, many=True)
        return Response({"status": "success", "data": serializer_event_founder.data}, status=status.HTTP_200_OK)

    def post(self, request, id=False):

        if id:
            data = request.data
            datastr = data['data']
            datastr = datastr.split(",")
            for datas in datastr:
                cast = int(datas)
                if Event.objects.get(id=cast):
                    serializers = Event.objects.all()

                    Event.objects.create(founder0=EventFounder.objects.get(id=id),
                                             location=serializers.get(id=cast).location,
                                             start_date=serializers.get(id=cast).start_date,
                                             end_date=serializers.get(id=cast).end_date,
                                             capacity=serializers.get(id=cast).capacity,
                                             access_fee=serializers.get(id=cast).access_fee)

            return Response("mere")

        serializer = EventFounderSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_201_CREATED)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id=None):
        item = EventFounder.objects.get(id=id)
        serializer = EventFounderSerializer(item, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id=None):
        item = get_object_or_404(EventFounder, id=id)
        item.delete()
        return Response({"status": "success", "data": "Item deleted"}, status=status.HTTP_200_OK)

class EventsList(APIView):
    queryset = Event.objects.all()
    serializer_class = EventSerializer

    def get(self, request, id=None, ):
        if id:
            event = Event.objects.get(id=id)
            event_serializer = EventSerializerId(event)

            schedule_objects = DjSchedule.objects.all()
            schedule_objects = schedule_objects.filter(event=id)
            entities = []

            for item in schedule_objects:
                entities.append(Dj.objects.get(id=item.dj.id))

            dj_serializer = DjSerializer(entities, many=True)

            data_event_serializer = event_serializer.data
            data_event_serializer['line_up'] = dj_serializer.data
            return Response({"status": "success", "data": data_event_serializer}, status=status.HTTP_200_OK)

        items = Event.objects.all()
        serializer = EventSerializer(items, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

    def post(self, request):
        serializer = EventSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_201_CREATED)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id=None):
        item = Event.objects.get(id=id)
        serializer = EventSerializer(item, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id=None):
        item = get_object_or_404(Event, id=id)
        item.delete()
        return Response({"status": "success", "data": "Item deleted"}, status=status.HTTP_200_OK)

class DjScheduleList(APIView):
    queryset = DjSchedule.objects.all()
    serializer_class = DjScheduleSerializer

    def get(self, request, id=None, dj_id=None, event_id=None):

        items = DjSchedule.objects.all()
        if dj_id:
            items = items.filter(dj=dj_id)
            if event_id:
                items = items.filter(event=event_id)
            entities = []

            for item in items:
                entities.append(Event.objects.get(id=item.event.id))

            serializer = EventSerializer(entities, many=True)
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

        if event_id:
            items = items.filter(event=event_id)
            if dj_id:
                items = items.filter(dj=dj_id)
            entities = []

            for item in items:
                entities.append(Dj.objects.get(id=item.dj.id))
            serializer = DjSerializer(entities, many=True)

            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

        if id:
            schedule = DjSchedule.objects.get(id=id)
            serializer = DjScheduleSerializer(schedule)
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        items = DjSchedule.objects.all()
        serializer = DjScheduleSerializer(items, many=True)
        return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)

    def post(self, request):
        serializer = DjScheduleSerializer(data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_201_CREATED)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def patch(self, request, id=None):
        item = DjSchedule.objects.get(id=id)
        serializer = DjScheduleSerializer(item, data=request.data, partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response({"status": "success", "data": serializer.data}, status=status.HTTP_200_OK)
        else:
            return Response({"status": "error", "data": serializer.errors}, status=status.HTTP_400_BAD_REQUEST)

    def delete(self, request, id=None):
        item = get_object_or_404(DjSchedule, id=id)
        item.delete()
        return Response({"status": "success", "data": "Item deleted"}, status=status.HTTP_200_OK)

class Statistics(APIView):
    @api_view(['GET'])
    def statistics(request):
        # events ordered by the max of the founders ratings

        statistics = Event.objects.annotate(
            founder_rating=Max('founder__rating'),
        ).order_by('-founder_rating')

        serializer = StatisticsSerializer(statistics, many=True)
        return Response(serializer.data)


class Statistics2(APIView):
    @api_view(['GET'])
    def statistics(request):
        # dj ordered by the nr of events

        statistics = DjSchedule.objects.annotate(
            rating_of_the_dj=Max('dj__rating'),
        ).order_by('-rating_of_the_dj')

        serializer = StatisticsSerializer2(statistics, many=True)
        return Response(serializer.data)

