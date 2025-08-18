SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE claim_docs;
TRUNCATE TABLE claim_status_history;
TRUNCATE TABLE claims;
TRUNCATE TABLE policies;
TRUNCATE TABLE user_roles;
TRUNCATE TABLE users;
TRUNCATE TABLE roles;
SET FOREIGN_KEY_CHECKS = 1;

-- Roles
INSERT INTO roles (role_id, role_name) VALUES (1, 'ROLE_CUSTOMER') ON DUPLICATE KEY UPDATE role_name=VALUES(role_name);
INSERT INTO roles (role_id, role_name) VALUES (2, 'ROLE_STAFF')    ON DUPLICATE KEY UPDATE role_name=VALUES(role_name);
INSERT INTO roles (role_id, role_name) VALUES (3, 'ROLE_ADMIN')    ON DUPLICATE KEY UPDATE role_name=VALUES(role_name);

-- Users
INSERT INTO users (user_id, username, password, email, phone, created_at, updated_at)
VALUES (1, 'aruni', 'password', 'aruni@example.com', '0712345678', NOW(), NOW())
ON DUPLICATE KEY UPDATE username=VALUES(username);

-- Join table
INSERT INTO user_roles (user_id, role_id) VALUES (1, 1)
ON DUPLICATE KEY UPDATE role_id=VALUES(role_id);

-- Policies
INSERT INTO policies (policy_id, title, description, coverage_amount, premium, duration_months, created_at)
VALUES (1, 'Silver Care', 'Basic health coverage', 500000.00, 2500.00, 12, NOW())
ON DUPLICATE KEY UPDATE title=VALUES(title);