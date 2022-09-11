CREATE TABLE if NOT EXISTS patient(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    cpf VARCHAR(12) UNIQUE,
    address VARCHAR(255),
    registeredAt TIMESTAMP
);

CREATE TABLE IF NOT EXISTS dentist(
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100),
    lastName VARCHAR(100),
    cro VARCHAR(8) UNIQUE,
    registeredAt TIMESTAMP
);

CREATE TABLE IF NOT EXISTS appointment(
    id INT AUTO_INCREMENT PRIMARY KEY,
    patientId int,
    FOREIGN KEY(patientId) REFERENCES patient(id),
    dentistId int,
    FOREIGN KEY(dentistId) REFERENCES dentist(id),
    appointmentDate DATE,
    registeredAt TIMESTAMP
);
