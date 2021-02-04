CREATE TABLE vehicle (
  id INT IDENTITY,
  name VARCHAR(255) NOT NULL,
  description VARCHAR(255) NOT NULL,
  brand VARCHAR(255) NOT NULL,
  model VARCHAR(255) NOT NULL
);

CREATE TABLE position (
  id INT IDENTITY,
  vehicle_id INT NOT NULL,
  datetime BIGINT NOT NULL,
  latitude DOUBLE NOT NULL,
  longitude DOUBLE NOT NULL,
  ignition BOOLEAN NOT NULL,
  hodometro INT NOT NULL,
  address VARCHAR(255) NOT NULL,
  FOREIGN KEY (vehicle_id) REFERENCES vehicle (id)
);
