# Generated by Django 4.1.7 on 2023-03-18 13:49

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('api_app', '0002_alter_cartitem_product_id_alter_cartitem_product_qty_and_more'),
    ]

    operations = [
        migrations.RenameField(
            model_name='cartitem',
            old_name='product_id',
            new_name='product',
        ),
        migrations.RenameField(
            model_name='cartitem',
            old_name='product_qty',
            new_name='quantity',
        ),
        migrations.RenameField(
            model_name='cartitem',
            old_name='session_id',
            new_name='session',
        ),
        migrations.RenameField(
            model_name='product',
            old_name='product_id',
            new_name='id',
        ),
    ]
