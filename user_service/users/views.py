from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from .models import CustomUser
from django.contrib.auth.hashers import make_password
from dapr.clients import DaprClient
from django.http import JsonResponse
import json
class UserView(APIView):
    def get(self, request):
        users = CustomUser.objects.all().values('id', 'username', 'email')
        return Response(users, status=status.HTTP_200_OK)

    def post(self, request):
        data = request.data
        user = CustomUser.objects.create(
            username=data['username'],
            email=data['email'],
            password=make_password(data['password'])
        )
        return Response({"message": "User created"}, status=status.HTTP_201_CREATED)
    
    def subscribe_user_order(request):
        with DaprClient() as dapr_client:
            topic = 'venteTopic'
            pubsub_name = 'pubsub'
            result = dapr_client.subscribe(pubsub_name, topic)
            for order in result:
                print('Received order:', order)
            return JsonResponse({'status': 'Order received successfully'})