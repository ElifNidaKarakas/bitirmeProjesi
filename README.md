# Finans Takip Sistemi - Bitirme Projesi

Bu proje, kişisel finans yönetimi için geliştirilmiş basit ve işlevsel bir web uygulamasıdır. Kullanıcıların gelir ve giderlerini takip etmelerini, kategori bazında raporlamalar yapmalarını sağlar.

---

## Özellikler

- Kullanıcı kaydı ve yönetimi
- Gelir ve gider işlemlerinin eklenmesi, görüntülenmesi
- Kategorilere göre işlemlerin gruplanması
- Aylık ve yıllık finansal raporların oluşturulması
- Hataların merkezi yönetimi (validasyon ve özel exception handling)
- işlem kayıtlarının loglanması

---

## Teknolojiler

- Java 17
- Spring Boot
- Spring Data JPA (Hibernate)
- H2 / MySQL (veritabanı tercihinize göre)
- Lombok
- Jakarta Validation (Bean Validation)
- Maven

---

## Proje Yapısı

- **model**: Veritabanı entity sınıfları (`User`, `Transaction`, `Category`, `TransactionType` enum vb.)
- **repository**: Veri erişim katmanı (Spring Data JPA repository arayüzleri)
- **service**: İş mantığı katmanı (işlem ekleme, kullanıcı yönetimi, raporlama, yedekleme vb.)
- **controller**: REST API endpoint'leri
- **exception**: Özel hata yönetimi sınıfları ve global exception handler
- **report**: Raporlama ve yedekleme servisleri

---

## Kurulum ve Çalıştırma

1. Projeyi klonlayın:

   ```bash
   git clone https://github.com/ElifNidaKarakas/bitirmeProjesi.git
