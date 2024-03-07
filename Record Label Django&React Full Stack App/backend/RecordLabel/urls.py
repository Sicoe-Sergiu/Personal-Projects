
from django.contrib import admin
from django.urls import path, include, re_path
from . import views
from rest_framework_swagger.views import get_swagger_view

from rest_framework import permissions
from drf_yasg.views import get_schema_view
from drf_yasg import openapi

schema_view = get_schema_view(

    openapi.Info(
        title="Jaseci API",
        default_version='v1',
        description="Welcome to the world of Jaseci",
        terms_of_service="https://www.jaseci.org",
        contact=openapi.Contact(email="jason@jaseci.org"),
        license=openapi.License(name="Awesome IP"),
    ),
    public=True,
    permission_classes=(permissions.AllowAny,),
)
urlpatterns = [
    # Four new paths are created here including
    #
    # /spec.json (json spec of API doc)
    # /spec.yaml (yaml spec of API doc)
    # /doc (Our nice pretty Swagger UI view of API doc)
    # /redoc (A pretty Redoc view of API doc)

    re_path(r'^doc(?P<format>\.json|\.yaml)$',
            schema_view.without_ui(cache_timeout=0), name='schema-json'),
    path('doc/', schema_view.with_ui('swagger', cache_timeout=0),
         name='schema-swagger-ui'),
    path('redoc/', schema_view.with_ui('redoc', cache_timeout=0),
         name='schema-redoc'),






    path('api/', include('RecordLabel.RecordLabel_app.urls')),
    path('', views.index, name='index'),
    path('admin/', admin.site.urls),
]
