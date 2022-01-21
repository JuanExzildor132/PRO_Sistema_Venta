SELECT
     cliente.`nombre` AS cliente_nombre,
     cliente.`apaterno` AS cliente_apaterno,
     cliente.`amaterno` AS cliente_amaterno,
     cliente.`ntelefono` AS cliente_ntelefono,
     final.`precioventa` AS final_precioventa,
     producto.`nombre` AS producto_nombre,
     producto.`punitario` AS producto_punitario,
     inventario.`existencia` AS inventario_existencia
FROM
     `cliente` cliente INNER JOIN `final` final ON cliente.`idCliente` = final.`idCliente`
     INNER JOIN `producto` producto ON final.`idProducto` = producto.`idProducto`
     INNER JOIN `inventario` inventario ON final.`idAlmacen` = inventario.`idAlmacen`