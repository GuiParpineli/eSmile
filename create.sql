CREATE TABLE if NOT EXISTS patient(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    cpf VARCHAR(12),
    address VARCHAR(255),
    registeredAt TIMESTAMP
);

CREATE TABLE IF NOT EXISTS denstist(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    cro VARCHAR(8),
    registeredAt TIMESTAMP
);

CREATE TABLE IF NOT EXISTS appointment(
    id INT AUTO_INCREMENT PRIMARY KEY,
    patientId int
    CONSTRAINT FK_Pacient FOREIGN KEY(patientId)
    REFERENCES Patient(id),
    dentistId int
    CONSTRAINT FK_Dentist FOREIGN KEY(dentistId)
    REFERENCES Dentist(id),
    appointmentDate DATETIME,
    registeredAt TIMESTAMP
)