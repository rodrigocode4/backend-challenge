CREATE TABLE vehicle (
  id INT IDENTITY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  brand VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL
);

CREATE TABLE position (
  id INT IDENTITY,
  vehicleId INT NOT NULL,
  datetime BIGINT NOT NULL,
  latitude REAL NOT NULL,
  longitude REAL NOT NULL,
  ignition BOOLEAN NOT NULL,
  hodometro INT NOT NULL,
  address VARCHAR(255) NOT NULL,
  FOREIGN KEY (vehicleId) REFERENCES vehicle (id)
);
