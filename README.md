# Music Management System
A desktop application where users can listen to music, create playlists, and follow Premium users.
It includes modules such as user management, music playlists, and an admin panel.
This project is designed according to object-oriented programming (OOP) principles and applies database normalization and relational database management concepts.

## Database Diagram
![Ekran görüntüsü 2025-03-09 235040](https://github.com/user-attachments/assets/a2638046-56b3-4044-9ec5-d631e1ae6009)

## Logging and Error Handling Mechanisms

Thanks to the integrated logging and error handling mechanisms in the project, events, error situations, and data operations occurring during the application's execution can be monitored in detail.

All important events in relational database operations are recorded, and especially critical operations such as deletion (DELETE), insertion (INSERT), and updating (UPDATE) are logged to enhance the system's reliability.
In deletion operations, log messages are generated to confirm that related dependent records have also been successfully removed.

Connection issues, data inconsistencies, and transaction errors are analyzed through logs, facilitating the debugging process.
Error logs are stored in a specific format, allowing the examination of unexpected situations that occur in the system.

Additionally, a transaction structure has been used to ensure that database operations are carried out smoothly and consistently.
This ensures the synchronization of related records, maintains data integrity, and enables rapid response to potential errors.

![image](https://github.com/user-attachments/assets/80d6b685-4260-4d15-81e8-1b11ddcbd4dd)

![image](https://github.com/user-attachments/assets/42a8ab95-03b5-4349-9ba0-4b338c6d8f45)


![image](https://github.com/user-attachments/assets/98dc99f9-28cb-4ef9-b648-592ce7cafb8d)


