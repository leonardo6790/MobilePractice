# 🧪 Guía Completa de Pruebas con Postman
## Backend - Aplicación Mobile - Spring Boot 3.2.0

---

## 📌 Información Base

**URL Base:** `http://localhost:8080/api`

**Base de Datos:** `app_mobile_db` (MySQL)

**Usuarios de Prueba:**
- **Admin:** username: `admin` | password: `admin123`
- **Coordinador:** username: `coordinador` | password: `coord123`

---

## 1️⃣ AUTENTICACIÓN

### 🔐 Login - Obtener Token JWT

**Método:** `POST`  
**URL:** `{{base_url}}/auth/login`

#### Request Body (JSON):
```json
{
  "username": "admin",
  "password": "admin123"
}
```

#### Response Esperado (200 OK):
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTczMTkwNjMzNiwiZXhwIjoxNzMxOTkyNzM2fQ.XXXXXXXXXXXXXXX",
  "user": {
    "id": 1,
    "username": "admin",
    "email": "admin@app.com",
    "role": "ADMIN",
    "active": true,
    "createdAt": "2025-11-18T01:18:36.456654"
  }
}
```

⚠️ **IMPORTANTE:** Copia el valor del campo `token`. Lo necesitarás en todas las siguientes peticiones en el header:
```
Authorization: Bearer <tu_token_aqui>
```

---

## 2️⃣ CATEGORÍAS

### 📋 GET - Obtener todas las categorías

**Método:** `GET`  
**URL:** `{{base_url}}/categories`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Headers Requeridos:
```
Authorization: Bearer <tu_token>
Content-Type: application/json
```

#### Response Esperado (200 OK):
```json
[
  {
    "id": 1,
    "name": "Electrónica",
    "description": "Productos electrónicos",
    "active": true
  },
  {
    "id": 2,
    "name": "Ropa y Accesorios",
    "description": "Prendas de vestir",
    "active": true
  }
]
```

---

### 🔍 GET - Obtener categoría por ID

**Método:** `GET`  
**URL:** `{{base_url}}/categories/{id}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Parámetro de URL:
- `id` (Long): ID de la categoría a consultar. Ejemplo: `1`

#### Response Esperado (200 OK):
```json
{
  "id": 1,
  "name": "Electrónica",
  "description": "Productos electrónicos",
  "active": true
}
```

---

### ➕ POST - Crear nueva categoría

**Método:** `POST`  
**URL:** `{{base_url}}/categories`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Headers Requeridos:
```
Authorization: Bearer <tu_token>
Content-Type: application/json
```

#### Request Body (JSON):
```json
{
  "name": "Ropa y Accesorios",
  "description": "Prendas de vestir y complementos",
  "active": true
}
```

#### Response Esperado (200 OK):
```json
{
  "id": 2,
  "name": "Ropa y Accesorios",
  "description": "Prendas de vestir y complementos",
  "active": true
}
```

---

### ✏️ PUT - Actualizar categoría

**Método:** `PUT`  
**URL:** `{{base_url}}/categories/{id}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Parámetro de URL:
- `id` (Long): ID de la categoría a actualizar

#### Request Body (JSON):
```json
{
  "name": "Electrónica - Premium",
  "description": "Productos electrónicos de calidad",
  "active": true
}
```

#### Response Esperado (200 OK):
```json
{
  "id": 1,
  "name": "Electrónica - Premium",
  "description": "Productos electrónicos de calidad",
  "active": true
}
```

---

### 🗑️ DELETE - Eliminar categoría

**Método:** `DELETE`  
**URL:** `{{base_url}}/categories/{id}`  
**Autenticación:** ✅ Requerida (ADMIN SOLO)

#### Parámetro de URL:
- `id` (Long): ID de la categoría a eliminar

#### Response Esperado (200 OK):
```json
{
  "message": "Categoria eliminada exitosamente"
}
```

---

## 3️⃣ SUBCATEGORÍAS

### 📋 GET - Obtener todas las subcategorías

**Método:** `GET`  
**URL:** `{{base_url}}/subcategories`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

---

### 🔍 GET - Obtener subcategorías por categoría

**Método:** `GET`  
**URL:** `{{base_url}}/subcategories/category/{categoryId}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Parámetro de URL:
- `categoryId` (Long): ID de la categoría padre. Ejemplo: `1`

---

### 🔍 GET - Obtener subcategoría por ID

**Método:** `GET`  
**URL:** `{{base_url}}/subcategories/{id}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

---

### ➕ POST - Crear nueva subcategoría

**Método:** `POST`  
**URL:** `{{base_url}}/subcategories`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Headers Requeridos:
```
Authorization: Bearer <tu_token>
Content-Type: application/json
```

#### Request Body (JSON):
```json
{
  "name": "Laptops",
  "description": "Computadoras portátiles",
  "categoryId": 1,
  "active": true
}
```

⚠️ **IMPORTANTE:** 
- El `categoryId` debe ser un ID válido de categoría existente
- Primero crea categorías con POST /categories
- Luego usa esos IDs para crear subcategorías

#### Response Esperado (200 OK):
```json
{
  "id": 1,
  "name": "Laptops",
  "description": "Computadoras portátiles",
  "active": true
}
```

---

### ✏️ PUT - Actualizar subcategoría

**Método:** `PUT`  
**URL:** `{{base_url}}/subcategories/{id}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Request Body (JSON):
```json
{
  "name": "Laptops Gaming",
  "description": "Computadoras portátiles para gaming",
  "categoryId": 1,
  "active": true
}
```

---

### 🗑️ DELETE - Eliminar subcategoría

**Método:** `DELETE`  
**URL:** `{{base_url}}/subcategories/{id}`  
**Autenticación:** ✅ Requerida (ADMIN SOLO)

---

## 4️⃣ PRODUCTOS

### 📋 GET - Obtener todos los productos

**Método:** `GET`  
**URL:** `{{base_url}}/products`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

---

### 🔍 GET - Obtener productos por categoría

**Método:** `GET`  
**URL:** `{{base_url}}/products/category/{categoryId}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Parámetro de URL:
- `categoryId` (Long): ID de la categoría. Ejemplo: `1`

---

### 🔍 GET - Obtener productos por subcategoría

**Método:** `GET`  
**URL:** `{{base_url}}/products/subcategory/{subcategoryId}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Parámetro de URL:
- `subcategoryId` (Long): ID de la subcategoría. Ejemplo: `1`

---

### 🔍 GET - Obtener producto por ID

**Método:** `GET`  
**URL:** `{{base_url}}/products/{id}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

---

### ➕ POST - Crear nuevo producto

**Método:** `POST`  
**URL:** `{{base_url}}/products`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Request Body (JSON):
```json
{
  "name": "Laptop Dell XPS 13",
  "description": "Laptop ultradelgada de 13 pulgadas con procesador Intel i7",
  "price": 1299.99,
  "stock": 15,
  "categoryId": 1,
  "subcategoryId": 1,
  "active": true
}
```

---

### ✏️ PUT - Actualizar producto

**Método:** `PUT`  
**URL:** `{{base_url}}/products/{id}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Request Body (JSON):
```json
{
  "name": "Laptop Dell XPS 13 Plus",
  "description": "Laptop ultradelgada última generación",
  "price": 1399.99,
  "stock": 20,
  "categoryId": 1,
  "subcategoryId": 1,
  "active": true
}
```

---

### 🗑️ DELETE - Eliminar producto

**Método:** `DELETE`  
**URL:** `{{base_url}}/products/{id}`  
**Autenticación:** ✅ Requerida (ADMIN SOLO)

#### Response Esperado (200 OK):
```json
{
  "message": "Producto eliminado exitosamente"
}
```

---

## 5️⃣ USUARIOS

### 📋 GET - Obtener todos los usuarios

**Método:** `GET`  
**URL:** `{{base_url}}/users`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Response Esperado (200 OK):
```json
[
  {
    "id": 1,
    "username": "admin",
    "email": "admin@app.com",
    "role": "ADMIN",
    "active": true,
    "createdAt": "2025-11-18T01:18:36"
  },
  {
    "id": 2,
    "username": "coordinador",
    "email": "coordinador@app.com",
    "role": "COORDINADOR",
    "active": true,
    "createdAt": "2025-11-18T01:18:36"
  }
]
```

---

### 🔍 GET - Obtener usuario por ID

**Método:** `GET`  
**URL:** `{{base_url}}/users/{id}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Parámetro de URL:
- `id` (Long): ID del usuario. Ejemplo: `1`

---

### ➕ POST - Crear nuevo usuario

**Método:** `POST`  
**URL:** `{{base_url}}/users`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Request Body (JSON):
```json
{
  "username": "nuevo_usuario",
  "email": "usuario@app.com",
  "password": "MiPassword123!",
  "role": "COORDINADOR",
  "active": true
}
```

#### Response Esperado (200 OK):
```json
{
  "id": 3,
  "username": "nuevo_usuario",
  "email": "usuario@app.com",
  "role": "COORDINADOR",
  "active": true,
  "createdAt": "2025-11-18T01:20:00"
}
```

---

### ✏️ PUT - Actualizar usuario

**Método:** `PUT`  
**URL:** `{{base_url}}/users/{id}`  
**Autenticación:** ✅ Requerida (ADMIN o COORDINADOR)

#### Request Body (JSON):
```json
{
  "username": "usuario_actualizado",
  "email": "usuario_nuevo@app.com",
  "role": "COORDINADOR",
  "active": true
}
```

---

### 🗑️ DELETE - Eliminar usuario

**Método:** `DELETE`  
**URL:** `{{base_url}}/users/{id}`  
**Autenticación:** ✅ Requerida (ADMIN SOLO)

#### Response Esperado (200 OK):
```json
{
  "message": "Usuario eliminado exitosamente"
}
```

---

## 📋 CONFIGURACIÓN EN POSTMAN

### Paso 1: Crear una Colección
1. En Postman, haz clic en **"+"** para crear una nueva colección
2. Nómbrala: `App Mobile Backend`
3. En la sección **Authorization**, selecciona **Bearer Token**
4. Ingresa: `{{token}}`

### Paso 2: Crear Ambiente (Environment)
1. Haz clic en el ⚙️ (engranaje) en la esquina superior derecha
2. Selecciona **Manage Environments**
3. Haz clic en **Create**
4. Crea un ambiente llamado `App Mobile` con las siguientes variables:

| Variable | Valor Inicial | Tipo |
|----------|---------------|------|
| `base_url` | `{{base_url}}` | string |
| `token` | (dejar vacío inicialmente) | string |
| `admin_user` | `admin` | string |
| `admin_pass` | `admin123` | string |
| `coordinador_user` | `coordinador` | string |
| `coordinador_pass` | `coord123` | string |

### Paso 3: Usar Variables en Peticiones

Usa la sintaxis `{{variable}}` para referenciar variables en Postman:

```
URL: {{base_url}}/categories
Authorization: Bearer {{token}}
```

### Paso 4: Automáticamente Actualizar Token

En la petición de login, ve a la pestaña **Tests** y agrega el siguiente script:

```javascript
if (pm.response.code === 200) {
    var jsonData = pm.response.json();
    pm.environment.set("token", jsonData.token);
}
```

---

## ✅ ORDEN RECOMENDADO DE PRUEBAS

### Fase 1: Autenticación (5 minutos)
- [ ] POST /auth/login (con admin)
- [ ] POST /auth/login (con coordinador)

### Fase 2: Crear Datos Base (10 minutos)
- [ ] POST /categories (crear 2-3 categorías)
- [ ] POST /subcategories (crear 3-4 subcategorías)
- [ ] POST /products (crear 4-5 productos)
- [ ] POST /users (crear 1-2 usuarios nuevos)

### Fase 3: Consultar Datos (15 minutos)
- [ ] GET /categories
- [ ] GET /categories/{id}
- [ ] GET /subcategories
- [ ] GET /subcategories/category/{categoryId}
- [ ] GET /products
- [ ] GET /products/{id}
- [ ] GET /products/category/{categoryId}
- [ ] GET /products/subcategory/{subcategoryId}
- [ ] GET /users
- [ ] GET /users/{id}

### Fase 4: Actualizar Datos (10 minutos)
- [ ] PUT /categories/{id}
- [ ] PUT /subcategories/{id}
- [ ] PUT /products/{id}
- [ ] PUT /users/{id}

### Fase 5: Eliminar Datos (5 minutos) - Solo como ADMIN
- [ ] DELETE /products/{id}
- [ ] DELETE /subcategories/{id}
- [ ] DELETE /categories/{id}
- [ ] DELETE /users/{id}

---

## 🆘 SOLUCIÓN DE PROBLEMAS

### ❌ Error: "Connection refused"
**Causa:** El servidor no está corriendo

**Solución:**
1. Verifica que Spring Boot esté ejecutándose en puerto 8080
2. Verifica que MySQL esté corriendo (XAMPP iniciado)
3. Verifica que la base de datos `app_mobile_db` existe

```bash
# En terminal, ejecuta:
cd C:\xampp\htdocs\app\Backend
mvn spring-boot:run
```

---

### ❌ Error: "401 Unauthorized"
**Causa:** Token faltante, inválido o expirado

**Solución:**
1. Verifica que incluyas el header `Authorization: Bearer <token>`
2. Genera un nuevo token (máximo 24 horas de vigencia)
3. Verifica que el formato sea correcto

---

### ❌ Error: "403 Forbidden"
**Causa:** Permisos insuficientes

**Solución:**
1. DELETE solo funciona con rol ADMIN
2. Verifica que tu usuario tenga el rol correcto
3. Usa usuario `admin` para operaciones DELETE

---

### ❌ Error: "Column 'email' cannot be null"
**Causa:** Falta el campo email al crear usuario

**Solución:**
Incluye siempre el campo `email` en el JSON:
```json
{
  "username": "usuario",
  "email": "usuario@app.com",
  "password": "password123",
  "role": "COORDINADOR",
  "active": true
}
```

---

### ❌ Error: "404 Not Found"
**Causa:** El recurso no existe

**Solución:**
1. Verifica que el ID sea correcto
2. Consulta primero con GET para obtener IDs válidos
3. Verifica que hayas creado el recurso antes de intentar consultarlo

---

## 📊 CÓDIGOS DE RESPUESTA HTTP

| Código | Estado | Significado |
|--------|--------|-------------|
| **200** | OK | La petición fue exitosa |
| **201** | Created | El recurso fue creado exitosamente |
| **400** | Bad Request | Error en la solicitud (datos inválidos) |
| **401** | Unauthorized | Se requiere autenticación o token inválido |
| **403** | Forbidden | No tienes permisos para acceder a este recurso |
| **404** | Not Found | El recurso no fue encontrado |
| **500** | Internal Server Error | Error interno del servidor |

---

## 💡 TIPS Y BUENAS PRÁCTICAS

1. **Guarda tu token:** Después del login, copia el token a tu ambiente de Postman
2. **Usa variables:** Facilita las pruebas reutilizando valores
3. **Estructura tu colección:** Agrupa peticiones por recurso
4. **Lee los errores:** Los mensajes de error te ayudan a entender qué pasó
5. **Prueba con ambos roles:** Verifica permisos con ADMIN y COORDINADOR
6. **Usa tests:** Automatiza validaciones en Postman
7. **Documenta:** Agrega descripción a cada petición
8. **Guarda ejemplos:** Guarda respuestas exitosas como referencia

---

## 📝 REFERENCIA RÁPIDA DE HEADERS

```
Content-Type: application/json
Authorization: Bearer {{token}}
```

---

## 🔗 POSTMAN COLLECTION JSON

Importa esta colección en Postman (File > Import):

```json
{
  "info": {
    "name": "App Mobile Backend",
    "description": "API de Aplicación Mobile - Spring Boot 3.2.0"
  },
  "auth": {
    "type": "bearer",
    "bearer": [
      {
        "key": "token",
        "value": "{{token}}",
        "type": "string"
      }
    ]
  }
}
```

---

**Documento generado:** 18 de Noviembre de 2025  
**Backend:** Spring Boot 3.2.0 | Java 17 | MySQL 8.0  
**Estado:** ✅ Completamente Funcional
