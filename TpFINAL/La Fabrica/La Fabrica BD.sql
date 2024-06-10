CREATE TABLE `Pedido` (
  `id` integer PRIMARY KEY,
  `create` timestamp,
  `state` varchar(255) DEFAULT 'Produciendo' COMMENT 'Completo, Suspendido, Produciendo',
  `delete` bool
);

CREATE TABLE `OrdenProduccion` (
  `id` integer PRIMARY KEY,
  `pedido` integer,
  `dateCreate` timestamp,
  `dateComplete` timestamp,
  `productoId` integer[],
  `state` bool
);

CREATE TABLE `Producto` (
  `id` integer PRIMARY KEY,
  `name` varchar(255),
  `stock` integer,
  `productosRelacionados` integer[]
);

CREATE TABLE `Formula` (
  `id` integer PRIMARY KEY,
  `materialesID` integer[],
  `cantidadID` integer[]
);

CREATE TABLE `MateriaPrima` (
  `id` integer PRIMARY KEY,
  `stock` integer
);

ALTER TABLE `OrdenProduccion` ADD FOREIGN KEY (`pedido`) REFERENCES `Pedido` (`id`);

ALTER TABLE `Producto` ADD FOREIGN KEY (`id`) REFERENCES `OrdenProduccion` (`productoId`);

ALTER TABLE `Formula` ADD FOREIGN KEY (`id`) REFERENCES `Producto` (`stock`);

ALTER TABLE `Producto` ADD FOREIGN KEY (`stock`) REFERENCES `Producto` (`productosRelacionados`);

ALTER TABLE `MateriaPrima` ADD FOREIGN KEY (`id`) REFERENCES `Formula` (`materialesID`);

ALTER TABLE `MateriaPrima` ADD FOREIGN KEY (`id`) REFERENCES `Formula` (`cantidadID`);
