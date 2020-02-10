CREATE TABLE IF NOT EXISTS patient (
    id INT AUTO_INCREMENT PRIMARY KEY,
    patient_name VARCHAR(255) NOT NULL,
	gender CHAR NOT NULL,
    born_date DATE NOT NULL,
    doc_type CHAR NOT NULL,
    doc_number VARCHAR(50) NOT NULL,
    nationality VARCHAR (50) NOT NULL,
    email VARCHAR(200),
    phone_number VARCHAR(20),
    address VARCHAR(50) NOT NULL,
    postal_code INT NOT NULL,
    country VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)  ENGINE=INNODB;