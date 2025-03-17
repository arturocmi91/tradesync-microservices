--Insertar registros en la tabla base Location
INSERT INTO location (id) VALUES ('LB001');
INSERT INTO location (id) VALUES ('LB002');
INSERT INTO location (id) VALUES ('LB003');
INSERT INTO location (id) VALUES ('LC004');
INSERT INTO location (id) VALUES ('LC005');
INSERT INTO location (id) VALUES ('LC006');
COMMIT;

-- Insertar registros en la tabla Bin
INSERT INTO bin (id, warehouse_section) VALUES ('LB001', 'ZONA-A');
INSERT INTO bin (id, warehouse_section) VALUES ('LB002', 'ZONA-B');
INSERT INTO bin (id, warehouse_section) VALUES ('LB003', 'ZONA-C');


-- Insertar registros en la tabla Container
INSERT INTO container (id, drop_zone) VALUES ('LC004', 'DROP-1');
INSERT INTO container (id, drop_zone) VALUES ('LC005', 'DROP-2');
INSERT INTO container (id, drop_zone) VALUES ('LC006', 'DROP-3');

-- Insertar registros en la tabla Item
INSERT INTO item (barcode, name, brand, description) VALUES ('1234567890123', 'Martillo', 'Stanley', 'Martillo de acero con mango de goma');
INSERT INTO item (barcode, name, brand, description) VALUES ('5463212547885', 'Martillo', 'Botch', 'Martillo de acero con mango de goma');

-- Insertar datos en la relación many-to-many, primero necesitamos inventarios
INSERT INTO inventory (sku, item_id, quantity, status) VALUES ('ST001', 1 , 100, 'SELLABLE');
INSERT INTO inventory (sku, item_id, quantity, status) VALUES ('BC002', 2 , 50, 'SELLABLE');

-- Asignar ubicaciones al inventario en la tabla de relación muchos a muchos
INSERT INTO inventory_location_table (sku, location_id) VALUES ('ST001', 'LC004');
INSERT INTO inventory_location_table (sku, location_id) VALUES ('BC002', 'LC004');