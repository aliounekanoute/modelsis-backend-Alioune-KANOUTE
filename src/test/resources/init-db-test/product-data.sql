INSERT INTO PRODUCT_TYPES 
        (column_id, type_name)
VALUES  ('ddedaa41-85ea-4a38-9db3-001992d33ea0', 'type 1'),
        ('ddedaa41-85ea-4a38-9db3-001992d33ea1', 'type 2');

INSERT INTO PRODUCTS
        (column_id, product_name, type_column_id)
VALUES ('ddedaa41-85ea-4a38-9db3-001992d33ea3', 'product 1', 'ddedaa41-85ea-4a38-9db3-001992d33ea0'),
       ('ddedaa41-85ea-4a38-9db3-001992d33ea4', 'product 2', 'ddedaa41-85ea-4a38-9db3-001992d33ea1');