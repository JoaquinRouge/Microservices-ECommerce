# 🛒 Backend E-Commerce Microservicios

Este proyecto consiste en el backend de una aplicación E-Commerce construida bajo una arquitectura de microservicios. Permite gestionar usuarios, productos, órdenes de compra y simular pagos. Su diseño está orientado a la escalabilidad, resiliencia y buenas prácticas del desarrollo moderno en Java.

---

## 📌 Tabla de Contenidos

- [Descripción](#descripción)
- [Tecnologías utilizadas](#tecnologías-utilizadas)
- [Características principales](#características-principales)
- [Endpoints](#endpoints)
- [Instalación y ejecución](#instalación-y-ejecución)
- [Autor](#autor)
- [Licencia](#licencia)

---

## 📖 Descripción

Este backend permite:

- Registrar y autenticar usuarios.
- Administrar productos (crear, actualizar, eliminar, buscar por marca).
- Crear y consultar órdenes de compra.
- Simular pagos asociados a órdenes existentes.

Cada módulo se encuentra separado como microservicio y se comunican entre sí mediante **Feign** y están orquestados por **Eureka Server** y un **API Gateway**.

---

## ⚙️ Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Cloud Netflix**
  - Eureka Server (Service Discovery)
  - API Gateway (Routing)
  - Feign Client (Comunicación entre servicios)
  - Circuit Breaker (Resiliencia)
- **MySQL**
- **Maven**

---

## 🚀 Características principales

- ✅ Gestión completa de usuarios (registro, login, CRUD)
- ✅ Gestión de productos con filtrado por marca
- ✅ Órdenes de compra por usuario o fecha
- ✅ Simulación de pago con control de errores
- ✅ Arquitectura de microservicios distribuida
- ✅ Password hashing con `BCryptPasswordEncoder`
- ✅ Control de excepciones

---

## 📡 Endpoints

### 🔐 User Service

| Método | Endpoint                  | Descripción                            |
|--------|---------------------------|----------------------------------------|
| GET    | `/user`                   | Obtener todos los usuarios             |
| GET    | `/user/id/{id}`           | Obtener usuario por ID                 |
| POST   | `/user/create`            | Crear nuevo usuario                    |
| POST   | `/user/login`             | Login con email y contraseña           |
| DELETE | `/user/delete/{id}`       | Eliminar usuario                       |
| PUT    | `/user/update`            | Actualizar datos del usuario           |
| PUT    | `/user/admin/{id}`        | Asignar rol de administrador           |

---

### 📦 Product Service

| Método | Endpoint                          | Descripción                        |
|--------|-----------------------------------|------------------------------------|
| GET    | `/product`                        | Obtener todos los productos        |
| GET    | `/product/brand/{brand}`          | Buscar productos por marca         |
| POST   | `/product/create`                 | Crear nuevo producto               |
| DELETE | `/product/delete/{id}`            | Eliminar producto                  |
| PUT    | `/product/update`                 | Actualizar datos de un producto    |

---

### 📋 Order Service

| Método | Endpoint                         | Descripción                                |
|--------|----------------------------------|--------------------------------------------|
| GET    | `/order`                         | Obtener todas las órdenes                  |
| GET    | `/order/{id}`                    | Obtener orden por ID                       |
| GET    | `/order/date/{date}`            | Obtener órdenes por fecha (`yyyy-MM-dd`)   |
| GET    | `/order/user/{userId}`           | Obtener órdenes de un usuario              |
| POST   | `/order/create`                  | Crear nueva orden                          |
| DELETE | `/order/delete/{id}`             | Eliminar orden                             |
| PUT    | `/order/update`                  | Actualizar orden                           |

---

### 💳 Payment Service

| Método | Endpoint               | Descripción                     |
|--------|------------------------|---------------------------------|
| POST   | `/payment/{orderId}`   | Simular pago de una orden       |

---

## ⚙️ Instalación y ejecución

### 🧱 Requisitos

- JDK 17+
- Maven 3+
- MySQL (configurado en `application.properties`)

### 🔧 Clonación

```bash
git clone https://github.com/tu-usuario/backend-ecommerce-microservicios.git
