from django.contrib.auth.models import AbstractUser
from django.db import models

class CustomUser(AbstractUser):
    # Ajoutez vos champs personnalisés ici, si nécessaire
    pass

    # Résolution des conflits sur les groupes et les permissions
    groups = models.ManyToManyField(
        'auth.Group',
        related_name='customuser_set',  # Modification du related_name
        blank=True
    )
    
    user_permissions = models.ManyToManyField(
        'auth.Permission',
        related_name='customuser_permissions',  # Modification du related_name
        blank=True
    )

