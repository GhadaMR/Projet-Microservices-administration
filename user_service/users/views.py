from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from .models import CustomUser
from django.contrib.auth.hashers import make_password

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
    