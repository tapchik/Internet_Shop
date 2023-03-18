# Generated by Django 4.1.7 on 2023-03-18 11:24

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('api_app', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='cartitem',
            name='product_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='api_app.product'),
        ),
        migrations.AlterField(
            model_name='cartitem',
            name='product_qty',
            field=models.PositiveSmallIntegerField(),
        ),
        migrations.AlterField(
            model_name='cartitem',
            name='session_id',
            field=models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, to='api_app.cart'),
        ),
    ]
