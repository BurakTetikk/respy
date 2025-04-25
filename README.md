# 📦 Package Repository API

## 🎯 Proje Amacı

Bu proje, `.rep` uzantılı yazılım paketlerini ve onlara ait `meta.json` bilgilerini bir REST API üzerinden yüklemeyi ve indirmeyi sağlayan basit bir paket deposudur. Paket bilgileri veritabanında (PostgreSQL) tutulur, dosyalar ise FileSystem veya MinIO üzerinde saklanabilir.

---

## 🛠 Kullanılan Teknolojiler

- Java 21
- Spring Boot 3.4.4
- PostgreSQL 15
- Docker & Docker Compose
- MinIO (Object Storage - opsiyonel)
- Maven

---

## 🚀 API'lar

### 📥 Paket Yükleme

```http
POST /{packageName}/{version}

Form-Data:

package = .rep dosyası

meta = meta.json dosyası

```

### 📤 Paket İndirme
```http
GET /{packageName}/{version}/{fileName}

{
  "name": "awesome-package",
  "version": "1.0.0",
  "author": "Burak",
  "dependencies": [
    { "package": "even", "version": "3.4.7" }
  ]
}


```


### 🐳 Docker ile Çalıştırma

```http
Maven build:

mvn clean package -DskipTests

Docker Compose:

docker-compose up --build
```