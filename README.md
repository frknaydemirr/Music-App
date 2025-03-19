# Müzik Yönetim Sistemi
Kullanıcıların müzik dinleyebileceği, çalma listeleri oluşturabileceği ve Premium kullanıcıları takip edebileceği bir masaüstü uygulamasıdır. 
Kullanıcı yönetimi, müzik çalma listeleri ve bir yönetici paneli gibi modülleri içerir. Nesne yönelimli programlama (OOP) 
prensiplerine göre tasarlanan bu proje, veritabanı normalizasyonu ve ilişkisel veritabanı yönetimi kavramlarını uygular.

## Veritabanı Diyagramı
![Ekran görüntüsü 2025-03-09 235040](https://github.com/user-attachments/assets/a2638046-56b3-4044-9ec5-d631e1ae6009)

## Loglama ve Hata Kontrol Mekanizmaları

Projeye entegre edilen loglama ve hata kontrol mekanizmaları sayesinde, uygulamanın çalışma sürecinde meydana gelen olaylar, hata durumları ve veri işlemleri detaylı bir şekilde takip edilebilmektedir.

İlişkisel veritabanı işlemlerinde gerçekleşen tüm önemli olaylar kaydedilmekte olup, özellikle silme (DELETE), ekleme (INSERT) ve güncelleme (UPDATE) gibi kritik işlemler loglanarak sistemin güvenilirliği
artırılmaktadır. Silme işlemlerinde, ilgili bağımlı kayıtların da başarıyla kaldırıldığını doğrulayan log mesajları oluşturulmaktadır.

Bağlantı sorunları, veri tutarsızlıkları ve işlem hataları, loglar üzerinden analiz edilerek hata ayıklama süreci kolaylaştırılmaktadır. Hata logları, belirli bir formatta saklanarak, sistemde meydana
gelen beklenmeyen durumların incelenmesine olanak tanımaktadır.

Ayrıca, veritabanı işlemlerinin sorunsuz ve tutarlı bir şekilde gerçekleşmesini sağlamak için transaction yapısı kullanılmıştır. Bu sayede, ilişkili kayıtların senkronize çalışması sağlanarak veri
bütünlüğü korunmuş ve olası hatalara karşı hızlı müdahale imkânı sunulmuştur.

![image](https://github.com/user-attachments/assets/80d6b685-4260-4d15-81e8-1b11ddcbd4dd)

![image](https://github.com/user-attachments/assets/42a8ab95-03b5-4349-9ba0-4b338c6d8f45)


![image](https://github.com/user-attachments/assets/98dc99f9-28cb-4ef9-b648-592ce7cafb8d)


