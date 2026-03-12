-- Seed data for Scooter Sharing App

-- Users
INSERT INTO scooter_user (id, email, free_kilometers) VALUES (1, 'alice@example.com', 2);
INSERT INTO scooter_user (id, email, free_kilometers) VALUES (2, 'bob@example.com', 0);
INSERT INTO scooter_user (id, email, free_kilometers) VALUES (3, 'charlie@example.com', 5);

-- Scooters
INSERT INTO scooter (id, manufacturer_id, model, price_per_kilometer) VALUES (1, 'Xiaomi', 'Mi Electric Scooter Pro 2', 0.2500);
INSERT INTO scooter (id, manufacturer_id, model, price_per_kilometer) VALUES (2, 'Segway', 'Ninebot Max G30', 0.3500);
INSERT INTO scooter (id, manufacturer_id, model, price_per_kilometer) VALUES (3, 'Bird', 'Bird One', 0.1500);

-- Trips (Trip 1-3 are ended, Trip 4 is still open)
INSERT INTO trip (id, user_id, scooter_id, begin_time, end_time, parking_longitude, parking_latitude)
VALUES (1, 1, 1, '2025-02-07T10:46:03', NULL, NULL, NULL);

INSERT INTO trip (id, user_id, scooter_id, begin_time, end_time, parking_longitude, parking_latitude)
VALUES (2, 2, 2, '2025-02-08T09:00:00', '2025-02-08T09:30:00', 16.37208, 48.20849);

INSERT INTO trip (id, user_id, scooter_id, begin_time, end_time, parking_longitude, parking_latitude)
VALUES (3, 1, 3, '2025-03-01T14:00:00', '2025-03-01T14:45:00', 16.39761, 48.19936);

INSERT INTO trip (id, user_id, scooter_id, begin_time, end_time, parking_longitude, parking_latitude)
VALUES (4, 3, 3, '2025-03-15T08:00:00', NULL, NULL, NULL);

-- TripLogs for Trip 1
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (1, 1, '2025-02-07T10:46:03', 16.48055, 48.16812, 12397);
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (2, 1, '2025-02-07T10:48:03', 16.60784, 48.30346, 12663);
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (3, 1, '2025-02-07T10:52:03', 15.85092, 48.21999, 13510);
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (4, 1, '2025-02-07T10:54:03', 15.44802, 48.26622, 14396);

-- TripLogs for Trip 2
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (5, 2, '2025-02-08T09:00:00', 16.37208, 48.20849, 5000);
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (6, 2, '2025-02-08T09:10:00', 16.38000, 48.21000, 6500);
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (7, 2, '2025-02-08T09:20:00', 16.38500, 48.21200, 8200);

-- TripLogs for Trip 3
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (8, 3, '2025-03-01T14:00:00', 16.39761, 48.19936, 20000);
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (9, 3, '2025-03-01T14:15:00', 16.40100, 48.20100, 23500);
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (10, 3, '2025-03-01T14:30:00', 16.40500, 48.20300, 27000);

-- TripLogs for Trip 4
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (11, 4, '2025-03-15T08:00:00', 16.35000, 48.18000, 30000);
INSERT INTO trip_log (id, trip_id, timestamp, longitude, latitude, mileage_in_meters)
VALUES (12, 4, '2025-03-15T08:10:00', 16.36000, 48.19000, 31200);
