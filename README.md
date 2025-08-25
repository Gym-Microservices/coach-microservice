# 💪 Coach Microservice

## 📋 Descripción

Microservicio para la gestión de entrenadores del gimnasio (Puerto 8082). Permite registrar, actualizar y consultar información de los entrenadores, incluyendo sus especialidades.

## 🔗 Endpoints

### 👨‍🏫 Gestión de Entrenadores

- `POST /api/coaches` - Registrar un nuevo entrenador
- `GET /api/coaches` - Obtener todos los entrenadores
- `GET /api/coaches/{id}` - Obtener entrenador por ID
- `PUT /api/coaches/{id}` - Actualizar información de un entrenador
- `DELETE /api/coaches/{id}` - Eliminar un entrenador

### 🔍 Consultas Específicas

- `GET /api/coaches/email/{email}` - Buscar entrenador por email
- `GET /api/coaches/specialty/{specialty}` - Obtener entrenadores por especialidad

## 🛠️ Tecnologías

- Spring Boot
- Spring Data JPA
- H2 Database (en memoria)
- Eureka Client
