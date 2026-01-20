-- ==========================================
-- 1. USERS (Mix of Tiers)
-- ==========================================
INSERT IGNORE INTO users (id, name, email, tier) VALUES (1, 'John Doe', 'john@regular.com', 'REGULAR');
INSERT IGNORE INTO users (id, name, email, tier) VALUES (2, 'Elon Musk', 'elon@vip.com', 'VIP');
INSERT IGNORE INTO users (id, name, email, tier) VALUES (3, 'Taylor Swift', 'taylor@platinum.com', 'PLATINUM');
INSERT IGNORE INTO users (id, name, email, tier) VALUES (4, 'Bill Gates', 'bill@vip.com', 'VIP');
INSERT IGNORE INTO users (id, name, email, tier) VALUES (5, 'Average Joe', 'joe@regular.com', 'REGULAR');
INSERT IGNORE INTO users (id, name, email, tier) VALUES (6, 'Sarah Connor', 'sarah@regular.com', 'REGULAR');

-- ==========================================
-- 2. EVENTS (Different Prices & Demands)
-- ==========================================
-- Event 1: Standard Rock Concert (Low Demand)
INSERT IGNORE INTO events (id, base_price, event_date, is_high_demand, name)
VALUES (1, 100.00, '2026-05-20', false, 'Rock Concert');

-- Event 2: New Year Gala (High Demand - VIPs pay full price)
INSERT IGNORE INTO events (id, base_price, event_date, is_high_demand, name)
VALUES (2, 500.00, '2026-12-31', true, 'New Year Gala');

-- Event 3: Tech Conference (Low Demand)
INSERT IGNORE INTO events (id, base_price, event_date, is_high_demand, name)
VALUES (3, 50.00, '2026-08-15', false, 'Tech Innovators Summit');

-- ==========================================
-- 3. SEATS for Event 1 (Rock Concert)
-- ==========================================
-- Available Seats
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (101, NULL, NULL, 'A1', 'AVAILABLE', 0, 1);
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (102, NULL, NULL, 'A2', 'AVAILABLE', 0, 1);
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (103, NULL, NULL, 'A3', 'AVAILABLE', 0, 1);
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (104, NULL, NULL, 'B1', 'AVAILABLE', 0, 1);
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (105, NULL, NULL, 'B2', 'AVAILABLE', 0, 1);

-- Sold Seats (Already gone)
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (106, 1, NULL, 'C1', 'SOLD', 0, 1);
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (107, 5, NULL, 'C2', 'SOLD', 0, 1);

-- Held Seat (Active Hold - "Ghost" User)
-- Note: Expiry set to future (2027) so it stays locked when you test
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id)
VALUES (108, 6, '2027-01-01 12:00:00', 'D1', 'HELD', 0, 1);

-- ==========================================
-- 4. SEATS for Event 2 (New Year Gala)
-- ==========================================
-- VIP Rows
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (201, NULL, NULL, 'VIP-1', 'AVAILABLE', 0, 2);
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (202, NULL, NULL, 'VIP-2', 'AVAILABLE', 0, 2);
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (203, NULL, NULL, 'VIP-3', 'AVAILABLE', 0, 2);

-- Sold VIP Seat
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (204, 3, NULL, 'VIP-GOLD', 'SOLD', 0, 2);

-- ==========================================
-- 5. SEATS for Event 3 (Tech Conf)
-- ==========================================
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (301, NULL, NULL, 'Row1-Seat1', 'AVAILABLE', 0, 3);
INSERT IGNORE INTO seats (id, held_by_user_id, hold_expiry, seat_number, status, version, event_id) VALUES (302, NULL, NULL, 'Row1-Seat2', 'AVAILABLE', 0, 3);