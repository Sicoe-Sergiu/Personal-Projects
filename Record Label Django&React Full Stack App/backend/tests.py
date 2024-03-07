from django.test import TestCase
from rest_framework.test import APIClient
from rest_framework import status

from RecordLabel.RecordLabel_app.models import *


class FilterDjViewTestCase(TestCase):

    def setUp(self):
        self.client = APIClient()

        Dj.objects.create(nick_name="Test1", full_name="Test11", nationality="Nat1", fee=111, rating=1.1)
        Dj.objects.create(nick_name="Test2", full_name="Test22", nationality="Nat2", fee=222, rating=2.1)
        Dj.objects.create(nick_name="Test3", full_name="Test33", nationality="Nat3", fee=333, rating=3.1)

    def test_dj_rating_filter(self):
        url = '/dj-rating-filter/2/'
        response = self.client.get(url)
        self.maxDiff = None

        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertDictEqual(response.data, {
            "status": "success",
            "data": [
                {
                    "id": 2,
                    "nick_name": "Test2",
                    "full_name": "Test22",
                    "nationality": "Nat2",
                    "fee": 222,
                    "rating": 2.1

                },
                {
                    "id": 3,
                    "nick_name": "Test3",
                    "full_name": "Test33",
                    "nationality": "Nat3",
                    "fee": 333,
                    "rating": 3.1
                }
            ]
        })


class EventFeeViewTestCase(TestCase):

    def setUp(self):
        EventFounder.objects.create(name='founder1', rating=1.0, email='founder1@f1.com', phone='07111')
        EventFounder.objects.create(name='founder2', rating=2.0, email='founder2@f2.com', phone='07222')
        EventFounder.objects.create(name='founder3', rating=3.0, email='founder3@f3.com', phone='07333')

        Event.objects.create(founder=EventFounder.objects.get(id=1), location='ev1', start_date='2023-07-20',
                             end_date='2023-07-20', capacity=111, access_fee=111)
        Event.objects.create(founder=EventFounder.objects.get(id=2), location='ev2', start_date='2023-07-20',
                             end_date='2023-07-20', capacity=222, access_fee=222)
        Event.objects.create(founder=EventFounder.objects.get(id=3), location='ev3', start_date='2023-07-20',
                             end_date='2023-07-20', capacity=333, access_fee=333)

    def test_event_fee_filter(self):
        url = '/event-fee-filter/200/'
        response = self.client.get(url)

        self.assertEqual(response.status_code, status.HTTP_200_OK)
        self.assertDictEqual(response.data, {
            "status": "success",
            "data": [
                {
                    "id": 1,
                    "location": "ev1",
                    "start_date": "2023-07-20",
                    "end_date": "2023-07-20",
                    "capacity": 111,
                    "access_fee": 111,
                    "founder": 1
                }
            ]
        })
