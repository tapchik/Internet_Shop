Superadmin:
Username: max
Password: root

One-time commands: 
1. `python3 -m venv env` - creates `env` folder
2. `. env/bin/activate` - activates venv
3. `pip3 install django` - locally installs django
4. `django-admin startproject my_shop` - creates prject names my_shop
5. `python manage.py startapp api_app`
6. `python manage.py createsuperuser`

To save changes about models:
1. `python manage.py makemigrations api_app`
2. `python manage.py migrate`

To run the server: 
1. `python manage.py runserver`